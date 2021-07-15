package mx.com.assessment.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.assessment.model.Transaction;
import mx.com.assessment.repository.TransactionDao;
import mx.com.assessment.repository.UserDao;

@Service
public class TransactionService {
	
	@Autowired
    public TransactionDao transactionsDao;
	
	@Autowired
    public UserDao userDao;
	
	public Transaction prepareTransaction(Long userId, Transaction transaction){
		transaction.setUserId(userId);
		transaction.setTransactionId(UUID.randomUUID().toString());
		transaction.setCreatedAt(this.dateFormater());
		return this.transactionsDao.save(transaction);
	}
	
	public Transaction getUniqueTransaction(String transactionId, Long userId) {
		return this.transactionsDao.findByTransactionIdAndUserId(transactionId, userId);
	}
	
	public List<Transaction> getTransactionsList(Long userId) throws Exception {
			if(this.userDao.existsById(userId)) {
			return this.transactionsDao.findAllTransactions(userId);
		}
			throw new Exception("User does not exist");
	}
	
	public  Map<String, String> getSum(Long userId){
		Map<String, String> result = new LinkedHashMap<String, String>();
		this.transactionsDao.calculateSum(userId);
        result.put("user_id", "" + userId);
        result.put("amount", this.transactionsDao.calculateSum(userId).toString());
		return result;
	}
	
	
	public Transaction randomTrx() {
		return this.transactionsDao.randomTrx();
	}
	
	public Date dateFormater() {
		 SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date date;
		try {
			date = format.parse(new Date().toString());
		} catch (ParseException e) {
			return new Date();
	}
		return date;
	}

}
