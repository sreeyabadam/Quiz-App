package com.SB_Project.QuizApp.controller;

import com.SB_Project.QuizApp.model.QuestionWrapper;
import com.SB_Project.QuizApp.model.Response;
import com.SB_Project.QuizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int noq, @RequestParam String title) {
        return quizService.createQuiz(category,noq,title);
    }
    /*  http://localhost:8080/quiz/create?category=Java&noq=5&title=JavaQuiz  */


    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }
    // http://localhost:8080/quiz/get/1


    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses) {
        return quizService.calculateResult(id,responses);
    }
    /*
    http://localhost:8080/quiz/submit/1

    Make sure the order of the Ids is same as the one in the response of quiz/get/{id} method
[
    {
        "id" : 9,
        "response" : "A method or variable shared by all instances of the class"
    },
    {
        "id" : 7,
        "response" : "To execute code that might throw an exception"
    },
    {
        "id" : 13,
        "response" : "Indicates that a method is overridden from a superclass"
    },
    {
        "id" : 15,
        "response" : "Two or more methods with the same name but different parameters"
    },
    {
        "id" : 17,
        "response" : "To call a constructor of the superclass"
    }
]
*/
}
