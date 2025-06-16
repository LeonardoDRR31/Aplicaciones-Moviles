package com.example.diarioelperuano.screens

import android.content.Intent
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.diarioelperuano.MainActivity
import com.example.diarioelperuano.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen() {
    val context = LocalContext.current
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }
    LaunchedEffect(key1=true) {
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(8f).getInterpolation(it)
                }
            )
        )
        delay(2000)
        val intent = Intent(
            context,
            MainActivity::class.java
        )
        context.startActivity(intent)
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.scale(scale.value)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo el banco"
        )
        Text(
            text = "Bienvenido a Radio El Peruano",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onTertiaryContainer
        )
        OutlinedButton(onClick = {
            val intent = Intent(
                context,
                MainActivity::class.java
            )
            context.startActivity(intent)
        }) {

        }
    }
}