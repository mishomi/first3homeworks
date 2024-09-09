package tetris;

import java.util.Random;

public class FigureFactory {

	public static int[][] createNextFigure() {
		Random randint = new Random();
		int randomNumber = randint.nextInt(7); // Get a random integer between 0 and 6

		switch (randomNumber) {
			case 0: return O();
			case 1: return J();
			case 2: return L();
			case 3: return I();
			case 4: return S();
			case 5: return Z();
			case 6: return T();
			default: throw new IllegalStateException("Unexpected value: " + randomNumber);
		}
	}

	static int[][] O() {
		return new int[][] {
			{0, 1, 1, 0},
			{0, 1, 1, 0},
			{0, 0, 0, 0},
			{0, 0, 0, 0},
		};
	}

	static int[][] J() {
		return new int[][] {
			{0, 0, 2, 0},
			{0, 0, 2, 0},
			{0, 2, 2, 0},
			{0, 0, 0, 0},
		};
	}
	static int[][] L() {
		return new int[][] {
				{0, 3, 0, 0},
				{0, 3, 0, 0},
				{0, 3, 3, 0},
				{0, 0, 0, 0},
		};
	}
	static int[][] I() {
		return new int[][] {
				{0, 4, 0, 0},
				{0, 4, 0, 0},
				{0, 4, 0, 0},
				{0, 0, 0, 0},
		};
	}
	static int[][] S() {
		return new int[][] {
				{0, 0, 0, 0},
				{0, 5, 5, 0},
				{5, 5, 0, 0},
				{0, 0, 0, 0},
		};
	}
	static int[][] Z() {
		return new int[][] {
				{0, 0, 0, 0},
				{6, 6, 0, 0},
				{0, 6, 6, 0},
				{0, 0, 0, 0},
		};
	}
	static int[][] T() {
		return new int[][] {
				{0, 0, 0, 0},
				{7, 7, 7, 0},
				{0, 7, 0, 0},
				{0, 0, 0, 0},
		};
	}

}
