import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.gl2.GLUT;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

class Branch {
  private double start[] = {0.0, 0.0, 0.0};
  private double end[] = {0.0, 0.0, 0.0};
  private double thick;
  private Branch parent = null;
  private int level;
  MyFlower flower = null;
  float color[] = { 139.0f/255.0f, 69.0f/255.0f, 19.0f/255.0f, 1.0f };
  float silver[] = { 139.0f/255.0f, 69.0f/255.0f, 19.0f/255.0f, 1.0f };

  float color2[] = { 57.0f/255.0f, 25.0f/255.0f, 9.0f/255.0f, 1.0f };
  float silver2[] = { 57.0f/255.0f, 25.0f/255.0f, 9.0f/255.0f, 1.0f };

  // Positions of vertices
  double vertex1[][] = {
    { -0.5, 0.0,  0.0 },
    {  0.5, 0.0,  0.0 },
    {  0.5, 0.25, 0.0 },
    { -0.5, 0.25, 0.0 },
    { -0.5, 0.0,  0.5 },
    {  0.5, 0.0,  0.5 },
    {  0.5, 0.25, 0.5 },
    { -0.5, 0.25, 0.5 }
  };

  // Positions of vertices
  double vertex2[][] = {
    { -0.3, 0.0, -0.25 },
    {  0.3, 0.0, -0.25 },
    {  0.2, 2.5, -0.25 },
    { -0.2, 2.5, -0.25 },
    { -0.3, 0.0, 0.45 },
    {  0.3, 0.0, 0.45 },
    {  0.2, 2.5, 0.45 },
    { -0.2, 2.5, 0.45 }
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

  Branch(double v[][], int f[][], double n[][], int lv) {
    for (int j = 0; j < 6 ; ++j ) {
      for (int i = 0; i < 4  ; ++i ) {
        face[j][i] = f[j][i];
      }
    }

    for (int j = 0; j < 6 ; ++j ) {
      for (int i = 0; i < 3  ; ++i ) {
        normal[j][i] = n[j][i];
      }
    }

    for (int j = 0; j < 8 ; ++j ) {
      for (int i = 0; i < 3 ; ++i ) {
        vertex2[j][i] = v[j][i];
      }
    }

    level = lv;

    flower = new MyFlower((vertex2[2][0] + vertex2[6][0]) / 2, (vertex2[2][1] + vertex2[6][1]) / 2, (vertex2[2][2] + vertex2[6][2]) / 2);
  }
  public double[][] getVertex() {
    return vertex2;
  }

  public int[][] getFace() {
    return face;
  }

  public double[][] getNormal() {
    return normal;
  }

  public int getLevel() {
    return level;
  }

  double[] getFlowerPos() {
    return flower.getPos();
  }

  public void dropFlowers() {
    flower.dropFlower();
  }

  public void setFirst() {
    flower.setFirst();
  }

  public void reBloom() {
    flower.reBloom((vertex2[2][0] + vertex2[6][0]) / 2, (vertex2[2][1] + vertex2[6][1]) / 2, (vertex2[2][2] + vertex2[6][2]) / 2);
  }

  void display(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    GLUT glut = new GLUT();
    gl.glPushMatrix();
    gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, color2, 0);
    gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, silver2, 0);

    for (int j = 0; j < 6; ++j) {
      gl.glBegin(GL2.GL_POLYGON);
      gl.glNormal3dv(normal[j], 0);
      for (int i = 0; i < 4; ++i) {
        gl.glVertex3dv(vertex2[face[j][i]], 0);
      }
      gl.glEnd();
    }
    gl.glPopMatrix();

    flower.display(drawable);
  }
}

public class MyTree {
  static Branch branch = null;
  int tree_level = 0;
  double posx;
  double posz;
  // Positions of vertices
  static double v[][] = {
    { -0.6, 0.0, -0.45 },
    {  0.6, 0.0, -0.45 },
    {  0.2, 2.5, -0.25 },
    { -0.2, 2.5, -0.25 },
    { -0.3, 0.0, 0.35 },
    {  0.3, 0.0, 0.35 },
    {  0.2, 2.5, 0.25 },
    { -0.2, 2.5, 0.25 }
  };

