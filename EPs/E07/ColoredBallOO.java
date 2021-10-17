/******************************************************************************
 *
 * MAC0121 ALGORITMOS E ESTRUTURAS DE DADOS I
 * Aluno: <Mateus Latrova Stephanin>
 * Numero USP: <12542821>
 * Tarefa: E07
 * Data: <04/10/2021>
 * 
 * Baseado em:
 *     
 *     - Programa ColoredBall.java, dado no enunciado.
 * 
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

import java.awt.Color;

public class ColoredBallOO { 
    private Vector p;              // position
    private Vector v;              // velocity
    private final double radius;   // radius
    private final Color color;     // color

    // constructor
    public ColoredBallOO() {
        double[] pos =  {0.0, 0.0};
        p = new Vector(pos);
        
        double vx = StdRandom.uniform(-0.025, 0.025);
        double vy = StdRandom.uniform(-0.025, 0.025);
        double[] vel = {vx, vy};
        v = new Vector(vel);
        
        radius = StdRandom.uniform(0.05, 0.15);
        color = Color.getHSBColor((float) StdRandom.uniform(0.0, 1.0), 0.8f, 0.8f);
    }
   
    public ColoredBallOO(Vector P, Vector V, double r, Color c) {
        p = P;
        v = V; 
        radius = r;
        color = c;
    }

    public Vector pos() { return p; }
    public Vector vel() { return v; }
    public double radius() { return radius; }

    public void setVel(Vector V) { v = V; }

    // move the ball one step
    public void move(double size, double dt) {
        treatWalls(size, dt);
        updatePosition(dt);
    }

    public void updatePosition(double dt) {
        Vector ds = v.scale(dt);
        p = p.plus(ds);
    }

    public void treatWalls(double size, double dt) {
        double rx = p.cartesian(0);
        double ry = p.cartesian(1);
        double vx = v.cartesian(0);
        double vy = v.cartesian(1);
	    if ((rx + dt * vx > size - radius) || (rx + dt * vx < radius)) vx = -vx;
	    if ((ry + dt * vy > size - radius) || (ry + dt * vy < radius)) vy = -vy;
        double[] vel = {vx,vy};
        v = new Vector(vel);
    }

    // draw the ball
    public void draw() { 
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(p.cartesian(0), p.cartesian(1), radius);
    }

    // test client
    public static void main(String[] args) {

        // create and initialize two balls
        ColoredBallOO b1 = new ColoredBallOO();
        ColoredBallOO b2 = new ColoredBallOO();

        //double SIZE = 2.0;
        double dt = 1.0;
        
        // animate them
        StdDraw.setXscale(-1.0, 1.0);
        StdDraw.setYscale(-1.0, 1.0);
        StdDraw.enableDoubleBuffering();

        while (true) {
            b1.move(1.0, dt);
            b2.move(1.0, dt);
            StdDraw.clear(StdDraw.GRAY);
            b1.draw();
            b2.draw();
            StdDraw.show();
            StdDraw.pause(20);
        }
    }
}
