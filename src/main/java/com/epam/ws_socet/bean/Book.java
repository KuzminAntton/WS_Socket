package com.epam.ws_socet.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Book", propOrder = { "id", "language", "edition", "author", "date" })
@XmlAccessorType(XmlAccessType.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {

    @XmlElement(required = true, name = "id")
    @JsonProperty("id")
    private int id;

    @XmlElement(required = true, name = "language")
    @JsonProperty("language")
    private String language;

    @XmlElement(required = true, name = "edition")
    @JsonProperty("edition")
    private String edition;

    @XmlElement(required = true, name = "author")
    @JsonProperty("author")
    private String author;

    @XmlElement(required = true, name = "create_date")
    @JsonProperty("create_date")
    private String date;

    public Book() {
    }

    public Book(int id, String language, String edition, String author, String date) {
        this.id = id;
        this.language = language;
        this.edition = edition;
        this.author = author;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != book.id) return false;
        if (language != null ? !language.equals(book.language) : book.language != null) return false;
        if (edition != null ? !edition.equals(book.edition) : book.edition != null) return false;
        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        return date != null ? date.equals(book.date) : book.date == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (edition != null ? edition.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{\"book\":{\"id\":\""+ id +"\"," +
                "\"language\":\""+language+"\"," +
                "\"edition\":\""+edition+"\"," +
                "\"author\":\""+author+"\"," +
                "\"create_date\":\""+date+"\"}}";
    }
}