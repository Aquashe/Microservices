package com.thomas.QuizApp.service;

import com.thomas.QuizApp.dao.QuestionDao;
import com.thomas.QuizApp.dao.QuizDao;
import com.thomas.QuizApp.model.Question;
import com.thomas.QuizApp.model.QuestionWrapper;
import com.thomas.QuizApp.model.Quiz;
import com.thomas.QuizApp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class QuizService {

    @Autowired
    private Quiz quiz;

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private QuizDao quizDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category,numQ);

        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success",HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question > questionFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionForUsers = new ArrayList<>();
        for(Question q :questionFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionForUsers.add(qw);
        }
        return new ResponseEntity<>(questionForUsers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculate(int id, List<Response> responses) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questions = quiz.get().getQuestions();
        int right =0,i=0;
        for(Response response: responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.FOUND);
    }
}
