package com.example.celciustofrhn

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ViewModelForTemperature : ViewModel() {
    var isFahrenheit by mutableStateOf(true)
    var convertedTemperature by mutableStateOf("")

   fun doSwitchToggle() {
       this.isFahrenheit = !isFahrenheit
   }

    fun changeTemperature(inputValue : String) {
        try {
         if (inputValue.isNotEmpty()) {
             convertedTemperature = "Input is necessary"
         }
            val inputDouble = inputValue.toDouble()
            if (isFahrenheit) {
                val temperature = "%.2f".format((inputDouble - 32) * 5/9)
                convertedTemperature = temperature.toString()
                convertedTemperature += "\u2103"
            }
            else {
                val temperature = "%.2f".format(( inputDouble * 9/5) + 32)
                convertedTemperature = temperature.toString()
                convertedTemperature += "\u2109"
            }
        }
        catch (e : Exception) {
           convertedTemperature = "Invalid input"
        }
    }

}
