open class ValidationException(message : String) : Exception(message)
class AddressNotFoundException(message : String) : ValidationException(message)



open class AuthenticationException(message: String) : Exception(message)

class AccountNotFoundException(message : String) : AuthenticationException(message)

class AuthenticationFailedException(message : String) : AuthenticationException(message)



class PhoneNumberAlreadyExistException(message : String) : Exception(message)

class LimitExceededException(message : String) : Exception(message)

