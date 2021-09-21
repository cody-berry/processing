package gravitation;

import processing.core.PApplet;
import processing.core.PVector;

public class Planet{
	PVector pos;
	PVector vel;
	PVector acc;
	float mass;
	float maxForce;

	public Planet(PApplet app, int x, int y, int z, int mass) {
		pos = new PVector(x, y, z);
		vel = PVector.random3D().mult(app.random(2f, 5f));
		acc = new PVector(0, 0, 0);
		this.mass = mass;
		maxForce = 0.1f;
	}

	public void edges(PApplet app) {
		// right
		if (pos.x + mass > app.width) {
			vel.x *= -1;
		}
		// left
		if (pos.x - mass < 0) {
			vel.x *= -1;
		}
		// bottom (y coordinates positive go down)
		if(pos.y + mass > app.height) {
			vel.y *= -1;
		}
		// top
		if (pos.y - mass < 0) {
			vel.y *= -1;
		}
	}

	// shows ourselves
	public void show(PApplet app) {
		app.noStroke();
		app.fill(0, 0, 100, 30); // white
		app.pushMatrix();
		app.translate(pos.x, pos.y, pos.z);
		app.sphere(this.mass);
		app.popMatrix();
	}

	// updates our position, velocity, and acceleration
	public void update(PApplet app) {
		vel.add(acc);
		pos.add(vel);
		acc.mult(0);
		pos.x = constrain(pos.x, -app.width, app.width);
		pos.y = constrain(pos.y, -app.height, app.height);
		pos.z = constrain(pos.z, -app.height, app.height);
	}

	// applies a force
	public void apply_force(PApplet app, PVector f) {
		// f = ma, so a = f/m
		f.limit(maxForce);
		acc.add(PVector.div(f, mass));
	}

	// returns the force of attracting another planet
	public PVector attract(PApplet app, Planet target) {
		// what is the vector pointing from us to the target?
		PVector vector = PVector.sub(pos, target.pos);
		// according to Newton's gravitational law:
		// F = GM₁M₂/r². We don't have the distance!
		float distance;
		distance = constrain(PVector.dist(target.pos, pos), 6, 16);
		// and the gravitational constant.
		int G;
		G = 3;
		// now we can use Newton's gravitational law.
		float strength;
		strength = G*(target.mass*mass)/(distance * distance);
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
