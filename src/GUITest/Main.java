package GUITest;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * ���α׷� ����� ����Ǵ� ����Ŭ����
 * ���α׷��� �����ϸ� �����ͺ��̽����� ������ �ҷ��´�.
 * @author �±�
 *
 */
public class Main {
	//Ʈ���̳ʿ� ȸ������ ����� ��� �ִ� ����Ʈ
	static List<Member> memberSet = new ArrayList<>();
	public static void main(String[] args) {
		
		//��ü ����� �ҷ��� �� ���� ���θ� ������
		
		//��� ������ ���
		Path path = Paths.get("src/Database/list/Member.csv");
		File memberList = new File(path.toUri());
		try {
			Database.readMember(memberList);
			System.out.println("ȸ�� ��� �ҷ����� ����!");
			System.out.println("ȸ�� : "+memberSet.size()+"��");
		} catch (Exception e) {
			System.out.println("��� �ҷ����� ����!");
		}
		
		
		//ȸ������ ��ũ�ƿ��� �ҷ��´�.
		for(int i=0;i<memberSet.size();i++) {
			try {
				
				String wname=Main.memberSet.get(i).getId()+".csv";
				Path wpath = Paths.get("src/Database/workout/"+wname);
				File wfile = new File(wpath.toUri());
				Database.readWorkout(wfile);
		
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//Ʈ���̳��� ��� ȸ�� ����� �ҷ��´�.
		for(int i =0;i<memberSet.size();i++) {
			if(Main.memberSet.get(i).getType()==1) {
				try {
					String tname=Main.memberSet.get(i).getId()+".csv";
					
					Path tpath = Paths.get("src/Database/traineeList/"+tname);
					File tfile = new File(tpath.toUri());
					Database.readTrainee(tfile);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		//GUI����
		new TextField1();
		
	}

}

class TextField1 extends JFrame{
	/*
	 * �ʱ�ȭ���� GUI Ŭ�����̴�.
	 * ���̵� �Է¹޾� �α����� �ϰų�
	 * ȸ�������� �� �� �ִ�.
	 */
	   static int id=-1;
	   static int login_cnt = 0;
	   public TextField1() 
	   {
	      setTitle("Training Manager");
	      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      LogIn login = new LogIn();
	      Register register = new Register();
	      Panel p = new Panel();
	      Label lab=new Label("�� �� ��    : ");
	      TextField txt=new TextField("",4);
	      JButton Blogin=new JButton("�α���");
	      JButton BsignIn=new JButton("ȸ������");
	      p.add(lab);
	      p.add(txt);
	      add(p);
	      p.add(BsignIn);
	      p.add(Blogin); 
	      p.setLayout(null);
	      lab.setBounds(20,30,70,22);
	      txt.setBounds(100,30,90,22);
	      BsignIn.setBounds(10, 60, 90, 30);
	      Blogin.setBounds(110,60,90,30);
	      setSize(250,200);
	      setVisible(true);
	        Blogin.addActionListener( new ActionListener() {
	             public void actionPerformed(ActionEvent e) { 
	            if(txt.getText().length()>=5) {
	            	//id�� 4���� ������ ���ڷθ� �Է¹޴´�.
	            	JOptionPane.showMessageDialog(null,"id�� 4���� �Ʒ��� �����մϴ�.");
	            }
	            else try {
	            	//����� �� �Է��� ������ ��쿡 �α��� class�� ���̵� �Ѱ��ش�.
	            	if(login.login(Integer.valueOf(txt.getText()))==0)
	            		setVisible(false);
	            	
	  	       }catch(Exception e1) {
	  	    	   //���ڰ� �ƴѰ���� �����޼����� ����ش�.
	  	           JOptionPane.showMessageDialog(null,"���ڷ� �� id�� �Է��� �ֽʽÿ�.");
	  	       }
	          }
	  } );
	        BsignIn.addActionListener( new ActionListener() {
	             public void actionPerformed(ActionEvent e) {
	            	//ȸ������ ��ư�� ������ ��� ȸ������ Ŭ������ GUI�� ����ش�.
	                Register r = new Register();
	                r.RegisterRun();
	          }
	  } );  
	}
}