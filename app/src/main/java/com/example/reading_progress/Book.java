package com.example.reading_progress;

import java.util.Date;

public class Book {
    //könyvCím., író., oldalszám.
    //kezdet., cél., elolvasottOldalak.
    private String title, author;
    private int pages, readPages, coverId, bookId;
    private double progress;
    private Date start, goal;

    static int count = 0;

    public Book(String _title, String _author, int _pages, int _coverId){
        this.title = _title;
        this.author = _author;
        this.bookId = count++;
        this.pages = _pages;
        this.readPages = 70;
        this.start = new Date();
        this.goal = new Date();
        this.coverId = _coverId;
        double prog = (double) this.readPages/_pages;
        this.progress = prog*100;
    }

    public Book(String _title, String _author, int _pages){
        this.title = _title;
        this.author = _author;
        this.bookId = count++;
        this.pages = _pages;
        this.readPages = 70;
        this.start = new Date();
        this.goal = new Date();
        this.coverId = R.drawable.empty_cover;
        double prog = (double) this.readPages/_pages;
        this.progress = prog*100;
    }

    public Date getStart() {
        return start;
    }

    public Date getGoal() {
        return goal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getReadPages(){
        return readPages;
    }

    public void setReadPages(int readPages) {
        this.readPages = readPages;
        setProgress();
    }

    public int getBookId() {
        return bookId;
    }

    public  int getCoverId(){
        return coverId;
    }

    public void setCoverId(int coverId) {
        this.coverId = coverId;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress() {
        this.progress = readPages/pages;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setGoal(Date goal) {
        this.goal = goal;
    }

    public int remainingPages(){
        return pages-readPages;
    }
}
