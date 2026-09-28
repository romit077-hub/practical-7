package com.example.a24012011189_mad_p7

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import org.json.JSONArray
import org.json.JSONObject

object HttpRequest {
    private const val ENDPOINT = "https://api.json-generator.com/templates/qjeKFdjkXCdK/data"
    private const val AUTH_TOKEN = "Bearer rbn0rerl1k0d3mcwgw7dva2xuwk780z1hxvyvrb1"

    private val fallbackPersons = listOf(
        Person("1", "John Doe", "john.doe@example.com", "+1-555-0100", "123 Main St, New York, NY", 40.7128, -74.0060),
        Person("2", "Jane Smith", "jane.smith@example.com", "+1-555-0200", "456 Market St, San Francisco, CA", 37.7749, -122.4194),
        Person("3", "Alice Johnson", "alice.j@example.com", "+1-555-0300", "789 Michigan Ave, Chicago, IL", 41.8781, -87.6298),
        Person("4", "Bob Brown", "bob.brown@example.com", "+1-555-0400", "321 Pine St, Seattle, WA", 47.6062, -122.3321),
        Person("5", "Charlie Davis", "charlie.d@example.com", "+1-555-0500", "654 Ocean Dr, Miami, FL", 25.7617, -80.1918)
    )

    fun fetchPersons(): List<Person> {
        try {
            val url = URL(ENDPOINT)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.setRequestProperty("Authorization", AUTH_TOKEN)
            connection.connectTimeout = 10000
            connection.readTimeout = 10000
            val responseCode = connection.responseCode
            if (responseCode != HttpURLConnection.HTTP_OK) {
                // HTTP 401 Unauthorized or other error -> use fallback data
                return fallbackPersons
            }
            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            val sb = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                sb.append(line)
            }
            reader.close()
            connection.disconnect()

            val jsonString = sb.toString().trim()
            val list = mutableListOf<Person>()

            val jsonArray = if (jsonString.startsWith("[")) {
                JSONArray(jsonString)
            } else if (jsonString.startsWith("{")) {
                val jsonObject = JSONObject(jsonString)
                var foundArray: JSONArray? = null
                val keys = jsonObject.keys()
                while (keys.hasNext()) {
                    val key = keys.next()
                    val value = jsonObject.opt(key)
                    if (value is JSONArray) {
                        foundArray = value
                        break
                    }
                }
                foundArray ?: return fallbackPersons
            } else {
                return fallbackPersons
            }

        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            val person = Person(
                id = obj.optString("id", obj.optString("_id", i.toString())),
                name = obj.optString("name", "Unknown"),
                emailId = obj.optString("emailId", obj.optString("email", "")),
                phoneNo = obj.optString("phoneNo", obj.optString("phone", "")),
                address = obj.optString("address", ""),
                latitude = obj.optDouble("latitude", 0.0),
                longitude = obj.optDouble("longitude", 0.0)
            )
            list.add(person)
        }
            return if (list.isEmpty()) fallbackPersons else list
        } catch (e: Exception) {
            e.printStackTrace()
            return fallbackPersons
        }
    }
}
