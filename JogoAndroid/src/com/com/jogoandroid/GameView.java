package com.com.jogoandroid;

import android.R.bool;
import android.R.color;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.SensorEvent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View implements Runnable {
	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		paint = new Paint();
		Thread minhaThread = new Thread(this);
		minhaThread.setPriority(Thread.MIN_PRIORITY);
		minhaThread.start();
		erros = 0;
		pontos = 0;
	}

	private static final int INTERVAL = 100;
	private static final int vidas = 10;
	
	private int erros;
	private int pontos;
	private boolean running = true;
	private int y;
	private Paint paint;
	private Inimigo[] inimigos;
	private Heroi heroi;

	public void run() {
		while (running) {
			try {
				Thread.sleep(INTERVAL);
			} catch (InterruptedException e) {
				Log.e("Jogo", "GameLoop finalizado!");
			}
			update();
		}
	}

	private void update() {
		if (inimigos == null) {
			return;
		}else if(heroi == null){
			return;
		}
		
		//Log.e(getHeight()+"", getWidth()+"");
		for (int i = 0; i < inimigos.length; i++) {
			inimigos[i].mexe(getHeight(), getWidth());
			
			if(heroi.colide(inimigos[i].getX(), inimigos[i].getY())){
				inimigos[i].setX(-50);
				pontos +=  10;
				//Log.e("Ganho Ponto", "Pegou, Pontuação: "+pontos);
				
				if(pontos % 100 == 0){
					erros--;
				}
			}
			else if(inimigos[i].getY() == heroi.getY()+1){				
				inimigos[i].setX(-50);

				erros++;
				//Log.e("perdeu ponto", "Você tem: "+ (vidas-erros) + " vidas" );
				
				if (!(erros <= vidas)){
					//Log.e("Game Over", "Voce morreu" );
					erros = 0;
				//	release();
				}
			}
		}
		
//		if(y < getHeight()){
//			y += 5;
//		}else{
//			y = 0;
//		}
		

		postInvalidate();
	}

	public void draw(Canvas canvas) {
		super.draw(canvas);
		if (inimigos == null || heroi == null) {
			iniciaJogo();
		}
		canvas.drawColor(Color.BLACK);

		for (int i = 0; i < inimigos.length; i++) {
			inimigos[i].draw(canvas, paint);
		}
		heroi.draw(canvas, paint);
		
		

	}

	public void release() {
		running = false;
	}

	public void iniciaJogo() {
		inimigos = new Inimigo[getHeight() / 50];		
		heroi = new Heroi(getWidth()/2, getHeight()-100);
		
		
		for (int i = 0; i < inimigos.length; i++) {
			int x = (int) (Math.random() * (getWidth() - 25));
			int y = i * 50;
			inimigos[i] = new Inimigo(x, y);
		}
	}

	public void onSensorChanged(SensorEvent event) {
		Log.e("Sensor","event.values[1]");
		if(event.values[1]>0){
			heroi.setY(+5);
		}else{
			heroi.setY(-5);
		}

	}	
	
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		int x = (int) event.getX();
		int y = (int) event.getY();
		for (int i = 0; i < inimigos.length; i++) {
			if (action == MotionEvent.ACTION_DOWN) {
				if (inimigos[i].colide(x, y)) {
					Log.e("tocado", "matou");
					inimigos[i].setX(-50);
					inimigos[i].setY(getHeight()+100);
				}
			}
		}

		return super.onTouchEvent(event);
	}

}
