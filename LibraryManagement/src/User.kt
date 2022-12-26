
open class User(
    val userData: UserData
){

    fun getBookByName(bookName : String) : List<Book>{
        return LibraryDB.getBooks().filter {
                book -> (modifyString(book.name)==modifyString(bookName))
        }
    }

    fun getBooksByAuthor(author : String) : List<Book>{
        return LibraryDB.getBooks().filter {
                book -> (modifyString(book.author)==modifyString(author))
        }
    }

    fun getBooksByGenre(genre : String) : List<Book>{
        return LibraryDB.getBooks().filter {
                book -> (modifyString(book.genre)==modifyString(genre))
        }
    }

    fun getBooksByPublisher(publisher : String) : List<Book>{
        return LibraryDB.getBooks().filter {
                book -> (modifyString(book.publisher)==modifyString(publisher))
        }
    }

    fun getBooksByLanguage(language: String) : List<Book>{
        return LibraryDB.getBooks().filter {
                book -> (modifyString(book.language)==modifyString(language))
        }
    }

    fun getAllBooks() : List<Book>{
        return LibraryDB.getBooks()
    }



}