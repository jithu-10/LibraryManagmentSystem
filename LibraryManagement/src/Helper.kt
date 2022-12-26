import java.util.*
import java.util.concurrent.TimeUnit


internal fun calculateNoOfDaysBetweenTwoDates(startDate : Date , endDate : Date) : Int{

    val timeDiff: Long = endDate.time - startDate.time
    return TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS).toInt()
}

internal fun getCalendarWithoutTime(date: Date): Calendar {
    val calendar: Calendar = Calendar.getInstance()
    calendar.time = date
    calendar[Calendar.HOUR] = 0
    calendar[Calendar.HOUR_OF_DAY] = 0
    calendar[Calendar.MINUTE] = 0
    calendar[Calendar.SECOND] = 0
    calendar[Calendar.MILLISECOND] = 0
    return calendar
}

internal fun phoneNumberValidator(phoneNumber: String): Boolean {
    return regexValidator("([1-9]\\d{9})", phoneNumber)
}

internal fun regexValidator(regex : String, value : String): Boolean{
    val pattern = Regex(regex)
    return pattern.matches(value)
}

internal fun modifyString(str: String): String {
    var newStr = StringBuilder()
    for (i in str.indices) {
        if (str[i] == ' ') {
            continue
        }
        newStr.append(str[i])
    }
    newStr = StringBuilder(newStr.toString().uppercase())
    return newStr.toString()
}
