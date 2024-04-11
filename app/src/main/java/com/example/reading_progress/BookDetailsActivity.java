package com.example.reading_progress;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.Date;

public class BookDetailsActivity extends AppCompatActivity {


    //CustomBaseAdapter customBaseAdapter;

    private TextView bookTitleText;
    Book selectedB;
    Button startDate, finDate;
    Calendar calendar;
    TextView startText, finText, ppd;

    TextView readPages;
    TextView progress;
    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        //customBaseAdapter = new CustomBaseAdapter(this, BookManager.getInstance().getBookList());

        startText = findViewById(R.id.startingDateDate);
        finText = findViewById(R.id.finDate);
        ppd = findViewById(R.id.pagesPerDayText);
        ppd.setText("...");


        Intent intent = getIntent();

        if(intent!= null){
            int selectedId = intent.getIntExtra("selectedBook", -1);
            if(selectedId >= 0){
                loadBookData(selectedId);
            }
        }

        updatePPD();

        calendar = Calendar.getInstance();

        startDate = findViewById(R.id.modStartDate);
        finDate = findViewById(R.id.modFinishDate);

        startDate.setOnClickListener(v -> {
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePick = new DatePickerDialog(BookDetailsActivity.this,
                    (view, year1, month1, dayOfMonth1) -> {
                        Calendar selectedDate = Calendar.getInstance();

                        selectedDate.set(Calendar.YEAR, year1);
                        selectedDate.set(Calendar.MONTH, month1);
                        selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth1);

                        Date finStart = selectedDate.getTime();

                        BookManager.getInstance().getBook(selectedB.getBookId()).setStart(finStart);

                        String finString = returnDateFormatted(finStart);

                        startText.setText(finString);

                        updatePPD();

                        //selectedB.setStart();

                        //selectedB.setStart(finStart);
                    }, year, month, dayOfMonth);

            datePick.show();
        });

        finDate.setOnClickListener(v -> {
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePick = new DatePickerDialog(BookDetailsActivity.this,
                    (view, year1, month1, dayOfMonth1) -> {
                        Calendar selectedDate = Calendar.getInstance();

                        selectedDate.set(Calendar.YEAR, year1);
                        selectedDate.set(Calendar.MONTH, month1);
                        selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth1);

                        Date finGoal = selectedDate.getTime();

                        BookManager.getInstance().getBook(selectedB.getBookId()).setGoal(finGoal);

                        String finString = returnDateFormatted(finGoal);

                        finText.setText(finString);

                        updatePPD();

                    }, year, month, dayOfMonth);

            datePick.show();
        });
    }

    private double countPPD(Date startD, Date finD) {
        long diffMillis = finD.getTime() - startD.getTime();

        if (diffMillis == 0) {
            return 0;
        }

        long days = diffMillis / (1000 * 60 * 60 * 24);

        double ppd = (double) selectedB.remainingPages() / days;

        return ppd;
    }

    private void updatePPD(){
        double ppdResult = countPPD(selectedB.getStart(), selectedB.getGoal());
        ppd.setText(String.format("%.2f", ppdResult));
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
        selectedB = selectedBook;

        if(selectedBook != null){
            bookTitleText = findViewById(R.id.bookTitle);
            TextView author = findViewById(R.id.author);
            TextView pages = findViewById(R.id.pages);
            readPages = findViewById(R.id.readPaged);
            progress = findViewById(R.id.progress);
            bar = findViewById(R.id.progressBar);
            ImageView cover = findViewById(R.id.bookCover);
            //TextView pagesPerDay = findViewById(R.id.pagesPerDayText);
            //startText = findViewById(R.id.startingDateDate);
            //TextView finDate = findViewById(R.id.finDate);

            //txtProgress.setText(String.format("%.2f",book.getProgress()) + "%");

            String start = returnDateFormatted(selectedBook.getStart());
            String finish = returnDateFormatted(selectedBook.getGoal());
            //double ppdResult = countPPD(selectedBook.getStart(), selectedBook.getGoal());

            int prog = (int) selectedBook.getProgress();

            bookTitleText.setText(selectedBook.getTitle());
            //adjustFontSize(selectedBook.getTitle());
            author.setText(selectedBook.getAuthor());
            pages.setText(String.valueOf(selectedBook.getPages()) + " pages");
            readPages.setText("pages read: " + String.valueOf(selectedBook.getReadPages()));
            progress.setText(String.format("%.2f",selectedBook.getProgress()) + "%");
            bar.setProgress(prog);
            startText.setText(start);
            finText.setText(finish);
            //ppd.setText(String.format("%.2f", ppdResult));
            cover.setImageResource(selectedBook.getCoverId());
            updatePPD();

        }
    }

    public void deleteBook(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BookDetailsActivity.this);
        builder.setTitle("Confirmation")
                .setMessage("Are you sure you want to delete this book: " + selectedB.getTitle() + " by " + selectedB.getAuthor())
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
//                        list.remove(position);
//                        adapter.notifyDataSetChanged();
                        BookManager.getInstance().deleteBook(selectedB.getBookId());
                        Toast.makeText(BookDetailsActivity.this, "Book deleted succefully.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(BookDetailsActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

    public void addReadPages(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Update read pages");

        // Set up the input
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Get the input number
                String inputPages = input.getText().toString();
                if (!inputPages.isEmpty()) {
                    int pagesToAdd = Integer.parseInt(inputPages);
                    // Update the read pages
                    selectedB.setReadPages(pagesToAdd);
                    // Refresh the UI
                    loadBookData(selectedB.getBookId());
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        int prog = (int) selectedB.getProgress();

        progress.setText(String.format("%.2f",selectedB.getProgress()) + "%");
        bar.setProgress(prog);
        readPages.setText("pages read: " + String.valueOf(selectedB.getReadPages()));

        //((MainActivity) getParent()).updateListView();

        //customBaseAdapter.updateBookList(BookManager.getInstance().getBookList());

        builder.show();
    }
}