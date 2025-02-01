package com.thomas.QuizApp.dao;

import com.thomas.QuizApp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionDao extends JpaRepository<Question,Integer> {
    List<Question> findAllByCategory(String category);

    @Query(value = "SELECT * FROM question q where q.category = :category ORDER BY RANDOM() LIMIT :numQ",nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numQ);
}
