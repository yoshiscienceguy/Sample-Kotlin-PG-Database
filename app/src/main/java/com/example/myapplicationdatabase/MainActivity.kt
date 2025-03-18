package com.example.myapplicationdatabase

import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplicationdatabase.ui.theme.MyApplicationDatabaseTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        setContent {
            MyApplicationDatabaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                    fetchUsers()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationDatabaseTheme {
        Greeting("Android")
    }
}

fun fetchUsers() {
    val connection = DatabaseHelper.getConnection()
    connection?.use { conn ->
        val statement = conn.createStatement()
        println("connection successful");
        val resultSet = statement.executeQuery("SELECT * FROM \"Checkin\"")

        while (resultSet.next()) {
            val id = resultSet.getString("userid")
            val name = resultSet.getDate("log_stamp")
            println("User: ID=$id, Name=$name")
        }
    }
}