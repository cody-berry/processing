package dotproduct;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * setup with vectors and visualization of vectors ☐
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

//		rect(mouseX, mouseY, 20, 20);
	}

	@Override
	public void mousePressed() {
		System.out.println(mouseX);
	}
}