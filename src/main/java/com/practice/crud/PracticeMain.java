package com.practice.crud;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.practice.crud.models.User;

public class PracticeMain {
public void it() {
	
	List<String> str=new ArrayList<String>();
	str.add("ha");str.add("na");str.add("wi");str.add("um");str.add("esr");str.add("glp");
	Iterator var10=str.iterator();
	while (var10.hasNext()) {
		User object = (User) var10.next();
		System.out.println(object);
		
	}

}
	
	static String name;
	public void harsha()
	{
		name="harsha";
		System.out.println(name);
	}
 	public static void main(String[] args) {
		PracticeMain main=new PracticeMain();
		main.harsha();
		main.it();
		System.out.println(PracticeMain.name="sai");
	}

}
