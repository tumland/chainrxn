package com.coffeejawa.chainrxn;

import android.graphics.Color;
import android.graphics.Paint;

public class Ball {
	Paint paint = new Paint(Color.WHITE);
	float radius = 10;
	boolean hit = false;
			
	Point pos = new Point(0,0);
	Point vel = new Point(0,0);
	
	Ball(float xpos, float ypos, float xvel, float yvel){
		pos = new Point(xpos,ypos);
		vel = new Point(xvel,yvel);
	}
	Ball(float xpos, float ypos){
		pos = new Point(xpos,ypos);
		vel = new Point(5,5);
	}
	Ball(Point pos){
		this.pos = pos;
		vel = new Point(5, 5);
	}
	Ball(Point pos, Point vel){
		this.pos = pos;
		this.vel = vel;
	}
	
	public void SetColor(int color){
		paint.setColor(color);
	}
	
}