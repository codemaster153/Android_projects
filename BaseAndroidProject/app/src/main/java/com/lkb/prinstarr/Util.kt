package com.lkb.prinstarr

import android.content.SharedPreferences
import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.math.abs

class Util {
    companion object {
        private const val DAY_IN_MILLI = 24*60*60*1000
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

        fun convertStrToEpoch(timeStr: String): Long {
            var time: Long = 0
            time = if (timeStr.isNotEmpty()) {
                try {
                    val sdf = SimpleDateFormat("dd-mm-yyyy")
                    val dt: Date = sdf.parse(timeStr)
                    val epoch: Long = dt.time
                    (epoch)
                } catch (e: ParseException) {
                    System.currentTimeMillis()
                }
            } else {
                System.currentTimeMillis()
            }
            return time
        }

        fun updatePref(pref: SharedPreferences, key: String, value: String) {
            pref.edit().putString(key, value).apply()
        }

        fun getDBPath(pref: SharedPreferences): String? {
            return pref.getString("dbPath", "prinstar_fallback")
        }

        fun fromEpochToDate(epoch: Long): String {
            val date = Date(epoch)
            val sdf = SimpleDateFormat("dd-MM-yyyy")
            sdf.timeZone = TimeZone.getTimeZone("GMT+05:30")
            return sdf.format(date)
        }

        fun absAmount(amount: Double): String {
            return abs(amount).toString()
        }

        fun isValidDate(date: String): Boolean {
            val regex = "^(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])-[0-9]{4}$"
            val pattern: Pattern = Pattern.compile(regex)
            val matcher: Matcher = pattern.matcher(date)
            return matcher.matches()
        }

        fun countDaysLeft(creditDays:Int,startTime:Long,currentTime:Long): Int {
            val difference = currentTime-startTime
            val daysLeft = (creditDays -(difference/ DAY_IN_MILLI))
            return daysLeft.toInt()
        }

    }
}