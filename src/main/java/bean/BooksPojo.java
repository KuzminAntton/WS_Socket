package bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;
import java.util.HashSet;

@XmlRootElement(name = "store")
@XmlAccessorType(XmlAccessType.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BooksPojo {

    @XmlElementWrapper(name = "books")
    @XmlElement(name = "book")
    @JsonProperty("books")
    private HashSet<Book> book;

    public BooksPojo(HashSet<Book> books) {
        this.book = books;
    }


    public HashSet<Book> getBook() {
        return book;
    }

    public void setBooks(HashSet<Book> book) {
        this.book = book;
    }
}
