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

	// find the projection between 3 points
	public PVector findProjection(PVector pos, PVector a, PVector b) {
		PVector v1 = PVector.sub(a, pos);
		PVector v2 = PVector.sub(b, pos);
		v2.normalize();
		float sp = v1.dot(v2);
		v2.mult(sp);
		v2.add(pos);
		return v2;
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

	// Path following algorithm here (takes in a Path)!!!
	public void follow(Path path) {
		// Step 1
		// Calculate a future position.
		PVector future = this.vel.copy();
		future.mult(50);
		future.add(this.pos);
		app.fill(0, 100, 100);
		app.circle(future.x, future.y, 16);
		app.stroke(0, 0, 100);
		app.strokeWeight(3);
		app.line(pos.x, pos.y, future.x, future.y);

		// Step 2
		// Is future "on" our path?
		// Part of it is also Step 3
		// Find projection point
		PVector target = findProjection(path.start, future, path.end);
		float d = PVector.dist(future, target);
		app.fill(120, 100, 100);
		app.noStroke();
		app.circle(target.x, target.y, 16);
		app.stroke(0, 0, 100);
		app.line(future.x, future.y, target.x, target.y);
		// Step 4
		// If our distance is greater than the path's radius, we seek our
		// target.
		if (d > path.radius) {
			this.seek(target);
		}
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
