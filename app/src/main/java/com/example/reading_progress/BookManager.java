package com.example.reading_progress;

import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BookManager {
    private static final String BOOK_LIST_FILE_NAME = "book_list.json";


    private static BookManager instance;
    private List<Book> bookList;

    private BookManager() {
        bookList = new ArrayList<>();
    }

    public static synchronized BookManager getInstance(Context context) {
        if (instance == null) {
            instance = new BookManager();
            instance.loadBooksFromInternalStorage(context);
            //instance.loadIdCountFromInternalStorage(context);
        }
        return instance;
    }
    private void loadBooksFromInternalStorage(Context context) {
        try {
            FileInputStream fis = context.openFileInput(BOOK_LIST_FILE_NAME);
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            String json = new String(buffer);
            Gson gson = new Gson();
            Type type = new TypeToken<List<Book>>() {}.getType();
            bookList = gson.fromJson(json, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveBooksToInternalStorage(Context context) {
        Gson gson = new Gson();
        String json = gson.toJson(bookList);
        try {
            FileOutputStream fos = context.openFileOutput(BOOK_LIST_FILE_NAME, Context.MODE_PRIVATE);
            fos.write(json.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public Book getBook(int bookId) {
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getBookId() == bookId) {
                return bookList.get(i);
            }
        }
        return new Book(" ", " ", 0);
    }

    public boolean newBookOk(String _title, String _author) {
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getTitle().equals(_title) && bookList.get(i).getAuthor().equals(_author)) {
                return false;
            }
        }
        return true;
    }

    public void addBook(Context context, String _title, String _author, int _pages) {
        Book newBook = new Book(_title, _author, _pages);
        bookList.add(newBook);
        saveBooksToInternalStorage(context);
    }

    public void deleteBook(Context context, int bookId) {
        bookList.remove(bookId);
        saveBooksToInternalStorage(context);
    }
}
