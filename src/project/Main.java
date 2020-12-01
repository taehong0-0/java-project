package project;

import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

class TextField1 extends JFrame{
	   static int id=-1;
	   static int login_cnt = 0;
	   public TextField1() 
	   {
	      
	      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      LogIn login = new LogIn();
	      Register register = new Register();
	      Panel p = new Panel();
	      Label lab=new Label("아 이 디 : ");
	      TextField txt=new TextField("ID",4);
	      JButton Blogin=new JButton("로그인");
	      JButton BsignIn=new JButton("회원가입");
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
	            	JOptionPane.showMessageDialog(null,"id는 4글자 아래만 가능합니다.");
	            }
	            else try {
	            login.login(Integer.valueOf(txt.getText()));
	           setVisible(false);
	  	       }catch(Exception e1) {
	  	           JOptionPane.showMessageDialog(null,"숫자로 된 id를 입력해 주십시오.");
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
public class Main {
	static List<Integer> idSet = new ArrayList<>();
	static List<Member> memberSet = new ArrayList<>();
	static String[][] member = new String[1000][7];
	public static void main(String[] args) {
		new TextField1();
		Path path = Paths.get("src/Member.csv");
		File memberList = new File(path.toUri());
		try {
			readMember(memberList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<memberSet.size();i++) {
			String id = String.valueOf(memberSet.get(i).getId())+".csv";
			Path woPath = Paths.get("src/"+id);
			File workout = new File(woPath.toUri());
			try {
				readWorkout(workout);
			}catch(Exception e) {
				System.out.println();
			}
		}
	}
	public static void saveMember() throws IOException {
		String fname="Member.csv";
		File file = new File(fname);
		//파일 출력 한글 인코딩 
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		OutputStreamWriter OutputStreamWriter = new OutputStreamWriter(fileOutputStream, "MS949");
		BufferedWriter bw = new BufferedWriter(OutputStreamWriter);

		bw.newLine();
		for(int i=0;i<memberSet.size();i++) {
			if(memberSet.get(i).getType()==1) {
				bw.write(+memberSet.get(i).getId()+","+memberSet.get(i).getName()+","+memberSet.get(i).getType()+","+memberSet.get(i).getAge()+","+memberSet.get(i).getSex());
				bw.newLine();
			}else {
				bw.write(+memberSet.get(i).getId()+","+memberSet.get(i).getName()+","+memberSet.get(i).getType()+","+memberSet.get(i).getAge()+","+memberSet.get(i).getSex());
				bw.newLine();
			}
		}
		bw.close();
		
	}
	
	
	public static void saveWorkout() throws IOException {
		
		for(int i = 0; i<memberSet.size();i++) {
			//각 회원별로 파일에 데이터베이스 저장
			if(memberSet.get(i).getType()==2) {
				String fname=memberSet.get(i).getId()+".csv";
				File file = new File(fname);
				//파일 출력 한글 인코딩 
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				OutputStreamWriter OutputStreamWriter = new OutputStreamWriter(fileOutputStream, "MS949");
				BufferedWriter bw = new BufferedWriter(OutputStreamWriter);
				bw.write(memberSet.get(i).getId());
				bw.newLine();
				ArrayList<WorkoutList> workoutList = ((Trainee)memberSet.get(i)).getWorkoutList();
				for(WorkoutList wl : workoutList) {
					Date wlDate = wl.getDate();
					ArrayList<Exercise> wlExList = wl.getExerciseList();
					bw.write(wlDate.year+","+wlDate.month+","+wlDate.day+","+wl.getWeight()+",");
					for(Exercise ex : wlExList) {
						bw.write(ex.getExName()+","+ex.getTargetMuscle()+","+ex.getReps()+","+ex.getSet()+","+ex.getWeight());
					}
					bw.newLine();
				}
				bw.close();
			}
			
		}
		
		
	}
	public static void readMember(File file) throws Exception {
		try {
			BufferedReader br =null;
			br = new BufferedReader(new FileReader(file));
			
			String line = "";
			while((line=br.readLine())!=null) {
				List<String> tmp = new ArrayList<String>();
				String arr[] = line.split(",");
				tmp=Arrays.asList(arr);
				int fId = 0;
				String fName = "";
				int fType=0;
				int fAge=0;
				String fSex="";
				int fTrainer=0;
				int fRemainPT=0;
				for(int i=0;i<tmp.size();i++) {
					  
						switch(i) {
							case 0:
								fId = Integer.valueOf(tmp.get(i));
								break;
							case 1:
								fName = tmp.get(i);
								break;
							case 2:
								fType = Integer.valueOf(tmp.get(i));
								break;
							case 3:
								fAge = Integer.valueOf(tmp.get(i));
								break;
							case 4:
								fSex = tmp.get(i);
								break;
							case 5:
								if(fType==1) break;
								fTrainer = Integer.valueOf(tmp.get(i));
								break;
							case 6:
								if(fType==1) break;
								fRemainPT = Integer.valueOf(tmp.get(i));
								break;
						}
					
				}
				if(fType ==1 ) {
					memberSet.add(new Trainer(fId,fName,fSex,fAge,1));		
				}else if(fType==2) {
					memberSet.add(new Trainee(fId,fName,fSex,fAge,2,fTrainer));
				}
			}
	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new Exception("회원 명단 불러오기를 실패하였습니다.");		
		}
	}
	
	
	public static void readWorkout(File file) throws Exception {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = "";
			line=br.readLine();
			int id = Integer.valueOf(line);
			int idx=0;
			for(int i = 0;i<memberSet.size();i++) {
				if(memberSet.get(i).getId()==id) {
					idx = i;
				}
			}
			
			while((line=br.readLine())!=null) {
				List<String> tmp = new ArrayList<String>();
				String arr[] = line.split(",");
				tmp=Arrays.asList(arr);
				int dy=0,dm=0,dd=0,ereps=0,eset=0;
				double weight=0,eweight=0;
				String ename="",etarget="";
				WorkoutList tWorkoutList = null;
				if(tmp.size()==4) {
					for(int i=0;i<4;i++) {
						switch(i) {	
							case 0:
								dy = Integer.valueOf(tmp.get(i));
								break;
							case 1:
								dm = Integer.valueOf(tmp.get(i));
								break;
							case 2:
								dd = Integer.valueOf(tmp.get(i));
								break;
							case 3:
								weight = Double.valueOf(tmp.get(i));
								break;
						}
					}
					tWorkoutList = new WorkoutList(dy,dm,dd,weight);
				}
				if(tmp.size()==5) {
					for(int i=0;i<5;i++) {	 
						switch(i) {
							case 0:
								ename = tmp.get(i);
								break;
							case 1:
								etarget = tmp.get(i);
								break;
							case 2:
								ereps = Integer.valueOf(tmp.get(i));
								break;
							case 3:
								eset = Integer.valueOf(tmp.get(i));
								break;
							case 4:
								eweight =Double.valueOf(tmp.get(i));
								break;
						}
					}
					Exercise tExercise = new Exercise(ename,etarget,ereps,eset,eweight);
					tWorkoutList.addExercise(tExercise);
				}
			}
	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new Exception("회원 명단 불러오기를 실패하였습니다.");		
		}
	}
}
