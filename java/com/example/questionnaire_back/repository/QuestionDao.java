package com.example.questionnaire_back.repository;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.questionnaire_back.entity.Question;
@Transactional
@Repository
public interface QuestionDao extends JpaRepository<Question, UUID> {
	public void deleteByQuestionnaireId(UUID questionnaireId);
}
