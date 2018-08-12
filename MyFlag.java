/**
 * Model of a flag
 */

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class MyFlag {

	// Positions of vertices
	double vertex3[][] = {
		  { -0.02, 0.0, -0.02 },
		  {  0.02, 0.0, -0.02 },
		  {  0.02, 1.0, -0.02 },
		  { -0.02, 1.0, -0.02 },
		  { -0.02, 0.0,  0.02 },
		  {  0.02, 0.0,  0.02 },
		  {  0.02, 1.0,  0.02 },
		  { -0.02, 1.0,  0.02 }
	};

	// Positions of vertices
	double vertex4[][] = {
			{ 0.5, 0.8, -0.5},
			{ 0.0, 1.0, 0.0},
			{ 0.0, 0.6, 0.0}
	};

	// IDs of vertices of faces
	int face[][] = {
		  { 0, 3, 2, 1 },
		  { 1, 2, 6, 5 },
		  { 5, 6, 7, 4 },
		  { 4, 7, 3, 0 },
		  { 4, 0, 1, 5 },
		  { 3, 7, 6, 2 }
	};

	// Normal vector of vertices
	double normal[][] = {
		  { 0.0, 0.0, 1.0 },
		  {-1.0, 0.0, 0.0 },
		  { 0.0, 0.0,-1.0 },
		  { 1.0, 0.0, 0.0 },
		  { 0.0, 1.0, 0.0 },
		  { 0.0,-1.0, 0.0 }
	};


	// Colors
	float red[] = { 0.8f, 0.0f, 0.0f, 1.0f };
	float blue[] = { 0.2f, 0.2f, 0.8f, 1.0f };
	float silver[] = { 0.5f, 0.5f, 0.5f, 1.0f };

	/**
	 * Draw the car
	 */
	public void draw(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();

		// Set reflection coefficients
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, blue, 0);
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, silver, 0);

		// Draw a box
		for (int j = 0; j < 6; ++j) {
			gl.glBegin(GL2.GL_POLYGON);
			gl.glNormal3dv(normal[j], 0);
			for (int i = 0; i < 4; ++i) {
				gl.glVertex3dv(vertex3[face[j][i]], 0);
			}
			gl.glEnd();
		}

		// Set reflection coefficients
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, red, 0);

		// Draw a triangle
		gl.glBegin(GL2.GL_POLYGON);
		for (int i = 0; i < 3; ++i) {
			gl.glVertex3dv(vertex4[i], 0);
		}
		gl.glEnd();
	}

}
