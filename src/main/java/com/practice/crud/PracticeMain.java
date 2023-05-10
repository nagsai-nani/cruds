package com.practice.crud;

public class PracticeMain {
	
	
	static String name;
	public void harsha()
	{
		name="harsha";
		System.out.println(name);
	}
 	public static void main(String[] args) {
		PracticeMain main=new PracticeMain();
		main.harsha();
		System.out.println(PracticeMain.name="sai");
	}

}
