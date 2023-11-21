package com.rpn.totel.utils

import android.content.Context
import android.content.SharedPreferences
import com.rpn.totel.utils.Utils.getRandomString

class SettingsUtility(context: Context) {

    private val sPreferences: SharedPreferences

    var uid: String
        get() = sPreferences.getString(UID,null) ?: getRandomString()
        set(value) {
            setPreference(UID, value)
        }
    var demoOtherUid: String
        get() = sPreferences.getString(OTHERS_UID,null) ?: getRandomString()
        set(value) {
            setPreference(OTHERS_UID, value)
        }

    var isFirstRun: Boolean
        get() = sPreferences.getBoolean(KEY_FIRST_RUN, true)
        set(value) {
            setPreference(KEY_FIRST_RUN, value)
        }

    var isLoggedIn: Boolean
        get() = sPreferences.getBoolean(LOGIN, true)
        set(value) {
            setPreference(LOGIN, value)
        }

    var startPageIndexSelected: Int
        get() = sPreferences.getInt(LAST_OPTION_SELECTED_KEY, 2)
        set(value) {
            setPreference(LAST_OPTION_SELECTED_KEY, value)
        }

    var intentPath: String
        get() = sPreferences.getString(INTENT_PATH_KEY, null) ?: ""
        set(value) {
            setPreference(INTENT_PATH_KEY, value)
        }


    var isGuestMode: Boolean
        get() = sPreferences.getBoolean(IS_GUEST_MODE, false)
        set(value) {
            setPreference(IS_GUEST_MODE, value)
        }


    //Setting of preferences
    private fun setPreference(key: String, value: String) {
        val editor = sPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    private fun setPreference(key: String, value: Int) {
        val editor = sPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }


    private fun setPreference(key: String, value: Long) {
        val editor = sPreferences.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    private fun setPreference(key: String, state: Boolean) {
        val editor = sPreferences.edit()
        editor.putBoolean(key, state)
        editor.apply()
    }

    companion object {
        private const val SHARED_PREFERENCES_FILE_NAME = "configs"

        private const val LAST_OPTION_SELECTED_KEY = "last_option_selected"
        private const val INTENT_PATH_KEY = "intent_path_key"


        private const val IS_GUEST_MODE = "IS_GUEST_MODE"

        private val UID = "userId"
        private val OTHERS_UID = "othersUserId"
        private val LOGIN = "login"
        private val USER = "user"
        private val PHONE_NO = "phone_no"
        private val MOBILE = "mobile"
        private val TOKEN = "token"
        private val ONLINE_USER = "online_user"
        private val ONLINE_GROUP = "online_group"
        private val LOGIN_TIME = "login_time"
        private val LAST_LOGGED_DEVICE_SAME = "last_logged_device_same"

        const val KEY_FIRST_RUN = "firstRun"
    }

    init {
        sPreferences = context.getSharedPreferences(
            SHARED_PREFERENCES_FILE_NAME,
            Context.MODE_PRIVATE
        )
    }
}