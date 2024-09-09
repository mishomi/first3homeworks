//package tetris;
//
//import java.awt.Color;
//import java.awt.Event;
//import java.awt.Graphics;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//public class Columns {
//
////	static final int TimeShift = 250;
////	static final int MinTimeShift = 200;
////
////	Model model = new Model();
////	View view = new View();
////	private ScheduledExecutorService timer;
//
//
//	//-----------------------------< Applet methods implementations >--------------------
//
//
//	//-----------------------------< ModelListener implementations >--------------------
////
////	@Override
////	public void foundNeighboursAt(int a, int b, int c, int d, int i, int j) {
////		view.highlightNeighbours(a, b, c, d, i, j);
////	}
////
////	@Override
////	public void figureMovedFrom(int oldX, int oldY) {
////		view.moveAndDrawFigure(model.Fig, oldX, oldY);
////	}
////
////	@Override
////	public void figureUpdated(Figure fig) {
////		view.drawFigure(fig);
////	}
////
////	@Override
////	public void fieldUpdated(int[][] newField) {
////		view.drawField(newField);
////	}
////
////	@Override
////	public void scoreHasChanged(int score) {
////		view.showScore(score);
////	}
//
////	@Override
////	public void levelHasChanged(int level) {
////		view.showLevel(level);
////	}
//
////	public boolean lostFocus(Event e, Object w) {
////		processUserActions('P');
////		return true;
////	}
//
////	@Override
////	public void gameOver() {
////		// TODO HomeWork
////
////	}
//
////	private void processUserActions(int keyPressedCode) {
////			switch (keyPressedCode) {
////			case Event.LEFT:
////				model.tryMoveLeft();
////				break;
////			case Event.RIGHT:
////				model.tryMoveRight();
////				break;
////			case Event.UP:
////				model.rotateUp();
////				break;
////			case Event.DOWN:
////				model.rotateDown();
////				break;
////			case ' ':
////				model.dropFigure(model.Fig);
////				break;
//
//	// TODO:  HomeWork:  move to a separate thread
////			case 'P':
////			case 'p':
////				while (!keyPressed) {
////					int oldX3 = model.Fig.x;
////					view.hideFigure(oldX3, oldY);
////					Delay(500);
////					view.drawFigure(model.Fig);
////					Delay(500);
////				}
////				lastTimeFigureMoved = System.currentTimeMillis();
////				break;
//
//	// TODO:  HomeWork
////			case '-':
////				if (model.level > 0)
////					model.descreaseLevel();
////				model.removedCellsCounter = 0;
////				view.showLevel(model.level);
////				break;
////			case '+':
////				if (model.level < Model.MaxLevel)
////					model.increaseLevel();
////				model.removedCellsCounter = 0;
////				view.showLevel(model.level);
////				break;
//}