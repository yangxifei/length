package com.example.length;

import java.io.Serializable;

public class Person implements Serializable{

	
	public String name ;
	public String address ;
	public int age ;
	

	public Person(String name,String address,int age)
	{
		this.name = name;
		this.address = address;
		this.age = age;
	}
	
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "age =="+age;
	}
}
