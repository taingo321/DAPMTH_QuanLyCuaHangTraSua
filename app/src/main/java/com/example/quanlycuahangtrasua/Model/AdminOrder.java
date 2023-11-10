package com.example.quanlycuahangtrasua.Model;

public class AdminOrder {
    private String note, date, time, totalAmount;

    public AdminOrder() {
    }

    public AdminOrder(String note, String date, String time, String totalAmount) {
        this.note = note;
        this.date = date;
        this.time = time;
        this.totalAmount = totalAmount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
