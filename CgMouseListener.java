/**
 * Listener for mouse events
 */


import java.awt.event.*;
import com.jogamp.opengl.util.Animator;


public class CgMouseListener implements MouseListener, MouseMotionListener {
	CgCanvas canvas;
	Animator animator;
	CgDrawer drawer;
	int x1, x2, y1, y2, x3, y3, z3;


	public CgMouseListener(CgCanvas c, Animator a, CgDrawer d) {
		canvas = c;
		animator = a;
		drawer = d;

	}


    /**
     * Called when cursor is moved
     */
    public void mouseMoved(MouseEvent e) {

    }

    /**
     * Called when cursor is entered
     */
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Called when cursor is exited
     */
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Called when cursor is clicked
     */
    public void mouseClicked(MouseEvent e) {
			//canvas.growTree();
    }

    /**
     * Called when cursor is dragged
     */
    public void mouseDragged(MouseEvent e) {
			x2 = e.getX();
			y2 = e.getY();
			if (e.getModifiersEx() == 1152) {
				canvas.lookAtPosChanged((double)(x2 - x1), 0.0, (double)(y2 - y1));
			} else if (e.getModifiersEx() == 1088) {
				canvas.eyePosChanged((double)(x2 - x1), (y2 - y1), 0.0);
			} else {
				canvas.lookAtPosChanged((double)(x2 - x1), (double)(y2 - y1), 0.0);
			}
			//System.out.println("point is");
			//System.out.println((double)(x2-x1));
			x1 = e.getX();
			y1 = e.getY();
    }

    /**
     * Called when cursor is pressed
     */
    public void mousePressed(MouseEvent e) {
    	animator.start();
			//System.out.println(e.getModifiersEx());
			x1 = x2 = e.getX();
			y1 = y2 = e.getY();
			//canvas.dropFlowers(x1, y2, 0.0);
    }

    /**
     * Called when cursor is released
     */
    public void mouseReleased(MouseEvent e) {
    	//animator.stop();
    }


}
