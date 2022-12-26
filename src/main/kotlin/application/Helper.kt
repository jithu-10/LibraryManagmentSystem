package application

import java.text.SimpleDateFormat
import java.util.*

object Helper {

    private val input : Scanner = Scanner(System.`in`)
    private var lastNumberValue = false

    private fun getIntegerInput(): Int {
        lastNumberValue = true
        val value: Int = try {
            input.nextInt()
        } catch (e: InputMismatchException) {
            input.nextLine()
            println("Enter Value in Integer Format")
            getIntegerInput()
        }
        return value
    }

    internal fun getInputWithinRange(start: Int, end: Int, str: String?): Int {
        var str = str
        if (str == null) {
            str ="Enter Input From Given Option"
        }
        val value: Int = getIntegerInput()
        if (value < start || value > end) {
            println(str)
            return getInputWithinRange(start, end, str)
        }
        return value
    }

    internal fun getPhoneNumber(): Long {
        lastNumberValue = true
        var phoneNumber: Long
        try {
            phoneNumber = input.nextLong()
            if (phoneNumber.toString().length != 10) {
                throw Exception("Enter Valid Phone Number ")
            }
        } catch (e: InputMismatchException) {
            input.nextLine()
            println("Enter Valid Phone Number ")
            phoneNumber = getPhoneNumber()
        } catch (e: Exception) {
            println("Enter Valid Phone Number ")
            phoneNumber = getPhoneNumber()
        }
        return phoneNumber
    }

    internal fun getStringInput(): String {
        var value: String
        if (lastNumberValue) {
            input.nextLine()
        }
        lastNumberValue = false
        try {
            value = input.nextLine().trim { it <= ' ' }
            if (value.isEmpty()) {
                throw Exception("String Can't be empty")
            }
        } catch (e: InputMismatchException) {
            println("Enter value in String format")
            value = getStringInput()
        } catch (e: Exception) {
            value = getStringInput()
        }
        return value
    }

    internal fun getPostalCode(): Int {
        lastNumberValue = true
        var postalCode: Int
        try {
            postalCode = input.nextInt()
            if (postalCode.toString().length != 6) {
                throw Exception("Enter Valid Postal Code")
            }
        } catch (e: InputMismatchException) {
            input.nextLine()
            println("Enter Valid Postal Code")
            postalCode = getPostalCode()
        } catch (e: Exception) {
            println("Enter Valid Postal Code")
            postalCode = getPostalCode()
            println(e.message)
        }
        return postalCode
    }

    internal fun formatDate(date: Date): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        return dateFormat.format(date)
    }
}