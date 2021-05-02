package com.lkb.prinstarr

import android.content.SharedPreferences
import android.util.Log
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*
import kotlin.math.abs

class Util {
    companion object {
        /**
         * phone number and first name and last name are used to create unique id
         * user id.
         */
        fun generateUserId(phoneNumber: Long, name: String): String {
            val preText = name.trim().split(" ")
            return if (preText.size < 2 && preText[0].length > 2)
                "${preText[0].toCharArray()[0]}${preText[0].toCharArray()[1]}$phoneNumber".toLowerCase()
            else if (preText.size == 2 && preText[0].length > 2 && preText[1].length > 2)
                "${preText[0].toCharArray()[0]}${preText[1].toCharArray()[0]}$phoneNumber".toLowerCase()
            else
                "xx$phoneNumber"
        }

        fun dLog(msg: String) {
            if (BuildConfig.DEBUG)
                Log.d("Prinstar", msg)
        }

        fun getCurrentTimeEpoch(): Long {
            return Instant.now().epochSecond
        }

        fun updatePref(pref: SharedPreferences, key: String, value: String) {
            pref.edit().putString(key, value).apply()
        }
        fun getDBPath(pref: SharedPreferences): String {
            return pref.getString("dbPath", "prinstar_fallback")
        }
        fun fromEpochToDate(epoch: Long): String {
            val date = Date(epoch * 1000L)
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            sdf.timeZone = TimeZone.getTimeZone("GMT+05:30")
            return sdf.format(date)
        }
        fun absAmount(amount:Double): String {
            return abs(amount).toString()
        }
    }
}