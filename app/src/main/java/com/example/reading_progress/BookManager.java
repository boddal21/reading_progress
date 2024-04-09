package com.example.reading_progress;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookManager {
    private static BookManager instance;
    private List<Book> bookList;

    private BookManager(){
        bookList = new ArrayList<>();
    }

    public static synchronized BookManager getInstance(){
        if(instance ==null){
            instance = new BookManager();
            instance.loadDefaultBooks();
        }
        return instance;
    }

    private void loadDefaultBooks(){
        if(bookList.isEmpty()){
            Book dune = new Book("Dune", "Frank Herbert",896, R.drawable.dune_cover);
            Book emma = new Book("Emma","Jane Austen",208, R.drawable.emma_cover);
            Book scythe = new Book("Scythe","Neal Shusterman",448,R.drawable.schyte_cover);
            Book weWereLiars = new Book("We Were Liars","E. Lockhart",256,R.drawable.we_were_liars_cover);
            Book city = new Book("City of Bones","Cassandra Clare",485,R.drawable.city_cover);

            bookList.add(dune);
            bookList.add(emma);
            bookList.add(scythe);
            bookList.add(weWereLiars);
            bookList.add(city);
        }
    }

    public List<Book> getBookList(){
        return bookList;
    }

    public Book getBook(String _title){
        for(int i=0; i<bookList.size(); i++){
            if(Objects.equals(bookList.get(i).getTitle(), _title)){
                return bookList.get(i);
            }
        }
        return new Book(" ", " ", 0);
    }

    public void addBook(String _title, String _author, int _pages){
        Book newBook = new Book(_title,_author,_pages);
        bookList.add(newBook);
    }

}
