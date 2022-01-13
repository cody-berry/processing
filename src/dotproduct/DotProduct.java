package dotproduct;

import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;

/**
 * setup with vectors and visualization of vectors ☒
 * add vector projection and visualization ☐
 * circle at position, end of vector a, end of scalar projection
 * Thanks to our hero Abe Pazos at https://vimeo.com/channels/p5idea, who teaches us how to use Processing inside IDEA
 */
public class DotProduct extends PApplet {
	PVector pos, a, b;
	public static void main(String[] args) {
		PApplet.main(new String[]{DotProduct.class.getName()});
	}

	@Override
	public void settings() {
		size(700, 600);
	}

	@Override
	public void setup() {
		rectMode(RADIUS);
		colorMode(HSB, 360f, 100f, 100f, 100f);
		// our position
		pos = new PVector(100, 200);
		// our path for path following
		b = new PVector(200, 60);
	}

	public PVector vectorProjection(PVector a, PVector b) {
		// copy b and normalize it
		PVector bCopy = b.copy().normalize();
		// our scalar projection is going to be a * bCopy
		float sp = a.dot(bCopy);
		// we multiply normalized b by sp, and then we return it. 
		bCopy.mult(sp);
		return bCopy;
	}

	@Override
	public void draw() {
		background(210, 100, 30, 100);
		// shown at our mouse, controls visualization of dot product
		a = new PVector(mouseX-pos.x, mouseY-pos.y);
		// visualize our vectors
		stroke(0, 0, 100);
		strokeWeight(4);
		line(pos.x, pos.y, pos.x + a.x, pos.y + a.y);
		line(pos.x, pos.y, pos.x + b.x, pos.y + b.y);


		// add a green circle at our position and the end of a
		fill(120, 100, 100);
		stroke(120, 100, 100);
		strokeWeight(10);
		circle(pos.x, pos.y, 8);
		circle(pos.x + a.x, pos.y + a.y, 8);


		// add scalar projection
		PVector v = vectorProjection(a, b);
		stroke(210, 100, 100);
		strokeWeight(8);
		line(pos.x, pos.y, pos.x + v.x, pos.y + v.y);
		// add red circle at the end of the scalar projection
		stroke(0, 100, 100);
		fill(0, 100, 100);
		circle(pos.x + v.x, pos.y + v.y, 8);

		// add dashed line
//		drawDashedLine(, (int) (pos.x + v.x),
//				(int) (pos.y + v.y), (int) (pos.x + a.x),
//				(int) (pos.y + a.y));




//		rect(mouseX, mouseY, 20, 20);
	}

	// draws a dashed line
	public void drawDashedLine(Graphics g, int x1, int y1, int x2, int y2){

		// Create a copy of the Graphics instance
		Graphics2D g2d = (Graphics2D) g.create();

		// Set the stroke of the copy, not the original
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
				0, new float[]{9}, 0);
		g2d.setStroke(dashed);

		// Draw to the copy
		g2d.drawLine(x1, y1, x2, y2);

		// Get rid of the copy
		g2d.dispose();
	}

	@Override
	public void mousePressed() {
		System.out.println(mouseX);
	}
}