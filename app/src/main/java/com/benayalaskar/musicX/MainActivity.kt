package com.benayalaskar.musicX

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.benayalaskar.musicX.ui.screen.AboutScreen
import com.benayalaskar.musicX.ui.screen.splash.SplashScreen
import com.benayalaskar.musicX.ui.theme.ListMusikTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListMusikTheme {
                val showSplash = remember { mutableStateOf(true) }

                if (showSplash.value) {
                    SplashScreen {
                        showSplash.value = false
                    }
                } else {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        MusikApp()
                    }
                }
            }
        }
    }
}