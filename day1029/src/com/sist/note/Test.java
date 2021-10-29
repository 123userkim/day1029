package com.sist.note;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String data= "메모.txt";
		
		/*
		String []arr= data.split(".");
		System.out.println(arr[0]);
		System.out.println(arr[1]);
		*/
		
		//"." 으로 분리가 불가능함 
		
		//마침표가 있는 위치를 알아내고, 문자열의 처음부터 마침표까지 추출
		String result= data.substring(0,data.indexOf("."));
		System.out.println(result);
	}

}
