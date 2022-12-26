
data class UserData internal constructor(
    val userName : String,
    val phoneNumber : Long
){
    var address : Address? = null
    init {
        if(!phoneNumberValidator(phoneNumber.toString())){
            throw ValidationException("Phone Number not valid")
        }
    }
}
