package com.example.questionnaire_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.questionnaire_back.entity.Fillout;
@Repository
public interface FilloutDao extends JpaRepository<Fillout, Integer>{

}
