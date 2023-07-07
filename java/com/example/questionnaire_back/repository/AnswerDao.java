package com.example.questionnaire_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.questionnaire_back.entity.Answer;

@Repository
public interface AnswerDao extends JpaRepository<Answer, Integer> {
	public List<Answer> findByParticipantId(int AnswerId);

	public List<Answer> findByOptionIdIn(List<Integer> intList);
}
