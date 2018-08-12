/**
 * Model of a scene
 */

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import java.util.ArrayList;


public class MyScene {

	// Models of a flag and a car
	static MyTree tree1 = null;
	static MyRabbit car1 = null;
	static ArrayList<MyTree> trees = new ArrayList<MyTree>();

	/**
	 * Initialization
	 */
	public static void init() {

		 // Allocate a flag
		 tree1 = new MyTree(0.0, 0.0);
		 tree1.init();
		 trees.add(tree1);

		 // Allocate a car
		 car1 = new MyRabbit();
		 car1.setColor(1.0, 0.0, 0.0);
		 car1.setVelocity(5);
		 car1.setTransform(1.5);

	}

	/**
	 * Draw the scene
	 */
	public static void draw(GLAutoDrawable drawable) {
		if(drawable == null) return;

		GL2 gl = drawable.getGL().getGL2();
		float color[] = { 0.3f, 0.5f, 0.2f, 1.0f };
	  float silver[] = { 0.3f, 0.5f, 0.2f, 1.0f };
		 gl.glLightModeli(GL2.GL_LIGHT_MODEL_TWO_SIDE, GL2.GL_TRUE);

		 // draw the tree
	    /*gl.glPushMatrix();
	    if(tree1 != null)
	    	tree1.draw(drawable);
	    gl.glPopMatrix();*/

			for (MyTree t : trees) {
				gl.glPushMatrix();
		    if(t != null)
		    	t.draw(drawable);
		    gl.glPopMatrix();
			}

			gl.glPushMatrix();
			gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, color, 0);
			gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, silver, 0);
			gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3f(-100.0f, 0.0f, 50.0f);
			gl.glVertex3f(100.0f, 0.0f, 50.0f);

			gl.glColor3f(34.0f/255.0f, 139.0f/255.0f, 34.0f/255.0f);
			gl.glVertex3f(-100.0f, 0.0f, -100.0f);
			gl.glVertex3f(100.0f, 0.0f, -100.0f);
			gl.glEnd();
			gl.glPopMatrix();

	}

	/**
	 * Reset the movement
	 */
	public static void resetMovement() {

		// Reset the position of the car
		car1.resetMovement();
	}

	public static void growTree() {
		//tree1.growTree();
		for (MyTree t : trees ) {
			t.growTree();
		}
	}

	public static void dropFlowers(double x, double y, double z) {
		//tree1.dropFlowers(x, y, z);
		for (MyTree t : trees ) {
			t.dropFlowers(x, y, z);
		}
	}

	public static void reBloom() {
		//tree1.reBloom();
		for (MyTree t : trees ) {
			t.reBloom();
		}
		}

	public static void generateTree(double x, double z) {
		MyTree tree = new MyTree(x, z);
		tree.init();
		trees.add(tree);

	}

}
