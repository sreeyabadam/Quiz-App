package com.SB_Project.QuizApp.repository;

import com.SB_Project.QuizApp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuesRepository extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String category);

    @Query(value = "SELECT * from question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :noq",nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int noq);
}
