package _02_File_Encrypt_Decrypt;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FileDecryptor {
	/*
	 * Decryption is the process of taking encoded or encrypted text or other data
	 * and converting it back into text that you or the computer can read and understand.
	 *
	 * The shift cipher is decrypted by using using the key and shifting back up,
	 * at the end of our encryption example we had our alphabet as:
	 *
	 * e f g h i j k l m n o p q r s t u v w x y z a b c d
	 *
	 * If we shift it back up by 4 based on the key we used the alphabet is decrypted:
	 *
	 * a b c d e f g h i j k l m n o p q r s t u v w x y z
	 *
	 * "Lipps Asvph" returns to "Hello World"
	 * 
	 * Create a program that opens the file created by FileEncryptor and decrypts
	 * the message, then display it to the user in a JOptionPane.
	 */
	public static void decrypt() {
		String input = JOptionPane.showInputDialog("Insert Text To Be Decrypted");
		char[] shift = input.toCharArray();
		for(int i = 0; i < input.length(); i++) {
			shift[i] = (char) (shift[i]-4);
		}
		String finals = "";
		for (int i = 0; i < shift.length; i++) {
			finals = finals+shift[i];
		}
		try {
			FileWriter fw = new FileWriter("src/_02_File_Encrypt_Decrypt/decrypted.txt");
			fw.write(finals);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		decrypt();
	}
}
