package com.demo.testenum;

public class TestEnum {
	
	public enum EnumTest{
		MON, TUE
	}
	
	public static void main(String[] args){
		System.out.println(EnumTest.MON);
		EnumTest e = EnumTest.TUE;
		switch(e){
		case MON:
			System.out.println("Today is Monday");
			break;
		case TUE:
			System.out.println("Today is Tuesday");
			break;
		}
	}

}