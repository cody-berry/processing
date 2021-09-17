package gravitation;

import processing.core.PApplet;
import processing.core.PVector;

public class Planet{
	PVector pos;
	PVector vel;
	PVector acc;
	float mass;

	public Planet(PApplet app, int x, int y, int mass) {
		pos = new PVector(x, y);
		vel = PVector.random2D();
		acc = new PVector(0, 0);
		this.mass = mass;

	}

	public void show(PApplet app){
		app.fill(0, 0, 100, 100); // white
		app.circle(pos.x, pos.y, this.mass);
	}
}
