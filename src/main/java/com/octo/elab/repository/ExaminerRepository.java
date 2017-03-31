package com.octo.elab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.octo.elab.pojo.db.Examiner;

public interface ExaminerRepository extends JpaRepository<Examiner, Integer> {

	@Query(value = "select e.* from elab.Examiner e where (e.pk_Examiner = :ExaminerID)", nativeQuery = true)
	public Examiner getExaminerByID(@Param("ExaminerID") Integer ExaminerID);

	@Query(value = "select e.* from elab.Examiner e order by e.pk_Examiner", nativeQuery = true)
	public List<Examiner> getExaminers();

	@Query(value = "select e.pk_Examiner from elab.Examiner e where e.legacy_Examiners like :ExaminerName", nativeQuery = true)
	public Integer getExaminerIDByName(@Param("ExaminerName") String ExaminerName);

	@Query(value = "select e.pk_Examiner from elab.Examiner e order by e.pk_Examiner", nativeQuery = true)
	public Integer[] getAllExaminerIDs();

	@Query(value = "select max(pk_Examiner) from elab.Examiner", nativeQuery = true)
	public Integer getMaxExaminerID();

}