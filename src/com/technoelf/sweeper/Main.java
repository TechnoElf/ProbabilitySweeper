package com.technoelf.sweeper;

import static java.lang.System.out;

public class Main {
	static Field field = new Field(new int[][]
			{{-1, -1, -1, -1},
			 {-1,  1,  1, -1},
			 {-1, -1, -1, -1}});

	public static void main(String[] args) {
		MineGenerator generator = new MineGenerator(field);
		Evaluator evaluator = new Evaluator(generator);
		out.println(evaluator);
	}
}
