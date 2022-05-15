package day_list;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;

public class list extends JFrame {

	private JPanel contentPane;
	private JTextField Field;
	private JTextField TimeFieldM;
	private JTextField TimeFieldS;
	private JLabel Task;
	private JLabel Sec_time;
	private JLabel Min_time;
	private JTextPane list;
	private JLabel DelT;
	private JTextField DelIndex;
	private String st="";
	private ArrayList<String> TaskList = new ArrayList<String>();
	private int index=0;
	private ArrayList<Integer> hc = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					list frame = new list();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public list() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("To_Do_List");
		setBounds(100, 100, 718, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(255,215,0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		Task = new JLabel();
		Task.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Task.setText("Текст задачи");
		Task.setBounds(474, 56, 181, 15);
		contentPane.add(Task);
		
		Field = new JTextField();
		Field.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Field.setBounds(474, 76, 181, 38);
		//Field.setBackground(Color.);
		contentPane.add(Field);
		Field.setColumns(10);
		
		Min_time = new JLabel();
		Min_time.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Min_time.setText("Время в мин");
		Min_time.setBounds(470, 118, 181, 15);
		contentPane.add(Min_time);
		
		TimeFieldM = new JTextField();
		TimeFieldM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		TimeFieldM.setBounds(470, 138, 90, 38);
		//TimeFieldM.setBackground(Color.gray);
		contentPane.add(TimeFieldM);
		TimeFieldM.setColumns(10);
		
		Sec_time = new JLabel();
		Sec_time.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Sec_time.setText("Время в сек");
		Sec_time.setBounds(570, 118, 181, 15);
		contentPane.add(Sec_time);
		
		TimeFieldS = new JTextField();
		TimeFieldS.setFont(new Font("Tahoma", Font.PLAIN, 15));
		TimeFieldS.setBounds(570, 138, 90, 38);
		//TimeFieldS.setBackground(Color.gray);
		contentPane.add(TimeFieldS);
		TimeFieldS.setColumns(10);
		
		JTextPane list = new JTextPane();
		list.setBounds(10, 10, 200, 300);
		list.setBackground(new Color(220,220,220));
		list.setEnabled(true);
		list.setEditable(false);
		list.setContentType("text/html");
		contentPane.add(list);
		
		JButton AddBut = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u0437\u0430\u0434\u0430\u0447\u0443");
		AddBut.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				st = "";
				String text = Field.getText().trim();
				String timem = TimeFieldM.getText().trim();
				String times = TimeFieldS.getText().trim();
				try {
					int time_intS = Integer.parseInt(times);
					int time_intM = Integer.parseInt(timem);
					if(text.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Введите текст задачи!");
					} else {
						TaskList.add(new String(text));
						for(int i=0; i<TaskList.size(); i++) {
							if(TaskList.get(i) != null) {
								String testtext = (String) TaskList.get(i);
								st = st+"<br>"+i+"."+"<strong>"+testtext+"</strong>"; 
							}
						}
						list.setText("<strong>"+st+"</strong>");
						TimerTask task = new TimerTask() {
					        public void run() {
					        	for(int i=0; i<TaskList.size();i++)
					        		if (hc.get(i) == this.hashCode()) { 
					        			String t = (String) TaskList.get(i);
					        			TaskList.set(i, "<em color='red'>"+t+"</em>");
					        			st = ""; 
					        			for(int j=0; j<TaskList.size(); j++) {
											if(TaskList.get(j) != null) {
												String testtext = (String) TaskList.get(j);
												st = st+"<br>"+j+"."+"<strong>"+testtext+"</strong>"; 
											}
										}
					        			list.setText("<strong>"+st+"</strong>");
					        			JOptionPane.showMessageDialog(null, i+"."+t);
					        		}
					        }
					    };
					    hc.add(new Integer(task.hashCode()));
					    Timer timer = new Timer("Timer");
					    int sec_time = time_intS*1000;
					    int min_time = time_intM*60000;
					    int ti = min_time+sec_time;
					    long delay = Long.valueOf(ti);
					    timer.schedule(task, delay);
						index += 1;
						JOptionPane.showMessageDialog(null, "Задача бала добавленна!");	
					}
				}
				catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Введите в поле 'Время' время через которое сработает таймер в формате минут!");
				}
			}
		});
		AddBut.setFont(new Font("Tahoma", Font.PLAIN, 12));
		AddBut.setBackground(new Color(154,205,50));
		AddBut.setBounds(500, 186, 140, 38);
		contentPane.add(AddBut);
		
		DelT = new JLabel();
		DelT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DelT.setText("Номер задачи для удаления");
		DelT.setBounds(230, 115, 200, 15);
		contentPane.add(DelT);
		
		DelIndex = new JTextField();
		DelIndex.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DelIndex.setBounds(260, 138, 140, 38);
		contentPane.add(DelIndex);
		DelIndex.setColumns(10);
		
		JButton DeletBut = new JButton("Удалить");
		DeletBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str_ind = DelIndex.getText();
				if(str_ind.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Укажите индекс!");
				} else {
					int ind = Integer.parseInt(str_ind);
					try {
						TaskList.remove(ind);
						st = "";
						for(int i=0; i<TaskList.size(); i++) {
							if(TaskList.get(i) != null) {
								String testtext = (String) TaskList.get(i);
								st = st+"<br>"+i+"."+"<strong>"+testtext+"</strong>"; 
							}
						}
						list.setText("<strong>"+st+"</strong>");
					}
					catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Указанный вами индекс не существует!");
					}
				}
			}
		});
		DeletBut.setFont(new Font("Tahoma", Font.PLAIN, 12));
		DeletBut.setBackground(new Color(154,205,50));
		DeletBut.setBounds(260, 186, 140, 38);
		contentPane.add(DeletBut);
	}
}
