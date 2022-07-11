package com.margub.validators;

public class IntegerValidator
{

	public static boolean isInteger(String s)
	{
		try {
			Integer.parseInt(s);
		}catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
