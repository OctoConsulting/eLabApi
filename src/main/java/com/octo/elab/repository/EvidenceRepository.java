package com.octo.elab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.octo.elab.pojo.db.Evidence;

public interface EvidenceRepository extends JpaRepository<Evidence, Integer> {

	@Query(value = "select e.*, et.description from elab.evidence e inner join elab.evidence_type et on et.id = e.evidence_type where (e.id = :evidenceID) order by e.id", nativeQuery = true)
	public Evidence getEvidenceByID(@Param("evidenceID") Integer evidenceID);

	@Query(value = "select e.*, et.description from elab.evidence e inner join elab.evidence_type et on et.id = e.evidence_type order by e.id", nativeQuery = true)
	public List<Evidence> getAllEvidences();
	
	@Query(value = "select e.*, et.description from elab.evidence e inner join elab.evidence_type et on et.id = e.evidence_type where (e.parent_id = :parentID) order by e.id", nativeQuery = true)
	public List<Evidence> getEvidencesByParentID(@Param("parentID") Integer parentID);
	
	@Query(value = "select e.*, et.description from elab.evidence e inner join elab.evidence_type et on et.id = e.evidence_type where (e.case_id = :caseID) order by e.id", nativeQuery = true)
	public List<Evidence> getEvidenceByCaseID(@Param("caseID") Integer caseID);
	
	@Query(value = "select e.*, et.description from elab.evidence e inner join elab.evidence_type et on et.id = e.evidence_type where (e._id in :evidenceIDs) order by e.id", nativeQuery = true)
	public List<Evidence> getEvidencesByID(@Param("evidenceIDs") Integer[] evidenceIDs);
	
	@Query(value = "select e.*, et.description from elab.evidence e inner join elab.evidence_type et on et.id = e.evidence_type where (e.case_id = :caseID) and e.evidence_type = 1 order by e.id", nativeQuery = true)
	public List<Evidence> getContainersByCaseID(@Param("caseID") Integer caseID);
	
	@Query(value = "select e.*, et.description from elab.evidence e inner join elab.evidence_type et on et.id = e.evidence_type where e.evidence_type = 3 and e.is_foranalysis = true order by e.id", nativeQuery = true)
	public List<Evidence> getAllEvidencesForExam();
	
	@Query(value = "select max(id) from elab.evidence", nativeQuery = true)
	public Integer getMaxEvidenceID();
	
	@Query(value = "select * from elab.evidence e where e.case_id = :caseID and e.evidence_type = :evidenceType order by e.id", nativeQuery = true)
	public List<Evidence> getEvidencesByEvidenceTypeForCaseID(@Param("caseID") Integer caseID, @Param("evidenceType") Integer evidenceType);
	
	@Query(value = "select max(_id) from elab.evidence where evidence_type = :evidenceType", nativeQuery = true)
	public Integer getMaxEvidence_ID(@Param("evidenceType") Integer evidenceType);
	
	@Query(value = "select e.*, et.description from elab.evidence e inner join elab.evidence_type et on et.id = e.evidence_type where (e.case_id = :caseID) and e.is_foranalysis = true order by e.id", nativeQuery = true)
	public List<Evidence> getEvidenceByCaseIDAndForAnalysis(@Param("caseID") Integer caseID);
	
	@Query(value = "select e.* from elab.evidence e where e.id in :ids order by e.id",nativeQuery = true)
	public List<Evidence> getEvidenceByIds(@Param("ids")List<Integer> ids);

}
