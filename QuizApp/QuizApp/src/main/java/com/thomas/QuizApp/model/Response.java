package com.thomas.QuizApp.model;

import org.springframework.stereotype.Component;

@Component
public class Response {
    private int id;
    private String response;

    public Response(int id, String response) {
        this.id = id;
        this.response = response;
    }

    public Response() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
