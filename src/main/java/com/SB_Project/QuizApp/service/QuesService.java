package com.SB_Project.QuizApp.service;

import com.SB_Project.QuizApp.model.Question;
import com.SB_Project.QuizApp.repository.QuesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuesService {

    @Autowired
    QuesRepository quesRepository;

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(quesRepository.findByCategory(category), HttpStatus.OK);
        }
        catch (Exception e) {e.printStackTrace();}
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(quesRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e) {e.printStackTrace();}
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<String> addQuestion(Question question) {
        quesRepository.save(question);
        return new ResponseEntity<>("Question added successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<String> updateQuestion(int Id, Question question) {
        quesRepository.save(question);
        return new ResponseEntity<>("Question updated successfully", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteQuestionById(int id) {
        quesRepository.deleteById(id);
        return new ResponseEntity<>("Question deleted successfully", HttpStatus.OK);
    }
}
