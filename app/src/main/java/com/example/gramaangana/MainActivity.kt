package com.example.gramaangana

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.gramaangana.navigation.NavGraph
import com.example.gramaangana.ui.theme.GramaAnganaTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            GramaAnganaTheme {

                NavGraph()
            }
        }
    }
}