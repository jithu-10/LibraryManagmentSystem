

class Customer internal constructor(
    userData: UserData
)
    : User(userData)
{

    private val borrowedBooksList : MutableList<RentalBook> = mutableListOf()



    fun borrowBook(book: Book, deliveryMethod: DeliveryMethod) : RentalBook {
        val rentalBook = RentalBook(book,  deliveryMethod)
        RentedBooksDB.addRentedBooksAndRentalDetails(rentalBook, userData)
        if(deliveryMethod== DeliveryMethod.HOME_DELIVERY){
            if(userData.address==null){
                throw AddressNotFoundException("Address not found -  Set Address ")
            }
        }
        borrowedBooksList.add(rentalBook)
        return rentalBook
    }

    fun returnBook(rentalBook: RentalBook) : Double{
        val refundablePrice = rentalBook.getRefundPrice()
        RentedBooksDB.removeRentedBooksAndRentalDetails(rentalBook)
        borrowedBooksList.remove(rentalBook)
        return if(refundablePrice<0){
            0.0
        } else {
            refundablePrice
        }

    }

    fun getBorrowedBooks() : List<RentalBook>{
        return borrowedBooksList
    }



}