package com.example.questionnaire_back.repository;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.questionnaire_back.entity.Qoption;


@Transactional
@Repository
public interface QoptionDao extends JpaRepository<Qoption, Integer> {
	
	
	public void deleteByQuestionIdIn(List<UUID> uuidList);
	
	/*
	 * 找多筆問題ID(後台)
	 */
	public List<Qoption> findByQuestionIdIn(List<UUID> uuidList);
}
