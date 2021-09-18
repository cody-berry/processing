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
		acc = PVector.random2D();
		this.mass = mass;

	}

	public void show(PApplet app){
		app.fill(0, 0, 100, 30); // white
		app.circle(pos.x, pos.y, this.mass);
	}

	public void update(PApplet app){
		pos.add(vel);
		vel.add(acc);
		acc.mult(0);
	}

	public void apply_force(PApplet app, PVector f){
		// f = ma, so a = f/m
		acc.add(f);
	}
}
