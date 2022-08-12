package com.app.jsonapp.service;

import com.app.jsonapp.model.AccountInfo;
import com.app.jsonapp.model.AccountJson;

public interface AccountService 
{
	public AccountInfo getUserAccountInfo(final AccountJson accountJson);
}
