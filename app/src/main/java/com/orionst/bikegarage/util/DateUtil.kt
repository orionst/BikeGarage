package com.orionst.bikegarage.util

import android.content.res.Resources
import com.orionst.bikegarage.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    private const val UTC = "UTC"

    fun getTextInputFormat(): SimpleDateFormat {
        val pattern =
            (DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault()) as SimpleDateFormat)
                .toPattern()
                .replace("\\s+".toRegex(), "")
        val format = SimpleDateFormat(pattern, Locale.getDefault())
        format.timeZone = getTimeZone()
        format.isLenient = false
        return format
    }

    fun getTextInputHint(res: Resources, format: SimpleDateFormat): String {
        var formatHint = format.toPattern()
        val yearChar = res.getString(R.string.picker_text_input_year_abbr)
        val monthChar = res.getString(R.string.picker_text_input_month_abbr)
        val dayChar = res.getString(R.string.picker_text_input_day_abbr)

        // Format year to always be displayed as 4 chars when only 1 char is used in localized pattern.
        // Example: (fr-FR) dd/MM/y -> dd/MM/yyyy
        if (formatHint.replace("[^y]".toRegex(), "").length == 1) {
            formatHint = formatHint.replace("y", "yyyy")
        }
        return formatHint
            .replace("d", dayChar)
            .replace("M", monthChar)
            .replace("y", yearChar)
    }

    private fun getTimeZone(): TimeZone {
        return TimeZone.getTimeZone(UTC)
    }
}
