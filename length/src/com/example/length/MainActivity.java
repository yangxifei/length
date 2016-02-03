package com.example.length;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Thread downCompareLoadThread;
	int length;
	TextView show_tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		downCompareLoadThread = new Thread(mdownCompareRunnable);
		downCompareLoadThread.start();
		show_tv = (TextView)findViewById(R.id.show_tv);
//		ScrollView aa;
//		ScaleGestureDetector a;
	//	Person aa = new Person("a","b",1);
		Intent intent = new Intent();
		Bundle bun = new Bundle();
//		bun.putSerializable("person", aa);
//		bb.putExtras(bun);
//		bun.putParcelable(key, value)
		
		//0.5M 1024*1024*8 =32M  
		Bitmap bitmap = Bitmap.createBitmap(480, 1200, Config.ARGB_8888);
		//Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
		bun.putParcelable("bitmap", bitmap);
		intent.putExtras(bun);
		
		
		
	}
	
	private Handler mHandler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 100:
				show_tv.setText("文件大小==="+length);
				break;
			default:
				break;
			}
		};
	};
	private Runnable mdownCompareRunnable = new Runnable() {	
		@Override
		public void run() {
			try {
				String aa = "http://123.57.64.37:8810/Uploads/Video/a.mp4";
				URL url = new URL(aa);

				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.setConnectTimeout(5000);
				conn.setDoInput(true);
				conn.connect();
			
				length = conn.getContentLength();
				
				System.out.println("文件大小==="+length);
				
				{
					mHandler.sendEmptyMessage(100);
				
				}
				
				conn.disconnect();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch(IOException e){
				e.printStackTrace();
			}
		}
	};


	
}
