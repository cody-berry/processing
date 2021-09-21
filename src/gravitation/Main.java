package gravitation;

import peasy.PeasyCam;
import processing.core.PApplet;
//import processing.core.PVector;

import java.util.ArrayList;

/*
version comments
. 	particle class: pos, vel, acc
.	show, update
.	apply_force
.	attract following universal law of gravitation
.	add attractor + planets
.	convert to 3D, add peasycam
* 	draw axes
 	add trails
 */
public class Main extends PApplet {
	ArrayList<Planet> planets;
	PeasyCam cam;

	public static void main(String[] args) {
		PApplet.main(new String[]{Main.class.getName()});
	}

	@Override
	public void settings() {
		size(1280, 720, P3D);
	}

	@Override
	public void setup() {
		rectMode(RADIUS);
		colorMode(HSB, 360f, 100f, 100f, 100f);
		planets = new ArrayList<>();
		for (int i = 0; i < 100; i++){
			planets.add(new Planet(this, (int) (Math.random()*width*2 - width),
					(int) (Math.random()*height*2 - height),
					(int) (Math.random()*-height*2+height),
					(int) (Math.random()*10 + 10)));
		}
		cam = new PeasyCam(this, 0, 0, 0, height);
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
		}
		drawAxes();
	}

	@Override
	public void keyPressed() {
		if (key == 'a') {
			planets.add(new Planet(this, mouseX, mouseY,
					(int) (Math.random() * height * 2 - height),
					(int) (Math.random() * 10 + 10)));
		}
	}

	// draws the axes
	public void drawAxes() {
		// TODO: Make it so that none of the spheres are darker than the others
		stroke(355, 83, 100);
		strokeWeight(1f);
		line(7000, 0, 0, 0, 0, 0);
		stroke(355, 83, 50);
		line(0, 0, 0, -7000, 0, 0);
		stroke(90, 83, 100);
		line(0, 7000, 0, 0, 0, 0);
		stroke(90, 83, 50);
		line(0, 0, 0, 0, -7000, 0);
		stroke(210, 83, 100);
		line(0, 0, 7000, 0, 0, 0);
		stroke(210, 83, 50);
		line(0, 0, 0, 0, 0, -7000);
	}
}