  // IDs of vertices of faces
  static int f[][] = {
    { 0, 3, 2, 1 },
    { 1, 2, 6, 5 },
    { 5, 6, 7, 4 },
    { 4, 7, 3, 0 },
    { 4, 0, 1, 5 },
    { 3, 7, 6, 2 }
  };

  // Normal vector of vertices
  static double n[][] = {
    { 0.0, 0.0, 1.0 },
    {-1.0, 0.0, 0.0 },
    { 0.0, 0.0,-1.0 },
    { 1.0, 0.0, 0.0 },
    { 0.0, 1.0, 0.0 },
    { 0.0,-1.0, 0.0 }
  };

  MyTree(double x, double z) {
    posx = x;
    posz = z;
  }
  static Random r = new Random();
  public void init() {
    branch = new Branch(v, f, n, 0);
    branch.setFirst();
    subDivide(branch);
  }

  ArrayList<Branch> branches = new ArrayList<Branch>();

  public void addBranche(Branch b) {
    branches.add(b);
  }

  double[][] initializeVertex(double[][] v) {
    for (int j = 0; j < 8 ; ++j ) {
      for (int i = 0 ; i < 3 ; ++i ) {
        v[j][i] = 0.0;
      }
    }
    return v;
  }

  double[][] reduct(double[][] original_v, int divpoint, int level) {
    double reducted_v [][] = {
      { -0.6, 0.0, -0.45 },
      {  0.6, 0.0, -0.45 },
      {  0.2, 2.5, -0.45 },
      { -0.2, 2.5, -0.45 },
      { -0.3, 0.0, 0.45 },
      {  0.3, 0.0, 0.45 },
      {  0.2, 2.5, 0.45 },
      { -0.2, 2.5, 0.45 }
    };

    double rotation = 0;
    double resize_l = 1;
    //divpoint = r.nextInt(2)+2;
    int rot_direction = r.nextInt(2);

    if (rot_direction == 0) {
      rotation = -0.2 * r.nextInt(10);
    } else {
      rotation = 0.2 * r.nextInt(10);
    }

    if (level%3 == 0) {
      resize_l = 1.2;
    } else if (level%5 == 0) {
      resize_l = 1.0;
    } else {
      resize_l = 0.7;
    }
    for (int i = 0; i < 3 ; ++i ) {
      reducted_v[0][i] = (original_v[0][i] + original_v[2][i])/divpoint;
      reducted_v[1][i] = (original_v[1][i] + original_v[3][i])/divpoint;
      reducted_v[4][i] = (original_v[4][i] + original_v[6][i])/divpoint;
      reducted_v[5][i] = (original_v[5][i] + original_v[7][i])/divpoint;
    }

    for (int i = 0; i < 3 ; ++i ) {
      reducted_v[2][i] = original_v[2][i] * resize_l + rotation;
      reducted_v[3][i] = original_v[3][i] * resize_l + rotation;
      reducted_v[6][i] = original_v[6][i] * resize_l + rotation;
      reducted_v[7][i] = original_v[7][i] * resize_l + rotation;
    }

      reducted_v[2][2] = original_v[2][2] * 0.3 + rotation;
      reducted_v[3][2] = original_v[3][2] * 0.3 + rotation;
      reducted_v[6][2] = original_v[6][2] * 0.3 + rotation;
      reducted_v[7][2] = original_v[7][2] * 0.3 + rotation;

    reducted_v[2][1] += 1.5;
    reducted_v[3][1] += 1.5;
    reducted_v[6][1] += 1.5;
    reducted_v[7][1] += 1.5;

    if (level%3 == 0) {

      reducted_v[2][0] -= 0.5;
      reducted_v[3][0] -= 0.5;
      reducted_v[6][0] -= 0.5;
      reducted_v[7][0] -= 0.5;
    }

    if (level%5 == 0) {

      reducted_v[2][0] += 1.5;
      reducted_v[3][0] += 1.5;
      reducted_v[6][0] += 1.5;
      reducted_v[7][0] += 1.5;
    }

    if (level%7 == 0) {

      reducted_v[2][2] += 1.5;
      reducted_v[3][2] += 1.5;
      reducted_v[6][2] += 1.5;
      reducted_v[7][2] += 1.5;
    } else if (level%7 == 1) {
      reducted_v[2][2] -= 1.5;
      reducted_v[3][2] -= 1.5;
      reducted_v[6][2] -= 1.5;
      reducted_v[7][2] -= 1.5;
    } else {
      reducted_v[2][2] -= 0.3;
      reducted_v[3][2] -= 0.3;
      reducted_v[6][2] -= 0.3;
      reducted_v[7][2] -= 0.3;
    }







    return reducted_v;
  }

