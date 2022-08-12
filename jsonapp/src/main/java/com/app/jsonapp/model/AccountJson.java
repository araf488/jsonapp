package com.app.jsonapp.model;

public class AccountJson 
{
	private String requestId;
	private String accountName;
	private String amount;

	public String getRequestId() 
	{
		return requestId;
	}

	public void setRequestId(final String requestId) 
	{
		this.requestId = requestId;
	}

	public String getAccountName() 
	{
		return accountName;
	}

	public void setAccountName(final String accountName) 
	{
		this.accountName = accountName;
	}

	public String getAmount() 
	{
		return amount;
	}

	public void setAmount(final String amount) 
	{
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "AccountJson [requestId=" + requestId + ", accountName=" + accountName + ", amount=" + amount + "]";
	}
}
