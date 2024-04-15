package com.example.reading_progress;

import java.util.Date;

public class Book {
    //könyvCím., író., oldalszám.
    //kezdet., cél., elolvasottOldalak.
    private String title, author, coverId;
    private int pages, readPages, bookId;
    private double progress;
    private Date start, goal, created;

    static int count = 0;

    public Book(String _title, String _author, int _pages, String _coverId){
        this.title = _title;
        this.author = _author;
        this.bookId = count++;
        this.pages = _pages;
        this.readPages = 0;
        this.start = new Date();
        this.goal = new Date();
        this.created = new Date();
        this.coverId = _coverId;
        this.progress = 0;
//        double prog = (double) this.readPages/_pages;
//        this.progress = prog*100;
    }

    public Book(String _title, String _author, int _pages){
        this.title = _title;
        this.author = _author;
        this.bookId = count++;
        this.pages = _pages;
        this.readPages = 0;
        this.start = new Date();
        this.goal = new Date();
        this.created = new Date();
        this.coverId = "empty_cover.jpg";
        this.progress = 0;
//        double prog = (double) this.readPages/_pages;
//        this.progress = prog*100;
    }

    public Date getCreated() {
        return created;
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

    public  String getCoverId(){
        return coverId;
    }

    public void setCoverId(String coverId) {
        this.coverId = coverId;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress() {
        double prog = (double) this.readPages/pages;
        this.progress = prog*100;
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

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
