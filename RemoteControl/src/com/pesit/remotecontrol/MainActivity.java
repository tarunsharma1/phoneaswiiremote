package com.pesit.remotecontrol;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {
	private SensorManager senSensorManager;
	private Sensor senAccelerometer;
	private long lastUpdate = 0;
	private float last_x, last_y, last_z;
	private static final int SHAKE_THRESHOLD = 600;
	
	Socket socket = null;
	trial c;	
	Button ip;
	Button a;
	Button s;
	Button d;
	Button w;
	Button acc;
	TextView i;
	String ipadd;
	WebSocketClient websoc;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		StrictMode.setThreadPolicy(policy); 
	
		
		ip=(Button)findViewById(R.id.ip);
		d=(Button)findViewById(R.id.d);
		s=(Button)findViewById(R.id.s);
		a=(Button)findViewById(R.id.a);
		w=(Button)findViewById(R.id.w);
		i=(TextView)findViewById(R.id.ipval);
		acc=(Button)findViewById(R.id.acc);		
		
		w.setOnClickListener(new OnClickListener() {
			
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//send(1);
				//c.send("w");
				send("w");
			}
		});
		a.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				send("a");
			}
		});
		s.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
		// TODO Auto-generated method stub
				send("s");
			}
			});
		d.setOnClickListener(new OnClickListener() {
	
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				send("d");
			}
		});
		ip.setOnClickListener(new OnClickListener() {
	
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ipadd=i.getText().toString();
				//ipadd="192.168.43.41";
	   
				//InetAddress serverAddr = InetAddress.getByName(ipadd);  
				//socket = new Socket(ipadd,8080);
				System.out.println("connected");
			
			//c = new trial( new URI( "ws://192.168.43.41:8080" ));
			connectWebSocket();
		
			}
		});
		acc.setOnClickListener(new OnClickListener() {
	
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
				senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	    
				senSensorManager.registerListener(MainActivity.this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	void send(int i)
	{
		try {
			System.out.println("Clicked w");
			
		    
		   // socket.sendUrgentData(i);

		    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		    out.println(3);
		} catch (UnknownHostException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		    System.out.println("catch unknownhost");
		    //response = "UnknownHostException: " + e.toString();
		   } catch (IOException e) {
		    // TODO Auto-generated catch block
			   System.out.println("IO exception");
			   e.printStackTrace();
		    //response = "IOException: " + e.toString();
		   }

	}
	private void connectWebSocket() {
		  URI uri;
		  try {
		    uri = new URI("ws://"+ipadd+":8080");
		  } catch (URISyntaxException e) {
		    e.printStackTrace();
		    return;
		  }

		  websoc = new WebSocketClient(uri) {
		    @Override
		    public void onOpen(ServerHandshake serverHandshake) {
		      Log.i("Websocket", "Opened");
		      websoc.send("Hello from " + Build.MANUFACTURER + " " + Build.MODEL);
		    }

		    @Override
		    public void onMessage(String s) {
		      final String message = s;
		      runOnUiThread(new Runnable() {
		        @Override
		        public void run() {
		         // TextView textView = (TextView)findViewById(R.id.messages);
		          //textView.setText(textView.getText() + "\n" + message);
		        }
		      });
		    }

		    @Override
		    public void onClose(int i, String s, boolean b) {
		      Log.i("Websocket", "Closed " + s);
		    }

		    @Override
		    public void onError(Exception e) {
		      Log.i("Websocket", "Error " + e.getMessage());
		    }
		  };
		  websoc.connect();
		}	
	public void send(String msg) {
		 // EditText editText = (EditText)findViewById(R.id.message);
		  websoc.send(msg);
		  //editText.setText("");
		}

	@Override
	public void onSensorChanged(SensorEvent sensorEvent) {
		  Sensor mySensor = sensorEvent.sensor;
		  
		    if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
		    	float x = sensorEvent.values[0];
		        float y = sensorEvent.values[1];
		        float z = sensorEvent.values[2];
		 
		        long curTime = System.currentTimeMillis();
		 
		        if ((curTime - lastUpdate) > 100) {
		            long diffTime = (curTime - lastUpdate);
		            lastUpdate = curTime;
		 
		            //float speed = <span class="skimlinks-unlinked">Math.abs(x</span> + y + z - last_x - last_y - last_z)/ diffTime * 10000;
		 
		            //if (speed > SHAKE_THRESHOLD) {
		 
		//            }
		 
		            last_x = x;
		            last_y = y;
		            last_z = z;
		            System.out.println("x acc value : "+x);
		            if(x < -0.55)
		            {
		            //	send("w");
		            }
		            if(z > 0.25)
		            {
		            	//send("s");
		            }
		            if(y > 1)
		            {
		            	send("d");
		            	send("d");
		            	send("d");
		            }
		            if(y < 1)
		            {
		            	send("a");
		            	send("a");
		            	send("a");
		            }
		 
		 
		 
		        }
		    }
}
	 
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	 
	}
	protected void onPause() {
	    super.onPause();
	    senSensorManager.unregisterListener(this);
	}
	protected void onResume() {
	    super.onResume();
	    //senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	}
}
