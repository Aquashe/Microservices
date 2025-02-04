package com.thomas.Quiz_service.model;

import org.springframework.stereotype.Component;

@Component
public class QuizDto {
    private String category;
    private Integer numQuestions;
    private  String title;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getNumQuestions() {
        return numQuestions;
    }

    public void setNumQuestions(Integer numQuestions) {
        this.numQuestions = numQuestions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
