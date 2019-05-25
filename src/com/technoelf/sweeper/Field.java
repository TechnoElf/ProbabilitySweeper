package com.technoelf.sweeper;

public class Field {
	private int width, height;
	private int[][] field;

	public static final int UNKNOWN = -1;
	public static final int MINE = -2;
	public static final int EMPTY = -3;

	public Field(int[][] field) {
		this.field = field;
		this.width = field[0].length;
		this.height = field.length;
	}

	public Field(Field field) {
		this.width = field.width;
		this.height = field.height;
		this.field = new int[height][width];

		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				setCell(x, y, field.getCell(x, y));
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getCell(int x, int y) {
		if(x >= 0 && x < width && y >= 0 && y < height) {
			return field[y][x];
		} else {
			return EMPTY;
		}
	}

	public void setCell(int x, int y, int v) {
		if(x >= 0 && x < width && y >= 0 && y < height) {
			field[y][x] = v;
		}
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Field(");
		s.append(width);
		s.append(", ");
		s.append(height);
		s.append(") = {\n");
		for (int y = 0; y < height - 1; y++) {
			s.append("\t{");
			for (int x = 0; x < width - 1; x++) {
				s.append(String.format("% d, ", getCell(x, y)));
			}
			s.append(String.format("% d},\n", getCell(width - 1, y)));
		}
		s.append("\t{");
		for (int x = 0; x < width - 1; x++) {
			s.append(String.format("% d, ", getCell(x, height - 1)));
		}
		s.append(String.format("% d}\n}", getCell(width - 1, height - 1)));
		return s.toString();
	}

	public int countCells(int type) {
		int n = 0;

		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				n += getCell(x, y) == type ? 1 : 0;
			}
		}

		return n;
	}

	public int countCellsAround(int type, int x, int y) {
		int n = 0;

		n += getCell(x + 1, y + 1) == type ? 1 : 0;
		n += getCell(x, y + 1) == type ? 1 : 0;
		n += getCell(x - 1, y + 1) == type ? 1 : 0;
		n += getCell(x + 1, y) == type ? 1 : 0;
		n += getCell(x - 1, y) == type ? 1 : 0;
		n += getCell(x + 1, y - 1) == type ? 1 : 0;
		n += getCell(x, y - 1) == type ? 1 : 0;
		n += getCell(x - 1, y - 1) == type ? 1 : 0;

		return n;
	}
}
