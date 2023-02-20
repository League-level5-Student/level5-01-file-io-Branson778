package _03_To_Do_List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ToDoList implements ActionListener {
	/*
	 * Create a program with five buttons, add task, view tasks, remove task, save list, and load list. 
	 *
	 * When add task is clicked:
	 * 		Create a JOptionPane to ask the user for a task and add it to an ArrayList
	 * 
	 * When the view tasks button is clicked:
	 *		show all the tasks in the list
	 * 
	 * When the remove task button is clicked:
	 * 		Create a JOptionPane to prompt the user for which task to remove and take it off of the list.
	 * 
	 * When the save list button is clicked:
	 * 		Save the list to a file
	 * 
	 * When the load list button is clicked:
	 * 		Create a JOptionPane to Prompt the user for the location of the file and load the list from that file
	 * 
	 * When the program starts, it should automatically load the last saved file into the list. 
	 */
	JFrame frame;
	JPanel panel;
	JButton add;
	JButton view;
	JButton remove;
	JButton save;
	JButton load;
	ArrayList<String> tasks;
	public static void main(String[] args) {
		ToDoList runner = new  ToDoList();
		runner.setUp();
	}
	protected void setUp() {
		frame = new JFrame();
		panel = new JPanel();
		add = new JButton();
		view = new JButton();
		remove = new JButton();
		save = new JButton();
		load = new JButton();
		tasks = new ArrayList<String>();
		add.setText("Add Task");
		view.setText("View Tasks");
		remove.setText("Remove Task");
		save.setText("Save List");
		load.setText("Load List");
		add.addActionListener(this);
		view.addActionListener(this);
		remove.addActionListener(this);
		save.addActionListener(this);
		load.addActionListener(this);
		frame.add(panel);
		panel.add(add);
		panel.add(view);
		panel.add(remove);
		panel.add(save);
		panel.add(load);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==add) {
			String temp = JOptionPane.showInputDialog("What Task Should Be Added To The List?\nEnter In A Word Or Sentence");
			tasks.add(temp);
		}
		if(e.getSource()==view) {
			String temp2 = "";
			for (int i = 0; i < tasks.size(); i++) {
				if(i==0) {
					temp2 = temp2+tasks.get(i);
				}
				else {
					temp2 = temp2+"\n"+tasks.get(i);
				}
			}
			JOptionPane.showMessageDialog(null, temp2);
		}
		if(e.getSource()==remove) {
			//JOptionPane.showOptionDialog(null,"Select One Task To Remove","Removal Window",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,tasks,tasks.get(0));
		}
		if(e.getSource()==save) {
			try {
				FileWriter fw = new FileWriter("src/_03_To_Do_List/saved.txt");
				fw.write(tasks.toString());
				fw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource()==load) {
			load();
		}
		
	}
	public void load() {
		
	}
}
