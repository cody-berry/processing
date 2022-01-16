package pathfollowing;

import processing.core.PApplet;
import processing.core.PVector;

public class Path {
	PVector start;
	PVector end;
	PApplet app;
	int radius;
	public Path(PApplet app, int x1, int y1, int x2, int y2) {
		start = new PVector(x1, y1);
		end = new PVector(x2, y2);
		this.app = app;
		radius = 20;
	}
	
	public void show() {
		app.stroke(0, 0, 100);
		app.strokeWeight(2);
		app.line(start.x, start.y, end.x, end.y);
		app.stroke(255, 10);
		app.strokeWeight(radius*2);
		app.line(start.x, start.y, end.x, end.y);
	}
}
