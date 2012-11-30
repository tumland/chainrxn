/* 
 * This project authored by Torrey Umland
 * p1rat33r@gmail.com
 * 2/11/2012
 */

package com.coffeejawa.chainrxn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ChainRxn extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Set full screen view
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);+

		//setContentView(R.layout.main);
		Intent splashIntent = new Intent(ChainRxn.this, SplashScreen.class);
		ChainRxn.this.startActivity(splashIntent);
		
	}

}