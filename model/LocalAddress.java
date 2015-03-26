package model;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class LocalAddress {

	public String getLocalIP(){
		try {
			InetAddress IP = InetAddress.getLocalHost();
			return IP.getHostAddress();
		} catch (Exception e){
			return null;
		}
	}

}
