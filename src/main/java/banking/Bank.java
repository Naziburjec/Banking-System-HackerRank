package banking;

import java.util.LinkedHashMap;

/**
 * Private Variables:<br>
 * {@link #accounts}: List&lt;Long, Account&gt;
 */
public class Bank implements BankInterface {
	private LinkedHashMap<Long, Account> accounts;

	public Bank() {
    // complete the function
    accounts = new  LinkedHashMap<Long, Account>();
	}

	private Account getAccount(Long accountNumber) {
    // complete the function        
        return accounts.get(accountNumber);
        
	}

	public Long openCommercialAccount(Company company, int pin, double startingDeposit) {
		// complete the function
		CommercialAccount commAcc=null;
		if(accounts.size()==0) {
			commAcc= new CommercialAccount(company, 1L, pin, startingDeposit);
		}else {
			Long []keys=accounts.keySet().toArray(new Long[accounts.size()]);
			commAcc= new CommercialAccount(company, (long) (keys[accounts.size()-1]+1), pin, startingDeposit);
		}
		accounts.put(commAcc.getAccountNumber(),commAcc);
        return commAcc.getAccountNumber();
	}

	public Long openConsumerAccount(Person person, int pin, double startingDeposit) {
		// complete the function
		ConsumerAccount consumerAcc=null;
		if(accounts.size()==0) {
			consumerAcc= new ConsumerAccount(person, 1L, pin, startingDeposit);
		}else {
			Long []keys=accounts.keySet().toArray(new Long[accounts.size()]);
			consumerAcc= new ConsumerAccount(person, (long) (keys[accounts.size()-1]+1), pin, startingDeposit);
		}
		accounts.put(consumerAcc.getAccountNumber(), consumerAcc);
        return consumerAcc.getAccountNumber();
	}

	public boolean authenticateUser(Long accountNumber, int pin) {
		// complete the function
		if(accounts.containsKey(accountNumber)) {
			return accounts.get(accountNumber).validatePin(pin);
		}
        return false;
	}

	public double getBalance(Long accountNumber) {
		// complete the function
		if(accounts.containsKey(accountNumber)) {
			return accounts.get(accountNumber).getBalance();
		}
        return 0;
	}

	public void credit(Long accountNumber, double amount) {
		// complete the function
		if(accounts.containsKey(accountNumber)) {
			accounts.get(accountNumber).creditAccount(amount);
		}
	}

	public boolean debit(Long accountNumber, double amount) {
		// complete the function
		if(accounts.containsKey(accountNumber)) {
			if(accounts.get(accountNumber).getBalance()>=amount) {
				accounts.get(accountNumber).debitAccount(amount);
				return true;
			}
			return false;
		}
        return false;
	}
}
