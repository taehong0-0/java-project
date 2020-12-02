package GUITest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

public class WorkoutGUI {
    private int id;
    public WorkoutGUI(int id) {
    	this.id=id;
    	main(this.id);
    }
    public void main(int id) {
        this.id = id;
        EventQueue.invokeLater(() -> {
            WorkoutFrame workoutFrame = new WorkoutFrame(id);
            workoutFrame.setTitle("Trainning Manager");
            workoutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            workoutFrame.setVisible(true);

        });
    }
}
class DateSelect extends JFrame implements MouseListener{

	Date selectedDate;
	String[] colNames = new String[] {"Year", "Month", "Day"};
	DefaultTableModel model = new DefaultTableModel(colNames, 0){
		public boolean isCellEditable(int i, int c){ return false; }
	};
	JTable table = new JTable(model);
	JScrollPane scrollPane = new JScrollPane(table);
	public DateSelect(int id) {
		ArrayList<WorkoutList> workoutlist = null;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setBounds(400, 200, 500, 300);
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		
		JPanel panel = new JPanel();
		JButton add=new JButton("�˻�");
		panel.add(add);
		add(scrollPane, BorderLayout.CENTER);
		//add(panel);
		for(int i=0;i<Main.memberSet.size();i++) {
			if(Main.memberSet.get(i).getId()==id) {
				workoutlist = ((Trainee)Main.memberSet.get(i)).listOfWorkOut();
				break;
			}
		}
		String[] row=new String[3];
		if(workoutlist.size()==0) {
			JOptionPane.showMessageDialog(null,"�����Ʈ�� ���� �����ϴ�.");
		}
		else {
		for(int i=0;i<workoutlist.size();i++) {
			Date date=workoutlist.get(i).getDate();
			row[0]=Integer.toString(date.getYear());
			row[1]=Integer.toString(date.getMonth());
			row[2]=Integer.toString(date.getDay());
			model.addRow(row);
			}

		add(panel,BorderLayout.SOUTH);
		setVisible(true);

		table.addMouseListener(this);
		}
		add.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				WorkoutFrame.model.setNumRows(0);
				int row = table.getSelectedRow();
				Date date=new Date(Integer.valueOf((String) table.getModel().getValueAt(row,0)),Integer.valueOf((String)table.getModel().getValueAt(row,1)),Integer.valueOf((String)table.getModel().getValueAt(row,2)));
				int findID=0;
				
				for(Member m : Main.memberSet) {
					if(m.getId()==id) {
						break;
					}
					findID++;
				}

				ArrayList<WorkoutList> list = ((Trainee)Main.memberSet.get(findID)).listOfWorkOut();
				int idx=0;
				int searchTag=0;
				for(int j = 0;j<list.size();j++) {
					if(list.get(j).getDate().equals(date))
					{
						idx=j;
						//System.out.println(list.get(idx).getExerciseList().size());
						for(int i=0; i<list.get(idx).getExerciseList().size();i++) {
							String[] row1 = new String[8];
							row1[0] = list.get(idx).getExerciseList().get(i).getExName();
							row1[1] = list.get(idx).getExerciseList().get(i).getTargetMuscle();
							row1[2] = String.valueOf(list.get(idx).getExerciseList().get(i).getSet());
							row1[3] = String.valueOf(list.get(idx).getExerciseList().get(i).getReps());
							row1[4] = String.valueOf(list.get(idx).getExerciseList().get(i).getWeight());
							row1[5] = String.valueOf(list.get(idx).getDate().getYear());
							row1[6] = String.valueOf(list.get(idx).getDate().getMonth());
							row1[7] = String.valueOf(list.get(idx).getDate().getDay());
							/*for(String s:row) {
								System.out.println(s);
							}*/
							WorkoutFrame.model.addRow(row1);
						}
						searchTag=1;
						
					}
				}
				if(searchTag==0) {
					model.setNumRows(0);
					JOptionPane.showMessageDialog(null, "�ش� ��¥�� � ������ �����ϴ�.");
				}
				setVisible(false);
			}   	
	    });
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		/*int row = table.getSelectedRow();
		int col = table.getSelectedColumn();
		System.out.println(table.getSelectedColumn() +"       "+ col + "        " +table.getColumnCount()+table.getRowCount());
		if(e.getClickCount()==2) {
			row = table.getSelectedRow();
			col = table.getSelectedColumn();
			WorkoutFrame.setDate((String)table.getModel().getValueAt(0,0 ),(String)table.getModel().getValueAt(0,1 ),(String)table.getModel().getValueAt(0,2 ));

			setVisible(false);
		}*/
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
	
}

class WorkoutFrame extends JFrame{

