package GUITest;

import java.awt.EventQueue;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

class LogIn extends JFrame {
	static int loginId;
	public static int getId() {
		return loginId;
	}
	private void setId(int id) {
		this.loginId=id;
	}
	public void login(int mainid) {
		//LogIn login = new LogIn();
		Scanner in = new Scanner(System.in);
		//idSet�ҷ�����
		int login_cnt = 0;
		int tag=0;
			int id=0;
				id = mainid;				
				for(int i=0;i<Main.memberSet.size();i++) {
					int search = Main.memberSet.get(i).getId();
					if(id == search) {
						// type���� Ʈ���̳� -> ȸ�� ����
						//ȸ�� -> ��ũ�ƿ� �������� �̵�
						tag=1;
		                JOptionPane.showMessageDialog(null, Main.memberSet.get(i).getName()+"�� ȯ���մϴ�.");
						if(Main.memberSet.get(i).getType()==1) Trainer.TrainerRun(id);
						else {
							WorkoutGUI menu = new WorkoutGUI(id);
						}//trainee�κ� ����
						//���� ����
					}
				}
				if(tag==0) {
					JOptionPane.showMessageDialog(null, "��ϵ��� ���� ���̵��Դϴ�.");
				}
	}

}
