package com.technoelf.sweeper;

import java.util.ArrayList;

public class MineGenerator {
	private Field field;

	private ArrayList<Field> possibleFields;

	private int[] xIndex;
	private int[] yIndex;

	public MineGenerator(Field field) {
		this.field = field;

		xIndex = new int[field.countCells(Field.UNKNOWN)];
		yIndex = new int[xIndex.length];

		int i = 0;
		for(int y = 0; y < field.getHeight(); y++) {
			for (int x = 0; x < field.getWidth(); x++) {
				if (field.getCell(x, y) == Field.UNKNOWN) {
					xIndex[i] = x;
					yIndex[i] = y;
					i++;
				}
			}
		}

		possibleFields = new ArrayList<>();
		int fieldCount = (int) Math.pow(2, field.countCells(Field.UNKNOWN));
		for(i = 0; i < fieldCount; i++) {
			Field f = new Field(field);
			for(int j = 0; j < xIndex.length; j++) {
				f.setCell(xIndex[j], yIndex[j], (i & (1 << j)) != 0 ? Field.MINE : Field.EMPTY);
			}

			if(checkField(f)) {
				possibleFields.add(f);
			}
		}
	}

	private boolean checkField(Field field) {
		for(int y = 0; y < field.getHeight(); y++) {
			for(int x = 0; x < field.getWidth(); x++) {
				switch(field.getCell(x, y)) {
					case Field.UNKNOWN:
						return false;
					case Field.EMPTY:
						break;
					case Field.MINE:
						break;
					case 0:
						if(field.countCellsAround(Field.MINE, x, y) != 0) return false;
						break;
					case 1:
						if(field.countCellsAround(Field.MINE, x, y) != 1) return false;
						break;
					case 2:
						if(field.countCellsAround(Field.MINE, x, y) != 2) return false;
						break;
					case 3:
						if(field.countCellsAround(Field.MINE, x, y) != 3) return false;
						break;
					case 4:
						if(field.countCellsAround(Field.MINE, x, y) != 4) return false;
						break;
					case 5:
						if(field.countCellsAround(Field.MINE, x, y) != 5) return false;
						break;
					case 6:
						if(field.countCellsAround(Field.MINE, x, y) != 6) return false;
						break;
					case 7:
						if(field.countCellsAround(Field.MINE, x, y) != 7) return false;
						break;
					case 8:
						if(field.countCellsAround(Field.MINE, x, y) != 8) return false;
						break;
					default:
						break;
				}
			}
		}

		return true;
	}

	public ArrayList<Field> getPossibleFields() {
		return possibleFields;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < possibleFields.size(); i++) {
			s.append(possibleFields.get(i));
			s.append("\n");
		}
		return s.toString();
	}
}
