package com.example.diarioelperuano

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.diarioelperuano.ui.theme.DiarioElPeruanoTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            DiarioElPeruanoTheme {
                // 📌 Menú principal con submenús
                val expandedItems = remember { mutableStateMapOf<String, Boolean>() }

                val menuItems = MenuData.items

                var selectedItem by remember { mutableStateOf<MenuItem.Simple?>(null) }
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                val scrollState = rememberScrollState()
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet(
                            modifier = Modifier.verticalScroll(scrollState)
                        ) {
                            Spacer(Modifier.height(12.dp))
                            menuItems.forEach { item ->
                                when (item) {
                                    is MenuItem.Simple -> {
                                        NavigationDrawerItem(
                                            label = { Text(item.title,
                                                style = TextStyle(
                                                    fontSize = 16.sp
                                                )) },
                                            selected = selectedItem == item,
                                            onClick = {
                                                selectedItem = item
                                                scope.launch { drawerState.close() }
                                            },
                                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                                        )
                                    }

                                    is MenuItem.WithSubItems -> {
                                        val isExpanded = expandedItems[item.title] ?: false

                                        NavigationDrawerItem(
                                            label = {
                                                Row(verticalAlignment = Alignment.CenterVertically) {
                                                    Text(item.title)
                                                    Spacer(modifier = Modifier.weight(1f))
                                                    Icon(
                                                        imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                                        contentDescription = if (isExpanded) "Colapsar" else "Expandir"
                                                    )
                                                }
                                            },
                                            selected = false,
                                            onClick = {
                                                expandedItems[item.title] = !isExpanded
                                            },
                                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                                        )

                                        AnimatedVisibility(
                                            visible = isExpanded,
                                            enter = expandVertically(),
                                            exit = shrinkVertically()
                                        ) {
                                            Column {
                                                item.subItems.forEach { subItem ->
                                                    NavigationDrawerItem(
                                                        label = {
                                                            Text(
                                                                text = "• ${subItem.title}",
                                                                color = Color.Gray,
                                                                maxLines = 1,
                                                                overflow = TextOverflow.Ellipsis
                                                            )
                                                        },
                                                        selected = selectedItem == subItem,
                                                        onClick = {
                                                            selectedItem = subItem
                                                            scope.launch { drawerState.close() }
                                                        },
                                                        modifier = Modifier.padding(start = 32.dp)
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text(
                                    text = "Diario El Peruano",
                                    style = MaterialTheme.typography.labelLarge
                                ) },
                                navigationIcon = {
                                    IconButton(onClick = {
                                        scope.launch { drawerState.open() }
                                    }) {
                                        Icon(Icons.Filled.Menu, contentDescription = "Menú")
                                    }
                                }
                            )
                        },
                        content = { innerPadding ->
                            Column(
                                modifier = Modifier
                                    .padding(innerPadding)
                                    .fillMaxSize()
                            ) {
                                AndroidView(
                                    factory = { context ->
                                        WebView(context).apply {
                                            webViewClient = WebViewClient()
                                            settings.javaScriptEnabled = true
                                            if (selectedItem != null) {
                                                loadUrl(selectedItem!!.url)
                                            } else {
                                                loadData(
                                                    "<html><body style='text-align:center; font-size:20px; padding-top:50px;'>Elige una categoría para empezar</body></html>",
                                                    "text/html",
                                                    "UTF-8"
                                                )
                                            }
                                        }
                                    },
                                    update = { webView ->
                                        if (selectedItem != null && webView.url != selectedItem!!.url) {
                                            webView.loadUrl(selectedItem!!.url)
                                        } else if (selectedItem == null) {
                                            webView.loadData(
                                                "<html><body style='text-align:center; font-size:20px; padding-top:50px;'>Elige una categoría para empezar</body></html>",
                                                "text/html",
                                                "UTF-8"
                                            )
                                        }
                                    },
                                    modifier = Modifier.fillMaxSize()
                                )

                            }
                        }
                    )
                }
            }
        }
    }
}
