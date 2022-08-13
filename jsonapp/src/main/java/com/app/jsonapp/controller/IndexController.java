package com.app.jsonapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.jsonapp.model.AccountInfo;
import com.app.jsonapp.model.AccountJson;
import com.app.jsonapp.service.AccountInfoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class IndexController 
{
	@Autowired
	AccountInfoService accountInfoService;
	
	@GetMapping("/")
	public String index() 
	{
		return "index";
	}

	@RequestMapping(value = "/getAccountInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ModelAndView getAccountInfo(@RequestParam("jsonData") final String jsonData)
	{
		try 
		{
			final AccountJson accountJson = getAccountJson(jsonData);
			if(accountJson != null)
			{
				if(accountJson.getRequestId() != null && accountJson.getAccountName() != null && accountJson.getAmount() != null)
				{
					final AccountInfo accountInfo = accountInfoService.getUserAccountInfo(accountJson);
					
					final ModelAndView view = new ModelAndView();
					view.setViewName("info");
					view.addObject("accountInfo", accountInfo);
					
					return view;
				}
			}
		} 
		catch (final Exception e) 
		{
			e.printStackTrace();
			return emptyView();
		}
		
		return emptyView();
	}
	
	public AccountJson getAccountJson(final String json) throws JsonMappingException, JsonProcessingException
	{
		if(json != null && json != "")
		{
			final ObjectMapper objectMapper = new ObjectMapper();
			final AccountJson accountJson = objectMapper.readValue(json, AccountJson.class);
			return accountJson;
		}
		
		return null;
	}
	
	public ModelAndView emptyView() 
	{
		final String message = "Please insert valid JSON";
		final ModelAndView view = new ModelAndView();
		view.setViewName("index");
		view.addObject("message", message);
		
		return view;
	}
}
