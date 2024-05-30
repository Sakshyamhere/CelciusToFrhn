package com.example.celciustofrhn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.celciustofrhn.ui.theme.CelciusToFrhnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CelciusToFrhnTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp(
                        modifier = Modifier
                            .padding(innerPadding)

                    )
                }
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var inputText by remember { mutableStateOf("") }
    val viewModelForTemperature : ViewModelForTemperature = viewModel()
    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .padding(30.dp, 50.dp, 30.dp, 30.dp)
                .shadow(5.dp),
        ) {
            Column {
                Row {
                    Switch(
                        checked = viewModelForTemperature.isFahrenheit,
                        onCheckedChange = {viewModelForTemperature.doSwitchToggle()},
                        modifier = Modifier
                            .padding(20.dp, 30.dp, 2.dp, 30.dp)
                    )
                    Column {
                        TextField(
                            value = inputText,
                            placeholder = { Text(text = "Enter temperature....") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(30.dp, 30.dp, 30.dp, 10.dp)
                                .clip(RoundedCornerShape(10.dp)),
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            onValueChange = { inputText = it }
                        )
                        Button(
                            onClick = {viewModelForTemperature.changeTemperature(inputText)},
                            shape = RoundedCornerShape(25),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.White,
                                containerColor = Color.Blue
                            ),
                            modifier = Modifier
                                .padding(30.dp, 0.dp, 30.dp, 30.dp)
                                .fillMaxWidth()
                        ) {
                            Text(text = "Convert")
                        }
                    }
                }
            }
        }
            
        Text(
            text = viewModelForTemperature.convertedTemperature,
            fontSize = 50.sp,
            modifier = Modifier
                .padding(0.dp, 400.dp, 0.dp, 0.dp)
                .align(Alignment.Center),
        )
    }


}
