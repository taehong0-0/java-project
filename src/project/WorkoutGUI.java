package GUITest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


/**
 * GUI class for WORKOUT PAGE
 * @author �±�
 *
 */
class WorkoutGUI {
    private int id;
    /**
     * @param id the id of the user who logged in
     */
    public WorkoutGUI(int id) {
       this.id=id;
       gui();
    }
    
    /**
     * WORKOUT PAGE�� ����ش�.
     */
    public void gui() {
        EventQueue.invokeLater(() -> {
            WorkoutFrame workoutFrame = new WorkoutFrame(this.id);
            workoutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            workoutFrame.setVisible(true);

        });
    }
}

/**
 * � ã�� ��ư�� ������ �� ��¥���� � ����� �����ִ� GUI
 * @author ��ȫ, �±�
 *
 */
class DateSelect extends JFrame{
	//��Ͽ��� ������ ��ũ�ƿ��� ��¥
	Date selectedDate;
	String[] colNames = new String[] {"Year", "Month", "Day"};
	DefaultTableModel model = new DefaultTableModel(colNames, 0){
		public boolean isCellEditable(int i, int c)
		{ 
			return false; 
		}
	};
   JTable table = new JTable(model);
   JScrollPane scrollPane = new JScrollPane(table);
   
   /**
    * ��¥ ���� ��ũ�ƿ��� ������ �� �ִ� ����� ����ִ� GUI 
    * @param id �α��� �� ������ ID
    */
   public DateSelect(int id) {
	   setTitle("Training Manager");
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
      
      //��ũ�ƿ� ����Ʈ�� ��¥�� �������� �����Ͽ� �����ش�.
      for(int i=0;i<Main.memberSet.size();i++) {
         if(Main.memberSet.get(i).getId()==id) {
            workoutlist = ((Trainee)Main.memberSet.get(i)).listOfWorkOut();
            Collections.sort(workoutlist);
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
      }
     
      add.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
            WorkoutFrame.model.setNumRows(0);
            try {
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
                     row1[0] = list.get(idx).getExerciseList().get(i).getTargetMuscle();
                     row1[1] = list.get(idx).getExerciseList().get(i).getExName();
                     row1[2] = String.valueOf(list.get(idx).getExerciseList().get(i).getSet());
                     row1[3] = String.valueOf(list.get(idx).getExerciseList().get(i).getReps());
                     row1[4] = String.valueOf(list.get(idx).getExerciseList().get(i).getWeight());
                     row1[5] = String.valueOf(list.get(idx).getDate().getYear());
                     row1[6] = String.valueOf(list.get(idx).getDate().getMonth());
                     row1[7] = String.valueOf(list.get(idx).getDate().getDay());
                   
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
	         }catch(ArrayIndexOutOfBoundsException err) {
	         	JOptionPane.showMessageDialog(null, "��¥�� �������ּ���.");
	         } 

         }      
       });
      } 

   }
   

