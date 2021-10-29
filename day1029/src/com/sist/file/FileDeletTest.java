package com.sist.file;

import java.io.File;

public class FileDeletTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//c:/Mydata/메모.txt 파일을 삭제한는 프로그램을 작성하기
		
		File file = new File("c:/MyData/메모.txt");
		
		file.delete();
		System.out.println("삭제 성공!");
	}

}
