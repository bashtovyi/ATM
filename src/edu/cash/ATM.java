package edu.cash;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Currency;
import java.util.List;
import java.util.Set;

public  class ATM {
	public static void addCash(String[] argumentsArray,
			List<CurrencyBox> currencyBoxList) {
		String currency = argumentsArray[1];
		int nominal = Integer.valueOf(argumentsArray[2]);
		int count = Integer.valueOf(argumentsArray[3]);

		boolean isCurrencyInTheBox = false;
		for (CurrencyBox each : currencyBoxList) {
			if (each.getCurency().equals(currency)) {
				isCurrencyInTheBox = true;
				List<Notes> notesList = each.getNotesList();

				boolean isNotesInBox = false;
				for (Notes note : notesList) {
					if (note.getNominal() == nominal) {
						isNotesInBox = true;
						note.setCount(note.getCount() + count);
						break;
					}
				}

				if (!isNotesInBox) {
					Notes notes = new Notes(nominal, count);
					notesList.add(notes);
					Collections.sort(notesList, new Comparator<Notes>() {
						@Override
						public int compare(Notes n1, Notes n2) {
							return n2.getNominal() - n1.getNominal();

						}
					});
				}

				break;
			}
		}

		if (!isCurrencyInTheBox) {
			Notes notes = new Notes(nominal, count);
			List<Notes> notesList = new ArrayList<>();
			notesList.add(notes);
			CurrencyBox currencyBox = new CurrencyBox(currency, notesList);
			currencyBoxList.add(currencyBox);
		}
	}

	public static boolean isAddCash(String[] argumentsArray) {
		if (argumentsArray.length != 4) {
			return false;
		}
		if (!argumentsArray[0].equals("+")) {
			return false;
		}
		Set<Currency> currenciesSet = Currency.getAvailableCurrencies();
		boolean isCurrency = false;
		for (Currency each : currenciesSet) {
			if (each.getCurrencyCode().equals(argumentsArray[1])) {
				isCurrency = true;
				break;
			}
		}
		if (!isCurrency) {
			return false;
		}

		int nominal = 0;
		try {
			nominal = Integer.valueOf(argumentsArray[2]);
		} catch (NumberFormatException e) {
			return false;
		}
		if (nominal < 1) {
			return false;
		}
		boolean isNominal = false;
		for (int i = 0; i < 4; i++) {
			if (nominal == Math.pow(10, i) || nominal == Math.pow(10, i) * 5) {
				isNominal = true;
			}
		}
		if (!isNominal) {
			return false;
		}
		int count;
		try {
			count = Integer.valueOf(argumentsArray[3]);
		} catch (NumberFormatException e) {
			return false;
		}
		if (count < 1) {
			return false;
		}

		return true;
	}

	public static boolean isWithDraw(String[] argumentsArray) {
		if (argumentsArray.length != 3) {
			return false;
		}
		if (!argumentsArray[0].equals("-")) {
			return false;
		}
		Set<Currency> currenciesSet = Currency.getAvailableCurrencies();
		boolean isCurrency = false;
		for (Currency each : currenciesSet) {
			if (each.getCurrencyCode().equals(argumentsArray[1])) {
				isCurrency = true;
				break;
			}
		}
		if (!isCurrency) {
			return false;
		}
		int amount = 0;
		try {
			amount = Integer.valueOf(argumentsArray[2]);
		} catch (NumberFormatException e) {
			return false;
		}
		if (amount < 1) {
			return false;
		}

		return true;
	}

	public static void printBalance(List<CurrencyBox> currencyBoxList,
			PrintWriter printWriter) {
		for (CurrencyBox each : currencyBoxList) {
			String currency = each.getCurency();
			List<Notes> notesList = each.getNotesList();
			for (Notes note : notesList) {
				printWriter.println(currency + " " + note.getNominal() + " "
						+ note.getCount());
			}
		}
	}
}
