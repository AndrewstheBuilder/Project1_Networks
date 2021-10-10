import java.io.*;
import java.util.Scanner;

public class ServerSide {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s = null;
		// String inpAddr = null;

		int portNum = 0;

		Scanner in = new Scanner(System.in);

		while (true) {
			System.out.println("Which port to run (Between 1025-4998)");

			portNum = in.nextInt();

			if (portNum <= 4998 && portNum >= 1025) {
				break;
			} else {
				System.out.print("Number is not within bounds");
			}
			

		}
		System.out.println(" Port is:  " + portNum);

	}

}
