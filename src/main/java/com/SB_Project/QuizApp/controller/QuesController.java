package com.SB_Project.QuizApp.controller;

import com.SB_Project.QuizApp.model.Question;
import com.SB_Project.QuizApp.service.QuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Question")
public class QuesController {

    @Autowired
    QuesService quesService;

    @GetMapping("/AllQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return quesService.getAllQuestions();
    }

    // http://localhost:8080/Question/AllQuestions

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getByCategory(@PathVariable String category){
        return quesService.getQuestionsByCategory(category);
    }

    //   http://localhost:8080/Question/category/Java

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return quesService.addQuestion(question);
    }

    /*
     http://localhost:8080/Question/add
{
    "question_Title": "What is the purpose of the `finally` block in Python?",
    "option1": "To skip code execution",
    "option2": "To handle logic when an exception is not raised",
    "option3": "To ensure code within the block executes regardless of exceptions",
    "option4": "To declare a function",
    "correct_Answer": "To ensure code within the block executes regardless of exceptions",
    "difficulty_Level": "Medium",
    "category": "Python"
}
     */

    @PutMapping("/update/{Id}")
    public ResponseEntity<String> updateQuestion(@PathVariable int Id,@RequestBody Question question){

        return quesService.updateQuestion(Id, question);
    }
    /*
     http://localhost:8080/Question/update/1

    {
        "questionTitle": "What is the main purpose of the Java Virtual Machine (JVM)?",
        "option1": "Update to some value",
        "option2": "To execute Java bytecode",
        "option3": "To interpret Python scripts",
        "option4": "To manage memory",
        "correctAnswer": "To execute Java bytecode",
        "difficultyLevel": "Easy",
        "category": "Java",
        "id": 1
    }
     */

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<String> deleteQuestionById(@PathVariable int Id){
        return quesService.deleteQuestionById(Id);
    }

}
