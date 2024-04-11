package com.example.reading_progress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewBookActivity extends AppCompatActivity {

    EditText bookTitle, authorName, pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_book);
    }

    public void registerBook(View view) {
        boolean ok = false;

        bookTitle = findViewById(R.id.titleInput);
        authorName = findViewById(R.id.authorInput);
        pages = findViewById(R.id.pagesInput);

        String title = String.valueOf(bookTitle.getText());
        String author = String.valueOf(authorName.getText());
        int pgs = Integer.valueOf(String.valueOf(pages.getText()));

        ok = BookManager.getInstance().newBookOk(title, author);
        if(ok){
            BookManager.getInstance().addBook(title, author, pgs);
            Toast.makeText(NewBookActivity.this, "Book added succesfully!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(NewBookActivity.this, MainActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(NewBookActivity.this, "You already have a book with this title and author.", Toast.LENGTH_SHORT).show();
        }
    }
}