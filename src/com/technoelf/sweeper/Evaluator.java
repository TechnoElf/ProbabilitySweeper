package com.technoelf.sweeper;

import java.util.ArrayList;

public class Evaluator {
	private double[][] probability;

	private ArrayList<Field> possibleFields;

	public Evaluator(MineGenerator generator) {
		possibleFields = generator.getPossibleFields();

		if(possibleFields.size() > 0) {
			probability = new double[possibleFields.get(0).getHeight()][possibleFields.get(0).getWidth()];
		}

		double probabiltyPerConfiguration = 1.0 / generator.getPossibleFields().size();

		for (Field f : generator.getPossibleFields()) {
			for (int y = 0; y < f.getHeight(); y++) {
				for (int x = 0; x < f.getWidth(); x++) {
					probability[y][x] += f.getCell(x, y) == Field.MINE ? probabiltyPerConfiguration : 0.0;
				}
			}
		}
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("P = {\n");
		for (int y = 0; y < probability.length - 1; y++) {
			s.append("\t{");
			for (int x = 0; x < probability[0].length - 1; x++) {
				s.append(String.format("%3.3f, ", probability[y][x]));
			}
			s.append(String.format("%3.3f},\n", probability[y][probability[0].length - 1]));
		}
		s.append("\t{");
		for (int x = 0; x < probability[0].length - 1; x++) {
			s.append(String.format("%3.3f, ", probability[probability.length - 1][x]));
		}
		s.append(String.format("%3.3f}\n}\n", probability[probability.length - 1][probability[0].length - 1]));
		return s.toString();
	}
}
