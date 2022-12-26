package application

import Address
import Book
import Customer
import DeliveryMethod
import RentalBook
import application.Helper.getPostalCode
import application.Helper.formatDate
import application.Helper.getStringInput
import java.lang.Exception

object CustomerView {

    fun menu(customer : Customer){
        do{
            println("Welcome "+customer.userData.userName)
            println("1.View All Books")
            println("2.Filter Books")
            println("3.Borrow Book")
            println("4.Return Book")
            println("5.View Borrowed Books")
            println("6.Log Out")
            when(Helper.getInputWithinRange(1,6,null)){
                1 -> getAndViewBooks(customer)
                2 -> filterBooks(customer)
                3 -> borrowBook(customer)
                4 -> returnBook(customer)
                5 -> viewBorrowedBooks(customer)
                6 -> break
            }
        }while (true)
    }

    private fun borrowBook(customer: Customer){
        getAndViewBooks(customer)
        val books = customer.getAllBooks()


        println("1.Borrow Book")
        println("2.Go Back")

        when(Helper.getInputWithinRange(1,2,null)){
            1 -> {
                println("Enter Book S.no : ")
                val choice = Helper.getInputWithinRange(1,books.size,null)
                borrowBook(customer,books[choice-1])
                return
            }

            2 -> return
        }

        println("Enter Book S.no : ")
        val choice = Helper.getInputWithinRange(1,books.size,null)
        borrowBook(customer,books[choice-1])

    }

    private fun borrowBook(customer: Customer,book: Book){
        println("Delivery Method : ")
        println("1."+DeliveryMethod.HOME_DELIVERY)
        println("2."+DeliveryMethod.COLLECT_FROM_LIBRARY)
        val deliveryChoice = if(Helper.getInputWithinRange(1,2,null)==1) DeliveryMethod.HOME_DELIVERY else DeliveryMethod.COLLECT_FROM_LIBRARY
        var rentalBook : RentalBook
        try{
            rentalBook = customer.borrowBook(book,deliveryChoice)
        }
        catch (e : Exception){
            println(e.message)
            setAddress(customer)
            rentalBook = customer.borrowBook(book,deliveryChoice)
        }

        println("Book Name     Author     Rental Price     Issue Date    Expiry Date")
        println(rentalBook.book.name+"  "+rentalBook.book.author+"  "+rentalBook.price+"  "+ formatDate(rentalBook.issuedDate)+"  "+ formatDate(rentalBook.expiryDate))
        println("You have to pay ₹${rentalBook.price}")
    }

    private fun setAddress(customer: Customer){
        println("Enter Building No : ")
        val buildingNo = getStringInput()
        println("Enter Street Name : ")
        val street = getStringInput()
        println("Enter Locality : ")
        val locality = getStringInput()
        println("Enter City : ")
        val city = getStringInput()
        println("Enter State : ")
        val state = getStringInput()
        println("Enter PostalCode")
        val postalCode: Int = getPostalCode()
        customer.userData.address= Address(buildingNo, street, locality, city, state, postalCode)
    }

    private fun getAndViewBooks(customer: Customer){
        val books = customer.getAllBooks()
        if(books.isEmpty()){
            println("No Books Available")
            return
        }
        viewBooks(books)
    }

    private fun viewBooks(books : List<Book>){
        var sno=0
        println("S.NO  Book Name   Author   Publisher  Genre   Language   Original Price  ")
        for(book in books){
            println((sno+1).toString()+". "+book.name+"  "+book.author+"  "+book.publisher+"  "+book.genre+"  "+book.language+"  "+book.price)
            sno++
        }
    }

    private fun filterBooks(customer: Customer){
        println("1. Filter Book By Name")
        println("2. Filter Book By Author")
        println("3. Filter Book By Genre")
        println("4. Filter Book By Publisher")
        println("5. Filter Book By Language")
        println("6. Go Back")
        var books : List<Book>? = null
        when(Helper.getInputWithinRange(1,6,null)){
            1 -> {
                println("Enter Book Name : ")
                val bookName = getStringInput()
                books = customer.getBookByName(bookName)
            }

            2 -> {
                println("Enter Author Name : ")
                val author = getStringInput()
                books = customer.getBooksByAuthor(author)
            }
            3 -> {
                println("Enter Genre Name : ")
                val genre = getStringInput()
                books = customer.getBooksByGenre(genre)
            }

            4 -> {
                println("Enter Publisher Name : ")
                val publisher = getStringInput()
                books = customer.getBooksByPublisher(publisher)
            }

            5 -> {
                println("Enter Language Name : ")
                val language = getStringInput()
                books = customer.getBooksByLanguage(language)
            }

            6 -> return

        }

        if(books!!.isEmpty()){
            println("No Books Found")
            return
        }
        viewBooks(books)
        println("1.Borrow Book")
        println("2.Go Back")

        when(Helper.getInputWithinRange(1,2,null)){
            1 -> {
                println("Enter Book S.no : ")
                val choice = Helper.getInputWithinRange(1,books.size,null)
                borrowBook(customer,books[choice-1])
            }

            2 -> return
        }

    }

    private fun returnBook(customer: Customer){
        val borrowedBooks= customer.getBorrowedBooks()
        if(borrowedBooks.isEmpty()){
            println("No Borrowed Books available")
            return
        }
        viewBorrowedBooks(customer)
        println("Enter S.no to Return Book ")
        val choice = Helper.getInputWithinRange(1,borrowedBooks.size,null)
        val refundableAmount = customer.returnBook(borrowedBooks[choice-1])
        println("You will be refunded with ₹$refundableAmount")
    }

    private fun viewBorrowedBooks(customer: Customer){
        val borrowedBooks = customer.getBorrowedBooks()
        if(borrowedBooks.isEmpty()){
            println("No Borrowed Books available")
            return
        }
        println("S.No  Book Name     Author     Rental Price     Issue Date    Expiry Date")
        var sno=0
        for(borrowedBook in borrowedBooks){
            println((sno+1).toString()+"    "+borrowedBook.book.name+"  "+borrowedBook.book.author+"  "+borrowedBook.price+"  "+formatDate(borrowedBook.issuedDate)+"  "+ formatDate(borrowedBook.expiryDate))
            sno++
        }
    }

}