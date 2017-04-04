package com.octo.elab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.octo.elab.pojo.db.Exam;

public interface ExamRepository extends JpaRepository<Exam, Integer> {

	@Query(value = "select e.* from elab.Exam e where (e._id = :ExamID)", nativeQuery = true)
	public Exam getExamByID(@Param("ExamID") Integer ExamID);

	@Query(value = "select e.* from elab.Exam e order by e._id", nativeQuery = true)
	public List<Exam> getAllExams();

	@Query(value = "select e._id from elab.Exam e order by e._id", nativeQuery = true)
	public Integer[] getAllExamIDs();

	@Query(value = "select max(_id) from elab.Exam", nativeQuery = true)
	public Integer getMaxExamID();

	@Query(value = "select e.* from elab.Exam e where (e.case_id = :CaseID)", nativeQuery = true)
	public Exam getExamByCaseID(@Param("CaseID") Integer CaseID);

	@Query(value = "select e.* from elab.Exam e where (e.examiner_id = :ExaminerID)", nativeQuery = true)
	public Exam getExamByExaminerID(@Param("ExaminerID") Integer ExaminerID);

}
