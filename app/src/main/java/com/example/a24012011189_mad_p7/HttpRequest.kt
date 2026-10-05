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
        Person("1", "Yesenia Yang", "yesenia_yang@gnu.ac.in", "+917983385148", "16 Sackett Street, Courtland, Michigan", 42.8466, -85.2934),
        Person("2", "Sosa Shaffer", "sosa_shaffer@gnu.ac.in", "+917979501912", "99 Union Street, Nicholson, Iowa", 41.8781, -87.6298),
        Person("3", "Roberts Gill", "roberts_gill@gnu.ac.in", "+919241276734", "39 Tompkins Place, Bartonsville, Oklahoma", 36.7468, -95.9758),
        Person("4", "Penelope Cooper", "penelope_cooper@gnu.ac.in", "+916836390000", "35 Aitken Place, Orovada, Maryland", 39.0458, -76.6413),
        Person("5", "Park George", "park_george@gnu.ac.in", "+918895814051", "95 Woods Place, Ahwahnee, Nebraska", 41.4925, -99.9018),
        Person("6", "Deloris Lawson", "deloris_lawson@gnu.ac.in", "+918379880401", "72 Troutman Street, Mooresburg, South Dakota", 44.3683, -100.3510),
        Person("7", "Conley Hickman", "conley_hickman@gnu.ac.in", "+918258363534", "38 Bath Avenue, Coalmont, Florida", 28.3781, -81.5700),
        Person("8", "Horne Koch", "horne_koch@gnu.ac.in", "+918797996598", "95 Woods Place, Ahwahnee, Nebraska", 37.3382, -121.8863)
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
