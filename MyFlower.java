import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.gl2.GLUT;
import java.util.ArrayList;
import java.util.Random;

public class MyFlower {
  private double posx;
  private double posy;
  private double posz;
  private double diameter;
  private double hue;
  boolean visibility = true;
  boolean dropped = false;
  boolean first_branch = false;
  //private float color[];
  //private float silver[];
  private float color[] = { 1.0f, 0.5f, 0.5f, 1.0f };
  private float silver[] = { 1.0f, 0.5f, 0.5f, 1.0f };

  //private Branch parent;

  Random r = new Random();

  MyFlower(double _x, double _y, double _z) {
    this.posx = _x;
    this.posy = _y;
    this.posz = _z;
    this.diameter = 1.0 + r.nextDouble();
    this.hue = 1.0 + r.nextDouble();
  }

  void setVisibility(boolean b) {
    visibility = b;
  }

  void setPos(double x, double y, double z) {
    posx = x;
    posy = y;
    posz = z;
  }

  void setFirst () {
    first_branch = true;
  }

  double[] getPos () {
    double [] p = {posx, posy, posz};
    return p;
  }

  void dropFlower() {
    //visibility = false;
    //GLUT glut = new GLUT();
    /*for (int i = 0; i < 10000000 ; ++i) {
      setPos(posx, posy - 0.00001, posz);
    }*/
    //posy = posy - 0.25;
    //glut.glutTimerFunc(1, timer, 0);
    dropped = true;
  }

  void reBloom(double x, double y, double z) {
    posx = x;
    posy = y;
    posz = z;
    dropped = false;

  }

  void display(GLAutoDrawable drawable) {
    if (visibility == true) {
      GL2 gl = drawable.getGL().getGL2();
      GLUT glut = new GLUT();
      //gl.glColor3f(255.0f/255.0f, 228.0f/255.0f, 225.0f/255.0f);
      gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, color, 0);
      gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, silver, 0);
      //need translation
      gl.glPushMatrix();
      gl.glTranslated(posx, posy, posz);

      if (first_branch != true) {
        glut.glutSolidSphere(0.1, 5, 10);
      }


      gl.glPopMatrix();

      if (dropped == true && 0.5 < posy) {
        posy = posy - 0.01;
      }

    }

  }
}
