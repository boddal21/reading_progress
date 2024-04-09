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

        bookTitle = findViewById(R.id.titleInput);
        authorName = findViewById(R.id.authorInput);
        pages = findViewById(R.id.pagesInput);

        String title = String.valueOf(bookTitle.getText());
        String author = String.valueOf(authorName.getText());
        int pgs = Integer.valueOf(String.valueOf(pages.getText()));

        BookManager.getInstance().addBook(title, author, pgs);

        Toast.makeText(NewBookActivity.this, "Book added succesfully!", Toast.LENGTH_LONG);

//        Intent intent = new Intent(MainActivity.this, NewBookActivity.class);
//        startActivity(intent);

        Intent intent = new Intent(NewBookActivity.this, MainActivity.class);
        startActivity(intent);
    }
}