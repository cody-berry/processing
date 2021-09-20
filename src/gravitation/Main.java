package gravitation;

import processing.core.PApplet;
//import processing.core.PVector;

import java.util.ArrayList;

/*
version comments
. 	particle class: pos, vel, acc
.	show, update
.	apply_force
.	attract following universal law of gravitation
 	add attractor + planets
 	convert to 3D
 	add peasycam
 	draw axes
 	add trails
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
		planets = new ArrayList<>();
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
			for (Planet otherP : planets){
				// We don't want to attract ourselves! That's not how
				// gravitation works.
				if (otherP != planet) {
					// Let's now apply the force!
					planet.apply_force(this, otherP.attract(this, planet));
				}
			}
		}

		// We need a separate loop for show and update, but I don't know why.
		for (Planet planet : planets) {
			planet.show(this);
			planet.update(this);
//			// Olive the planets go to the edges!
//			planet.edges(this);
		}
	}

	@Override
	public void mousePressed() {
		planets.add(new Planet(this, mouseX, mouseY,
				(int) (Math.random()*10 + 10)));
	}
}