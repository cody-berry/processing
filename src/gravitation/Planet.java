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
		vel = PVector.random2D().mult(app.random(2f, 5f));
		acc = new PVector(0, 0);
		this.mass = mass;

	}

	public void show(PApplet app) {
		app.fill(0, 0, 100, 30); // white
		app.circle(pos.x, pos.y, this.mass);
	}

	public void update(PApplet app) {
		vel.add(acc);
		pos.add(vel);
		acc.mult(0);
	}

	public void apply_force(PApplet app, PVector f) {
		// f = ma, so a = f/m
		acc.add(PVector.div(f, mass));
	}

	public PVector attract(PApplet app, Planet target) {
		// what is the vector pointing from us to the target?
		PVector vector = PVector.sub(pos, target.pos);
		// according to Newton's gravitational law:
		// F = GM₁M₂/r². We don't have the distance!
		float distance;
		distance = PVector.dist(target.pos, pos);
		// and the gravitational constant.
		int G;
		G = 3;
		// now we can use Newton's gravitational law.
		float strength;
		strength = constrain(G*(target.mass*mass)/(distance * distance), 8,
				15);
		vector.setMag(strength);
		return vector; // what if we're going to do something to the force?
	}

	private float constrain(float value, float low, float high){
		if (value < low) {
			return low;
		}
		if (value > high) {
			return high;
		}
		else {
			return value;
		}
	}
}
