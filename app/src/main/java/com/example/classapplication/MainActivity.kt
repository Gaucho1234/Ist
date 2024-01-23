package com.example.classapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.classapplication.common.DemandApp
import com.example.classapplication.common.Routes
import com.example.classapplication.presentation.screens.main.SplashScreen
import com.example.classapplication.presentation.ui.theme.ClassApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.Timer
import java.util.TimerTask
import kotlin.concurrent.schedule

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            DemandApp()
        }
    }
}

