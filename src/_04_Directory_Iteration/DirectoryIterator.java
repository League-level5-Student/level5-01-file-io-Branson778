package _04_Directory_Iteration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JFileChooser;

public class DirectoryIterator {
	protected static int returns;
	public static void main(String[] args) {
		/* 
		 * The following is an example of how to list all of the files in a directory.
		 * Once the program is running, the directory is chosen using the JFileChooser.
		 */
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = jfc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File directory = jfc.getSelectedFile();
			File[] files = directory.listFiles();
			if(files != null) {
				for(File f : files) {
				  System.out.println(f.getAbsolutePath());
				}
			}
		}
		
		/*
		 * Your task is to write a program that iterates through the src folder of this current Java Project. 
		 * For every .java file it finds, the program will add a (non-legally binding) copyright statement at the bottom.
		 * Be aware of possible directories inside of directories.
		 * (e.g //Copyright © 2019 FirstName LastName)
		 */
		DirectoryIterator runner = new DirectoryIterator();
		JFileChooser jfc2 = new JFileChooser();
		jfc2.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		returns = jfc2.showOpenDialog(null);
		if(returns == JFileChooser.APPROVE_OPTION) {
		runner.recursiveCopyright(jfc2.getSelectedFile());
		}
	}
	public void recursiveCopyright(File direct) {
		File directory2 = direct;
		File[] directories = directory2.listFiles();
		if(directories != null) {
			for (File file : directories) {
				if(!file.isDirectory()) {
				try {
					if(file.getName().contains(".java")) {
					FileWriter fr = new FileWriter(directory2,true);
					fr.write("Copyright © 2023 Branson Boock");
					System.out.println(file.getAbsolutePath());
					fr.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
				else {
					recursiveCopyright(file);
				}
			}
		}
}
}
