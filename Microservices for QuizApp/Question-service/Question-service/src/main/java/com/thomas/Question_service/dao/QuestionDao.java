package com.thomas.Question_service.dao;

import com.thomas.Question_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionDao extends JpaRepository<Question,Integer> {
    List<Question> findAllByCategory(String category);

    @Query(value = "SELECT q.id FROM question q where q.category = :category ORDER BY RANDOM() LIMIT :numQ",nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int numQ);
}
