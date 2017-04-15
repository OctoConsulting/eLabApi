package com.octo.elab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.octo.elab.pojo.db.Evidence;

public interface EvidenceRepository extends JpaRepository<Evidence, Integer> {

	@Query(value = "select e.* from elab.evidence e where (e.id = :evidenceID)", nativeQuery = true)
	public Evidence getEvidenceByID(@Param("evidenceID") Integer evidenceID);

	@Query(value = "select e.* from elab.evidence e order by e.id", nativeQuery = true)
	public List<Evidence> getAllEvidences();
	
	@Query(value = "select e.* from elab.evidence e where (e.parent_id = :parentID)", nativeQuery = true)
	public List<Evidence> getEvidencesByParentID(@Param("parentID") Integer parentID);
	
	@Query(value = "select e.* from elab.evidence e where (e.case_id = :caseID)", nativeQuery = true)
	public List<Evidence> getEvidencesByCaseID(@Param("caseID") Integer caseID);
	
	@Query(value = "select e.* from elab.evidence e where (e.case_id = :caseID) and e.evidence_type = 1", nativeQuery = true)
	public List<Evidence> getContainersByCaseID(@Param("caseID") Integer caseID);

}
