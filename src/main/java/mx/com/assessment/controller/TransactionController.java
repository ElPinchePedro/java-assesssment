package mx.com.assessment.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.com.assessment.commons.AppUrlConstants;
import mx.com.assessment.commons.RestUtils;
import mx.com.assessment.model.Transaction;
import mx.com.assessment.service.TransactionService;


@RestController
@RequestMapping(AppUrlConstants.BASE_NAME)
public class TransactionController {
	 
	private final RestUtils restUtils = new RestUtils();
	
	@Autowired
    public TransactionService transactionsService;
    
	
	
	@PostMapping(AppUrlConstants.NEW_TRX)
	public ResponseEntity<Transaction> createTransaction(@PathVariable Long userId, @RequestBody Transaction transaction) {
		Transaction createdTransaction = this.transactionsService.prepareTransaction(userId, transaction);
		return new ResponseEntity<Transaction>(createdTransaction, HttpStatus.OK);
	}

	
	@SuppressWarnings("unchecked")
	@GetMapping(AppUrlConstants.FIND_TRX)
	public ResponseEntity<Transaction> getTransaction(@RequestParam(name = "transactionId") String transactionId, @RequestParam(name = "userId") Long userId ) {
		Transaction transactionFound = this.transactionsService.getUniqueTransaction(transactionId, userId);
		if(!transactionFound.equals(null)) {
			return new ResponseEntity<Transaction>(transactionFound, HttpStatus.OK);
		}
		return restUtils.errorMessage(HttpStatus.NOT_FOUND, "Transaction not found");
	}
	
	@GetMapping(AppUrlConstants.TRX_LIST)
	public @ResponseBody List<Transaction> getTransactions(@RequestParam(name = "userId") Long userId ) {
		List<Transaction> transactionsFound = null;
		try {
			transactionsFound = this.transactionsService.getTransactionsList(userId);
		} catch (Exception e) {
			return null; //should be return the exception message
		}
		
		return transactionsFound;
	}
	
	@GetMapping(AppUrlConstants.TRX_SUM)
	public @ResponseBody  Map<String, String> transactionSummary(@RequestParam(name = "userId") Long userId ) {
		return this.transactionsService.getSum(userId);
	}
	
	@GetMapping(AppUrlConstants.RDM_TRX)
	public ResponseEntity<Transaction> randomTrx() {
			return new ResponseEntity<Transaction>(this.transactionsService.randomTrx(), HttpStatus.OK);
	}
	
}
