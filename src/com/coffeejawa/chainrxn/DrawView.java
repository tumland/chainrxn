package com.coffeejawa.chainrxn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import android.widget.Toast;

public class DrawView extends View implements OnTouchListener {
	    private static final String TAG = "DrawView";
		Paint paint = new Paint();
		Paint paint2 = new Paint();
		Ball userBall;
		List<Ball> balls = new ArrayList<Ball>();
		TextView sv = null;
		private boolean soundEnabled = true;
		Timer timer = new Timer();
		
		
		public boolean isSoundEnabled() {
			return soundEnabled;
		}
		public void setSoundEnabled(boolean soundEnabled) {
			this.soundEnabled = soundEnabled;
		}
		private int ballCount = 10;
		private int ballSpeed = 10;
		
		private SoundPool sounds;
		private int sPing;
		
		int ballScore = 0;
		
		public DrawView(Context context, AttributeSet attrs){
			super(context, attrs);
			init();
		}
		public DrawView(Context context)
        {
            super(context);
            init();
        }
		private void init(){
	        setFocusable(true);
	        setFocusableInTouchMode(true);
	        
	        this.setOnTouchListener(this);
	        
			paint.setColor(Color.WHITE);	
			paint.setAntiAlias(true);
			
			sounds = new SoundPool(10, AudioManager.STREAM_MUSIC,0);
			sPing = sounds.load(getContext(), R.raw.sonar, 1);
			
		}
		public void reset(){
			ballScore = 0;
			balls.clear();
			userBall = null;
		}
		public int GetScore() {
			return ballScore;
		}
		public void SetScoreView(TextView sv) {
			this.sv = sv;
		}
		
		@Override
		public void onDraw(Canvas canvas){
			float height = canvas.getHeight();
			float width = canvas.getWidth();
			
			float fTimeElapsed = timer.GetTimeElapsed();
			
			// spawn balls
			while (balls.size() < (ballCount + 1) ){
				int edge = (int) Math.floor((Math.random() * 4));
				
				Point pPos = new Point(0,0);		
				Point pVel = new Point(0,0); // It's really just a vector
				
				float timeFactor = fTimeElapsed * .1f * ballSpeed;
				
				switch (edge) {
				case 0:
					pPos = new Point(0, (float) (Math.random() * height));
					pVel = new Point(
							timeFactor * .5f, 
							timeFactor * (float) (Math.random()  - .5));
					break;
				case 1:
					pPos = new Point((float) (width * Math.random()), 0);
					pVel = new Point(
							timeFactor * (float) (Math.random() - .5),
							timeFactor * .5f);
					break;
				case 2:
					pPos = new Point(width, (float) (Math.random() * height));
					pVel = new Point(
							timeFactor * -.5f,
							timeFactor * (float) (Math.random() - .5));
					break;
				case 3:
					pPos = new Point((float) (Math.random() * width),height);
					pVel = new Point(
							timeFactor * (float) (Math.random() - .5),
							timeFactor * -.5f);
					break;
				}
				Ball ball = new Ball(pPos,pVel);

				int color = Color.WHITE;
				int colorindex = (int) Math.floor((Math.random() * 10));
				switch (colorindex) {
				case 0:
					color = Color.BLUE;
					break;
				case 1:
					color = Color.CYAN;
					break;
				case 2:
					color = Color.DKGRAY;
					break;
				case 3:
					color = Color.GRAY;
					break;
				case 4:
					color = Color.GREEN;
					break;
				case 5:
					color = Color.LTGRAY;
					break;
				case 6:
					color = Color.MAGENTA;
					break;
				case 7:
					color = Color.RED;
					break;
				case 8:
					color = Color.WHITE;
					break;
				case 9:
					color = Color.YELLOW;
					break;
				}
				ball.SetColor(color);
				ball.radius = 15;
				balls.add(ball);
			}
			
			Iterator<Ball> i = balls.iterator();
			while (i.hasNext()) {
				// update position based on velocity
				Ball ball = i.next();
				if (ball.pos.x <= width && ball.pos.x >= 0)
					ball.pos.x += ball.vel.x * fTimeElapsed;
				else {
					i.remove(); 
					continue;
				}
				if (ball.pos.y <= height && ball.pos.y >= 0)
					ball.pos.y += ball.vel.y * fTimeElapsed;
				else {
					i.remove(); 
					continue;
				}
				if (ball.hit && ball.radius < 100) {
					ball.radius += .1f * fTimeElapsed;
				}
				else if (ball.hit && ball.radius >= 100) {
					// remove ball
					i.remove();
					continue;
				}
				
				canvas.drawCircle(ball.pos.x, ball.pos.y, ball.radius, ball.paint);
			}
			if (userBall != null && userBall.radius < 100) {
				userBall.hit = true;
				balls.add(userBall);
			}
			// check for collisions
			for (Ball ball : balls){
				for (Ball ball2 : balls){
					if(ball2 == null || ball == null)
						continue;
					// only care if one ball is hit, not 0 or both.
					if(!(ball2.hit ^ ball.hit))
						continue;
					double distance = Math.sqrt(Math.pow((double) (ball.pos.x - ball2.pos.x), 2) + Math.pow((double) (ball.pos.y - ball2.pos.y), 2));
						if (distance <= (double)(ball.radius + ball2.radius)){
							Ball hitBall = ball.hit ? ball2 : ball;
							// collision
							hitBall.vel.x = 0;
							hitBall.vel.y = 0;
							hitBall.hit = true;
							ballScore++;
							
							//play sound
							if (soundEnabled) {
								sounds.play(sPing, 1.0f, 1.0f, 0, 0, 1.5f);
							}
							
							//update score
							if (sv != null) {
								sv.setText("Balls: "+ballScore);
							}
					}
					break;
				}
			}
			// check for no explosion
			Boolean bNoLiveBalls = true;
			for (Ball ball : balls){
				if(ball.hit){
					bNoLiveBalls = false;
					break;
				}			
			}
			
			// display toast if there are no live explosions
			Toast toast = null;
			if(bNoLiveBalls && userBall != null){
				//display toast
				toast = Toast.makeText(getContext(), "You set off a chain reaction of " + ballScore + " explosions! Press Reset to go again." , 5);
				toast.show();
			}
			else {
				toast = null;
			}
				
			invalidate();
		}
		
	    public boolean onTouch(View view, MotionEvent event) {
	        // if(event.getAction() != MotionEvent.ACTION_DOWN)
	        // return super.onTouchEvent(event);
	        Point point = new Point(event.getX(), event.getY());
	        Point vel = new Point(0,0);
	        if (userBall == null ){
	        	userBall = new Ball(point,vel);
	        	userBall.radius = 15;
	        	userBall.SetColor(Color.WHITE);
	        }
	        invalidate();
	        Log.d(TAG, "point: " + point);
	        return true;
	    }
		public void setBallCount(String ballCount){
			if( ballCount == null || ballCount.isEmpty()){
				return;
			}
			this.ballCount = Integer.parseInt(ballCount);
			Log.d("DrawView", "Ball Count = " + ballCount);
		}
		public void setBallSpeed(String ballSpeed){
			if( ballSpeed == null || ballSpeed.isEmpty()){
				return;
			}
			this.ballSpeed = Integer.parseInt(ballSpeed);

			Log.d("DrawView", "Ball Speed = " + ballSpeed);
		}
		
		
	}