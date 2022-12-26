

data class BookInventory internal constructor(
    val book : Book,
    private var availableBooks : Int

    ) {


    private var rentedBooks : Int = 0
    init{
        validateAvailableBooksQuantity(availableBooks)
    }


    fun getTotalQuantity() : Int{
        return availableBooks+rentedBooks

    }

    fun getAvailableQuantity() : Int{
        return availableBooks
    }

    fun getRentedQuantity() : Int{
        return rentedBooks
    }


    internal fun updateTotalQuantity(quantity : Int){
        validateAvailableBooksQuantity(availableBooks+quantity)
        availableBooks+=quantity
    }

    internal fun addRentedNumberOfBooks(){
        rentedBooks++
        availableBooks--
    }

    internal  fun removeRentedNumberOfBooks(){
        if(rentedBooks!=0){
            rentedBooks--
        }
        availableBooks++
    }




    private fun validateAvailableBooksQuantity(totalQuantity: Int){
        if(totalQuantity<0){
            throw ValidationException("Invalid Quantity")
        }
    }

}