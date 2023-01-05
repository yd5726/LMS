package com.example.lms_kmj.tt_recv;

public class TT_DTO {
    private String date, time, context;

    public TT_DTO(String date, String time, String context) {
        this.date = date;
        this.time = time;
        this.context = context;
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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
