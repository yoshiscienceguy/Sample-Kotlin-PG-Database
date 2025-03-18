package com.example.myapplicationdatabase

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object DatabaseHelper {
    private const val URL = "jdbc:postgresql://ep-long-salad-a6y03sz0-pooler.us-west-2.aws.neon.tech/neondb"
    private const val USER = "neondb_owner"
    private const val PASSWORD = "npg_JN3Da4lyimnW"
    private const val url2 = "jdbc:postgresql://ep-long-salad-a6y03sz0-pooler.us-west-2.aws.neon.tech:5432/neondb?user=neondb_owner&password=npg_JN3Da4lyimnW&sslmode=require"
    init {
        try {
            Class.forName("org.postgresql.Driver") // Load JDBC driver
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
    }

    fun getConnection(): Connection? {
        return try {

            //DriverManager.getConnection(url2);
            DriverManager.getConnection(URL, USER, PASSWORD)
        } catch (e: SQLException) {
            e.printStackTrace()
            null
        }
    }
}