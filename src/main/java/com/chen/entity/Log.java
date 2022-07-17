package com.chen.entity;

public class Log {
    private Integer id;
    private String date;
    private String log_user;
    private String message;

    public Log() {
    }

    public Log(String date, String log_user, String message) {
        this.date = date;
        this.log_user = log_user;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLog_user() {
        return log_user;
    }

    public void setLog_user(String log_user) {
        this.log_user = log_user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", log_user='" + log_user + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
