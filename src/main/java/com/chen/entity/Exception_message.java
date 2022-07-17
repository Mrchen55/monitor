package com.chen.entity;

public class Exception_message {
    private int id;
    private String date;
    private String city;
    private String monitor;
    private String message;

    public Exception_message() {
    }

    public Exception_message(String date, String city, String monitor, String message) {
        this.date = date;
        this.city = city;
        this.monitor = monitor;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Exception_message{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", city='" + city + '\'' +
                ", monitor='" + monitor + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
