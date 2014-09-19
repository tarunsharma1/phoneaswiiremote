
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ServerHandshake;
import java.awt.Robot;
import java.awt.AWTException;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
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
		Robot robot1;
		try {
			robot1 = new Robot();
			//System.out.println(message);
			if(message.equalsIgnoreCase("w"))
			{
				 robot1.keyPress(KeyEvent.VK_W);
				 Thread.sleep(18);
				 robot1.keyRelease(KeyEvent.VK_W);
			}
			else if(message.equalsIgnoreCase("s"))
			{
				 robot1.keyPress(KeyEvent.VK_S);
				 Thread.sleep(18);
				 robot1.keyRelease(KeyEvent.VK_S);
			}
			else if(message.equalsIgnoreCase("a"))
			{
				 robot1.keyPress(KeyEvent.VK_A);
				 Thread.sleep(18);
				 robot1.keyRelease(KeyEvent.VK_A);
			}
			else if(message.equalsIgnoreCase("d"))
			{
				 robot1.keyPress(KeyEvent.VK_D);
				 Thread.sleep(18);
				 robot1.keyRelease(KeyEvent.VK_D);
			}
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("recieved :"+message);
 catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void onClose(int code, String reason, boolean remote) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onError(Exception ex) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String args[])throws URISyntaxException, AWTException
	{
		System.out.println("hello world");
		Robot robot=new Robot();
		trial c = new trial( new URI( "ws://192.168.1.3:8080" ), new Draft_10() ); // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
		c.connect();
		
	}

}
