package com.tsystems.javaschool.tasks.currencyconverter;

public class CannotConvertCurrencyException extends RuntimeException {
	
	public CannotConvertCurrencyException(String mensaje) {
		super(mensaje);
	}

}
