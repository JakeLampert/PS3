package pkgLibrary;

public class BookException extends Exception {
	
	private Book b;
	
	public BookException() {
		super();
	}
	
	public BookException(Book b) {
		super("BAD");
		this.b = b;
	}

	public Book b() {
		return b;
	}

}
