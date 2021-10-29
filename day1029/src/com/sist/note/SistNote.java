package com.sist.note;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;




//주메뉴 "파일"
//부메뉴 "새파일" "열기" "저장"메뉴만 만들기

public class SistNote extends JFrame  implements ActionListener{
	JTextArea jta;   //메모장의 입출력을 위해서 열어줄의 문장을 입력할 수 있는 JTextArea를 멤버변수로 선언
	
	//저장할 파일이름과 열어올 파일이름을 선택하기 위한 JFileChooser를 멤버변수로 설정
	JFileChooser jfc;
 
	String fileName = "제목없음";
	
	//생성자에서 생성해서 프레임에 담아줌
	public SistNote() {
		jta = new JTextArea();
		
		
		
		//JFileChooser 객체를 생성함
		jfc = new JFileChooser("c:/Mydata");
		
		
		//add(jta);
		//JTextArea를 바로 프레임에 담으면 화면을 벗어난 글자가 안보임
		//JTextArea를 바로 담지 않고, 스크롤을 만들어주는 JScorllPane으로 감싸서 프레임에 담기
		
		//화면의 모든 요소는 companent의 후손
		JScrollPane jsp= new JScrollPane(jta);
		add(jsp);
		
		//프레임의 크기를 설정하고, 보여주기
		setSize(800,600);
		setVisible(true);
		setTitle(fileName);
		
		
		JMenuBar jmb = new JMenuBar();
		
		JMenu mn_file= new JMenu("파일");
		
		JMenuItem file_new= new JMenuItem("새파일");
		JMenuItem file_open = new JMenuItem("열기");
		JMenuItem file_save = new JMenuItem("저장");
		 
		
		mn_file.add(file_new);
		mn_file.add(file_open);
		mn_file.add(file_save);
		
		jmb.add(mn_file);
		
		setJMenuBar(jmb);
		//부메뉴에 대해서 메뉴를 눌렀을 때 어떤 일을 하도록 이벤트를 등록함
		  
		//매개변수로 이벤트처리 담당자(객체)전달, 
		//이 클래스 SistNote자신이 처리하기 위해서 this전달
		
		file_new.addActionListener( this );
		file_open.addActionListener(this);
		file_save.addActionListener(this);
		
		/*
		jta.setText("안녕하세요");
		try {
			Thread.sleep(3000);
		}catch(Exception e) {
			
		}
		jta.setText(""); //모두 지우기
		*/
	
	}

	
	//부메뉴를 누르면 이게 동작됨 -> 그러기 위해선 이벤트를 등록
	//어떤 걸 눌렀는지에 따라서 해야하는 일이 다른데, 그걸 여기서 파악하기
	//ActionEvent의 getActionCommand 메소드를 이용해서 눌러진 메뉴의
	//글자를 가져옴  
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		
		
		//눌러진 메뉴에 따라 각각 일처리
		if(cmd.equals("새파일")) {  //빈칸까지도 똑같이 물어봐야함
			
			jta.setText("");
			fileName = "제목없음";
			setTitle(fileName);
			
		}else if(cmd.equals("열기")) {
		
			
			
			try {
				//다이얼로그를 띄움
				int re = jfc.showOpenDialog(this);
				
				//취소를 누르지 않고 열기를 눌렀을 때에 파일의 내용을 처리
				if(re ==0 ) {
					
					//다이얼로그에서 선택한 파일정보를 가져옴
					File file= jfc.getSelectedFile();
					 
					//컴의 메모리로 읽어들이기위한 스트림 생성
					FileReader fileReader = new FileReader(file);
					
					//파일의 모든 내용을 담기위한 문자열 변수 만들기
					String data=""; //한 글자씩 읽어와 누적학 위한 "초기화"
					int ch;
					while(true) {
						ch = fileReader.read();
						
						if(ch == -1) {
							break;
						}
						data= data+(char)ch;
					}
					//파일의 내용을 모두 읽어들인 후 반복문 탈출함
					//파일의 모든 내용은 String변수 data에 담겨짐
					//data의 내용을 txtArea에 설정
					jta.setText(data);
					
					JOptionPane.showMessageDialog(this, "파일을 읽어오기 성공!");
					
					
					//읽어온 파일의 정보를 담고있는 file객체로부터 파일명을 뽑아서 멤버변수 filename에 담기
					fileName = file.getName();
					//String []arr= fileName.split(".");
					//fileName = arr[0];
					
					fileName= fileName.substring(0, fileName.indexOf("."));
					
					setTitle(fileName);
				
				}
			}catch (Exception ex) {
				System.out.println("예외발생:"+ex.getMessage());
			}
			
		}else if(cmd.equals("저장")) {

			//저장메뉴를 눌렀을 때 어디에 어떤 파일이름을 저장할 것인지
			//다이얼로그 띄우기
			int re=jfc.showSaveDialog(this);  //this: 이 다이얼로그는 어떤 프레임위에서 띄울 것인지 전달
			//현재 이프레임 SistNote에서 띄우라는 의미
			// showSaveDialog에서 저장or취소를 누를 수가 있음
			//이 메소드는 저장을 누르면 0, 취소를 누르면 1을 반환
			//그 반환하는 값을 변수에 담아, 그 값에 따라서 그 다음 일처리를 다르게 할 수 있음
		
			if(re==0) {
				//저장하기를 눌렀을 때 명령어들이 동작하도록함
				//선택한 파일의 정보를 읽어옴
				File file= jfc.getSelectedFile();
				
				//그 선택한 파일에 텍스트에리어 쓰여진 내용을 출력하기 위한 스트림을 생성
				//문자단위의 출력을 위한 writer의후손인 fileWriter을 이용함
				//입출력관련된 모든 생성자와 메소드는 예외를 포함함
				//그 예외들은 RunTimeException의 후손이 아님-> 사용자가 처리
			
			try {
				FileWriter fw = new FileWriter(file);
				
				//TextArea에 씌여진 내용을 읽어와서 파일로 출력
				fw.write(jta.getText());
				fw.close();
				
				//System.out.println("파일로 저장했습니다");
				
				JOptionPane.showMessageDialog(this, "저장 성공!");
				
				//저장한 파일이름으로 제목표시줄을 설정함
				//파일의 정보를 갖고있는 객체 File file로부터 파일이름을 뽑아옴
				//fileName = file.getName(); ->메모.txt까지 다 감
				//.을 기준으로 파일명만 뽑아옴
			
				//읽어온 파일의 정보를 담고있는 file객체로부터 파일명을 뽑아서 멤버변수 filename에 담기
				fileName = file.getName();
				//System.out.println("파일명:"+fileName);
				//String []arr= fileName.split(".");
				//fileName = arr[0];
				fileName= fileName.substring(0, fileName.indexOf("."));
				
				setTitle(fileName);
				
			}catch(Exception ex) { 
				System.out.println("예외발생 : "+ex.getMessage());
			}
			
			}
		}
	}
	
	
	
}
