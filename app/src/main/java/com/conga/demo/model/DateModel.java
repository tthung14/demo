package com.conga.demo.model;

public class DateModel {
    private int day, month, year;

    public String formatDate() {
        return day + "/" + (month + 1) + "/" + year;
    }

    public DateModel() {
    }

    public DateModel(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
