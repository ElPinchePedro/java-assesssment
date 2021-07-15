package mx.com.assessment.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mx.com.assessment.model.Transaction;
public interface TransactionDao extends CrudRepository<Transaction, Long> {

	public Transaction findByTransactionIdAndUserId(String transactionId, Long userId);
	
	
	@Query(value = "SELECT * FROM Transaction t WHERE t.userId = ?1", 
			  nativeQuery = true)
			List<Transaction> findAllTransactions(Long userId);
	
	@Query(value = "SELECT SUM(amount)FROM Transaction t WHERE t.userId = ?1", 
			  nativeQuery = true)
			Float calculateSum(Long userId);
	
	@Query(value = "SELECT * FROM Transaction t ORDER BY RAND() LIMIT 1", 
			  nativeQuery = true)
			Transaction randomTrx();
	
}
