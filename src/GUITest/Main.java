package GUITest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

	static List<Member> memberSet = new ArrayList<>();
	static String[][] member = new String[1000][7];
	public static void main(String[] args) {
		
		Path path = Paths.get("src/Database/list/Member.csv");
		File memberList = new File(path.toUri());
		try {
			Database.readMember(memberList);
			System.out.println("ȸ�� ��� �ҷ����� ����!");
			System.out.println("ȸ�� : "+memberSet.size()+"��");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
		for(int i =0;i<memberSet.size();i++) {
			if(Main.memberSet.get(i).getType()==1) {
				try {
					String tname=Main.memberSet.get(i).getId()+".csv";
					System.out.println(tname+" read");
					Path tpath = Paths.get("src/Database/traineeList/"+tname);
					File tfile = new File(tpath.toUri());
					Database.readTrainee(tfile);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		new TextField1();
		
	}
	
		
	public static Member findById(int id) {
		Member found = new Member();
		for(Member m : memberSet) {
			if(m.getId()==id) {
				found = m;
			}
		}
		return found;
	}

}

class TextField1 extends JFrame{
	   static int id=-1;
	   static int login_cnt = 0;
	   public TextField1() 
	   {
	      
	      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      LogIn login = new LogIn();
	      Register register = new Register();
	      Panel p = new Panel();
	      Label lab=new Label("�� �� �� : ");
	      TextField txt=new TextField("ID",4);
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
	            	JOptionPane.showMessageDialog(null,"id�� 4���� �Ʒ��� �����մϴ�.");
	            }
	            else try {
	            login.login(Integer.valueOf(txt.getText()));
	  	       }catch(Exception e1) {
	  	           JOptionPane.showMessageDialog(null,"���ڷ� �� id�� �Է��� �ֽʽÿ�.");
	  	       }
	          }
	  } );
	        BsignIn.addActionListener( new ActionListener() {
	             public void actionPerformed(ActionEvent e) { 
	                Register r = new Register();
	                r.RegisterRun();
	          }
	  } );  
	}
}