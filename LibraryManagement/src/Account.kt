import java.lang.Exception

object Account{


    fun signUp(userName : String, phoneNumber : Long, password : String){
        if(AccountDB.isPhoneNumberAlreadyExist(phoneNumber)){
            throw Exception("Phone Number already exist")
        }
        val userData = UserData(userName, phoneNumber)
        val customer = Customer(userData)
        AccountDB.addUser(customer, password)
    }

    fun signIn(phoneNumber: Long, password: String) : UserData {
        return AccountDB.isAccountExist(phoneNumber, password) ?:throw AuthenticationException("Account Not Found ")
    }

    fun signInAsAdmin(userData: UserData) : Admin {
        val user = AccountDB.getUser(userData, Admin::class.simpleName) ?: throw AuthenticationException("Authentication Failed")
        return user as Admin
    }

    fun signInAsCustomer(userData: UserData) : Customer {
        val user = AccountDB.getUser(userData, Customer::class.simpleName) ?: throw AuthenticationException("Authentication Failed")
        return user as Customer
    }



}
