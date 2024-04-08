package com.example.reading_progress;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BookDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        Intent intent = getIntent();

        if(intent!= null){
            String selectedTitle = intent.getStringExtra("selectedBook");
            if(selectedTitle != null){
                loadBookData(selectedTitle);
            }
        }

    }

    private void loadBookData(String title){
        Book selectedBook = BookManager.getInstance().getBook(title);

        if(selectedBook != null){
            TextView bookTitleText = findViewById(R.id.bookTitleText);
            //ImageView cover = findViewById(R.id.bookCover);

            //bookTitleText.setText(String.valueOf(selectedBook.getTitle()));
            //cover.setImageResource(selectedBook.getCoverId());
        }
    }
}