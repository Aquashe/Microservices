package com.thomas.Question_service.service;

import com.thomas.Question_service.dao.QuestionDao;
import com.thomas.Question_service.model.Question;
import com.thomas.Question_service.model.QuestionWrapper;
import com.thomas.Question_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<Question> addQuestion(Question question) {
        try{
            return new ResponseEntity<>(questionDao.save(question),HttpStatus.CREATED) ;
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE) ;
        }

    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try{
            return new ResponseEntity<>(questionDao.findAllByCategory(category),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
        List<Integer> questions = questionDao.findRandomQuestionsByCategory(categoryName,numQuestions);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionsIds) {
        List<QuestionWrapper> wrapper = new ArrayList<>();
        List<Question> questionList = new ArrayList<>();
        for(Integer id : questionsIds)
            questionList.add(questionDao.findById(id).get());

        for(Question question : questionList){
            QuestionWrapper questionWrapper = new QuestionWrapper(question.getId(),question.getQuestionTitle(),question.getOption1(),question.getOption2(),question.getOption3(),question.getOption4());
            wrapper.add(questionWrapper);
        }

        return new ResponseEntity<>(wrapper,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {

        int right =0;
        for(Response response: responses){
            Optional<Question> question = questionDao.findById(response.getId());
            if(response.getResponse().equals(question.get().getRightAnswer()))
                right++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
