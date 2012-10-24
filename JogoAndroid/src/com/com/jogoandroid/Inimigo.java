package com.com.jogoandroid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Inimigo extends Retangulo {

	public Inimigo(int x, int y) {
		super(x, y, 25, 25);
		// TODO Auto-generated constructor stub

	}

	public void mexe(int height, int width) {
		if (getY() < height) {
			setY(getY() + 5);
		} else {
			setY(-5);
			int x = (int) (Math.random() * (width - 25));
			setX(x);

		}
	}

	public void draw(Canvas canvas, Paint paint) {
		paint.setColor(Color.RED);

		canvas.drawRect(getX(), getY(), getX() + getWidth(), getY()
				+ getHeight(), paint);
	}

}
