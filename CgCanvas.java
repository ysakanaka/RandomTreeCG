/**
 * Window panel to draw the scene
 */

import javax.swing.*;

import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.awt.GLCanvas;



public class CgCanvas extends JPanel {
	static GLAutoDrawable drawable = null;
	GLCanvas glc;
	CgDrawer drawer;
	int width, height;



	public CgCanvas(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		setSize(width, height);
		glc = new GLCanvas();
		drawer = new CgDrawer();
		glc.addGLEventListener(drawer);
	}


	public GLCanvas getGLCanvas() {
		return glc;
	}
  public CgDrawer getCgDrawer() {
    return drawer;
  }

  public void lookAtPosChanged(double x, double y, double z) {
    drawer.lookAtPosChanged(x, y, z);
  }

	public void eyePosChanged(double x, double y, double z) {
		drawer.eyePosChanged(x, y, z);
	}

  public void growTree() {
    drawer.growTree();
  }

	public void dropFlowers() {
		drawer.dropFlowers();
	}

	public void generateTree() {
		drawer.generateTree();
	}

	public void reBloom() {
		drawer.reBloom();
		}

	public void display() {
		if (drawer == null) return;
		GLAutoDrawable  glAD = drawer.getGLAutoDrawable();
		if (glAD == null) return;
		glAD.display();
	}

}
