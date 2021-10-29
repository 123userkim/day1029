package com.sist.file03;
import java.awt.BorderLayout;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;

public class FileList extends JFrame implements MouseListener,ActionListener {
	JList<Vector<String>> list;
	JTextArea jta;
	String path = "c:/myData";
	
	//jlist의 데이터를 위한 벡터를 선언
	Vector vector;
	
	public FileList() {
		//백터를 생성
		vector = new Vector<String>();
		
		// c:\javastudy\1025경로의 파일목록으로 JList를 만들기
		
		//디렉토리 (폴더)도 File 객체로 만들기
		File dir = new File(path);
		String []arr= dir.list();
		
		//디렉토리의 파일목록을 벡터에 담아요
		for( String str  :arr ) {
			vector.add(str);
		}
		
		
		//vactor의 내용으로 JList만들기
		list = new JList<Vector<String>>(vector);
		
		//jlist의 이벤트를 등록합니다. -> 내가 담당함
		
		list.addMouseListener(this);
		
		
		//jlist도 jtxtarea와 마찬가지로 프레임에 바로 담지 않고, jscrollpan으로 감싸서
		
		JScrollPane jsp_list = new JScrollPane(list);
		
		
		
		jta = new JTextArea();
		JScrollPane jsp_area = new JScrollPane(jta);
		
		
		JButton btn_delete = new JButton("삭제");
		
		//버튼에 대해서 이벤트를 등록
		btn_delete.addActionListener(this);
		
		add(jsp_list,BorderLayout.WEST);
		add(jsp_area,BorderLayout.CENTER);
		
		add(btn_delete, BorderLayout.SOUTH);
		
		setSize(800,600);
		setVisible(true);
	}


	


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		//jList에서 선택한 file이름을 가져옴
		int idx = list.getSelectedIndex();
		String fileName =(String)vector.get(idx);
		System.out.println(fileName);
		
		try {
			//선택한 파일이름과, 파일이 있는 경로를 합쳐서 File객체 OR String으로 만들어 
			//파일을 읽기위한 스트림을 생성함
			//"c:/myData/"
			FileReader fr= new FileReader(path+"/"+fileName);
			
			//파일의 내용을 모두 읽어와서 누적하기 위한 문자열 변수를 생성 후 ""초기화
			String data="";
			
			int ch;
			
			while(true) {
				ch = fr.read();
				if(ch == -1) {
					break;
				}
				
				//파일의 끝이 아니면 읽어온 문자를 데이ㅣ터에 축전
				data= data+(char)ch;						
			}
			
			//현재 JList에서 선택한 파일을 다 읽으면 탈출
			//현재 선택한 파일의 모든 내용은 문자열 변수 data에 저장되어 있음
			//그 데이터를 텍스트에리어에 출력
			jta.setText(data);
			fr.close();
			
		}catch(Exception ex) {
			System.out.println("예외 발생 : "+ex.getMessage());
		}
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("삭제 성공!");
		//String fileName =  list.getSelectedValue();
		File file = new File(path+"/"+list.getSelectedValue());
		file.delete();
		jta.setText("");
		
		
		//c:/myData 폴더의 파일 목록을 다시 읽어와서 Vector의 내용을 바꾸고,
		//JList를 다시 그리도록 요청함
		File dir = new File(path);
		String []arr= dir.list();
		
		//백터를 깨끗이 지우고, 다시 읽어온 파일 목록을 담아줌 
		vector.clear();
		for(String f : arr) {
			vector.add(f);
			
		}
		
		//jList의 모양을 바뀐 백터의 모습으로 다시 그려주도록 요청
		list.updateUI();
		
		
		JOptionPane.showMessageDialog(this, "선택한 파일 삭제 성공!");
		
		
	}
}