    static String[] exerciseText = new String[] {"����","�̸�","��Ʈ","Ƚ��","�߷�","��","��","��"};
    static DefaultTableModel model = new DefaultTableModel(exerciseText,0);
    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = 500;
    private int id; 
    JTextField textField;
    static JTextField exerciseDateYear = new JTextField(4);
    static JTextField exerciseDateMonth = new JTextField(2);
    static JTextField exerciseDateDay = new JTextField(2);

   
   
    public WorkoutFrame(int id){
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        //setResizable(false);
        this.id=id;
        Container contentPlane = this.getContentPane();
        JPanel topPanel = new JPanel();
        JLabel label = new JLabel("WORKOUT PAGE");
        topPanel.add(label);
        contentPlane.add(topPanel,BorderLayout.NORTH);

        
        
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane,BorderLayout.CENTER);
        contentPlane.add(scrollPane,BorderLayout.CENTER);
        
        //Container center = new Container();
        //JPanel leftPanel = new JPanel();
        //leftPanel.setLayout(new BorderLayout());
        //textField = new JTextField("�ؽ�Ʈ �ʵ�");
        //leftPanel.add(textField,BorderLayout.CENTER);
        //contentPlane.add(leftPanel,BorderLayout.CENTER);

        //������ ��ư �г�
        JPanel rightPanel = new JPanel();
        JPanel textPanel = new JPanel();

        //textPanel.setLayout(new GridLayout(5,2));
        textPanel.setLayout(new GridLayout(10,1));
        JLabel exerciseLabel = new JLabel("� ����");
        JTextField exercise = new JTextField(4);
        JLabel exerciseNameLabel = new JLabel("� �̸�");
        JTextField exerciseName = new JTextField(4);
        JLabel exerciseSetLabel = new JLabel("��Ʈ");
        JTextField exerciseSet = new JTextField(4);
        JLabel exerciseRepsLabel = new JLabel("Ƚ��");
        JTextField exerciseReps = new JTextField(4);
        JLabel exerciseWeightLabel = new JLabel("�߷�");
        JTextField exerciseWeight = new JTextField(4);
        JLabel exerciseDateLabel = new JLabel("��¥");
        JLabel exerciseDateYearLabel = new JLabel("��");
        JLabel exerciseDateMonthLabel = new JLabel("��");
        JLabel exerciseDateDayLabel = new JLabel("��");

        JPanel datePanel = new JPanel();

        datePanel.add(exerciseDateYear);
        datePanel.add(exerciseDateYearLabel);
        datePanel.add(exerciseDateMonth);
        datePanel.add(exerciseDateMonthLabel);
        datePanel.add(exerciseDateDay);
        datePanel.add(exerciseDateDayLabel);

        textPanel.add(exerciseLabel);
        textPanel.add(exercise);
        textPanel.add(exerciseNameLabel);
        textPanel.add(exerciseName);
        textPanel.add(exerciseSetLabel);
        textPanel.add(exerciseSet);
        textPanel.add(exerciseRepsLabel);
        textPanel.add(exerciseReps);
        textPanel.add(exerciseWeightLabel);
        textPanel.add(exerciseWeight);
        textPanel.add(exerciseDateLabel);
        textPanel.add(datePanel);
        JPanel btnPanel = new JPanel();
        JButton searchExercise = new JButton("� ã��");
        JButton addExercise = new JButton("� �߰�");
        JButton saveExercise = new JButton("� ����");
        JButton deleteExercise = new JButton("��ũ�ƿ� ����");
        
        
        btnPanel.setLayout(new FlowLayout());
        btnPanel.add(addExercise);
        btnPanel.add(searchExercise);
        btnPanel.add(saveExercise);
        btnPanel.add(deleteExercise);
        
