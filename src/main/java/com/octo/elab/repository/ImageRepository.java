package com.octo.elab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.octo.elab.pojo.db.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {

	@Query(value = "select i.* from elab.image i where (i._id = :id)", nativeQuery = true)
	public Image getImageByID(@Param("id") Integer id);

	@Query(value = "select i.* from elab.image i order by i._id", nativeQuery = true)
	public List<Image> getAllImages();

	@Query(value = "select i._id from elab.image i order by i.id", nativeQuery = true)
	public Integer[] getAllImageIDs();

	@Query(value = "select max(i._id) from elab.image i", nativeQuery = true)
	public Integer getMaxImageID();

}