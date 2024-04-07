package com.example.reading_progress;
//package com.example.customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //String bookList[] = {"Dune", "Emma", "Scythe", "We Were Liars", "City of Bones"};

    List<Book> bookList = new ArrayList<>();

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateBooks();

        listView = (ListView) findViewById(R.id.booksList);
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(), bookList);
        listView.setAdapter(customBaseAdapter);

    }

    private void populateBooks(){
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