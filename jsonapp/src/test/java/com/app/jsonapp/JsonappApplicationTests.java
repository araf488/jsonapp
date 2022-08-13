package com.app.jsonapp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.jsonapp.controller.IndexController;
import com.app.jsonapp.model.AccountInfo;
import com.app.jsonapp.model.AccountJson;
import com.app.jsonapp.service.AccountInfoService;

@SpringBootTest
class JsonappApplicationTests 
{
	@Autowired
	IndexController controller;
	
	@Autowired
	AccountInfoService service;
	
	final String mockData = "{\"requestId\": \"A32W4ER2341\", \"accountName\": \"TXIuIE5henJ1bCBJc2xhbQ==\", \"amount\": \"SCNVQFV3V0tiY0wh\"}";

	@Test
	void contextLoads() {}
	
	@Test
	void saveAccountJsonData() 
	{
		try 
		{
			final AccountJson accountJson = controller.getAccountJson(mockData);
			assertThat(accountJson.getRequestId()).isNotNull();
			assertThat(accountJson.getAccountName()).isNotNull();
		} 
		catch (final Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	void saveAccountInfoData() 
	{
		try 
		{
			final AccountJson accountJson = controller.getAccountJson(mockData);
			final AccountInfo accountInfo = service.getUserAccountInfo(accountJson);
			assertThat(accountInfo.getDecodedString()).isNotNull();
			assertEquals(accountInfo.getAmount(), 1681);
		} 
		catch (final Exception e) 
		{
			e.printStackTrace();
		}
	}
}
