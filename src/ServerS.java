import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.CharBuffer;
import java.util.Scanner;


public class ServerS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
			System.out.println("Port is:  " + portNum);
			ServerSocket serverSocket = new ServerSocket(portNum);
			Process result;
			do {
				System.out.println("Waiting for new connection...");
				Socket socket = serverSocket.accept();
				System.out.println("New client connected:"+socket.getInetAddress());
				
				InputStream input = socket.getInputStream();
				
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(input));
				String line = reader.readLine();  
				
				int inputInt = Integer.parseInt(line);
				//System.out.println("Command received:"+inputInt);
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
						result = Runtime.getRuntime().exec("date");
						break; 
					case 2:
						result = Runtime.getRuntime().exec("uptime");
						break; 
					case 3:
						//THIS IS MEMORY USE 
						result = Runtime.getRuntime().exec("free");
						break; 
					case 4:
						result = Runtime.getRuntime().exec("netstat");
						break; 
					case 5:
						result = Runtime.getRuntime().exec("who");
						break; 
					case 6:
						result = Runtime.getRuntime().exec("ps");
						break;
					default:
						result = Runtime.getRuntime().exec("");
						break;
					}
				BufferedReader stdInput = new BufferedReader(new InputStreamReader(result.getInputStream()));
	            BufferedReader stdError = new BufferedReader(new InputStreamReader(result.getErrorStream()));
				OutputStream output = socket.getOutputStream();
	            PrintWriter writer = new PrintWriter(output, true);
	            //stdInput.read(outputBuffer);
	            // read the output from the command
//	            while ((temp=stdInput.readLine()) != null) {
//	            	outputBuffer += temp;
//	            	outputBuffer += "\n";
//	            	//System.out.println(outputBuffer);
//	            	
//	            }
	            Scanner s = new Scanner(result.getInputStream()).useDelimiter("\\A");
	            String outputBuffer = s.hasNext() ? s.next() : "";
	            writer.println(outputBuffer);
	            
//	            // read any errors from the attempted command
//	            System.out.println("Here is the standard error of the command (if any):\n");
//	            while ((outputBuffer = stdError.readLine()) != null) {
//	                System.out.println(outputBuffer);
//	            }
	            //writer.println(result.toString());
				//writer.println(result.getOutputStream().toString());
				
				//System.out.println(result.getErrorStream().toString()); 
				
				//socket.close();
				//serverSocket.close();
			} while(true);
		} catch(Exception ex) {
			System.out.println("Server exception: "+ex.getMessage());
			ex.printStackTrace();
		}		

	}

}
