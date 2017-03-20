package pkgLibrary;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class Catalog {

	@XmlAttribute
	int id;	
	
	@XmlElement(name="book")
	ArrayList<Book> books;
	
	
	public int getId() {
		return id;
	}
	

	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Book> getBooks() {
		return books;
	}
	

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}
	

	
	public Book GetBook (String id) throws BookException{
		Book tempBook = null;
				
				for(Book bk : books){
					if(bk.getId().equals(id))
						tempBook= bk;
				}
			if (tempBook==null){
				tempBook= new Book(id);
				throw new BookException(tempBook);
			}
			else{
				return tempBook;
			}
	}
	public void AddBook(String id, Book book) throws BookException{
		try{
			Catalog cat = ReadXMLFile();
			ArrayList<Book>tempList = cat.getBooks();
			for(Book bk : cat.getBooks())
				if (bk.getId() == id)
					throw new BookException(bk);
			tempList.add(book);
			cat.setBooks(tempList);
			WriteXMLFile(cat);
		}
		catch (BookException e) {
			System.out.println("Book id: "+ id + "is already in catalog" );
		}
		
	}
	private static void WriteXMLFile(Catalog cat){
		try{
			String basePath = new File("").getAbsolutePath();
			basePath = basePath + "//src//main//resources//XMLFiles//Books.xml";
			File file = new File(basePath);
			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(cat, file);
			jaxbMarshaller.marshal(cat, System.out);
		}
		catch(JAXBException e){
			e.printStackTrace();
		}			
	}
	private static Catalog ReadXMLFile(){
		
		Catalog cat = null;
		String basePath = new File("").getAbsolutePath();
		basePath = basePath + "//src//main//resources//XMLFiles//Books.xml";
		File file = new File(basePath);
		
		try{
			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			cat= (Catalog) jaxbUnmarshaller.unmarshal(file);
		}
		catch (JAXBException e){
			e.printStackTrace();
		}
		return cat;
		}


	
	
	
	
}
