package GUITest;


import javax.swing.*;

class LogIn {
	/*
	 * MainŬ������ GUI���� �α��� ��ư�� ������ ��
	 * ó�� ���ִ� Ŭ�����̴�.
	 */
	public int login(int mainid) {
		int tag=0;
		int id=mainid;
		int search;
		for(int i=0;i<Main.memberSet.size();i++) {
			search = Main.memberSet.get(i).getId();
			// ã�ƾ� �� ���̵� search ������ ����ش�.
			if(id == search) {
				tag=1;
			    JOptionPane.showMessageDialog(null, Main.memberSet.get(i).getName()+"�� ȯ���մϴ�.");
			    
				if(Main.memberSet.get(i).getType()==1) Trainer.TrainerRun(id);
				//Ʈ���̳ʷ� �α��� �ÿ� ȸ���� �����Ѵ�.
				else {
					new WorkoutGUI(id);
					//ȸ������ �α��� �ÿ� ��ũ�ƿ� �������� �Ѿ��.
					return 0;
				}
			}
		}
		if(tag==0) {
			//tag�� 1�� �ٲ��� �ʾ����Ƿ� ���̵� ã�� ���� ����̴�.
			JOptionPane.showMessageDialog(null, "��ϵ��� ���� ���̵��Դϴ�.");
			return -1;
		}else {
			return 0;
		}
	}

}
