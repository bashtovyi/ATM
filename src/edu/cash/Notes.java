package edu.cash;

import java.util.Comparator;

public class Notes implements Comparator<Notes> {
	private int nominal;
	private int count;

	public Notes(int nominal, int count) {
		this.nominal = nominal;
		this.count = count;
	}

	public int getNominal() {
		return nominal;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public String toString() {
		return nominal + " " + count;
	}

	@Override
	public int compare(Notes n1, Notes n2) {
		return n2.getNominal() - n1.getNominal();
	}
}
