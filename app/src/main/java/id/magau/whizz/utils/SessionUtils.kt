package id.magau.whizz.utils

import android.content.Context
import id.magau.whizz.BuildConfig

/**
 * Created by Andi Tenroaji Ahmad on 12/7/2019.
 */

class SessionUtils(context: Context) {
    companion object {
        const val PREF_NAME = BuildConfig.APPLICATION_ID + "_setting"
        const val PREF_KEY_REMEMBER = "PREMIUM"
        const val PREF_KEY_EMAIL = "EMAIL"
        const val PREF_KEY_NAME = "NAME"
        const val PREF_KEY_PASSWORD = "PASSWORD"
        const val PREF_KEY_TOKEN = "TOKEN"
        const val PREF_KEY_LOGIN = "LOGIN"

        @Volatile
        private var INSTANCE: SessionUtils? = null

        @JvmStatic
        fun getInstance(context: Context): SessionUtils {
            if (INSTANCE == null) {
                synchronized(SessionUtils::class) {
                    if (INSTANCE == null) {
                        INSTANCE =
                            SessionUtils(
                                context
                            )
                    }
                }
            }
            return INSTANCE!!
        }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }

    private val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    fun <T> editData(key : String, data : T) {
       val editor = sharedPreferences.edit()
        when (data) {
            is Boolean -> {
                editor.putBoolean(key,data)
            }
            is Int -> {
                editor.putInt(key,data)
            }
            is String -> {
                editor.putString(key,data)
            }
            else -> {
                throw RuntimeException("$data class or type are not supported")
            }
        }
        editor.apply()
    }

    fun <T> getData(key: String, default : T): T {
        return when (default) {
            is Boolean -> {
                sharedPreferences.getBoolean(key, default) as T
            }
            is Int -> {
                sharedPreferences.getInt(key, default) as T
            }
            is String -> {
                sharedPreferences.getString(key, default) as T
            }
            else -> {
                throw RuntimeException("$default class or type are not supported")
            }
        }
    }

    fun clearData(){
        sharedPreferences.edit().clear().apply()
    }



}