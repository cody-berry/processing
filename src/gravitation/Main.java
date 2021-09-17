package gravitation;

import processing.core.PApplet;

import java.util.ArrayList;

/**
 * version comments
 * 	particle class: pos, vel, acc,
 * 	show, update
 * 	apply_force
 * 	create 100 objects
 */
public class Main extends PApplet {
	ArrayList<Planet> planets;
	public static void main(String[] args) {
		PApplet.main(new String[]{Main.class.getName()});
	}

	@Override
	public void settings() {
		size(640, 360);
	}

	@Override
	public void setup() {
		rectMode(RADIUS);
		colorMode(HSB, 360f, 100f, 100f, 100f);
		planets = new ArrayList<Planet>();
		for (int i = 0; i < 100; i++){
			planets.add(new Planet(this, (int) (Math.random()*width),
					(int) (Math.random()*height),
					(int) (Math.random()*10 + 10)));
		}
	}

	@Override
	public void draw() {
		background(210, 100, 30, 100);
		for (Planet planet : planets){
			planet.show(this);
		}

	}

	@Override
	public void mousePressed() {
		System.out.println(mouseX);
	}
}