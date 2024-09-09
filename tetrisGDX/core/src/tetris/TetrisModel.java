package tetris;

import javax.swing.*;
import kiu.tetris.TetrisStage;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class TetrisModel implements GameEventsListener {
	TetrisStage stage = new TetrisStage();
	private boolean gameOverFlag = false;

	public static final int DEFAULT_HEIGHT = 20;
	public static final int DEFAULT_WIDTH = 10;
	public static final int DEFAULT_COLORS_NUMBER = 7;

	int maxColors;
	public TetrisState state = new TetrisState();
	final List<ModelListener> listeners = new ArrayList<>();

	public void addListener(ModelListener listener) {
		listeners.add(listener);
	}

	public void removeListener(ModelListener listener) {
		listeners.remove(listener);
	}

	public TetrisModel() {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_COLORS_NUMBER);
	}

	public TetrisModel(int width, int height, int maxColors) {
		this.state.width = width;
		this.state.height = height;
		this.maxColors = maxColors;
		state.field = new int[height][width];
		initFigure();
	}

	private void initFigure() {
		state.figure = FigureFactory.createNextFigure();
		state.position = new Pair(state.width / 2 - 2, 0);
	}

	public Pair size() {
		return new Pair(state.width, state.height);
	}

	@Override
	public void slideDown() {
		if (gameOverFlag) return;

		var newPosition = new Pair(state.position.x(), state.position.y() + 1);
		if (isNewFiguresPositionValid(newPosition)) {
			state.position = newPosition;
			notifyListeners();
		} else {
			pasteFigure();
			fullRowRemoval();
			initFigure();
			notifyListeners();
			if (!isNewFiguresPositionValid(state.position)) {
				gameOver();
			}
		}
	}

	private void notifyListeners() {
		listeners.forEach(listener -> listener.onChange(this));
	}

	private void gameOver() {
		if (!gameOverFlag) {
			gameOverFlag = true;
			notifyListeners();
			SwingUtilities.invokeLater(() -> {
				JOptionPane.showMessageDialog(null, "Game Over", "Game Over", JOptionPane.INFORMATION_MESSAGE);
			});
		}
	}

	private void restartGame() {
		state = new TetrisState();
		stage = new TetrisStage();
		gameOverFlag = false;
		initFigure();
		notifyListeners();
	}

	public boolean isGameOver() {
		return gameOverFlag;
	}

	@Override
	public void moveLeft() {
		if (gameOverFlag) return;

		var newPosition = new Pair(state.position.x() - 1, state.position.y());
		if (isNewFiguresPositionValid(newPosition)) {
			state.position = newPosition;
			notifyListeners();
		}
	}

	@Override
	public void moveRight() {
		if (gameOverFlag) return;

		var newPosition = new Pair(state.position.x() + 1, state.position.y());
		if (isNewFiguresPositionValid(newPosition)) {
			state.position = newPosition;
			notifyListeners();
		}
	}

	@Override
	public void rotate() {
		if (gameOverFlag) return;

		int[][] rotatedFigure = new int[4][4];
		for (int r = 0; r < state.figure.length; r++) {
			for (int c = 0; c < state.figure[r].length; c++) {
				rotatedFigure[c][3 - r] = state.figure[r][c];
			}
		}

		int[][] originalFigure = state.figure;
		state.figure = rotatedFigure;

		if (!isNewFiguresPositionValid(state.position)) {
			state.figure = originalFigure;
		}
		notifyListeners();
	}

	@Override
	public void drop() {
		if (gameOverFlag) return;

		while (isNewFiguresPositionValid(new Pair(state.position.x(), state.position.y() + 1))) {
			state.position = new Pair(state.position.x(), state.position.y() + 1);
		}
		slideDown();
		notifyListeners();
	}

	public void deleteRow(int lowestRow) {
		for (int i = 0; i < state.width; i++) {
			state.field[lowestRow][i] = 0;
		}
	}

	public void dropRows(int lowestRow) {
		deleteRow(lowestRow);
		state.score += 10;
		stage.changeSpeed();
		for (int i = lowestRow; i > 0; i--) {
			for (int j = 0; j < state.width; j++) {
				state.field[i][j] = state.field[i - 1][j];
			}
		}
		for (int j = 0; j < state.width; j++) {
			state.field[0][j] = 0;
		}
	}

	public void fullRowRemoval() {
		for (int j = state.height - 1; j >= 0; j--) {
			boolean checkIfFullLine = true;
			for (int i = 0; i < state.width; i++) {
				if (state.field[j][i] == 0) {
					checkIfFullLine = false;
					break;
				}
			}
			if (checkIfFullLine) {
				dropRows(j);
				j++;
			}
		}
	}

	public boolean isNewFiguresPositionValid(Pair newPosition) {
		boolean[] result = new boolean[1];
		result[0] = true;

		walkThroughAllFigureCells(newPosition, (absPos, relPos) -> {
			if (result[0]) {
				result[0] = checkAbsPos(absPos);
			}
		});

		return result[0];
	}

	private void walkThroughAllFigureCells(Pair newPosition, BiConsumer<Pair, Pair> payload) {
		for (int row = 0; row < state.figure.length; row++) {
			for (int col = 0; col < state.figure[row].length; col++) {
				if (state.figure[row][col] == 0)
					continue;
				int absCol = newPosition.x() + col;
				int absRow = newPosition.y() + row;
				payload.accept(new Pair(absCol, absRow), new Pair(col, row));
			}
		}
	}

	private boolean checkAbsPos(Pair absPos) {
		var absCol = absPos.x();
		var absRow = absPos.y();
		if (isColumnPositionOutOfBoundaries(absCol))
			return false;
		if (isRowPositionOutOfBoundaries(absRow))
			return false;
		if (state.field[absRow][absCol] != 0)
			return false;
		return true;
	}

	private boolean isRowPositionOutOfBoundaries(int absRow) {
		return absRow < 0 || absRow >= state.height;
	}

	private boolean isColumnPositionOutOfBoundaries(int absCol) {
		return absCol < 0 || absCol >= state.width;
	}

	public void pasteFigure() {
		walkThroughAllFigureCells(state.position, (absPos, relPos) -> {
			state.field[absPos.y()][absPos.x()] = state.figure[relPos.y()][relPos.x()];
		});
	}
}
