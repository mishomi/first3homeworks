package tetris;

public interface GameEventsListener {
	
	void slideDown();
	void moveLeft();
	void moveRight();
	void rotateUp();
	void rotateDown();
	void drop();
	

}