  double[][] setBranchRoot(double[][] original_v) {
    return original_v;
  }

  void subDivide(Branch parent) {
    ArrayList<Branch> newBranches = new ArrayList<Branch>();
    int newBranchAxis = 0;
    int p_level = parent.getLevel();
    if (p_level == 0) {
      newBranchAxis = 3;
    } else {
      newBranchAxis = r.nextInt(3);
    }
    //System.out.println(newBranchAxis);



    // Positions of vertices
    double v2[][] = {
      { -0.3, 0.0, -0.25 },
      {  0.3, 0.0, -0.25 },
      {  0.2, 2.5, -0.25 },
      { -0.2, 2.5, -0.25 },
      { -0.3, 0.0, 0.25 },
      {  0.3, 0.0, 0.25 },
      {  0.2, 2.5, 0.25 },
      { -0.2, 2.5, 0.25 }
    };

    double v3[][] = {
      { -0.3, 0.0, -0.25 },
      {  0.3, 0.0, -0.25 },
      {  0.2, 2.5, -0.25 },
      { -0.2, 2.5, -0.25 },
      { -0.3, 0.0, 0.25 },
      {  0.3, 0.0, 0.25 },
      {  0.2, 2.5, 0.25 },
      { -0.2, 2.5, 0.25 }
    };

    double v4[][] = {
      { -0.3, 0.0, -0.25 },
      {  0.3, 0.0, -0.25 },
      {  0.2, 2.5, -0.25 },
      { -0.2, 2.5, -0.25 },
      { -0.3, 0.0, 0.45 },
      {  0.3, 0.0, 0.45 },
      {  0.2, 2.5, 0.45 },
      { -0.2, 2.5, 0.45 }
    };

    double v5[][] = {
      { -0.3, 0.0, -0.25 },
      {  0.3, 0.0, -0.25 },
      {  0.2, 2.5, -0.25 },
      { -0.2, 2.5, -0.25 },
      { -0.3, 0.0, 0.45 },
      {  0.3, 0.0, 0.45 },
      {  0.2, 2.5, 0.45 },
      { -0.2, 2.5, 0.45 }
    };

    double v6[][] = {
      { -0.3, 0.0, -0.25 },
      {  0.3, 0.0, -0.25 },
      {  0.2, 2.5, -0.25 },
      { -0.2, 2.5, -0.25 },
      { -0.3, 0.0, 0.45 },
      {  0.3, 0.0, 0.45 },
      {  0.2, 2.5, 0.45 },
      { -0.2, 2.5, 0.45 }
    };

    double v7[][] = {
      { -0.3, 0.0, -0.25 },
      {  0.3, 0.0, -0.25 },
      {  0.2, 2.5, -0.25 },
      { -0.2, 2.5, -0.25 },
      { -0.3, 0.0, 0.25 },
      {  0.3, 0.0, 0.25 },
      {  0.2, 2.5, 0.25 },
      { -0.2, 2.5, 0.25 }
    };

    // IDs of vertices of faces
    int f2[][] = {
      { 0, 3, 2, 1 },
      { 1, 2, 6, 5 },
      { 5, 6, 7, 4 },
      { 4, 7, 3, 0 },
      { 4, 0, 1, 5 },
      { 3, 7, 6, 2 }
    };

    // Normal vector of vertices
    double n2[][] = {
      { 0.0, 0.0, 1.0 },
      {-1.0, 0.0, 0.0 },
      { 0.0, 0.0,-1.0 },
      { 1.0, 0.0, 0.0 },
      { 0.0, 1.0, 0.0 },
      { 0.0,-1.0, 0.0 }
    };

    double p_vertex[][] = parent.getVertex();

    for (int j = 0; j < 8 ; ++j ) {
      for (int i = 0; i < 3 ; ++i ) {
        v2[j][i] = p_vertex[j][i];
      }
    }

    for (int j = 0; j < 8 ; ++j ) {
      for (int i = 0; i < 3 ; ++i ) {
        v3[j][i] = p_vertex[j][i];
      }
    }

    for (int j = 0; j < 8 ; ++j ) {
      for (int i = 0; i < 3 ; ++i ) {
        v4[j][i] = p_vertex[j][i];
      }
    }

    for (int j = 0; j < 8 ; ++j ) {
      for (int i = 0; i < 3 ; ++i ) {
        v5[j][i] = p_vertex[j][i];
      }
    }

    for (int j = 0; j < 8 ; ++j ) {
      for (int i = 0; i < 3 ; ++i ) {
        v6[j][i] = p_vertex[j][i];
      }
    }

    for (int j = 0; j < 8 ; ++j ) {
      for (int i = 0; i < 3 ; ++i ) {
        v7[j][i] = p_vertex[j][i];
      }
    }

    if (p_level < 1) {
      v2[0][1] = p_vertex[2][1];
      v2[1][1] = p_vertex[3][1];
      v2[4][1] = p_vertex[6][1];
      v2[5][1] = p_vertex[7][1];

      v2[2][1] = p_vertex[2][1] + 2.0;
      v2[3][1] = p_vertex[2][1] + 2.0;
      v2[6][1] = p_vertex[2][1] + 2.0;
      v2[7][1] = p_vertex[2][1] + 2.0;

      v2[0][0] = p_vertex[2][0];
      v2[1][0] = p_vertex[3][0];
      v2[2][0] = (0.2 * p_vertex[2][0]) + 1.0;
      v2[3][0] = (0.2 * p_vertex[3][0]) + 1.0;
      v2[4][0] = p_vertex[6][0];
      v2[5][0] = p_vertex[7][0];
      v2[6][0] = (0.2 * p_vertex[6][0]) + 1.0;
      v2[7][0] = (0.2 * p_vertex[7][0]) + 1.0;

      v2[0][2] = p_vertex[2][2];
      v2[1][2] = p_vertex[3][2];
      v2[2][2] = p_vertex[2][2] - 5.0;
      v2[3][2] = p_vertex[3][2] - 5.0;
      v2[4][2] = p_vertex[6][2];
      v2[5][2] = p_vertex[7][2];
      v2[6][2] = p_vertex[6][2] - 5.0;
      v2[7][2] = p_vertex[7][2] - 5.0;


      v3[0][1] = p_vertex[2][1] - 0.1;
      v3[1][1] = p_vertex[3][1] - 0.1;
      v3[4][1] = p_vertex[6][1] - 0.1;
      v3[5][1] = p_vertex[7][1] - 0.1;

      v3[2][1] = p_vertex[2][1] + 1.2;
      v3[3][1] = p_vertex[2][1] + 1.2;
      v3[6][1] = p_vertex[2][1] + 1.2;
      v3[7][1] = p_vertex[2][1] + 1.2;

      v3[0][0] = p_vertex[2][0];
      v3[1][0] = p_vertex[3][0];
      v3[2][0] = (0.5 * p_vertex[2][0]) - 2.5;
      v3[3][0] = (0.5 * p_vertex[3][0]) - 2.5;
      v3[4][0] = p_vertex[6][0];
      v3[5][0] = p_vertex[7][0];
      v3[6][0] = (0.5 * p_vertex[6][0]) - 2.5;
      v3[7][0] = (0.5 * p_vertex[7][0]) - 2.5;

      v3[0][2] = p_vertex[2][2];
      v3[1][2] = p_vertex[3][2];
      v3[2][2] = p_vertex[2][2] + 4.0;
      v3[3][2] = p_vertex[3][2] + 4.0;
      v3[4][2] = p_vertex[6][2];
      v3[5][2] = p_vertex[7][2];
      v3[6][2] = p_vertex[6][2] + 4.0;
      v3[7][2] = p_vertex[7][2] + 4.0;


      v4[0][1] = p_vertex[2][1] - 0.1;
      v4[1][1] = p_vertex[3][1] - 0.1;
      v4[4][1] = p_vertex[6][1] - 0.1;
      v4[5][1] = p_vertex[7][1] - 0.1;

      v4[2][1] = p_vertex[2][1] + 2.0;
      v4[3][1] = p_vertex[2][1] + 2.0;
      v4[6][1] = p_vertex[2][1] + 2.0;
      v4[7][1] = p_vertex[2][1] + 2.0;

      v4[0][0] = p_vertex[2][0];
      v4[1][0] = p_vertex[3][0];
      v4[2][0] = (0.5 * p_vertex[2][0]) + 1.5;
      v4[3][0] = (0.5 * p_vertex[3][0]) + 1.5;
      v4[4][0] = p_vertex[6][0];
      v4[5][0] = p_vertex[7][0];
      v4[6][0] = (0.5 * p_vertex[6][0]) + 1.5;
      v4[7][0] = (0.5 * p_vertex[7][0]) + 1.5;

      v4[0][2] = p_vertex[2][2];
      v4[1][2] = p_vertex[3][2];
      v4[2][2] = p_vertex[2][2] + 1.8;
      v4[3][2] = p_vertex[3][2] + 1.8;
      v4[4][2] = p_vertex[6][2];
      v4[5][2] = p_vertex[7][2];
      v4[6][2] = p_vertex[6][2] + 1.8;
      v4[7][2] = p_vertex[7][2] + 1.8;

      v5[0][1] = p_vertex[2][1] - 0.1;
      v5[1][1] = p_vertex[3][1] - 0.1;
      v5[4][1] = p_vertex[6][1] - 0.1;
      v5[5][1] = p_vertex[7][1] - 0.1;

      v5[2][1] = p_vertex[2][1] + 0.7;
      v5[3][1] = p_vertex[2][1] + 0.7;
      v5[6][1] = p_vertex[2][1] + 0.7;
      v5[7][1] = p_vertex[2][1] + 0.7;

      v5[0][0] = p_vertex[2][0];
      v5[1][0] = p_vertex[3][0];
      v5[2][0] = (0.5 * p_vertex[2][0]) - 0.5;
      v5[3][0] = (0.5 * p_vertex[3][0]) - 0.5;
      v5[4][0] = p_vertex[6][0];
      v5[5][0] = p_vertex[7][0];
      v5[6][0] = (0.5 * p_vertex[6][0]) - 0.5;
      v5[7][0] = (0.5 * p_vertex[7][0]) - 0.5;

      v5[0][2] = p_vertex[2][2];
      v5[1][2] = p_vertex[3][2];
      v5[2][2] = p_vertex[2][2] - 1.0;
      v5[3][2] = p_vertex[3][2] - 1.0;
      v5[4][2] = p_vertex[6][2];
      v5[5][2] = p_vertex[7][2];
      v5[6][2] = p_vertex[6][2] - 1.0;
      v5[7][2] = p_vertex[7][2] - 1.0;

      v5[0][1] = p_vertex[2][1] - 0.1;
      v5[1][1] = p_vertex[3][1] - 0.1;
      v5[4][1] = p_vertex[6][1] - 0.1;
      v5[5][1] = p_vertex[7][1] - 0.1;

      v6[0][1] = p_vertex[2][1] - 0.1;
      v6[1][1] = p_vertex[3][1] - 0.1;
      v6[4][1] = p_vertex[6][1] - 0.1;
      v6[5][1] = p_vertex[7][1] - 0.1;

      v6[2][1] = p_vertex[2][1] + 0.7;
      v6[3][1] = p_vertex[2][1] + 0.7;
      v6[6][1] = p_vertex[2][1] + 0.7;
      v6[7][1] = p_vertex[2][1] + 0.7;

      v6[0][0] = p_vertex[2][0];
      v6[1][0] = p_vertex[3][0];
      v6[2][0] = (0.5 * p_vertex[2][0]) - 0.5;
      v6[3][0] = (0.5 * p_vertex[3][0]) - 0.5;
      v6[4][0] = p_vertex[6][0];
      v6[5][0] = p_vertex[7][0];
      v6[6][0] = (0.5 * p_vertex[6][0]) - 0.5;
      v6[7][0] = (0.5 * p_vertex[7][0]) - 0.5;

      v6[0][2] = p_vertex[2][2];
      v6[1][2] = p_vertex[3][2];
      v6[2][2] = p_vertex[2][2] - 3.0;
      v6[3][2] = p_vertex[3][2] - 3.0;
      v6[4][2] = p_vertex[6][2];
      v6[5][2] = p_vertex[7][2];
      v6[6][2] = p_vertex[6][2] - 3.0;
      v6[7][2] = p_vertex[7][2] - 3.0;

      v7[0][1] = p_vertex[2][1] - 0.1;
      v7[1][1] = p_vertex[3][1] - 0.1;
      v7[4][1] = p_vertex[6][1] - 0.1;
      v7[5][1] = p_vertex[7][1] - 0.1;

      v7[2][1] = p_vertex[2][1] + 0.7;
      v7[3][1] = p_vertex[2][1] + 0.7;
      v7[6][1] = p_vertex[2][1] + 0.7;
      v7[7][1] = p_vertex[2][1] + 0.7;

      v7[0][0] = p_vertex[2][0];
      v7[1][0] = p_vertex[3][0];
      v7[2][0] = (0.5 * p_vertex[2][0]) - 0.5;
      v7[3][0] = (0.5 * p_vertex[3][0]) - 0.5;
      v7[4][0] = p_vertex[6][0];
      v7[5][0] = p_vertex[7][0];
      v7[6][0] = (0.5 * p_vertex[6][0]) - 0.5;
      v7[7][0] = (0.5 * p_vertex[7][0]) - 0.5;

      v7[0][2] = p_vertex[2][2];
      v7[1][2] = p_vertex[3][2];
      v7[2][2] = p_vertex[2][2] + 5.0;
      v7[3][2] = p_vertex[3][2] + 5.0;
      v7[4][2] = p_vertex[6][2];
      v7[5][2] = p_vertex[7][2];
      v7[6][2] = p_vertex[6][2] + 5.0;
      v7[7][2] = p_vertex[7][2] + 5.0;
    }

    else {
      v2 = reduct(v2, 2, p_level);
      v3 = reduct(v3, 2, p_level);
      v4 = reduct(v4, 2, p_level);
      v5 = reduct(v5, 2, p_level);
      v6 = reduct(v6, 2, p_level);
      v7 = reduct(v7, 2, p_level);
    }

    switch (newBranchAxis) {
      case 0:
      newBranches.add(new Branch(v3, f2, n2, p_level + 1));
      //newBranches.add(new Branch(v2, f2, n2, p_level + 1));
      //newBranches.add(new Branch(v4, f2, n2, p_level + 1));
      //newBranches.add(new Branch(v6, f2, n2, p_level + 1));
      //newBranches.add(new Branch(v7, f2, n2, p_level + 1));
      break;

      case 1:
      //newBranches.add(new Branch(v3, f2, n2, p_level + 1));
      newBranches.add(new Branch(v5, f2, n2, p_level + 1));
      //newBranches.add(new Branch(v4, f2, n2, p_level + 1));
      newBranches.add(new Branch(v6, f2, n2, p_level + 1));
      //newBranches.add(new Branch(v7, f2, n2, p_level + 1));
      break;

      case 2:
      //newBranches.add(new Branch(v3, f2, n2, p_level + 1));
      newBranches.add(new Branch(v2, f2, n2, p_level + 1));
      //newBranches.add(new Branch(v4, f2, n2, p_level + 1));
      //newBranches.add(new Branch(v5, f2, n2, p_level + 1));
      //newBranches.add(new Branch(v6, f2, n2, p_level + 1));
      newBranches.add(new Branch(v7, f2, n2, p_level + 1));
      break;

      case 3:
      newBranches.add(new Branch(v3, f2, n2, p_level + 1));
      newBranches.add(new Branch(v2, f2, n2, p_level + 1));
      //newBranches.add(new Branch(v4, f2, n2, p_level + 1));
      //newBranches.add(new Branch(v5, f2, n2, p_level + 1));
      //newBranches.add(new Branch(v6, f2, n2, p_level + 1));
      newBranches.add(new Branch(v7, f2, n2, p_level + 1));
      break;

      default:
      newBranches.add(new Branch(v3, f2, n2, p_level + 1));
      //newBranches.add(new Branch(v2, f2, n2, p_level + 1));
      //newBranches.add(new Branch(v5, f2, n2, p_level + 1));
      //newBranches.add(new Branch(v6, f2, n2, p_level + 1));
      //newBranches.add(new Branch(v7, f2, n2, p_level + 1));
    }

    int count = 0;

    for (Branch newBranch : newBranches) {
      addBranche(newBranch);
      if (newBranch.getLevel() < tree_level) {
        if (10 < newBranch.getLevel()) {
          //count = r.nextInt(newBranch.getLevel() * newBranch.getLevel());
          ++count;
          System.out.print("count is");
          System.out.print(count);
          if (count % (p_level * p_level) == 0) {
            subDivide(newBranch);
          }
        } else {
          //subDivide(newBranch);
        }
      }
    }

    //set leaf
  }

