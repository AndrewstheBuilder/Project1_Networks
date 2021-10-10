
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class ServerS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s = null;
		

		int portNum = 0;
		

		try {
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
			
			ServerSocket serverSocket = new ServerSocket(portNum);
			
			do {
				Socket socket = serverSocket.accept();
				InputStream input = socket.getInputStream();
				
				System.out.println("Input is: " + input);
				break; 
				//Runtime.getRuntime().exec();
				
				
				
				
			} while(true);
		} catch(Exception ex) {
			System.out.println(ex);
		}		

	}

}
