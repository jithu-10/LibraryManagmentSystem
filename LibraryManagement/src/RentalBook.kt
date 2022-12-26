import java.util.*


data class RentalBook(
    val book : Book,
    val deliveryMethod: DeliveryMethod
){
    val issuedDate = setIssueDate()
    val expiryDate = setExpiryDate()
    val price = setPrice()
    private var refundablePrice = setRefundPrice()

    private fun setPrice() : Double{
        val bookPrice = book.price
        var actualPrice = String.format("%.2f",(70.0/100.0)*bookPrice ).toDouble()
        return if(DeliveryMethod.HOME_DELIVERY ==deliveryMethod){
            actualPrice+=30.0
            actualPrice
        }
        else{
            actualPrice
        }
    }

    private fun setIssueDate() : Date {
        val issueDate : Calendar =getCalendarWithoutTime(Date())
        return issueDate.time
    }

    private fun setExpiryDate(): Date {

        val issueDate = getCalendarWithoutTime(issuedDate)
        issueDate.add(Calendar.DATE,30)
        return issueDate.time
    }

    private fun setRefundPrice() : Double{
        return String.format("%.2f", (85.0/100.0)*price).toDouble()
    }

    fun getRefundPrice() : Double{
        val currentDate = getCalendarWithoutTime(Date())

//        val cal = Calendar.getInstance()
//        cal[2023, Calendar.MAY, 25, 0, 0] = 0
//        val returnDate = cal.time

        val fineDaysCount = calculateNoOfDaysBetweenTwoDates(expiryDate, currentDate.time)
        return if(fineDaysCount>0){
            refundablePrice-=String.format("%.2f",(fineDaysCount.toDouble()/100)*refundablePrice ).toDouble()
            refundablePrice
        } else{
            refundablePrice
        }
    }
}

