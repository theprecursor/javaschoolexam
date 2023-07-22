package com.tsystems.javaschool.tasks.currencyconverter;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrencyConverter {

    /**
     * Currency converter which converts the input statement and returns the result value.
     *
     * @param dollarToEuroRate 1 Dollar equals XX Euro
     * @param statement input statement to convert
     * @return converted value
     */
	
	//These are 3 conditions depending on the type of conversion example
	//convertToDollar to change from euros to dollars or convertToEuro 
	//to change from dollars to euros. The method evaluateExpression to
	//add format currency depending about the currency was converted
	
	private double euroToDollarExchangeRate = 1.25;
	
    public String convert(double dollarToEuroRate, String statement) {
    	
    	 if (statement.startsWith("convertToDollar(") && statement.endsWith(")")) {
             double amountInDollars = convertToDollar(dollarToEuroRate);
             String innerExpression = statement.substring(15, statement.length() - 1);
             return evaluateExpression(amountInDollars, innerExpression);
         } else if (statement.startsWith("convertToEuro(") && statement.endsWith(")")) {
             double amountInDollars = Double.parseDouble(statement.substring(14, statement.length() - 5));
             return convertToEuro(amountInDollars);
         } else {
             throw new IllegalArgumentException("Invalid conversion string");
         }
    }
    
    private double convertToDollar(double amountInEuros) {
        double exchangeRate = 1.25; // Assuming 1 Euro = 1.25 Dollars
        return amountInEuros * exchangeRate;
    }

    private String convertToEuro(double amountInDollars) {
        double exchangeRate = 0.8; // Assuming 1 Dollar = 0.8 Euros
        double amountInEuros = amountInDollars * exchangeRate;
        return String.format("%.1feuro", amountInEuros);
    }

    private String evaluateExpression(double amount, String expression) {
        String[] parts = expression.split("\\(|\\)");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid expression");
        }

        String functionName = parts[0];
        double value = Double.parseDouble(parts[1]);

        if (functionName.equals("convertToDollar")) {
            return String.format("$%.1f", value);
        } else if (functionName.equals("convertToEuro")) {
            return String.format("%.1feuro", value);
        } else {
            throw new IllegalArgumentException("Invalid function in expression");
        }
    }
    
}
