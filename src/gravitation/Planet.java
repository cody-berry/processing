package gravitation;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Planet{
	PVector pos;
	PVector vel;
	PVector acc;
	float mass;
	float maxForce;
	ArrayList<PVector> trails;

	public Planet(PApplet app, int x, int y, int z, int mass) {
		pos = new PVector(x, y, z);
		vel = PVector.random3D().mult(app.random(1f, 3f));
		acc = new PVector(0, 0, 0);
		this.mass = mass;
		maxForce = .2f;
		trails = new ArrayList<PVector>();

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
		for (PVector trail : trails) {
			app.noStroke();
			app.fill(0, 0, 30); // dark grey
			app.pushMatrix();
			app.translate(trail.x, trail.y, trail.z);
			app.sphere(this.mass);
			app.popMatrix();
		}
		app.noStroke();
		app.fill(0, 0, 100); // white
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
		trails.add(new PVector(pos.x, pos.y, pos.z));
		// What happens if we have too much trail objects?
		if (trails.size() > 90) {
			trails.remove(0);
		}
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
