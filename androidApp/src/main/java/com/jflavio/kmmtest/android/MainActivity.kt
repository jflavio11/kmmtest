package com.jflavio.kmmtest.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jflavio.kmmtest.Greeting

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivity()
        }
    }

    @Composable
    fun MainActivity() {
        Text(text = "Hello world!")
    }

    @Composable
    @Preview
    fun MainPreview() {
        MainActivity()
    }
}
