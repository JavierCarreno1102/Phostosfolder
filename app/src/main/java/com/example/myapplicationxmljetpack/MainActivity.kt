package com.example.myapplicationxmljetpack

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplicationxmljetpack.ui.theme.MyApplicationXmlJetpackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationXmlJetpackTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    Greeting("Android")
//                }
                Column() {
                    Text(text = "Hello Compose")
                    Button(onClick = { showNext() }) {
                        Text(text = "next")
                    }
                }
                
                
            }
        }
    }


    // para mostrar la siguente actividad cuando se va a crear un avion nuevo-----------------
    private fun showNext() {
        
        val homeIntent = Intent(this, MainActivity3::class.java)
        startActivity(homeIntent)
        
    }


}

