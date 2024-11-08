package com.example.loginhomeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.iterable.iterableapi.IterableApi
import org.json.JSONObject

class HomeActivity : AppCompatActivity() {
    private lateinit var textViewWelcome: TextView
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val username = intent.getStringExtra("username")

        textViewWelcome = findViewById(R.id.textViewWelcome)
        textViewWelcome.text = username

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)

        // Logout Button
        button1.text = "Logout"
        button1.setOnClickListener {
            val dataFields = JSONObject()
            dataFields.put("logoutTime", System.currentTimeMillis())
            IterableApi.getInstance().track("userLogout", dataFields)

            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        // Update Profile Button
        button2.text = "Update Profile"
        button2.setOnClickListener {
            updateUserProfile()
        }

        // Custom Event Button
        button3.text = "Send Custom Event"
        button3.setOnClickListener {
            sendCustomEvent()
        }
}

    private fun updateUserProfile() {
        try {
            val dataFields = JSONObject().apply {
                put("firstName", "Anish")
                put("isRegisteredUser", true)
                put("TC_User_Test_Key", "completed")
            }

            IterableApi.getInstance().updateUser(dataFields)
            Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            Toast.makeText(this, "Error updating profile", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendCustomEvent() {
        try {
            val dataFields = JSONObject().apply {
                put("platform", "Android")
                put("isTestEvent", true)
                put("url", "https://iterable.com/sa-test/Anish")
                put("secret_code_key", "Code_123")
            }

            IterableApi.getInstance().track("mobileTCTestEvent", dataFields)
            Toast.makeText(this, "Custom event sent successfully", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            Toast.makeText(this, "Error sending custom event", Toast.LENGTH_SHORT).show()
        }
    }
}