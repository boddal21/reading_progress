package com.example.reading_progress;
//package com.example.customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //List<Book> bookList = new ArrayList<>();

    ListView listView;
    CustomBaseAdapter customBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //populateBooks();

        listView = (ListView) findViewById(R.id.booksList);
        customBaseAdapter = new CustomBaseAdapter(getApplicationContext(), BookManager.getInstance().getBookList());
        listView.setAdapter(customBaseAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book selectedBook = BookManager.getInstance().getBookList().get(position);
                Intent intent = new Intent(MainActivity.this, BookDetailsActivity.class);
                intent.putExtra("selectedBook", selectedBook.getBookId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        customBaseAdapter.updateBookList(BookManager.getInstance().getBookList());
    }

    public void addNewBook(View view) {
        Intent intent = new Intent(MainActivity.this, NewBookActivity.class);
        startActivity(intent);
    }

//    public void updateListView(){
//        customBaseAdapter.updateBookList(BookManager.getInstance().getBookList());
//    }

//    private void populateBooks(){
//        BookManager.getInstance().getBookList();
//    }
}