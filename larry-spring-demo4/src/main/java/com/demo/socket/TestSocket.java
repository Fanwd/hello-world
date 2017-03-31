package com.demo.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestSocket {
	
	public static void main(String[] args){
		try{
			ServerSocket server = new ServerSocket(9898);
			System.out.println("init");
			while(true){
				Socket client = server.accept();
				System.out.println("accept");
				InputStream is = client.getInputStream();
				byte[] b = new byte[8];
				is.read(b);
				String lenStr = new String(b, "UTF-8");
				System.out.println("len:"+lenStr);
				int len = Integer.parseInt(lenStr);
				byte[] data = new byte[len];
				is.read(data);
				String dataStr = new String(data, "GBK");
				System.out.println("data:"+dataStr);
				OutputStream os = client.getOutputStream();
				String outData = "00000007success";
				os.write(outData.getBytes("UTF-8"));
				
				is.close();
				os.close();
				client.close();
			}
//			server.close();
			
		}catch(UnknownHostException ex){
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
