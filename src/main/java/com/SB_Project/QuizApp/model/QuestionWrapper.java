package com.SB_Project.QuizApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class QuestionWrapper {
    private Integer Id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    public QuestionWrapper(Integer id, String option4, String option3, String option2, String option1, String questionTitle) {
        Id = id;
        this.option4 = option4;
        this.option3 = option3;
        this.option2 = option2;
        this.option1 = option1;
        this.questionTitle = questionTitle;
    }
}
