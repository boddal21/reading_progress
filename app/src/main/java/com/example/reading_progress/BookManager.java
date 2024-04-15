package com.example.reading_progress;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookManager {
    private static BookManager instance;
    private List<Book> bookList;

    private BookManager(){
        bookList = new ArrayList<>();
    }

    public static synchronized BookManager getInstance(Context context){
        if(instance ==null){
            instance = new BookManager();
            instance.loadDefaultBooks(context);
        }
        return instance;
    }

    private void loadDefaultBooks(Context context){
        if(bookList.isEmpty()){
            Book dune = new Book("Dune", "Frank Herbert",896, "dune_cover.jpg");
//            Book emma = new Book("Emma","Jane Austen",208, R.drawable.emma_cover);
//            Book scythe = new Book("Scythe","Neal Shusterman",448,R.drawable.schyte_cover);
//            Book weWereLiars = new Book("We Were Liars","E. Lockhart",256,R.drawable.we_were_liars_cover);
//            Book city = new Book("City of Bones","Cassandra Clare",485,R.drawable.city_cover);

            bookList.add(dune);
            FileManager.copyDrawableToAppFolder(context, "dune_cover", FileManager.getAppDirectory());
//            bookList.add(emma);
//            bookList.add(scythe);
//            bookList.add(weWereLiars);
//            bookList.add(city);
        }
    }

    public List<Book> getBookList(){
        return bookList;
    }

    public Book getBook(int bookId){
        for(int i=0; i<bookList.size(); i++){
            if(bookList.get(i).getBookId() == bookId){
                return bookList.get(i);
            }
        }
        return new Book(" ", " ", 0);
    }

    public boolean newBookOk(String _title, String _author){
        for(int i = 0; i < bookList.size(); i++){
            if(bookList.get(i).getTitle().equals(_title) && bookList.get(i).getAuthor().equals(_author)){
                return false;
            }
        }
        return true;
    }

    public void addBook(String _title, String _author, int _pages){
        Book newBook = new Book(_title,_author,_pages);
        bookList.add(newBook);
    }

    public void deleteBook(int bookId){
        bookList.remove(bookId);
    }
}
