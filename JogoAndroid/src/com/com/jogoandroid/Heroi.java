package com.com.jogoandroid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Heroi extends Retangulo {

	public Heroi(int x, int y) {
		super(x, y, 100, 100);
		// TODO Auto-generated constructor stub

	}

	public void mexe(int height, int width, int y) {
		if (getY() < height) {
			setY(y);
		}
//		else {
//			setY(-5);
//			int x = (int) (Math.random() * (width - 25));
//			setX(x);
//
//		}
	}

	public void draw(Canvas canvas, Paint paint) {
		paint.setColor(Color.WHITE);

		canvas.drawRect(getX(), getY(), getX() + getWidth(), getY()
				+ getHeight(), paint);
	}
	
		

}
