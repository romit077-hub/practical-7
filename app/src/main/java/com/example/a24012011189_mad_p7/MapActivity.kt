package com.example.a24012011189_mad_p7

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val person = intent.getSerializableExtra("person") as? Person
        val tvDetails = findViewById<TextView>(R.id.tvPersonDetails)
        val btnOpenMap = findViewById<Button>(R.id.btnOpenMap)

        if (person != null) {
            tvDetails.text = "Name: ${person.name}\nEmail: ${person.emailId}\nPhone: ${person.phoneNo}\nAddress: ${person.address}\nLatitude: ${person.latitude}\nLongitude: ${person.longitude}"
            btnOpenMap.setOnClickListener {
                val gmmIntentUri = Uri.parse("geo:${person.latitude},${person.longitude}?q=${person.latitude},${person.longitude}(${person.name})")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            }
        }
    }
}
