package com.demo.testio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestIO {
	
	private static Logger LOG = LoggerFactory.getLogger(TestIO.class);

	public static void main(String[] args){
//		createFile();
//		readFile();
//		copyFile();
//		objectStream();
		dataStream();
	}
	
	public static void dataStream(){
		URI uri;
		File f;
		DataInputStream dataInput = null;
		DataOutputStream dataOutput = null;
		try{
			uri = new URI("file:/E:/data.txt");
			f = new File(uri);
			dataOutput = new DataOutputStream(new FileOutputStream(f));
			Pojo p = new Pojo();
			p.setCount(1);
			p.setName("1");
			dataOutput.writeUTF(p.getName());
			dataOutput.writeInt(p.getCount());
			dataOutput.flush();
			dataInput = new DataInputStream(new FileInputStream(f));
			String name = dataInput.readUTF();
			int count = dataInput.readInt();
			System.out.println("name:"+name);
			System.out.println("count:"+count);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
				dataInput.close();
				dataOutput.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * object对象
	 */
	public static void objectStream(){
		URI uri = null;
		File f = null;
		ObjectInputStream objectInput = null;
		ObjectOutputStream objectOutput = null;
		try {
			uri = new URI("file:/E:/student.txt");
			f = new File(uri);
			objectOutput = new ObjectOutputStream(new FileOutputStream(f));
			objectOutput.writeObject(new Student("fwd", 27));
			objectOutput.writeObject(new Student("gxm", 27));
			
			objectInput = new ObjectInputStream(new FileInputStream(f));
//			System.out.println(objectInput.readObject());
//			System.out.println(objectInput.readObject());
			Student s = (Student)objectInput.readObject();
			System.out.println(s.toString());
			LOG.info("end");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// TODO: handle finally clause
			try {
				objectOutput.close();
				objectInput.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 复制文件
	 */
	public static void copyFile(){
		URI uri = null;
		File fIn = null;
		File fOut = null;
		FileInputStream fiStream = null;
		FileOutputStream foStream = null;
		byte[] b = new byte[512];
		try{
			uri = new URI("file:/E:/nclcore.jar");
			fIn = new File(uri);
			uri = new URI("file:/E:/nclcorebak.jar");
			fOut = new File(uri);
			fiStream = new FileInputStream(fIn);
			foStream = new FileOutputStream(fOut);
			
			while(fiStream.read(b)!=-1){
				foStream.write(b);
			}
			
			LOG.info("end");
			
		}catch(URISyntaxException ex){
			LOG.error("copyFile Exception", ex);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				fiStream.close();
				foStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 读文件
	 */
	public static void readFile(){
		try{
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
		}catch(Exception ex){
			LOG.error("readFileException", ex);
		}
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

class Student implements Serializable{

	/**
	 * autu generated
	 */
	private static final long serialVersionUID = 2275373136784309264L;
	
	private String name;
	private int age;
	
	Student(String name, int age){
		this.name = name;
		this.age = age;
	}
	
	@Override
	public String toString(){
		String str = "Student {name:"+name+", age:"+age+"}";
		return str;
	}
	
}

class Pojo{
	private String name;
	private int count;
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setCount(int count){
		this.count = count;
	}
	public int getCount(){
		return count;
	}
}
