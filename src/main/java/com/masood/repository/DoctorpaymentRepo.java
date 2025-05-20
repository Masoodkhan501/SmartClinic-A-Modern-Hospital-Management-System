package com.masood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.masood.model.DoctorBills;
public interface DoctorpaymentRepo extends JpaRepository<DoctorBills, Long> 
{
	@Query("select d from DoctorBills d where d.doctor.doc_id = :id")
	public List<DoctorBills> findByDoctorId(String id);
	@Query("delete from DoctorBills d where d.doctor.doc_id = :id")
	public void DeleteBillsByDoctorId(String id);
	@Query("select db from DoctorBills db where LOWER(db.doctor.user_id.name) LIKE (CONCAT('%',:name,'%'))")
	public List<DoctorBills> findBillsByDoctorNameLike(@Param("name") String name);
}
