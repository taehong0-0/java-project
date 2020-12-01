package GUITest;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

class signframe extends JFrame{
		String signSex;
		private void setSex(int signSex) {
			if(signSex==1)
			this.signSex="male";
			else this.signSex="female";
		}
		private String getSex() {
			return signSex;
		}
		int signType=1;
		private void setSignType(int signType) {
			this.signType=signType;
		}
		private int getSignType() {
			return signType;
		}
		int duplechk=0;
		private int getDup() {
			return duplechk;
		}
		private void setDup(int duplechk) {
			this.duplechk=duplechk;
		}
		int dupledid=0;
		private void setDupid(int dupledid) {
			this.dupledid=dupledid;
		}
		private int getDupid() {
			return dupledid;
		}
	   public signframe()
	   {
		   boolean duplicateChk = false;
	      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      Panel p = new Panel();
	      Label lab1=new Label("�� �� �� : ");
	      TextField txt1=new TextField(10);
	      JButton duple=new JButton("�ߺ�Ȯ��");
	      Label lab2=new Label("�� �� : ");
	      TextField txt2=new TextField(10);
	      Label lab3=new Label("�� �� : ");
	      JRadioButton male = new JRadioButton("��");
	        JRadioButton female = new JRadioButton("��");
	        male.setSelected(true);
	        ButtonGroup sex = new ButtonGroup();
	        sex.add(male);
	        sex.add(female);
	        Label lab4=new Label("�� å : ");
	      JRadioButton trainer = new JRadioButton("Ʈ���̳�");
	      JRadioButton trainee = new JRadioButton("ȸ��");
	      trainee.setSelected(true);
	      ButtonGroup member = new ButtonGroup();
	      member.add(trainer);
	      member.add(trainee);
	      Label lab5=new Label("�� �� : ");
	      TextField txt5=new TextField(10);
	      JButton signin=new JButton("ȸ������");
	      p.add(lab1);
	      p.add(txt1);
	      p.add(duple);
	      p.add(lab2);
	      p.add(txt2);
	      p.add(lab3);
	      p.add(male);
	      p.add(female);
	      p.add(lab4);
	      p.add(trainee);
	      p.add(trainer);
	      p.add(lab5);
	      p.add(txt5);
	      p.add(signin);
	      add(p);
	      p.setLayout(null);
	      lab1.setBounds(30, 30, 50, 22);
	      txt1.setBounds(100, 30, 120, 22);
	      duple.setBounds(230, 30, 90, 30);
	      lab2.setBounds(30, 70, 50, 22);
	      txt2.setBounds(100, 70, 120, 22);
	      lab3.setBounds(30, 110, 50, 22);
	      male.setBounds(100, 110, 60, 22);
	      female.setBounds(170, 110, 60, 22);
	      lab4.setBounds(30, 150, 50, 22);
	      trainer.setBounds(100, 150, 100, 22);
	      trainee.setBounds(170, 150, 60, 22);
	      lab5.setBounds(30, 190,50, 22);
	      txt5.setBounds(100, 190, 120, 22);
	      signin.setBounds(120, 240, 100, 30);
	        setSize(400, 400);
	        setVisible(true);
	        int duplechecked=0;
	        signin.addActionListener( new ActionListener() {
	             public void actionPerformed(ActionEvent e) { 
	                if(getDup()==0||(getDupid()!=(Integer.valueOf(txt1.getText())))) {
	                   JOptionPane.showMessageDialog(null, "�ߺ�Ȯ���� ���� �ϼž� �մϴ�.");
	                }
	                else {
	                	try {
	                	int signId = getDupid();
	                	String signName = txt2.getText();
	                	if(male.isSelected()) setSex(1);
	                	else setSex(2);
	                	if(trainer.isSelected()) setSignType(1);
	                	else setSignType(2);
	                	int signAge=Integer.valueOf(txt5.getText());
	                	if(getSignType()==1) {
	                		Member member = new Trainer(signId,signName,getSex(),signAge,getSignType());
		                	Main.memberSet.add(member);
		                	try {
		                		Database.saveMember();
		                	}catch(Exception err) {
		                		System.out.println("��� ���� ����");
		                	}try {
		                	Database.saveTrainee();
		                	}catch(Exception err) {
		                		System.out.println("Trainee���� ����");
		                	}
		                	JOptionPane.showMessageDialog(null, "ȸ�����Կ� �����ϼ̽��ϴ�!");
	                		}
	                	if(getSignType()==2) {
	                		Trainee trainee=new Trainee(signId,signName,getSex(),signAge,getSignType());
	                		new trainerList(trainee);
	                		
	                		
	                		
	                		
	                	}
	                	setVisible(false);
	                	}catch(Exception e1) {
	                		JOptionPane.showMessageDialog(null, "��ĭ �Ǵ� �ùٸ��� ���� �Է��� �ֽ��ϴ�.");
	                	}
	                }
	      }
	} );
	        duple.addActionListener( new ActionListener() {
	             public void actionPerformed(ActionEvent e) {
	            	 try {
	            		 if(Register.duplicateCheck(Integer.valueOf(txt1.getText()))==true) {
								JOptionPane.showMessageDialog(null, "�̹� �����ϴ� ���̵� �Դϴ�. �ٽ� �õ����ּ���.");						
								}
							else{
								if(txt1.getText().length()>4) {
									JOptionPane.showMessageDialog(null, "id�� 4�ڸ� ���� ���ڿ��� �մϴ�.");							
								}
								else 
									{JOptionPane.showMessageDialog(null, "��밡���� ���̵��Դϴ�.");
								setDup(1);
								setDupid(Integer.valueOf(txt1.getText()));
									}
							}
	            	 }catch(Exception e1) {
	            		 JOptionPane.showMessageDialog(null,"���ڷ� �� id�� �Է��� �ֽʽÿ�.");
	            	 }
	      }
	} );
	   }
	}
