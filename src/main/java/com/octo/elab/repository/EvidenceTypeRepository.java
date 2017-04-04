package com.octo.elab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.octo.elab.pojo.db.EvidenceType;

public interface EvidenceTypeRepository extends JpaRepository<EvidenceType, Integer> {

	@Query(value = "select e.* from elab.evidence_type e where (e._id = :EvidenceTypeID)", nativeQuery = true)
	public EvidenceType getEvidenceTypeByID(@Param("EvidenceTypeID") Integer EvidenceTypeID);

	@Query(value = "select e.* from elab.evidence_type e order by e._id", nativeQuery = true)
	public List<EvidenceType> getAllEvidenceTypes();

	@Query(value = "select e._id from elab.evidence_type e order by e._id", nativeQuery = true)
	public Integer[] getAllEvidenceTypeIDs();

	@Query(value = "select max(_id) from elab.evidence_type", nativeQuery = true)
	public Integer getMaxEvidenceTypeID();

}
