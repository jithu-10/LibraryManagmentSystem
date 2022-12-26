

class Admin internal constructor(
    userData: UserData
)
    : User(userData)
{

    fun addBooks(book : Book, quantity : Int){
        val bookInventory = BookInventory(book, quantity)
        LibraryDB.addBookInventory(bookInventory)
    }

    fun removeBooks(book: Book){
        LibraryDB.removeBookInventory(book)
    }

    fun increaseBookQuantity(book : Book, quantity: Int){
        LibraryDB.getBookInventories().single {
            it.book == book
        }.apply {
            updateTotalQuantity(+quantity)
        }

    }

    fun decreaseBookQuantity(book : Book, quantity: Int){

        val bookInventory = LibraryDB.getBookInventories().single {
            it.book == book
        }

        val quantityDiff = bookInventory.getAvailableQuantity() - quantity
        if(quantityDiff==0){
            LibraryDB.removeBookInventory(bookInventory)
        }
        else if(quantityDiff<0){
            throw LimitExceededException("Only ${bookInventory.getAvailableQuantity()} books available")
        }
        else{
            bookInventory.updateTotalQuantity(-quantity)
        }

    }

    fun getRentedBooks() : List<Book>{
        val books = mutableListOf<Book>()
        RentedBooksDB.getRentedBooksAndRentalDetails().keys.forEach {
            books.add(it.book)
        }
        return books
    }

    fun getRentalHistory() : List<RentalBook>{
        return RentedBooksDB.getRentedBooksAndRentalDetails().keys.toList()
    }

    fun getBookInventories() : List<BookInventory>{
        return LibraryDB.getBookInventories()
    }


}