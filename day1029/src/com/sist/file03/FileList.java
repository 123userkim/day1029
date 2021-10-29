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
	
	//jlist�� �����͸� ���� ���͸� ����
	Vector vector;
	
	public FileList() {
		//���͸� ����
		vector = new Vector<String>();
		
		// c:\javastudy\1025����� ���ϸ������ JList�� �����
		
		//���丮 (����)�� File ��ü�� �����
		File dir = new File(path);
		String []arr= dir.list();
		
		//���丮�� ���ϸ���� ���Ϳ� ��ƿ�
		for( String str  :arr ) {
			vector.add(str);
		}
		
		
		//vactor�� �������� JList�����
		list = new JList<Vector<String>>(vector);
		
		//jlist�� �̺�Ʈ�� ����մϴ�. -> ���� �����
		
		list.addMouseListener(this);
		
		
		//jlist�� jtxtarea�� ���������� �����ӿ� �ٷ� ���� �ʰ�, jscrollpan���� ���μ�
		
		JScrollPane jsp_list = new JScrollPane(list);
		
		
		
		jta = new JTextArea();
		JScrollPane jsp_area = new JScrollPane(jta);
		
		
		JButton btn_delete = new JButton("����");
		
		//��ư�� ���ؼ� �̺�Ʈ�� ���
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

		//jList���� ������ file�̸��� ������
		int idx = list.getSelectedIndex();
		String fileName =(String)vector.get(idx);
		System.out.println(fileName);
		
		try {
			//������ �����̸���, ������ �ִ� ��θ� ���ļ� File��ü OR String���� ����� 
			//������ �б����� ��Ʈ���� ������
			//"c:/myData/"
			FileReader fr= new FileReader(path+"/"+fileName);
			
			//������ ������ ��� �о�ͼ� �����ϱ� ���� ���ڿ� ������ ���� �� ""�ʱ�ȭ
			String data="";
			
			int ch;
			
			while(true) {
				ch = fr.read();
				if(ch == -1) {
					break;
				}
				
				//������ ���� �ƴϸ� �о�� ���ڸ� ���̤��Ϳ� ����
				data= data+(char)ch;						
			}
			
			//���� JList���� ������ ������ �� ������ Ż��
			//���� ������ ������ ��� ������ ���ڿ� ���� data�� ����Ǿ� ����
			//�� �����͸� �ؽ�Ʈ����� ���
			jta.setText(data);
			fr.close();
			
		}catch(Exception ex) {
			System.out.println("���� �߻� : "+ex.getMessage());
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
		System.out.println("���� ����!");
		//String fileName =  list.getSelectedValue();
		File file = new File(path+"/"+list.getSelectedValue());
		file.delete();
		jta.setText("");
		
		
		//c:/myData ������ ���� ����� �ٽ� �о�ͼ� Vector�� ������ �ٲٰ�,
		//JList�� �ٽ� �׸����� ��û��
		File dir = new File(path);
		String []arr= dir.list();
		
		//���͸� ������ �����, �ٽ� �о�� ���� ����� ����� 
		vector.clear();
		for(String f : arr) {
			vector.add(f);
			
		}
		
		//jList�� ����� �ٲ� ������ ������� �ٽ� �׷��ֵ��� ��û
		list.updateUI();
		
		
		JOptionPane.showMessageDialog(this, "������ ���� ���� ����!");
		
		
	}
}
