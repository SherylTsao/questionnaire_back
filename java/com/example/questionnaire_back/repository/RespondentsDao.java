package com.example.questionnaire_back.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.questionnaire_back.entity.Respondents;


@Repository
public interface RespondentsDao extends JpaRepository<Respondents, Integer>{

	
	// 新增資料
		@Transactional
		@Modifying
		@Query(value = "insert into respondents (name, email, age, gender,address,phone) values (:participantId, :name, :email, :age, :gender, :address, :phone)", nativeQuery = true)
		public int addInfo(@Param("name") String name,
				@Param("email") String email, @Param("age") int age, @Param("gender") String gender, @Param("address") String address, @Param("phone") String phone);

}
