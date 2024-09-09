package tetris;

public class Controller implements ModelListener, GameEventsListener {
	public Controller(Model model, View view) {
	this.model = model;
	model.addListener(this);
	this.view = view;
}

	private Model model;
	private View view;

	@Override
	public void foundNeighboursAt(int a, int b, int c, int d, int i, int j) {
		view.highlightNeighbours(a, b, c, d, i, j);
	}

	@Override
	public void figureMovedFrom(int oldX, int oldY) {
		view.moveAndDrawFigure(model.Fig, oldX, oldY);
	}

	@Override
	public void figureUpdated(Figure fig) {
		view.drawFigure(fig);
	}

	@Override
	public void gameOver() {

	}

	@Override
	public void fieldUpdated(int[][] newField) {
		view.drawField(newField);
	}

	@Override
	public void scoreHasChanged(int score) {
		view.showScore(score);
	}

	@Override
	public void levelHasChanged(int level) {
		view.showLevel(level);
	}


//	@Override
	public void onChange(Model model) {
		view.draw(model);
	}

	@Override
	public void slideDown() {
		model.trySlideDown();
	}

	@Override
	public void moveLeft() {
		model.tryMoveLeft();
	}

	@Override
	public void moveRight() {
		model.tryMoveRight();
	}


	@Override
	public void rotateUp() {
		model.rotateUp();

	}
	@Override
	public void rotateDown() {
		model.rotateDown();

	}
	@Override
	public void drop() {
		model.dropFigure(model.Fig);
	}

}
