package GUITest;

import java.util.*;


/**
 * ȸ������ ������ ��� Ŭ����
 * @author �±�
 *
 */
class Trainee extends Member {
	//trainer id
	private int myTrainer; 
	private ArrayList<WorkoutList> workoutList = new ArrayList<>();
	
	public Trainee(int id, String name, String sex, int age, int type) {
		super(id, name, sex, age, type);
	}
	
	public Trainee(int id, String name, String sex, int age, int type, int TrainerID) {
		super(id, name, sex, age, type);
		this.myTrainer = TrainerID;

	}
	
	public int getTrainer() {
		return myTrainer;
	}
	
	public void setTrainer(int TrainerID) {
		this.myTrainer = TrainerID;
	}

	public void loadWorkout(WorkoutList add) {
		this.workoutList.add(add);
	}
	
	public ArrayList<WorkoutList> listOfWorkOut(){
		return this.workoutList;
	}
	
	/**
	 * �Է��� ��¥�� ��� �߰��ϴ� �޼���
	 * �ش� ��¥�� �̹� ��ũ�ƿ��� �����Ѵٸ� �ش� ��ũ�ƿ��� �߰��Ѵ�. ��ũ�ƿ��� �������� �ʴ´ٸ� {@code WorkoutList}��ü�� ����� �����Ѵ�.
	 * @param date � ��¥
	 * @param ex �߰��� �
	 */
	public void addWorkout(Date date,Exercise ex) {
		int idx=0;
		int tag = 1;
		
		for(WorkoutList w : workoutList) {
			if(w.getDate().equals(date)) {
				this.workoutList.get(idx).addExercise(ex);
				tag=0;
				break;
			}
			idx++;
		}
		//�ش� ��¥�� ��ũ�ƿ��� ������ ��ũ�ƿ�����Ʈ ��ü�� ���� �����Ͽ� �߰��Ѵ�.
		if(tag ==1) {
			this.workoutList.add(new WorkoutList());
			this.workoutList.get(this.workoutList.size()-1).addWorkout(date,ex);
		}
	}
	

}