        rightPanel.setLayout(new GridLayout(2,2));;
        rightPanel.add(textPanel);
        rightPanel.add(btnPanel);
        contentPlane.add(rightPanel,BorderLayout.EAST);
        //� �߰� ��ư action
        addExercise.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//�Էµ� �� ȸ��-��ũ�ƿ�����Ʈ-�ش� ��¥ ��ũ�ƿ�-�����Ʈ-�߰�
				//�Էµ� �� ���̺� ���
				String[] rows = new String[8];
				//����
				rows[0] = exercise.getText();
				rows[1] = exerciseName.getText();
				//�����Է¹޾ƾ���
				rows[2] = exerciseSet.getText();
				rows[3] = exerciseReps.getText();
				rows[4] = exerciseWeight.getText();
				rows[5] = exerciseDateYear.getText();
				rows[6] = exerciseDateMonth.getText();
				rows[7] = exerciseDateDay.getText();
				int tag = 0;
				for(String s : rows) {
					if(s.length()==0) {
						tag=1;
						break;
					}
				}
				if(tag==1) {
					//�Է¾��� ĭ�� ����. �˸�â. ��� �Է����ּ���.
					 JOptionPane.showMessageDialog(null, "�������� �Է����ּ���.");
				}
				else {
					
					try {
						Exercise ex = new Exercise(exerciseName.getText(),exercise.getText(),Integer.valueOf(exerciseSet.getText()),Integer.valueOf(exerciseReps.getText()),Double.valueOf(exerciseWeight.getText()));
						Date date = new Date(Integer.valueOf(exerciseDateYear.getText()),Integer.valueOf(exerciseDateMonth.getText()),Integer.valueOf(exerciseDateDay.getText()));
						
						//�ؽ�Ʈ �ʵ� �� ����
						exercise.setText("");
						exerciseName.setText("");
						exerciseSet.setText("");
						exerciseReps.setText("");
						exerciseWeight.setText("");
						exerciseDateYear.setText("");
						exerciseDateMonth.setText("");
						exerciseDateDay.setText("");
						int idx=0;
						for(Member m : Main.memberSet) {
							
							if(m.getId()==id) {
								break;
							}
							idx++;
						}
						model.addRow(rows);
						//ȸ��(id) - ��ũ�ƿ�����Ʈ - �ش� ��¥ ��ũ�ƿ� -�����Ʈ�� � ��ü, ��¥��ü�� ��¥ �߰�
						((Trainee)Main.memberSet.get(idx)).addWorkout(date,ex);
						//((Trainee)Main.memberSet.get(idx)).getWorkoutList();
						try {
							Database.saveWorkout();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}catch(NumberFormatException err) {
						JOptionPane.showMessageDialog(null, "Ƚ��, ��Ʈ��, �߷�, ��¥�� ���ڷ� �Է����ּ���");
					}
				}
			}
        	
        });
        
        //ã�� ��ư action
        searchExercise.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				{
				new DateSelect(id);
				}
				//��¥�� ���� WorkoutList���� getExercise
						//idȸ���� workoutList�� workout �� date�� ���� workout�� � ����Ʈ�� �
						
			}
        	
        });
        
        saveExercise.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Database.saveWorkout();
					JOptionPane.showMessageDialog(null, "��ũ�ƿ��� ����Ǿ����ϴ�.");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
        	
        });
    
    
        deleteExercise.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Integer> rows = new ArrayList<>();
				String year = exerciseDateYear.getText();
				String month = exerciseDateMonth.getText();
				String day = exerciseDateDay.getText();
				try {
				rows.add(Integer.valueOf(exerciseDateYear.getText()));
				rows.add(Integer.valueOf(exerciseDateMonth.getText()));
				rows.add(Integer.valueOf(exerciseDateDay.getText()));
				int tag=0; int tag2=0;
				
					for(int i=0;i<3;i++) {
						if(i==0&&(rows.get(i)>2099||rows.get(i)<1900)) {
							tag2=1;
							throw new Exception();
						
						}
						else if(i==1&&(rows.get(i)>12||rows.get(i)<1)) {
							tag2=1;
							throw new Exception();
						
						}
						else if(i==2&&(rows.get(i)>31||rows.get(i)<1)) {
							tag2=1;
							throw new Exception();
						
						}
						else{
							if(rows.get(i)==null) {
								tag=1;
							}
						}
					}
					
					if(tag2!=1) {
						Date date = new Date(Integer.valueOf(exerciseDateYear.getText()),Integer.valueOf(exerciseDateMonth.getText()),Integer.valueOf(exerciseDateDay.getText()));
							
				       //idȸ���� workoutList�� workout �� date�� ���� workout�� � ����Ʈ�� �
						int findID=0;
						
						for(Member m : Main.memberSet) {
							if(m.getId()==id) {
								break;
							}
							findID++;
						}

						ArrayList<WorkoutList> list = ((Trainee)Main.memberSet.get(findID)).listOfWorkOut();
						int idx=0;
						int searchTag=0;
						for(int j = 0;j<list.size();j++) {
							if(list.get(j).getDate().equals(date))
							{
								list.remove(j);
								JOptionPane.showMessageDialog(null, "�ش� ��¥�� ��ũ�ƿ��� �����Ǿ����ϴ�."
										+ " ���� ��ư�� �� �����ּ���!");
								model.setNumRows(0);
								searchTag=1;
								
							}
						}
						if(searchTag==0) {
							model.setNumRows(0);
							JOptionPane.showMessageDialog(null, "�ش� ��¥�� � ������ �����ϴ�.");
						}
					}
				}catch(NumberFormatException err) {
					JOptionPane.showMessageDialog(null, "��¥�� ��ĭ ���� �Է����ּ���.");
				}
				catch(Exception err) {	
					JOptionPane.showMessageDialog(null, "��¥�� �ùٸ��� �Է����ּ���.");
				}
				
			}
        	
        	
        });
        
    
    }
    
   

}


