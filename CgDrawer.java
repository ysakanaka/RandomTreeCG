/**
* Drawing functions
*/


import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.glu.gl2.GLUgl2;
import com.jogamp.opengl.util.gl2.GLUT;


public class CgDrawer implements GLEventListener {
	GLAutoDrawable glAD;

	// Positions of light sources
	static float light0pos[] = { 0.0f, 13.0f, 0.0f, 1.0f };
	static float light1pos[] = { 5.0f, 3.0f, 3.0f, 1.0f };
	double eyex, eyey, eyez, posx, posy, posz;

	/*private void mygl_GradientBackground(GLAutoDrawablefloat top_r, float top_g, float top_b, float top_a, float bot_r, float bot_g, float bot_b, float bot_a) {
	gl.glDisable(GL.GL_DEPTH_TEST);

	//static GLuint background_vao = 0;
}*/

/**
* Initialization of drawing setting
*/
public void init(GLAutoDrawable drawable) {
	float silver[] = {0.5f, 0.5f, 0.5f, 1.0f};

	float top_r = 0.6f;
	float top_g = 0.8f;
	float top_b = 0.3f;
	float top_a = 1.0f;
	float bot_r = 0.3f;
	float bot_g = 0.0f;
	float bot_b = 0.6f;
	float bot_a = 1.0f;

	eyex = 0.5;
	eyey = 0.5;
	eyez = 20.0;
	posx = 0;
	posy = 0;
	posz = 0;

	this.glAD = drawable;

	GL2 gl= drawable.getGL().getGL2();

	// Initialization of OpenGL setting
	gl.glEnable(GL.GL_RGBA);
	gl.glEnable(GL2.GL_DEPTH);
	gl.glEnable(GL2.GL_DOUBLE);
	gl.glEnable(GL.GL_DEPTH_TEST);
	gl.glEnable(GL2.GL_NORMALIZE);
	gl.glEnable(GL.GL_CULL_FACE);
	gl.glCullFace(GL.GL_BACK);

	// Initialization of light sources
	gl.glEnable(GL2.GL_LIGHTING);
	gl.glEnable(GL2.GL_LIGHT0);
	gl.glEnable(GL2.GL_LIGHT1);
	gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, silver, 0);
	gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, silver, 0);

	float red = 240.0f/255.0f;
	float green = 248.0f/255.0f;
	float blue = 255.0f/255.0f;

	// Specification of background color
	gl.glClearColor(red, green, blue, 1.0f);
	/*gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

	//mygl_GradientBackground
	gl.glDisable(GL.GL_DEPTH_TEST);

	gl.glMatrixMode(GL2.GL_PROJECTION);
	gl.glLoadIdentity();

	gl.glMatrixMode(GL2.GL_MODELVIEW);
	gl.glLoadIdentity();
	gl.glDisable(GL2.GL_LIGHTING);

	gl.glBegin(GL2.GL_QUADS);
	gl.glColor3f(1.0f, 0.0f, 0.0f);
	gl.glVertex2f(-1.0f, 1.0f);
	gl.glVertex2f(-1.0f, -1.0f);

	gl.glColor3f(0.0f, 0.0f, 1.0f);
	gl.glVertex2f(1.0f, -1.0f);
	gl.glVertex2f(1.0f, 1.0f);
	gl.glEnd();
	gl.glEnable(GL2.GL_LIGHTING);*/

	/*gl.glMatrixMode(GL2.GL_PROJECTION);
	gl.glPushMatrix();
	gl.glLoadIdentity();

	gl.glMatrixMode(GL2.GL_MODELVIEW);
	gl.glLoadIdentity();
	gl.glDisable(GL2.GL_LIGHTING);
	gl.glBegin(GL2.GL_POLYGON);
	//red color
	gl.glColor3f(1.0f,0.0f,0.0f);
	gl.glVertex2f(-1.0f,-1.0f);
	gl.glVertex2f(1.0f,-1.0f);
	//blue color
	gl.glColor3f(0.0f,0.0f,1.0f);
	gl.glVertex2f(1.0f, 1.0f);
	gl.glVertex2f(-1.0f, 1.0f);
	gl.glEnd();

	gl.glMatrixMode(GL2.GL_PROJECTION);
	gl.glPopMatrix();
	gl.glMatrixMode(GL2.GL_MODELVIEW);*/


	gl.glEnable(GL.GL_DEPTH_TEST);


}

/**
* Called when the shape of the window is modified
*/
public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
	GL2 gl = drawable.getGL().getGL2();
	GLUgl2 glu = new GLUgl2();

	if (height <= 0)
	height = 1;
	float h = (float) width / (float) height;

	// Set the viewport
	gl.glViewport(0, 0, width, height);

	// Set the matrix for coordinate system transformation
	gl.glMatrixMode(GL2.GL_PROJECTION);
	gl.glLoadIdentity();
	glu.gluPerspective(30.0, h, 1.0, 100.0);

	// Set the matrix for object transformation
	gl.glMatrixMode(GL2.GL_MODELVIEW);
	gl.glLoadIdentity();


}


/**
* Called when redraw is needed
*/
public void display(GLAutoDrawable drawable) {
	draw(drawable);
}


/**
* Dummy function (DO NOT remove)
*/
public void displayChanged(
GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
	;
}

public void lookAtPosChanged(double x, double y, double z) {
	posx -= x * 0.1;

	posz += z * 0.1;

	if (0.05 < posy + y * 0.1) {
		posy += y * 0.1;
	}

}

public void eyePosChanged(double x, double y, double z) {
	eyex -= x * 0.1;
	eyez += z * 0.1;

	if (0.05 < eyey + y * 0.1) {
		eyey += y * 0.1;
	}
}

public void growTree() {
	MyScene.growTree();
}

public void dropFlowers() {
	MyScene.dropFlowers(posx, posy, posz);
}

public void reBloom() {
	MyScene.reBloom();
	}

public void generateTree() {
	MyScene.generateTree(posx, posz);
}

/**
* Called when redraw is needed
*/
public void draw(GLAutoDrawable drawable) {
	GL2 gl = drawable.getGL().getGL2();
	GLUgl2 glu = new GLUgl2();

	// Clear the scene
	gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

	// Set the viewpoint
	gl.glLoadIdentity();
	glu.gluLookAt(eyex, eyey, eyez, posx, posy, posz, 0.0, 1.0, 0.0);
	//System.out.println(posz);

	// Set the positions of light sources
	gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, light0pos, 0);
	gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, light1pos, 0);

	// Draw
	MyScene.draw(drawable);

}


public GLAutoDrawable getGLAutoDrawable() {
	return glAD;
}

@Override
public void dispose(GLAutoDrawable arg0) {
	// TODO Auto-generated method stub

}

}
