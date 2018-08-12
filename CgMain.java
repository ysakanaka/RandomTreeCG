/**
 * Main method
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;


public class CgMain extends JApplet {
	static int width = 600;
	static int height = 650;
	static Animator animator;

	/**
	 * Initialization of the application
	 */
	public void init() {
		setSize(new Dimension(width, height));

		CgCanvas canvas = new CgCanvas(width, height);
		canvas.requestFocus();
		GLCanvas glc = canvas.getGLCanvas();
		animator = new Animator(glc);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(glc, BorderLayout.CENTER);

		Container container = this.getContentPane();
		container.setLayout(new BorderLayout());
		container.add(panel, BorderLayout.CENTER);

		// Listener of keyboard events
		CgKeyListener mkl = new CgKeyListener(canvas);
		canvas.addKeyListener(mkl);
		glc.addKeyListener(mkl);

		CgDrawer drawer = canvas.getCgDrawer();

		// Listener of mouse events
		CgMouseListener mml = new CgMouseListener(canvas, animator, drawer);
		canvas.addMouseListener(mml);
		canvas.addMouseMotionListener(mml);
		glc.addMouseListener(mml);
		glc.addMouseMotionListener(mml);

		// Initialization of the scene
		MyScene.init();
	}

	/**
	 * Start the application
	 */
	public void start() {
	}

	/**
	 * Stop the application
	 */
	public void stop() {
	}


	/**
	 * The main method
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("I love CG!!!");
		frame.setSize(width, height);
		CgMain cgmain = new CgMain();

		cgmain.init();
		frame.getContentPane().add(cgmain);
		frame.setVisible(true);

		cgmain.start();

	    // Listener of the window
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Thread(new Runnable() {
                    public void run() {
                    	animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
	}

}
