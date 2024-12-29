package com.SB_Project.QuizApp.service;

import com.SB_Project.QuizApp.model.Question;
import com.SB_Project.QuizApp.model.QuestionWrapper;
import com.SB_Project.QuizApp.model.Quiz;
import com.SB_Project.QuizApp.model.Response;
import com.SB_Project.QuizApp.repository.QuesRepository;
import com.SB_Project.QuizApp.repository.QuizRepository;
import jakarta.persistence.Id;
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
    QuizRepository quizRepository;

    @Autowired
    QuesRepository quesRepository;

    public ResponseEntity<String> createQuiz(String category, int noq, String title) {

        List<Question> questions = quesRepository.findRandomQuestionsByCategory(category,noq);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);

        return new ResponseEntity<>("Quiz is Created", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz= quizRepository.findById(id);
        List<Question> questionsfromDB = quiz.get().getQuestions();

        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Question q : questionsfromDB){
            QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getOption4(),q.getOption3(),q.getOption2(),q.getOption1(),q.getQuestionTitle());
            questionsForUser.add(qw);
        }
        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Question> questions=quiz.getQuestions();
        int right=0;
        int i=0;
        for(Response response : responses){
            if((response.getResponse()).equals(questions.get(i).getCorrectAnswer()))
                right++;
            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
