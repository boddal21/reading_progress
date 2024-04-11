package com.example.reading_progress;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.content.Intent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;

public class BookDetailsActivity extends AppCompatActivity {

    private TextView bookTitleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        Intent intent = getIntent();

        if(intent!= null){
            int selectedId = intent.getIntExtra("selectedBook", -1);
            if(selectedId >= 0){
                loadBookData(selectedId);
            }
        }
    }

    private String returnDateFormatted(Date date){
        String result = String.valueOf(date.getYear()+1900) + ".";

        if(date.getMonth() < 9){
            result += "0" +  String.valueOf(date.getMonth()+1) + ".";
        }else{
            result += String.valueOf(date.getMonth()+1) + ".";
        }

        if(date.getDate() <10){
            result += "0" +  String.valueOf(date.getDate());

        }else{
            result += String.valueOf(date.getDate());
        }

        return result;
    }

    private void loadBookData(int bookId){
        Book selectedBook = BookManager.getInstance().getBook(bookId);

        if(selectedBook != null){
            bookTitleText = findViewById(R.id.bookTitle);
            TextView author = findViewById(R.id.author);
            TextView pages = findViewById(R.id.pages);
            TextView readPages = findViewById(R.id.readPaged);
            TextView progress = findViewById(R.id.progress);
            ProgressBar bar = findViewById(R.id.progressBar);
            TextView startDate = findViewById(R.id.startingDateDate);
            TextView finDate = findViewById(R.id.finDate);
            ImageView cover = findViewById(R.id.bookCover);

            //txtProgress.setText(String.format("%.2f",book.getProgress()) + "%");

            String start = returnDateFormatted(selectedBook.getStart());
            String finish = returnDateFormatted(selectedBook.getGoal());

            int prog = (int) selectedBook.getProgress();



            bookTitleText.setText(selectedBook.getTitle());
            //adjustFontSize(selectedBook.getTitle());
            author.setText(selectedBook.getAuthor());
            pages.setText(String.valueOf(selectedBook.getPages()) + " pages");
            readPages.setText("read pages: " + String.valueOf(selectedBook.getReadPages()));
            progress.setText(String.format("%.2f",selectedBook.getProgress()) + "%");
            bar.setProgress(prog);
            startDate.setText(start);
            finDate.setText(finish);
            cover.setImageResource(selectedBook.getCoverId());

        }


    }

//    private void adjustFontSize(String title){
//        int maxChar = 15;
//
//        int titleLen = title.length();
//
//        float defaultFontSize = getResources().getDimension(R.dimen.default_font_size);
//
//        if(titleLen > maxChar){
//            float reducedFontSize = defaultFontSize * (maxChar/ (float) titleLen);
//            bookTitleText.setTextSize(reducedFontSize);
//        }
//    }
}