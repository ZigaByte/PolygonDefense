package com.zigabyte.polygondefense.math;

public class Rectangle {
	public Vector2f v0;
	public Vector2f v1;

	public Rectangle(Vector2f v0, Vector2f v1) {
		this.v0 = v0;
		this.v1 = v1;
	}

	public boolean isInside(Vector2f v) {
		return !(v.x < v0.x || v.x > v1.x || v.y < v0.y || v.y > v1.y);
	}

}
