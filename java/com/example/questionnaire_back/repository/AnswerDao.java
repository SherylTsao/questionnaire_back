package com.example.questionnaire_back.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.questionnaire_back.entity.Answer;
import com.example.questionnaire_back.entity.Questionnaire;

@Repository
public interface AnswerDao extends JpaRepository<Answer, Integer> {
	public List<Answer> findByParticipantId(int AnswerId);

	public List<Answer> findByOptionIdIn(List<Integer> intList);
	
	/*
	 * 利用使用者ID尋找
	 */
//	@Query(value = "select * from answer "
//			+ "where participant_id = :participant_id", nativeQuery = true)
//	public List<Answer> searchByParticipantId(@Param("participant_id") int participant_Id);
}
