/**
* Listener for keyboard events
*/


import java.awt.event.*;

public class CgKeyListener implements KeyListener {
	CgCanvas canvas;


	public CgKeyListener(CgCanvas c) {
		canvas = c;
	}


	/**
	* Called when a key is pressed
	*/
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {



			// "Q": exit
			case KeyEvent.VK_Q:
			System.exit(0);
			break;

			// "R": reset
			case KeyEvent.VK_R:
			MyScene.resetMovement();
			canvas.display();
			break;

			case KeyEvent.VK_G:
			canvas.growTree();
			canvas.display();
			break;

			case KeyEvent.VK_T:
			canvas.generateTree();
			canvas.display();
			break;

			case KeyEvent.VK_D:
			canvas.dropFlowers();
			canvas.display();
			break;

			case KeyEvent.VK_F:
			canvas.reBloom();
			canvas.display();
			break;
		}

	}

	/**
	* Called when a key is released
	*/
	public void keyReleased(KeyEvent e) {

	}

	/**
	* Called when a key is typed
	*/
	public void keyTyped(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {

			// "Q": exit
			case KeyEvent.VK_Q:
			System.exit(0);
		}
	}
}
