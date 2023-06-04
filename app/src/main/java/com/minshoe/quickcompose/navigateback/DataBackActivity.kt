package com.minshoe.quickcompose.navigateback

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.minshoe.quickcompose.R
import com.minshoe.quickcompose.ui.theme.QuickComposeTheme

class DataBackActivity : AppCompatActivity() {
    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_data_back)

        setContent{
            QuickComposeTheme{
                val navController = rememberNavController()// navigators = )
                //Screen to desination have in app
                NavHost(navController = navController, startDestination = "screen1"){
                    composable("screen1"){entry->
                        val text = entry.savedStateHandle.get<String>("dataText")


                        Column(modifier = Modifier.fillMaxSize()) {
                            text?.let {
                                Text(text = text)
                            }

                            Button(onClick = { navController.navigate("screen2") }) {
                                Text(text = "Go to Scrren2")
                            }
                        }
                    }
                    composable("screen2"){
                        Column(modifier = Modifier.fillMaxSize()) {
                            ///import setValue, getValue
                            var text  by remember {
                                mutableStateOf("")
                            }
                            ///fix: experiment@ onCreate
                            OutlinedTextField(value = text, onValueChange ={ text = it}, modifier= Modifier.width(300.dp))
                            /**
                             * Problem: back with data
                             * How do we know to get back to previous screen
                             * and take result, sot the text we entered in textfield
                             * to previous screen so we share there
                             */
                            Button(onClick = {
                                //. pass value to savestate hanlde
                                // saveStateHandle:
                                navController.previousBackStackEntry?.savedStateHandle?.set("dataText", text)
                                // :: add entry composable
                                navController.popBackStack() }) {
                                Text(text = "Apply")
                            }
                        }
                    }
                }

            }
        }
    }
}