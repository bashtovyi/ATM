package edu.cash;

import java.util.List;


public class CurrencyBox  {
	private String currency;
	private List<Notes> notesList;

	public CurrencyBox(String currency, List<Notes> notesList) {
		this.currency = currency;
		this.notesList = notesList;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCurency() {
		return currency;
	}

	public void setNotesList(List<Notes> notesList) {
		this.notesList = notesList;
	}

	public List<Notes> getNotesList() {
		return notesList;
	}

	public String toString() {
		return currency + " " + notesList;
	}

	
	
}
