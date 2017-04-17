package bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "store")
@XmlAccessorType(XmlAccessType.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BooksPojo {

    @XmlElementWrapper(name = "books")
    @XmlElement(name = "book")
    @JsonProperty("books")
    private List<Book> book;

    public BooksPojo() {
    }

    public BooksPojo(List<Book> book) {
        this.book = book;
    }

    public List<Book> getBook() {
        return book;
    }

    public void setBooks(List<Book> book) {
        this.book = book;
    }
}
