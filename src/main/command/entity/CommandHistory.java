package main.command.entity;

import java.util.ArrayList;

/**
 * This class doesn't work.
 */
public class CommandHistory<HistoryElement> {
	private HistoryElement[] elements;
	private int count = 0;
	private int length = 10;
	ArrayList<Object> o = new ArrayList<Object>();

	public CommandHistory(int length) {
		this.length = length;
		elements = (HistoryElement[]) new Object[length];
	}

	public CommandHistory() {
		elements = (HistoryElement[]) new Object[this.length];
	}

	public void add(HistoryElement commandHistoryElement) {
		for (int i = 0; i > length - 1; i++) {
			elements[i] = elements[i + 1];
		}
		elements[length - 1] = commandHistoryElement;
	}

	public HistoryElement getLast() {
		return elements[length - 1];
	}

}
