package com.octo.elab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.octo.elab.pojo.db.Evidence;

public interface EvidenceRepository extends JpaRepository<Evidence, Integer> {

	@Query(value = "select e.* from elab.Evidence e where (e._id = :EvidenceID)", nativeQuery = true)
	public Evidence getEvidenceByID(@Param("EvidenceID") Integer EvidenceID);

	@Query(value = "select e.* from elab.Evidence e order by e._id", nativeQuery = true)
	public List<Evidence> getAllEvidences();

	@Query(value = "select e._id from elab.Evidence e order by e._id", nativeQuery = true)
	public Integer[] getAllEvidenceIDs();

	@Query(value = "select max(_id) from elab.Evidence", nativeQuery = true)
	public Integer getMaxEvidenceID();
	
	@Query(value = "select e.* from elab.Evidence e where (e.case_id = :CaseID)", nativeQuery = true)
	public Evidence getEvidenceByCaseID(@Param("CaseID") Integer CaseID);

}
