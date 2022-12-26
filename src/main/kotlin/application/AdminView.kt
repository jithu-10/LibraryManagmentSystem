package application

import Admin
import Book

object AdminView {

    fun menu(admin : Admin){
        do{
            println("Welcome Admin")
            println("1. Add Books")
            println("2. Remove Books")
            println("3. Increase Book Quantity ")
            println("4. Decrease Book Quantity")
            println("5. View Rented Books")
            println("6. View Rental History")
            println("7. View Book Inventory")
            println("8. View Books ")
            println("9. Log Out")
            when(Helper.getInputWithinRange(1,9,null)){
                1-> addBooks(admin)
                2-> removeBooks(admin)
                3-> increaseBookQuantity(admin)
                4-> decreaseBookQuantity(admin)
                5-> viewRentedBooks(admin)
                6-> viewRentalHistory(admin)
                7-> viewBookInventory(admin)
                8-> viewBooks(admin)
                9-> break
            }
        }while (true)

    }

    private fun addBooks(admin: Admin){
        println("Enter Book Name : ")
        val bookName = Helper.getStringInput()
        println("Enter Book Author : ")
        val author = Helper.getStringInput()
        println("Enter Book Publisher : ")
        val publisher = Helper.getStringInput()
        println("Enter Book Genre : ")
        val genre = Helper.getStringInput()
        println("Enter Book Language : ")
        val language = Helper.getStringInput()
        println("Enter Book Price : ")
        val price = Helper.getInputWithinRange(1, Int.MAX_VALUE,null)
        val book = Book(bookName,author,publisher,genre,language, price.toDouble())
        println("Enter Quantity : ")
        val quantity= Helper.getInputWithinRange(1, Int.MAX_VALUE,null)
        admin.addBooks(book,quantity)
        println("Books Successfully Added")
    }

    private fun removeBooks(admin : Admin){
        val bookInventories = admin.getBookInventories()
        if(bookInventories.isEmpty()){
            println("No Book Inventories Found")
            return
        }
        viewBookInventory(admin)
        println("Enter Choice : ")
        val choice = Helper.getInputWithinRange(1,bookInventories.size,null)

        admin.removeBooks(bookInventories[choice-1].book)
    }

    private fun increaseBookQuantity(admin : Admin){
        val bookInventories = admin.getBookInventories()
        if(bookInventories.isEmpty()){
            println("No Book Inventories Found")
            return
        }
        viewBookInventory(admin)
        println("Enter Choice : ")
        val choice = Helper.getInputWithinRange(1,bookInventories.size,null)
        println("Enter No of Books to be added : ")
        val quantity = Helper.getInputWithinRange(1,Int.MAX_VALUE,null)
        admin.increaseBookQuantity(bookInventories[choice-1].book,quantity)
    }

    private fun decreaseBookQuantity(admin : Admin){
        val bookInventories = admin.getBookInventories()
        if(bookInventories.isEmpty()){
            println("No Book Inventories Found")
            return
        }
        viewBookInventory(admin)
        println("Enter Choice : ")
        val choice = Helper.getInputWithinRange(1,bookInventories.size,null)
        println("Enter No of Books to be removed : ")
        val quantity = Helper.getInputWithinRange(1,Int.MAX_VALUE,null)
        admin.decreaseBookQuantity(bookInventories[choice-1].book,quantity)
    }

    private fun viewRentedBooks(admin : Admin){
        val rentedBooks = admin.getRentedBooks()
        if(rentedBooks.isEmpty()){
            println("No Rented Books Found")
            return
        }
        var sno = 0
        println("S.No  Book Name    Author     Genre   Price  Quantity")
        for(book in rentedBooks){
            println((sno+1).toString()+". "+book.name+" "+book.author+"  "+book.genre+"    "+book.price)
            sno++
        }
    }

    private fun viewRentalHistory(admin : Admin){
        val rentalInfo = admin.getRentalHistory()
        if(rentalInfo.isEmpty()){
            println("No Rental Info Found")
            return
        }
        var sno = 0
        println("SNo   Book Name   Issue Date   Expiry Date   Rental Amount    Refundable Price")
        for(rentals in rentalInfo){
            println((sno+1).toString()+"  "+rentals.book.name+"   "+Helper.formatDate(rentals.issuedDate)+"  "+Helper.formatDate(rentals.expiryDate)+"   "+rentals.price+"   "+rentals.getRefundPrice())
            sno++
        }
    }

    private fun viewBookInventory(admin : Admin){
        val bookInventories = admin.getBookInventories()
        if(bookInventories.isEmpty()){
            println("No Book Inventories Found")
            return
        }
        var sno = 0
        println("S.No  Book Name    Author     Genre   Price  Quantity")
        for(bookInventory in bookInventories){
            val book = bookInventory.book
            println((sno+1).toString()+". "+book.name+" "+book.author+"  "+book.genre+"    "+book.price+"   "+bookInventory.getTotalQuantity()+"(Available : "+bookInventory.getAvailableQuantity()+" , Rented : "+bookInventory.getRentedQuantity()+")")
            sno++
        }
    }

    private fun viewBooks(admin : Admin){
        val books = admin.getAllBooks()
        if(books.isEmpty()){
            println("No Book  Found")
            return
        }
        var sno=0
        println("S.NO  Book Name   Author   Publisher  Genre   Language   Price  ")
        for(book in books){
            println((sno+1).toString()+". "+book.name+"  "+book.author+"  "+book.publisher+"  "+book.genre+"  "+book.language+"  "+book.price)
            sno++
        }
    }
}