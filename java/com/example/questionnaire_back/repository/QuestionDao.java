package com.example.questionnaire_back.repository;


import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.questionnaire_back.entity.Question;

@Transactional
@Repository
public interface QuestionDao extends JpaRepository<Question, UUID> {
	
	/*
	 * 刪除多筆問卷(後台)
	 */
	public void deleteByQuestionIdIn(List<UUID> uuidList);
	
	/*
	 * 找多筆問卷(後台)
	 */
	public List<Question> findByQuestionnaireIdIn(List<UUID> uuidList);
	
	/*
	 * 找多筆問卷(後台)
	 */
	public void deleteByQuestionnaireIdIn(List<UUID> uuidList);
	
	/*
	 * 尋找_多筆問卷資訊(前後台)
	 * 利用questionnaireId找到所有資訊
	 */
	@Query(value = "select * from question "
			+ "where questionnaire_id = :questionnaireId"
			, nativeQuery = true)
	public List<Question> searchProjectsByQuestionnaireId(@Param("questionnaireId") String questionnaireId);
	
	/*
	 * 尋找_單筆問卷資訊(前後台)
	 */
	@Query(value = "select * from question "
			+ "where questionnaire_id :questionnaireId"
			, nativeQuery = true)
	public List<Question> searchOneByQuestionnaireId(@Param("questionnaireId") String questionnaireId);
	
	
//	public void deleteByQuestionnaireId(UUID questionnaireId);
	
	//---刪除的questionnaireId型態用UUID可以嗎???????????????????????????
	/*
	 *  刪除單筆問卷表
	 */
	@Transactional
	@Modifying
	@Query("delete Question q "
			+ "where q.questionnaireId = :questionnaireId")
	public int deleteProjectByQuestionnaireId(
			@Param("questionnaireId") UUID questionnaireId); 
	
			
	

	
}
