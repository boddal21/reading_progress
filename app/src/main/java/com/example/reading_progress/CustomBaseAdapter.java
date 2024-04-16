package com.example.reading_progress;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomBaseAdapter extends BaseAdapter {

    Context context;
    //String listBooks[];
    //int listCovers[];
    List<Book> listBook;
    LayoutInflater inflater;


    public CustomBaseAdapter(Context ctx, List<Book> bookList){
        //int [] bookCovers
        this.context = ctx;
        this.listBook = bookList;
        //this.listCovers = bookCovers;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return listBook.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_custom_list_view, parent, false);
        }



        TextView txtView = convertView.findViewById(R.id.bookTitleText);
        TextView txtAuthor = convertView.findViewById(R.id.authorText);
        TextView txtProgress = convertView.findViewById(R.id.progressText);
        ImageView bookCvr = convertView.findViewById(R.id.imageIcom);

        Book book = listBook.get(position);
        txtView.setText(book.getTitle());
        txtAuthor.setText(book.getAuthor());
        txtProgress.setText(String.format("%.2f",book.getProgress()) + "%");

        Bitmap bitmap = BitmapFactory.decodeFile(book.getCoverId());
        if(bitmap!= null){
            bookCvr.setImageBitmap(bitmap);
        }else{
            bookCvr.setImageResource(R.drawable.empty_cover);
        }

        //bookCvr.setImageResource(book.getCoverId());

        return convertView;
    }

    public void updateBookList(List<Book> newList){
        this.listBook = newList;
        notifyDataSetChanged();
    }
}
