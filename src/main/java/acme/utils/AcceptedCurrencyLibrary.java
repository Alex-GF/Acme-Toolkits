package acme.utils;

import java.util.ArrayList;
import java.util.List;

public class AcceptedCurrencyLibrary {

	public static List<String> getAcceptedCurrencies(final String acceptedCurrenciesString){
		final List<String> result = new ArrayList<>();

		final String[] acceptedCurrenciesArray = acceptedCurrenciesString.split(",");

		for(int i= 0; i<acceptedCurrenciesArray.length; i++){
			result.add(acceptedCurrenciesArray[i].trim());
		}

		return result;
	}

}