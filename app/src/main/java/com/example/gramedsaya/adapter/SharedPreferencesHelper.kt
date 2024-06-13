package com.example.gramedsaya.adapter

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesHelper {

    private const val PREFS_NAME = "BookPreferences"
    private const val ADMIN_KEY = "AdminKey"
    private const val STATUS_KEY_PREFIX = "Status_"

    fun setStatus(context: Context, bookName: String, status: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(STATUS_KEY_PREFIX + bookName, status).apply()
    }

    fun getStatus(context: Context, bookName: String): String {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(STATUS_KEY_PREFIX + bookName, "Tersedia") ?: "Tersedia"
    }

    fun setAdmin(context: Context, isAdmin: Boolean) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(ADMIN_KEY, isAdmin).apply()
    }

    fun isAdmin(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(ADMIN_KEY, false)
    }

    fun logoutAdmin(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(ADMIN_KEY, false).apply()
    }

    fun clearAdmin(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().remove(ADMIN_KEY).apply()
    }
}
