import java.io.*;
import java.net.InetAddress;
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
			InetAddress ipAddr = InetAddress.getByName("139.62.210.153");
			ServerSocket serverSocket = new ServerSocket(portNum);
			do {
				Socket socket = serverSocket.accept();
				System.out.println("New client connected");
				
				InputStream input = socket.getInputStream();
				
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(input));
				String line = reader.readLine();  
				
				int inputInt = Integer.parseInt(line);
				System.out.println("Command received(ServerSide):"+inputInt);
				/*
				Date and Time - the date and time on the server
				Uptime - how long the server has been running since last boot-up
				Memory Use - the current memory usage on the server
				Netstat - lists network connections on the server
				Current Users - list of users currently connected to the server
				Running Processes - list of programs currently running on the server
				*/
				
				switch (inputInt) {
					case 1:
						Runtime.getRuntime().exec("date");
						break; 
					case 2:
						Runtime.getRuntime().exec("uptime");
						break; 
					case 3:
						//THIS IS MEMORY USE 
						Runtime.getRuntime().exec("free");
						break; 
					case 4:
						Runtime.getRuntime().exec("netstat");
						break; 
					case 5:
						Runtime.getRuntime().exec("who");
						break; 
					case 6:
						Runtime.getRuntime().exec("ps");
						break; 
					}
				//Runtime.getRuntime().exec();
				
				OutputStream output = socket.getOutputStream();
	            PrintWriter writer = new PrintWriter(output, true);
				writer.println("Wasup bro I am the server");
				//socket.close();
				//serverSocket.close();
			} while(true);
		} catch(Exception ex) {
			System.out.println(ex);
		}		

	}

}
