package GUITest;


import javax.swing.*;

class LogIn {
	
	public int login(int mainid) {
		int tag=0;
		int id=0;
			id = mainid;				
		for(int i=0;i<Main.memberSet.size();i++) {
			int search = Main.memberSet.get(i).getId();
			if(id == search) {
				tag=1;
			    JOptionPane.showMessageDialog(null, Main.memberSet.get(i).getName()+"�� ȯ���մϴ�.");
			    //Ʈ���̳ʷ� �α��� �ÿ� ȸ���� �����Ѵ�.
				if(Main.memberSet.get(i).getType()==1) Trainer.TrainerRun(id);
				//ȸ������ �α��� �ÿ� ��ũ�ƿ� �������� �Ѿ��.
				else {
					new WorkoutGUI(id);
					return 0;
				}
			}
		}
		if(tag==0) {
			JOptionPane.showMessageDialog(null, "��ϵ��� ���� ���̵��Դϴ�.");
			return -1;
		}else {
			return 0;
		}
	}

}
