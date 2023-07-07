package com.example.questionnaire_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.questionnaire_back.entity.Respondents;


@Repository
public interface RespondentsDao extends JpaRepository<Respondents, Integer>{

}
