package pathfollowing;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * Thanks to our hero Abe Pazos at https://vimeo.com/channels/p5idea, who teaches us how to use Processing inside IDEA
 */
public class PathFollowing extends PApplet {
	Vehicle v;
	public static void main(String[] args) {
		PApplet.main(new String[]{PathFollowing.class.getName()});
	}

	@Override
	public void settings() {
		size(700, 600);
	}

	@Override
	public void setup() {
		rectMode(RADIUS);
		colorMode(HSB, 360f, 100f, 100f, 100f);
		v = new Vehicle(this, (float) (Math.random()*width),
				(float) (Math.random()*height));
	}

	@Override
	public void draw() {
		background(210, 100, 30, 100);
		// fill with a red color for our target
		fill(0, 100, 100);
		noStroke();
		PVector target = new PVector(mouseX, mouseY);
		circle(target.x, target.y, 32);
		v.show();
		v.update();
		v.seek(target);
	}

	@Override
	public void mousePressed() {
		System.out.println(mouseX);
	}
}
