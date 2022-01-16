package pathfollowing;

import processing.core.PApplet;
import processing.core.PVector;

public class Vehicle {
	PVector pos;
	PVector vel;
	PVector acc;
	int r;
	PApplet app;
	float maxSpeed;
	float maxForce;
	public Vehicle(PApplet app, float x, float y) {
		pos = new PVector(x, y);
		vel = new PVector(2, 0);
		acc = new PVector(0, 0);
		// we are a triangle, and we need a radius
		r = 16;
		this.app = app;
		// our max speed and force
		maxSpeed = 4;
		maxForce = 0.1f;
	}

	// seeks a PVector target
	public void seek(PVector target) {
		// our desired velocity is a straight line pointing form us to the
		// target at our maximum speed
		PVector desired = PVector.sub(target, this.pos);
		desired.setMag(maxSpeed);
		// steering = desired - vel, Creg Renold's formula
		PVector steering = PVector.sub(desired, this.vel);
		steering.limit(maxForce);
		applyForce(steering);
	}

	public void show() {
		app.fill(0, 0, 100);
		app.noStroke();
		// make a triangle
		app.push();
		app.translate(pos.x, pos.y);
		app.rotate(vel.heading());
		app.triangle(-r, -r/2, -r, r/2, r, 0);
		app.pop();
	}

	public void applyForce(PVector f) {
		// F = ma. Since m = 1, a = f.
		acc.add(f);
	}

	public void update() {
		vel.add(acc);
		pos.add(vel);
		acc = new PVector(0, 0);
	}
}
