package com.app.jsonapp.service;

import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.app.jsonapp.model.AccountInfo;
import com.app.jsonapp.model.AccountJson;

@Service
public class AccountInfoService implements AccountService
{
	@Override
	public AccountInfo getUserAccountInfo(final AccountJson accountJson)
	{
		final byte[] nameString = Base64.getDecoder().decode(accountJson.getAccountName());
		final String name = new String(nameString);
		
		final byte[] decodedBytes = Base64.getDecoder().decode(accountJson.getAmount());
		final String decodedString = new String(decodedBytes);
		
		final Pattern pattern = Pattern.compile("[^a-z A-Z]");
		final Matcher matcher = pattern.matcher(decodedString);
		final String trimmedString = matcher.replaceAll("");
		
		final String incrimentalString = incrementCharsToString(trimmedString);
		final String uppercaseString = incrimentalString.toUpperCase();
		final int amount = convertRomanToDecimalAmount(uppercaseString);
		final String reverseString = convertIntegerToRomanNumerals(amount);
		
		final AccountInfo accountInfo = new AccountInfo();
		accountInfo.setName(name);
		accountInfo.setDecodedString(decodedString);
		accountInfo.setTrimmedString(trimmedString);
		accountInfo.setIncrimentalString(incrimentalString);
		accountInfo.setUppercaseString(uppercaseString);
		accountInfo.setReverseString(reverseString);
		accountInfo.setAmount(amount);

		return accountInfo;
	}
	
	public String incrementCharsToString(final String word) 
	{
	    final StringBuffer b = new StringBuffer();
	    final char[] chars = word.toCharArray();
	    for (char c : chars) 
	    {
	    	if(c == 'z' || c == 'Z')
	    	{
	    		c = (char) (c - 26);
	    	}

	        if(c != ' ')
	        {
	        	c = (char) (c + 1);
	        }

	        b.append(c);
	    }

	    return b.toString();
	 }
	
	private int convertRomanToDecimalAmount(final String romanNumeral) 
	{
		int decimal = 0;

	    for(int x = 0; x < romanNumeral.length(); x++)
	    {
	        final char convertToDecimal = romanNumeral.charAt(x);

	        switch (convertToDecimal)
	        {
	        case 'M':
	            decimal += 1000;
	            break;

	        case 'D':
	            decimal += 500;
	            break;

	        case 'C':
	            decimal += 100;
	            break;

	        case 'L':
	            decimal += 50;
	            break;

	        case 'X':
	            decimal += 10;
	            break;

	        case 'V':
	            decimal += 5;
	            break;

	        case 'I':
	            decimal += 1;
	            break;
	        }
	    }
	    
	    return decimal;
	}
	
	public String convertIntegerToRomanNumerals(int num) 
	{
        final int[] values = {1000, 500, 100, 50, 10, 5, 1};
        final String[] romanLiterals = {"M", "D", "C", "L", "X", "V", "I"};

        final StringBuilder roman = new StringBuilder();

        for(int i = 0; i < values.length; i++) 
        {
        	if(num >= values[i])
        	{
        		int divisible = num/values[i];
        		if(divisible > 1)
        		{
        			num -= values[i]*divisible;
        			for (int j = 0; j < divisible; j++) 
        			{
        				roman.append(romanLiterals[i]);
					}
        		}
        		else
        		{
        			num = num % values[i];
        			roman.append(romanLiterals[i]);
        		}
        	}
        }
        
        return roman.toString();
    }
}