  float color[] = { 69.0f/255.0f, 69.0f/255.0f, 19.0f/255.0f, 1.0f };
  float silver[] = { 69.0f/255.0f, 69.0f/255.0f, 19.0f/255.0f, 1.0f };

  public void growTree() {
    ArrayList<Branch> stock_branches = new ArrayList<Branch>();
    int count = 0;
    ++tree_level;
    if (0 < branches.size()) {
      for (Branch b : branches) {
        if (tree_level  == b.getLevel()) {
          stock_branches.add(b);
        }
      }

      for (Branch b : stock_branches ) {
        int b_level = b.getLevel();
        //System.out.println(b_level);
        if (4 < b_level) {
          if (count%(b_level * b_level) == 0) {
              subDivide(b);
          }
        } else {
          subDivide(b);
        }

        ++count;
      }
    }

  }

  public void dropFlowers(double x, double y, double z) {
    double[] flower_pos= {0.0, 0.0, 0.0};
    for (Branch b : branches) {
      flower_pos = b.getFlowerPos();
      //System.out.println(Math.abs(flower_pos[0] - x));
      if (Math.abs(flower_pos[0] - x) < 3.0 && Math.abs(flower_pos[1] - y) < 3.0) {
        b.dropFlowers();
      }
    }
  }

  public void reBloom() {
    for (Branch b : branches) {
      b.reBloom();
    }
  }

  public void draw(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    GLUT glut = new GLUT();
    gl.glTranslated(posx, 0.0, posz);
    //draw root
    gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, color, 0);
    gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, silver, 0);
    gl.glTranslated(0.0, -0.4, 0.0);
    glut.glutSolidIcosahedron();

    if (branch != null) {
      branch.display(drawable);
    }

    //glut.glutSolidCylinder(5, 5, 5, 5);


    for (int i = 0; i < branches.size() ; i++ ) {
      Branch branch = branches.get(i);
      branch.display(drawable);
    }

    /*for (int i = leaves.size() - 1; i > -1 ; i-- ) {
    Leaf leaf = leaves.get(i);
    leaf.display();
  }*/
}
}
