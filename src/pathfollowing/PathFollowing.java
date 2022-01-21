package pathfollowing;

import processing.core.PApplet;

/**
 * Thanks to our hero Abe Pazos at https://vimeo.com/channels/p5idea, who teaches us how to use Processing inside IDEA
 */
public class PathFollowing extends PApplet {
	Vehicle v;
	Path p;
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
		p = new Path(this, 0, height/2, width, height/2);
	}

	@Override
	public void draw() {
		background(210, 100, 30, 100);
		v.show();
		v.update();
		// let's follow our path!
		v.follow(p);
		p.show();
		// oh, and edges
		v.edges();
	}

	@Override
	public void mousePressed() {
//		System.out.println(mouseX);
	}
}
