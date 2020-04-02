package id.magau.whizz.utils

import android.content.Context
import java.text.DateFormatSymbols
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object TimeHandler {
    fun formatDate(date: String, formatBefore: String, formatAfter: String): String {
//        val YMDSformat = "yyyy-MM-dd HH:mm:ss"
//        val YMDformat = "dd MMM yyyy"

        val simpleDateFormatBefore = SimpleDateFormat(formatBefore, Locale.getDefault())
        val dateFormatAfter = SimpleDateFormat(formatAfter, Locale.getDefault())
        var dateChange: Date?
        try {
            dateChange = simpleDateFormatBefore.parse(date)
        } catch (e: ParseException) {
            dateChange = null
        }


        return dateFormatAfter.format(dateChange)
    }


    fun timesUploadHandler(dateTimes: String, dateNow: String, context: Context): Long {
//        if (TextUtils.isEmpty(dateTimes) || TextUtils.equals(dateTimes, "")) {
//            return dateTimes
//        }

//        val YMDSZformat = "yyyy-MM-dd'T'HH:mm:ssZ"
        val YMDSformat = "yyyy-MM-dd HH:mm:ss"
        val timeZoneDevice = TimeZone.getDefault()

        val today = Calendar.getInstance(timeZoneDevice, Locale.getDefault()).time

        val simpleDateFormat = SimpleDateFormat(YMDSformat, Locale.getDefault())
        simpleDateFormat.timeZone = timeZoneDevice

        var dateUploaded: Date?
        var dateStart : Date?
        try {
            dateUploaded = simpleDateFormat.parse(dateTimes)
            dateStart = simpleDateFormat.parse(dateNow)
        } catch (e: ParseException) {
            dateUploaded = null
            dateStart = null
        }

//        if (dateUploaded == null) {
//            return dateTimes
//        }

//        val dateUploadedString = simpleDateFormat.format(dateUploaded)
//        val MONTHS = arrayOf(
//            "Januari",
//            "Februari",
//            "Maret",
//            "April",
//            "Mei",
//            "Juni",
//            "Juli",
//            "Agustus",
//            "September",
//            "Oktober",
//            "November",
//            "Desember"
//        )
//        val datesTimesArray =
//            dateUploadedString.split("T".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
//        val datesArray =
//            datesTimesArray[0].split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
//        val timeUploaded = datesTimesArray[1].substring(0, 5)
//        var dislpayedUpload =
//            datesArray[2] + " " + MONTHS[Integer.valueOf(datesArray[1]) - 1] + " " + datesArray[0] + ", pukul " + timeUploaded

        val selisihMiliSecond = dateUploaded!!.time - dateStart!!.time
//        val selisihMiliSecond = abs(dateUploaded!!.time - today.time)
//        val selisihDetik = (selisihMiliSecond / 1000 % 60).toInt()
//        val selisihMenit = (selisihMiliSecond / (60 * 1000) % 60).toInt()
//        val selisihJam = (selisihMiliSecond / (60 * 60 * 1000) % 24).toInt()
//        val selisihHari = (selisihMiliSecond / (24 * 60 * 60 * 1000)).toInt()

//        when (selisihHari) {
//            0 -> {
//                if (selisihMenit < 1 && selisihJam == 0 && selisihDetik >= 0) {
//                    dislpayedUpload = context.resources.getString(R.string.aaa_time_seconds, selisihDetik)
//                } else if (selisihMenit >= 1 && selisihJam < 1) {
//                    dislpayedUpload = context.resources.getString(R.string.aaa_time_minutes, selisihMenit)
//                } else {
//                    dislpayedUpload = context.resources.getString(R.string.aaa_time_hours, selisihJam)
//                }
//            }
//            1 -> {
//                dislpayedUpload = context.resources.getString(R.string.aaa_time_yesterday, timeUploaded)
//            }
//            in 2..5 -> {
//                dislpayedUpload = context.resources.getString(R.string.aaa_time_days, selisihHari)
//            }
//        }

        return selisihMiliSecond
    }

    fun getDisplayYMD(ymdDatex: String, locale: Locale): String? {
        var dateFormatSymbols = DateFormatSymbols(locale)
        var ymdDate = ymdDatex.split("-")
        return ymdDate[2] + " " + dateFormatSymbols.months[Integer.valueOf(ymdDate[1]) - 1] + " " + ymdDate[0]
    }
}