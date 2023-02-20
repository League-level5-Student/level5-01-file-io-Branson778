package _01_File_Recorder;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FileRecorder {
	static String input = "";
	public static void inputAndWrite() {
		input = JOptionPane.showInputDialog("Enter a phrase to save.");
		try {
			FileWriter fw = new FileWriter("src/_01_File_Recorder/test.txt");
			fw.write(input);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		inputAndWrite();
	}
}
