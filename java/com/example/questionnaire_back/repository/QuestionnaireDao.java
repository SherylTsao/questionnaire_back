package com.example.questionnaire_back.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.questionnaire_back.entity.Questionnaire;
@Transactional
@Repository
public interface QuestionnaireDao extends JpaRepository<Questionnaire, UUID> {
	/*
	 * 新增問卷
	 */
	@Transactional
	@Modifying
	@Query(value = "insert into questionnaire (questionnaire_id, title, description, created_at, start_at, end_at) values (:questionnaireId, :title, :description, :createdAt, :startAt, :endAt)", nativeQuery = true)
	public int insertProject(@Param("questionnaireId") String questionnaireId, @Param("title") String title,
			@Param("description") String description, @Param("createdAt") LocalDateTime createdAt,
			@Param("startAt") LocalDate startAt, @Param("endAt") LocalDate endAt);
	
	/*
	 * 完成問卷
	 */
	@Transactional
	@Modifying
	@Query(value = "update questionnaire set title = :title, description = :description, created_at = :createdAt, start_at = :startAt, end_at = :endAt "
			+ "where questionnaire_id = :questionnaireId", nativeQuery = true)
	public int updateInfo(@Param("questionnaireId") String questionnaireId, @Param("title") String title, @Param("description") String description,
			@Param("createdAt") LocalDateTime createdAt, @Param("startAt") LocalDate startAt,
			@Param("endAt") LocalDate endAt);

	/*
	 * 尋找問卷資訊(前台)
	 */
	@Query(value = "select * from questionnaire "
			+ "where title like %:title% AND (start_at >= :startAt AND end_at <= :endAt) "
			+ "order by created_at desc", nativeQuery = true)
	public List<Questionnaire> searchProjectsByCreatedAtDesc(@Param("title") String title,
			@Param("startAt") LocalDate startAt, @Param("endAt") LocalDate endAt);
	
	/*
	 * 尋找問卷資訊(前台)
	 */
	@Query(value = "select * from questionnaire "
			+ "where questionnaire_id :questionnaireId"
			, nativeQuery = true)
	public List<Questionnaire> searchProjectsByQuestionnaireId(@Param("questionnaireId") String questionnaireId);
	
	
	/*
	 * 刪除多筆問卷(後台)
	 */
	public void deleteByQuestionnaireIdIn(List<UUID> uuidList);

}
