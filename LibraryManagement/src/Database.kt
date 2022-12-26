


object AccountDB{
    private val accountAndPassword = mutableMapOf<UserData,String>()

    private val users : MutableList<User> = mutableListOf<User>().apply {
        val userData = UserData("admin", 1234567890)
        add(Admin(userData))
        add(Customer(userData))
        addAccountAndPassword(userData,"pass")
    }




    internal fun addUser(user : User, password: String){
        addAccountAndPassword(user.userData,password)
        users.add(user)
    }

    internal fun isPhoneNumberAlreadyExist(phoneNumber : Long) : Boolean{
        for(user in users){
            if(user.userData.phoneNumber==phoneNumber){
                return true
            }
        }
        return false
    }


    internal fun isAccountExist(phoneNumber: Long, password : String) : UserData?{
        for(account in accountAndPassword){
            if(account.key.phoneNumber==phoneNumber){
                if(account.value==password){
                    return account.key
                }
            }
        }
        return null
    }

    private fun addAccountAndPassword(userData : UserData, password: String){
        accountAndPassword[userData] = password
    }

    internal fun getUser(userData: UserData, userType : String?) : User?{
        for(user in users){
            if(user.userData==userData){
                if(userType == user::class.simpleName){
                    return user;
                }
            }
        }
        return null;
    }
}

object LibraryDB{

    private val bookInventories : MutableList<BookInventory> = mutableListOf()

    internal fun getBooks(): List<Book> {
        val books = mutableListOf<Book>()
        bookInventories.forEach{
            if(it.getAvailableQuantity()!=0){
                books.add(it.book)
            }
        }
        return books;
    }

    internal fun addBookInventory(bookInventory: BookInventory){
        bookInventories.add(bookInventory)
    }

    internal fun getBookInventories() : List<BookInventory>{
        return bookInventories
    }

    internal fun removeBookInventory(book : Book){
        for (bookInventory in bookInventories){
            if(bookInventory.book==book){
                bookInventories.remove(bookInventory)
            }
        }
    }

    internal fun removeBookInventory(bookInventory : BookInventory){
        bookInventories.remove(bookInventory)
    }



}

object RentedBooksDB{

    private val rentedBooksAndRentalDetails : MutableMap<RentalBook, UserData> = mutableMapOf()

    internal fun getRentedBooksAndRentalDetails() : Map<RentalBook, UserData>{
        return rentedBooksAndRentalDetails
    }

    internal fun addRentedBooksAndRentalDetails (rentalBook : RentalBook, userData: UserData){
        for(bookInventory in LibraryDB.getBookInventories()){
            if(bookInventory.book == rentalBook.book){
                bookInventory.addRentedNumberOfBooks()
                break
            }
        }
        rentedBooksAndRentalDetails[rentalBook]=userData
    }

    internal fun removeRentedBooksAndRentalDetails(rentalBook: RentalBook){
        rentedBooksAndRentalDetails.remove(rentalBook)
        for(bookInventory in LibraryDB.getBookInventories()){
            if(bookInventory.book == rentalBook.book){
                bookInventory.removeRentedNumberOfBooks()
                return
            }
        }
        LibraryDB.addBookInventory(BookInventory(rentalBook.book, 1))

    }

}
