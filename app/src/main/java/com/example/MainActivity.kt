package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.MainScreen
import com.example.ui.theme.TianYanTheme
import com.example.ui.viewmodel.TianYanViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TianYanTheme {
                val viewModel: TianYanViewModel = viewModel()
                MainScreen(viewModel = viewModel)
            }
        }
    }
}
