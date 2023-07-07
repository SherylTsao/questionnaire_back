package com.example.questionnaire_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.questionnaire_back.entity.Answer;



@Repository
public interface AnswerDao extends JpaRepository<Answer, Integer> {

}