/**
 * WORKOUT PAGE�� ������ GUI Ŭ����
 * @author �±�
 *
 */
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

   
   /**
    * ���� UI ������Ʈ��� Action���� �����Ǿ��ִ� ������
    * @param id �α��� �� ������ ID
    */
    public WorkoutFrame(int id){
    	setTitle("Training Manager");
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);

        //setResizable(false);
        
        //��� �޴���
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        //��� �޴����� �޴� ��ư
        JMenu menu = new JMenu("�޴�");
        menuBar.add(menu);
        //�޴��� ���� ��ư
        JMenuItem exit = new JMenuItem("����");
        //�����ư ActionListenr
        exit.addActionListener(event->System.exit(0));
        
        //�޴��� �α׾ƿ� ��ư
        JMenuItem logout = new JMenuItem("�α׾ƿ�");
        //�α׾ƿ� ��ư ActionListener
        logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				setVisible(false);
				new TextField1();
			}
        	
        });
        
        menu.add(logout);
        menu.addSeparator();
        menu.add(exit);
        
        
        this.id=id;
        String name = null;
        for(Member m : Main.memberSet) {
        	if(m.getId()==id) {
        		name = m.getName();
        	}
        }
        Container contentPlane = this.getContentPane();
        JPanel topPanel = new JPanel();
        JLabel label = new JLabel(name+"���� WORKOUT PAGE");
        topPanel.add(label);
        contentPlane.add(topPanel,BorderLayout.NORTH);

        
        
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane,BorderLayout.CENTER);
        contentPlane.add(scrollPane,BorderLayout.CENTER);
        

        //������ ��ư �г�
        JPanel rightPanel = new JPanel();
        //� ������ �Է��ϴ� �ؽ�Ʈ�ʵ带 ���� �ؽ�Ʈ�г�
        JPanel textPanel = new JPanel();

        textPanel.setLayout(new GridLayout(10,1));
        
        JLabel exerciseLabel = new JLabel("� ����");
        //� ������ �Է��ϴ� �ؽ�Ʈ �ʵ�
        JTextField exercise = new JTextField(4);
        
        JLabel exerciseNameLabel = new JLabel("� �̸�");
        //� �̸��� �Է��ϴ� �ؽ�Ʈ �ʵ�
        JTextField exerciseName = new JTextField(4);
       
        JLabel exerciseSetLabel = new JLabel("��Ʈ"); 
        //��Ʈ ���� �Է��ϴ� �ؽ�Ʈ �ʵ�
        JTextField exerciseSet = new JTextField(4);
        
        JLabel exerciseRepsLabel = new JLabel("Ƚ��");
        //Ƚ���� �Է��ϴ� �ؽ�Ʈ �ʵ�
        JTextField exerciseReps = new JTextField(4);
        
        JLabel exerciseWeightLabel = new JLabel("�߷�");
        //�߷��� �Է��ϴ� �ؽ�Ʈ �ʵ�
        JTextField exerciseWeight = new JTextField(4);
        JLabel exerciseDateLabel = new JLabel("��¥");
        JLabel exerciseDateYearLabel = new JLabel("��");
        JLabel exerciseDateMonthLabel = new JLabel("��");
        JLabel exerciseDateDayLabel = new JLabel("��");
        
        
        //��¥�� �Է��ϴ� �ؽ�Ʈ�ʵ带 ������ �г�
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
               //�Է¾��� ĭ�� �������� �� ���â.
                JOptionPane.showMessageDialog(null, "�������� �Է����ּ���.");
            }
            else {
               
               try {
                  Exercise ex = new Exercise(rows[1],rows[0],Integer.valueOf(rows[2]),Integer.valueOf(rows[3]),Double.valueOf(rows[4]));
                  Date date = new Date(Integer.valueOf(rows[5]),Integer.valueOf(rows[6]),Integer.valueOf(rows[7]));
                  
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
                  
                  //ȸ��(id) - ��ũ�ƿ�����Ʈ - �ش� ��¥ ��ũ�ƿ� -�����Ʈ�� � ��ü, ��¥��ü�� ��¥ �߰�
                  ((Trainee)Main.memberSet.get(idx)).addWorkout(date,ex);
                  
                  model.setNumRows(0);
                  ArrayList<WorkoutList> list = ((Trainee)Main.memberSet.get(idx)).listOfWorkOut();
                  idx=0;
                  for(int j = 0;j<list.size();j++) {
                     if(list.get(j).getDate().equals(date))
                     {
                        idx=j;
                    
                        for(int i=0; i<list.get(idx).getExerciseList().size();i++) {
                           String[] row1 = new String[8];
                           row1[0] = list.get(idx).getExerciseList().get(i).getTargetMuscle();
                           row1[1] = list.get(idx).getExerciseList().get(i).getExName();
                           row1[2] = String.valueOf(list.get(idx).getExerciseList().get(i).getSet());
                           row1[3] = String.valueOf(list.get(idx).getExerciseList().get(i).getReps());
                           row1[4] = String.valueOf(list.get(idx).getExerciseList().get(i).getWeight());
                           row1[5] = String.valueOf(list.get(idx).getDate().getYear());
                           row1[6] = String.valueOf(list.get(idx).getDate().getMonth());
                           row1[7] = String.valueOf(list.get(idx).getDate().getDay());
                       
                           WorkoutFrame.model.addRow(row1);
                        }
                     }
                  }
                
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
        
       
        /*
         * ã�� ��ư action
         * ã�� ��ư�� ������ �˾�â�� ����ش�.
         */
        searchExercise.addActionListener(event->new DateSelect(id));
        
        
       
        /*
         * � ���� ��ư
         * ��ũ�ƿ� ���� �Ŀ� �����ͺ��̽��� �ݿ��� �Ѵ�.
         * ��ư�� ������ ���� �ÿ��� ���α׷��� �� �����Ͽ��� �� ������ �ൿ�� �ݿ����� �ʴ´�.
         * �����ϸ� ����ų �� ���⶧��
         */
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
    
        /*
         *� ���� ��ư �׼�
         *�Է� ���� ��¥�� ��ũ�ƿ��� �����Ѵ�.
         *���� �Ŀ��� ���� ��ư�� ������ ���α׷� �� ����ÿ� �ݿ��� �ȴ�.
         */
        deleteExercise.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent arg0) {
            ArrayList<Integer> rows = new ArrayList<>();
            String year = exerciseDateYear.getText();
            String month = exerciseDateMonth.getText();
            String day = exerciseDateDay.getText();
            try {
            rows.add(Integer.valueOf(year));
            rows.add(Integer.valueOf(month));
            rows.add(Integer.valueOf(day));
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
                  Date date = new Date(Integer.valueOf(year),Integer.valueOf(month),Integer.valueOf(day));
                     
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

