package com.sist.draw;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.text.AttributeSet.ColorAttribute;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.awt.*;

//MyFrameŬ���� �ڽ��� "JMenuItem"�� ���������� "�̺�Ʈó�� �����"�� �ǵ��� �ϱ� ���Ͽ� 
//ActionListener�������̽��� �����ϵ��� �մϴ�.
public class MyFrame extends JFrame implements ActionListener {
	private LinePanel lp;
	
	//������ ���ϸ�� ����� ���ϸ��� ������ �� �ֵ��� �ϴ� JFileChooser�� �ɹ������� �����մϴ�.
	JFileChooser jfc;
	
	//������ �����ϱ� ���� ���̾�α� JChooerChooser�� ��������� ����
	
	JColorChooser jcc;
	

	
	public MyFrame() {
		lp = new LinePanel();
		add(lp);
		
		//JFileChooser ��ü�� �����մϴ�.
		jfc = new JFileChooser("c:/myData");
		
		//jcolorChooser ��ü�� ������
		jcc= new JColorChooser();
		
		//�޴��ٸ� �����մϴ�.
		JMenuBar jmb = new JMenuBar();
		
		//�ָ޴� "����"�� �����մϴ�.
		JMenu  mn_file = new JMenu("����");
		
		//�ָ޴� "�׸��⵵��"�� �����մϴ�.
		JMenu mn_draw = new JMenu("�׸��⵵��");
		
		//�ָ޴� "�׸��� ����"�� �����մϴ�.
		JMenu mn_color = new JMenu("�׸��� ����");
		
		//�θ޴� "������"�� �����մϴ�.
		JMenuItem file_new = new JMenuItem("������");
		
		//�θ޴� "����"�� �����մϴ�.
		JMenuItem file_open = new JMenuItem("����");
		
		//�θ޴� "����"�� �����մϴ�.
		JMenuItem file_save = new JMenuItem("����");
		
		//"�θ޴�"���� "�ָ޴�"�� ��ƿ�
		mn_file.add(file_new);
		mn_file.add(file_open);
		mn_file.add(file_save);
		
		
		//�ָ޴� "�׸��⵵��"�� ��� �θ޴����� ������
		JMenuItem draw_line = new JMenuItem("��");
		JMenuItem draw_rect = new JMenuItem("�簢��");
		JMenuItem draw_oval = new JMenuItem("��");
		
		//��,�簢��,�� �θ޴����� "�׸��⵵��"�ָ޴��� ��ƿ�
		mn_draw.add(draw_line);
		mn_draw.add(draw_rect);
		mn_draw.add(draw_oval);		
		
		
		//�ָ޴� �׸��� ������ ��� �θ޴����� �����
		JMenuItem color_red = new JMenuItem("����");
		JMenuItem color_bule = new JMenuItem("�Ķ�");
		JMenuItem color_green = new JMenuItem("�ʷ�");
		JMenuItem color_other = new JMenuItem("�ٸ� ����");
		
		//����, �Ķ�, �ʷ�, �ٸ� ������ �׸��� ���� �ָ޴��� ���
		mn_color.add(color_red);
		mn_color.add(color_bule);
		mn_color.add(color_green);
		mn_color.add(color_other);
		
		//"�ָ޴�"�� "�޴���"�� ��ƿ�
		jmb.add(mn_file);
		
		//"�׸��⵵��" �ָ޴��� "�޴���"�� ��ƿ�
		jmb.add(mn_draw);
		
		//�׸������ �ָ޴��� �޴��ٿ� ���
		jmb.add(mn_color);
		
		//"�޴���"�� "������"�� �����ؿ�!
		setJMenuBar(jmb);
		
		//������ JMenuItem�� ���Ͽ� �̺�Ʈ�� ����մϴ�.
		file_new.addActionListener(this);
		file_open.addActionListener(this);
		file_save.addActionListener(this);
		
		//�׸��⵵���� �θ޴��鿡 ���ؼ��� �̺�Ʈ�� ����մϴ�.
		draw_line.addActionListener(this);
		draw_rect.addActionListener(this);
		draw_oval.addActionListener(this);
		
		//�׸��� ������ �θ޴��鿡 ���ؼ��� �̺�Ʈ�� �����
		color_red.addActionListener(this);
		color_bule.addActionListener(this);
		color_green.addActionListener(this);
		color_other.addActionListener(this);
		
		setSize(600,400);
		setVisible(true);
		setTitle("���׸���");
		
	}

	
	//������ JMenuItem�� ������ actionPerformed�޼ҵ尡 �����մϴ�.
	@Override
	public void actionPerformed(ActionEvent e) {
		//JMenuItem�߿� � �޴��� ���������� �ľ��ϱ� ���Ͽ� �Ű����� ActionEvent�� getActionCommand()�� �̿��մϴ�.
		String cmd = e.getActionCommand();
		//System.out.println(cmd+"�� ������");
		
		if(cmd.equals("����")) {
			try {  
				System.out.println("�����մϴ�.");
				//��� ��̸����� �������� �����ϵ��� JFileChooser ���̾�α׸� �����
				int re = jfc.showSaveDialog(this);
				
				//"�����ϱ�" ���̾�α׿��� "����"�� �������� �ְ� "���"�� �������� �־��
				//"����"�� ������ 0�� ��ȯ�� �˴ϴ�.
				if(re == 0) {		
					
					//���̾�α׿��� ������ ������ ���� �ɴϴ�
					File file = jfc.getSelectedFile();
					
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
					oos.writeObject(lp.list);					
					oos.close();	
				}
				
			
		}catch (Exception exx) {
			System.out.println("���ܹ߻�:"+exx.getMessage());
		}
		}else if(cmd.equals("����")) {
			// "����"�� ���� ����� JFileChooser�� �̿��Ͽ� ����� ������ �����ϵ��� ���� �� ���ϴ�.
			// "������"�� ���� ��ɵ� ������ ���ϴ�.
			
			String cmd1 = e.getActionCommand();
			try {
				int re = jfc.showSaveDialog(this);
				if(re == 0) {		
					
					File file = jfc.getSelectedFile();
					
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
					
					lp.list = (ArrayList<GraphicInfo>)ois.readObject();
					
					lp.repaint();
				
					ois.close();
				}
				
				
				
			}catch (Exception ex) {
				System.out.println("���ܹ߻�:"+ex.getMessage());
			}
			
		}else if(cmd.equals("������")) {
			//����ڰ� �׸� �׷����� ����ִ� ����Ʈ�� ��� ����
			lp.list.clear();
			
			//�׷����� �׸��� �ǳ��� �ٽ� �׸�
			lp.repaint();
						
			
			
		}else if(cmd.equals("��")) {		//�׸��� ���� �޴��� ���� ó����
			lp.drawType = 0;
		}else if(cmd.equals("�簢��")) {
			lp.drawType = 1;
		}else if(cmd.equals("��")) {
			lp.drawType = 2;
		}else if(cmd.equals("����")) {	//�׸��� ���� �޴��鿡 ���� ó��
			lp.drawcolor = Color.RED;
		}else if (cmd.equals("�Ķ�")) {
			lp.drawcolor = Color.BLUE;
		}else if (cmd.equals("�ʷ�")) {
			lp.drawcolor = Color.GREEN;
		}else if (cmd.equals("�ٸ� ����")) {
			//�ٸ������� �����ϵ��� JColorChooer ���̾�α׸� ���
			Color color =jcc.showDialog(this, "�׸��� ����",Color.RED);
			if(color != null) {
				lp.drawcolor =color;
			}
		}
		
	}
}




// ���α׷��� �����ϴٰ� ����
// ���ܰ� �߻��� ������ ���µ�
// try~catch�� ����ϴ� ������
// ObjectInputStrea, ObjectOutStream�� ����ϱ� �����ΰ���?
//	==> ��, �½��ϴ�.
// ObjectInputStream, ObjectOutStream���� 
// java.io ��Ű���� ��� Ŭ������ �����ڿ� �޼ҵ���� ���ܸ� �����ϰ� �ϰ� �־��.
// ������ �̸� �ڿ� �޼ҵ� �̸��ڿ� ��� throws ~~~~Exception���� �Ǿ� �־��
// �׷��� �̷��� �͵带 ����Ϸ��� �ݵ�� ����ó�� �ؾ� �մϴ�.












