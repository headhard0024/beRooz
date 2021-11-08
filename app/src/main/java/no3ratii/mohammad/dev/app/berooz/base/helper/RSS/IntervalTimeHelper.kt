package no3ratii.mohammad.dev.app.berooz.base.helper.RSS

import saman.zamani.persiandate.PersianDate
import saman.zamani.persiandate.PersianDateFormat
import java.util.*

/**
 *  use PersianDate for convert miladi date to shamsy
 *  use this dip
 *  implementation 'com.github.samanzamani.persiandate:PersianDate:0.8'
 *
 *  ------------------------------------------- need to use better and clin code <-
 */
class IntervalTimeHelper {

    companion object {
        fun nowCurrentTime(): PersianDate {
            val resultdate = Date(System.currentTimeMillis())
            val myDate = Date(resultdate.toString())
            return PersianDate(myDate)
        }
    }

    /**
     *  java
     *  private PersianDate nowCurrentTime() {
        Date resultdate = new Date(System.currentTimeMillis());
        Date myDate = new Date(String.valueOf(resultdate));
        return new PersianDate(myDate);
    }
     */

    fun start(date: String): PersianDate {
        val myDate = Date(date);
        return PersianDate(myDate)
    }

    private fun new(): PersianDate {
        val yourmilliseconds = System.currentTimeMillis()
        val resultdate = Date(yourmilliseconds)
        val myDate = Date(resultdate.toString());
        return PersianDate(myDate)
    }

    fun currentTime(
        start: PersianDate
    ): String {
        val diff: Long = start.time - new().time
        val diffSeconds = diff / 1000 % 60
        val diffMinutes = diff / (60 * 1000) % 60
        val diffHours = diff / (60 * 60 * 1000) % 24
        val diffDays = diff / (24 * 60 * 60 * 1000)
        val diffWeek = diffDays / 7
        val diffMonth = diffDays / 30
        val diffYear = diffDays / 365

        val m = Math.abs(diffMinutes)
        val mo = Math.abs(diffMonth)
        val w = Math.abs(diffWeek)
        val d = Math.abs(diffDays)
        val h = Math.abs(diffHours)
        val y = Math.abs(diffYear)

        var diffTime = ""
        if (diffYear != 0L && diffMonth >= 12) {
            diffTime = "سال پیش" + y
        } else if (diffMonth != 0L && diffWeek >= 4) {
            diffTime = "ماه پیش" + mo
        } else if (diffWeek != 0L && diffDays >= 6) {
            diffTime = "هفته پیش" + w
        } else if (diffDays != 0L) {
            if (d >= 10) {
                diffTime = "چند روز پیش"
            } else {
                diffTime = "" + d + " روز پیش"
            }
        } else if (diffHours != 0L) {
            if (h >= 5) {
                diffTime = "چند ساعت پیش"
            } else {
                diffTime = "" + h + " ساعت پیش"
            }
        } else if (diffMinutes != 0L) {
            diffTime = when {
                m <= 5 -> {
                    return "داغ داغ"
                }
                m <= 15 -> {
                    return "چند لحظه پیش"
                }
                m <= 30 -> {
                    return "نیم ساعت پیش"
                }
                m > 30 -> {
                    return "" + m + " دقیقه پیش"
                }
                else -> "" + m + " دقیقه پیش"
            }
        } else if (diffSeconds != 0L) {
            diffTime = "داغ داغ"
        }
        return diffTime
    }

    private fun setPershanDate(date: String): String {
        val myDate = Date(date);
        val pdate = PersianDate(myDate)
        val pdformater2 = PersianDateFormat("y F j")
        pdformater2.format(pdate)
        return "" + pdate.shYear + "/" + pdate.shMonth + "/" + pdate.shDay
    }

    private fun setNewsTime(date: String?): String {
        val myDate = Date(date);
        val pdate = PersianDate(myDate)
        val pdformater2 = PersianDateFormat("y F j")
        pdformater2.format(pdate)
        return "" + pdate.hour + ":" + pdate.minute + ":" + pdate.second
    }
}