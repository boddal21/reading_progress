package com.example.reading_progress;

import java.util.Date;

public class Book {
    private String title, author, coverId, bookId;
    private int pages, readPages;
    private double progress;
    private Date start, goal, created;

    public Book(String _title, String _author, int _pages){
        this.title = _title;
        this.author = _author;
        this.pages = _pages;
//        Random r1 = new Random(100);
//        Random r2 = new Random(1000);
//        int sum = _pages+r1.nextInt()+r2.nextInt();
//        this.bookId = sum;
        this.bookId = _title + _author + pages;
        this.readPages = 0;
        this.start = new Date();
        this.goal = new Date();
        this.created = new Date();
        this.coverId = "";
        this.progress = 0;
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

    public String getBookId() {
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

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
