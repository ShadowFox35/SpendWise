package com.example.spendwise.navigation

import android.net.Uri
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

class CustomNavType<T : Any>(
    private val serializer: KSerializer<T>,
    isNullableAllowed: Boolean = false
) : NavType<T>(isNullableAllowed) {
    override fun get(bundle: Bundle, key: String): T? {
        return bundle.getString(key)?.let { Json.decodeFromString(serializer, it) }
    }

    override fun parseValue(value: String): T {
        return Json.decodeFromString(serializer, Uri.decode(value))
    }

    override fun serializeAsValue(value: T): String {
        return Uri.encode(Json.encodeToString(serializer, value))
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putString(key, Json.encodeToString(serializer, value))
    }
}

val NavController.currentRoute: String
    @Composable
    get() = currentBackStackEntryAsState().value?.destination?.route ?: "Empty route"