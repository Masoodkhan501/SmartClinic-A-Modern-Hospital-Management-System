package com.masood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.masood.model.PatientBills;
import com.masood.model.PaymentStatus;
public interface PatientBillsRepo extends JpaRepository<PatientBills, Long>
{
	@Query("select p from Patientbills p where p.patient_id = :id")
	public List<PatientBills> findByPatientId(@Param("id") String id);
	@Query("delete from Patientbills p where p.patient_id = :id")
	public void DeleteBillsByPatientId(@Param("id") String id);
	@Query("select p from Patientbills p where LOWER(p.patient_id.user_id.name) LIKE (CONCAT('%',:name,'%'))")
	public List<PatientBills> findBillsByPatientNameLike(@Param("name") String name);
	@Query("select p from Patientbills p where p.pay_status = :payment")
	public List<PatientBills> findByPaymentDetails(@Param("payment") PaymentStatus payment);
}