class trainerList extends JFrame{
	private static int trainerId=0;
	public static int getTrainerId() {
		return trainerId;
	}
	private void setTrainerId(int trainerId) {
		this.trainerId=trainerId;
	}
	public trainerList(Trainee trainee) 
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setBounds(200, 100, 400, 200);
		String[] colNames = new String[] {"Id", "Name", "Gender"};
		DefaultTableModel model = new DefaultTableModel(colNames, 0);
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(2, 1));
		JTextField trainId = new JTextField(6);
		JButton btn = new JButton("Ȯ ��");
		JPanel panel2 = new JPanel();

		panel2.add(new JLabel("ID"));
		panel2.add(trainId);
		panel2.add(btn);
		
		bottomPanel.add(panel2);	
		String[] arr=new String[3];
		for(int i=0;i<Main.memberSet.size();i++) {
			if(Main.memberSet.get(i).getType() == 1) {
				arr[0]=Integer.toString(Main.memberSet.get(i).getId());
				arr[1]=Main.memberSet.get(i).getName();
				arr[2]=Main.memberSet.get(i).getSex();
				model.addRow(arr);
			}
		}
		btn.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(trainId.getText().length()>4) {
					JOptionPane.showMessageDialog(null, "id�� 4�ڸ� ���� ���ڿ��� �մϴ�.");							
				}
            	else {
            	try {
            	for(int i=0;i<Main.memberSet.size();i++) {
        			if(Main.memberSet.get(i).getType()==1&&Main.memberSet.get(i).getId()==Integer.valueOf(trainId.getText())) {
        				setTrainerId(Integer.valueOf(trainId.getText()));
        				Register.matchTrainer(getTrainerId(),trainee);
        				
        				break;
        			}
        		}
            	}catch(Exception e1) {
            		JOptionPane.showMessageDialog(null,"���ڷ� �� id�� �Է��� �ֽʽÿ�.");
            	}
            	if(getTrainerId()==0) {
            		JOptionPane.showMessageDialog(null,"��ϵ��� ���� ���̵� �Դϴ�.");
            	}
            	else {
            		//trainerID.setID(getTrainerId());
            		JOptionPane.showMessageDialog(null,"ȸ�����Կ� �����ϼ� ���ϴ�.\nƮ���̳��� ���̵��"+getTrainerId()+"�Դϴ�.");
            		trainee.setTrainer(getTrainerId());
            		Main.memberSet.add(trainee);
            		try {
                		Database.saveMember();
                	}catch(Exception err) {
                		System.out.println("��� ���� ����");
                	}try {
                		Database.saveTrainee();
                	}catch(Exception err) {
                		System.out.println("Trainee���� ����");
                	}
            		setVisible(false);
            	}
            	}
     }
} );
        add(bottomPanel,BorderLayout.SOUTH);		
		setVisible(true);
	}
}
class Register {
	public void RegisterRun() {
		new signframe();
	}
	
	static boolean duplicateCheck(int id) {
		
		try {
		for(int i=0;i<Main.memberSet.size();i++) {
			int search = Main.memberSet.get(i).getId();
			if(id == search) {
				return true;
			}
		}
		}catch(NoSuchElementException e) {
			return false;
		}
		return false;
	}
	
	static void matchTrainer(int trainerID, Trainee trainee) {
		for(int i =0;i<Main.memberSet.size();i++) {
			if(Main.memberSet.get(i).getId() == trainerID) {
				//System.out.println("123123");
				((Trainer)Main.memberSet.get(i)).addTrainee(trainee);
				break;
			}
		}
	}
}


