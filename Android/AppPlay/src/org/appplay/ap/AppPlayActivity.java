package org.appplay.ap;

import java.util.List;

import org.appplay.ap.util.SystemUiHider;
import org.appplay.lib.AppPlayBaseActivity;
import org.appplay.lib.AppPlayMetaData;
import org.appplay.platformsdk.PlatformSDK;
import org.appplay.platformsdk.PlatformSDKCreater;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class AppPlayActivity extends AppPlayBaseActivity
{
	// power
	private PowerManager powerManager = null; 
	private WakeLock wakeLock = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		// super create     	
        super.onCreate(savedInstanceState);       
        
		this.powerManager = (PowerManager)this.getSystemService(Context.POWER_SERVICE);
		this.wakeLock = this.powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "My Lock");
        
        Log.d("appplay.ap", "begin - AppPlayActivity::onCreate"); 
        
        AppPlayMetaData.Initlize(getApplicationContext());
		
		if (AppPlayMetaData.sIsNettable)
			PlatformSDK.sThePlatformSDK = PlatformSDKCreater.Create(this);
		else
			Show_GLView();
		
		 Log.d("appplay.ap", "end - AppPlayActivity::onCreate"); 
    }
    
    @Override
	protected void onPause() 
    {  
    	Log.d("appplay.ap", "AppPlayActivity::onPause");
		 
	    super.onPause();
	     
	    if (null!=wakeLock)
	    	wakeLock.release(); 
    }

	 @Override
	 protected void onResume() 
	 {	 
		 super.onResume();

		 if (null!=wakeLock)
			 wakeLock.acquire(); 
	 }
    
}
