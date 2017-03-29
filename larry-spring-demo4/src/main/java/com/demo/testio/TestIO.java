package com.demo.testio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestIO {
	
	private static Logger LOG = LoggerFactory.getLogger(TestIO.class);

	public static void main(String[] args){
//		TestIO t = new TestIO();
//		URL url = t.getClass().getResource("");
//		LOG.info(""+url.toString());
//		String str = t.getClass().getResource("/")+"";
//		LOG.info("str:"+str);
		try {
			readFile();
		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void readFile() throws URISyntaxException, IOException{
		URI uri = new URI("file:/E:/readFile.txt");
		File f = new File(uri);
		InputStream inputStream = null;
		inputStream = new FileInputStream(f);
		int count = 0;
		while(inputStream.read() != -1){
			count++;
		}
		LOG.info("read length:"+count);
		inputStream.close();
	}
	
	/**
	 * 创建文件
	 */
	public static void createFile(){
		URI u = null;
		try {
			u = new URI("file:/E:/create.txt");
			System.out.println(u.toString());
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		File f = new File(u);
		LOG.info("exit"+f.exists());
		try {
			boolean flag = f.createNewFile();
			LOG.info("createNewFile:"+flag);
			long space = f.getTotalSpace();
			LOG.info("space:"+space+"B");
			LOG.info("space:"+space/(1024)+"KB");
			LOG.info("space:"+space/(1024*1024)+"MB");
			LOG.info("space:"+space/(1024*1024*1024)+"GB");
			flag = f.delete();
			LOG.info("delete:"+flag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
