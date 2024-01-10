package com.example.classapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.classapplication.common.DemandApp
import com.example.classapplication.presentation.ui.theme.ClassApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ClassApplicationTheme {
                DemandApp()
            }
        }
    }
}