package com.docker.initial.repository;

import com.docker.initial.modal.exam.Category;
import com.docker.initial.modal.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    public List<Quiz> findBycategory(Category category);

    public List<Quiz> findByActive(Boolean active);

    public List<Quiz> findByCategoryAndActive(Category cat, Boolean active);
}
