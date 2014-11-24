package com.pesit.remotecontrol;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ServerHandshake;


public class trial extends WebSocketClient{
	public trial( URI serverUri , Draft draft ) {
		super( serverUri, draft );
	}
	public trial(URI serverURI) {
		super(serverURI);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void onOpen(ServerHandshake handshakedata) {
		System.out.println("opened");
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onMessage(String message) {
		
		// TODO Auto-generated method stub
	
		
	}
	@Override
	public void onClose(int code, String reason, boolean remote) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onError(Exception ex) {
		// TODO Auto-generated method stub
		
	}
	//public static void main(String args[])throws URISyntaxException
	//{
		//System.out.println("hello world");
	//	Robot robot=new Robot();
	//	trial c = new trial( new URI( "ws://192.168.43.1:8080" ), new Draft_10() ); // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
		//c.connect();
		
	//}

}
