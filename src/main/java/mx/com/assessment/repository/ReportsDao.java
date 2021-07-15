package mx.com.assessment.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mx.com.assessment.model.Transaction;

public interface ReportsDao extends CrudRepository<Transaction, Long> {
	
	@Query(value = "SELECT COUNT transactionId FROM Transaction t WHERE t.createAt BETWEEN ?1 AND ?2", 
			  nativeQuery = true)
			Integer salesQuantity(String startDate, String endDate);

}
