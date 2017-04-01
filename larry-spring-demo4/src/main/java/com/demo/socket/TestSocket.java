package com.demo.socket;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.databind.util.ArrayBuilders.ByteBuilder;

public class TestSocket {
	
	public static void main(String[] args){
		try{
			ServerSocket server = new ServerSocket(9898);
			System.out.println("init");
//			while(true){
				Socket client = server.accept();
				System.out.println("accept");
				InputStream is = client.getInputStream();
				
				while(true){
					String str = readLine(is);
					System.out.println("line:" + str);
					if(str.length() == 0){
						break;
					}
				}
				System.out.println("end");
				is.close();
//				os.close();
				client.close();
//			}
//			server.close();
			
		}catch(UnknownHostException ex){
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String readLine(InputStream is) throws IOException{
		ByteArrayBuilder build = new ByteArrayBuilder();
		PushbackInputStream pushInput = new PushbackInputStream(is);
		int tmp = 0;
		while((tmp = pushInput.read()) != -1){
			if(13 == tmp){
				int next = pushInput.read();
				if(10 == next){
					break;
				}else{
					pushInput.unread(next);
				}
			}
			build.append(tmp);
		}
		byte[] b = build.toByteArray();
		String retStr = new String(b, "UTF-8");
		return retStr;
	}
	
	private static void test(){
		ServerSocket server = null;
		try{
			server = new ServerSocket(9898);
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
		}finally{
			try{
				server.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}

}
