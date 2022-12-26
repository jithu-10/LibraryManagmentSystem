package application

import Account
import AccountNotFoundException
import Address
import AuthenticationFailedException
import Book
import PhoneNumberAlreadyExistException
import UserData

object Application {
    fun startApp(){
        init()
        entry()

    }

    private fun init(){
        val admin = Account.signInAsAdmin(Account.signIn(1234567890,"pass"))
        val prideAndPrejudice = Book("Pride and Prejudice", "Jane Austen", "Penguin Classics", "Romance", "English", 800.0)
        val toKillAMockingbird = Book("To Kill a Mockingbird", "Harper Lee", "Grand Central Publishing", "Fiction", "English", 900.0)
        val oneHundredYearsOfSolitude = Book("One Hundred Years of Solitude", "Gabriel García Márquez", "Harper Perennial Modern Classics", "Fiction", "Spanish", 1200.0)
        val theAlchemist = Book("The Alchemist", "Paulo Coelho", "HarperOne", "Fiction", "Portuguese", 1400.0)
        val thePictureOfDorianGray = Book("The Picture of Dorian Gray", "Oscar Wilde", "Dover Publications", "Fiction", "English", 700.0)
        val theDivineComedy = Book("The Divine Comedy", "Dante Alighieri", "Penguin Classics", "Poetry","English",1000.0)
        val theCatcherInTheRye = Book("The Catcher in the Rye", "J.D. Salinger", "Little, Brown and Company", "Fiction", "English", 1000.0)
        val mobyDick = Book("Moby-Dick", "Herman Melville", "Penguin Classics", "Fiction", "English", 940.0)
        val warAndPeace = Book("War and Peace", "Leo Tolstoy", "Modern Library", "Fiction", "Russian", 1400.0)
        val wutheringHeights = Book("Wuthering Heights", "Emily Brontë", "Barnes & Noble Classics", "Fiction", "English", 700.0)
        admin.apply {
            addBooks(prideAndPrejudice,10)
            addBooks(toKillAMockingbird,20)
            addBooks(oneHundredYearsOfSolitude,5)
            addBooks(theAlchemist,3)
            addBooks(thePictureOfDorianGray,6)
            addBooks(theDivineComedy,10)
            addBooks(theCatcherInTheRye,40)
            addBooks(mobyDick,2)
            addBooks(warAndPeace,1)
            addBooks(wutheringHeights,2)
        }


        val customer = Account.signInAsCustomer(Account.signIn(1234567890,"pass"))
        customer.userData.address= Address("1A","Old Trafford Street","Tambaram","Chennai","Tamil Nadu",123456)

//        val rentalBook = customer.borrowBook(prideAndPrejudice,DeliveryMethod.COLLECT_FROM_LIBRARY)
//        println("Amount while Buy"+ rentalBook.price)
//        println("Refund Amount "+ customer.returnBook(rentalBook))

    }

    private fun entry(){
        do{
            println("Welcome to Library ")
            println("1.Sign In ")
            println("2.Sign Up ")
            println("3.Exit")

            when(Helper.getInputWithinRange(1,3,null)){
                1 -> signIn()
                2 -> signUp()
                3 -> break
            }
        }while (true)
    }

    private fun signIn(){
        println("Enter Phone Number : ")
        val phoneNumber : Long = Helper.getPhoneNumber()
        println("Enter Password : ")
        val password : String = Helper.getStringInput()
        try{
            val userData : UserData = Account.signIn(phoneNumber,password)
            signingInOptions(userData)
        }catch (e : AccountNotFoundException){
            println(e.message)
        }
    }

    private fun signUp(){
        println("Enter User name : ")
        val userName : String = Helper.getStringInput()

        do{
            try{
                println("Enter Phone Number : ")
                val phoneNumber : Long = Helper.getPhoneNumber()
                println("Enter Password : ")
                val password : String = Helper.getStringInput()
                Account.signUp(userName,phoneNumber,password)
                println("Signed Up Successfully. You can sign in now ")
                break
            }
            catch (e : PhoneNumberAlreadyExistException){
                println(e.message)
            }
        }while(true)
    }

    private fun signingInOptions(userData: UserData){
        println("1.Sign In as Admin")
        println("2.Sign In as Customer")

        when(Helper.getInputWithinRange(1,2,null)){
            1 ->try{
                AdminView.menu(Account.signInAsAdmin(userData))
            } catch (e : AuthenticationFailedException){
                println(e.message)
            }

            2 ->try{
                CustomerView.menu(Account.signInAsCustomer(userData))
            }catch (e : AuthenticationFailedException){
                println(e.message)
            }


        }
    }
}