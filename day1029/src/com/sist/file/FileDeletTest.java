package com.sist.file;

import java.io.File;

public class FileDeletTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//c:/Mydata/�޸�.txt ������ �����Ѵ� ���α׷��� �ۼ��ϱ�
		
		File file = new File("c:/MyData/�޸�.txt");
		
		file.delete();
		System.out.println("���� ����!");
	}

}
