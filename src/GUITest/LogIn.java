package GUITest;


import javax.swing.*;

class LogIn {
	/*
	 * Main클래스의 GUI에서 로그인 버튼을 눌렀을 때
	 * 처리 해주는 클래스이다.
	 */
	public int login(int mainid) {
		int tag=0;
		int id=mainid;
		int search;
		for(int i=0;i<Main.memberSet.size();i++) {
			search = Main.memberSet.get(i).getId();
			// 찾아야 할 아이디를 search 변수에 담아준다.
			if(id == search) {
				tag=1;
			    JOptionPane.showMessageDialog(null, Main.memberSet.get(i).getName()+"님 환영합니다.");
			    
				if(Main.memberSet.get(i).getType()==1) Trainer.TrainerRun(id);
				//트레이너로 로그인 시에 회원을 선택한다.
				else {
					new WorkoutGUI(id);
					//회원으로 로그인 시에 워크아웃 페이지로 넘어간다.
					return 0;
				}
			}
		}
		if(tag==0) {
			//tag가 1로 바뀌지 않았으므로 아이디를 찾이 못한 경우이다.
			JOptionPane.showMessageDialog(null, "등록되지 않은 아이디입니다.");
			return -1;
		}else {
			return 0;
		}
	}

}
