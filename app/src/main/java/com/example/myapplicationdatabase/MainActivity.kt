package com.example.myapplicationdatabase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplicationdatabase.ui.theme.MyApplicationDatabaseTheme
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationDatabaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )
                {
                    Greeting("Android")

                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    //var input by remember { mutableStateOf("") }
    var input by remember { mutableStateOf("")};
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
    Column( modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    )
    {
        Text(text = input, fontSize = 32.sp,
            modifier = Modifier.fillMaxWidth().padding(8.dp))

        val buttons = listOf(listOf("7","8","9","/"),
                            listOf("4","5","6","*"),
                            listOf("1","2","3","-"),
                            listOf("0",".","=","+")
            )
        buttons.forEach{ row ->
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                row.forEach { label ->
                    Button(
                        onClick = {
                            when (label){
                                "=" -> input = evaluateExpression(input)
                                else->input += label

                                }
                            },
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 4.dp)
                    )
                    {
                        Text(label, fontSize = 24.sp)
                    }
                }
            }
        }

    }
}

fun evaluateExpression(expression:String):String{
        return eval(expression).toString();

}

fun eval(expr: String): Double {
    val expression = ExpressionBuilder(expr).build()
    return expression.evaluate()
}

@Preview
@Composable
fun GreetingPreview() {
    MyApplicationDatabaseTheme {
        Greeting("Android")
    }
}

