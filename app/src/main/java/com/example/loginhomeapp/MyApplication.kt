package com.example.loginhomeapp

import android.app.Application
import com.iterable.iterableapi.IterableApi
import com.iterable.iterableapi.IterableConfig

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()


        // Configure Iterable SDK
        val config = IterableConfig.Builder()
            .setAllowedProtocols(arrayOf("http", "https", "mailto"))
            .setLogLevel(android.util.Log.DEBUG)  // Enable debug logging here
            .build()

        // Initialize Iterable SDK with API key
        IterableApi.initialize(this, "15161afb186b4b56847c4970fed7e5dc", config)

        // Set the user’s email or userId (replace with actual user information)
        IterableApi.getInstance().setEmail("anishsomani@hotmail.com")

    }
}