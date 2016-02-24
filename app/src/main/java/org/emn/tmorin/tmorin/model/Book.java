package org.emn.tmorin.tmorin.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by theop on 28/01/2016.
 */
public class Book implements Parcelable{

    private String isbn;
    private String title;
    private String price;
    private String cover;

    public Book() {};

    public Book(String isbn, String name, String price, String coverURL) {
        this.isbn = isbn;
        this.title = name;
        this.price = price;
        this.cover = coverURL;
    }

    protected Book(Parcel in){
        this.isbn = in.readString();
        this.title = in.readString();
        this.price = in.readString();
        this.cover = in.readString();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!this.isbn.equals(book.getIsbn())) return false;
        if (!this.title.equals(book.getTitle())) return false;
        if (!this.price.equals(book.getPrice())) return false;
        if (!this.cover.equals(book.getCover())) return false;
        return true;

    }

    @Override
    public int hashCode() {
        return title.hashCode() + price.hashCode() + isbn.hashCode() + cover.hashCode();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.isbn);
        dest.writeString(this.title);
        dest.writeString(this.price);
        dest.writeString(this.cover);
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) { return new Book(source); }
        @Override
        public Book[] newArray(int size) { return new Book[size]; }
    };
}
