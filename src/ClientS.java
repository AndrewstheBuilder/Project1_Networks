import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

public class ClientS implements Runnable{
	public int command;
	public static PrintWriter writer;
	public static Socket socket;
	
	ClientS(int command){
		this.command = command;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Thread[] threads = new Thread[25];
			Scanner in = new Scanner(System.in);
			System.out.println("Enter host to connect to:");
			InetAddress ipAddr = InetAddress.getByName(in.next());
			System.out.println("Enter Port Number:");
			int port = in.nextInt();
			
			int threadsRequired = -1;
			int userCommand = -1;
			double startTime;
			double endTime;
			double totalElapsedTime = 0;
			double avgElapsedTime = 0;
			
			while(true) {
				System.out.print("1.Date and Time\n"
						+ "2.Uptime\n"
						+ "3.Memory Use\n"
						+ "4.Netstat\n"
						+ "5.Current Users\n"
						+ "6.Running Processes\n"
						+ "7.Quit\n");
				userCommand = in.nextInt();
				while(userCommand < 1 || userCommand > 7) {
					System.out.println("Command must be between 1-7");
					userCommand = in.nextInt();
				}
				if(userCommand ==7 ) break;
				while(true) {
					System.out.println("How many times do you want to run this command?");
					threadsRequired = in.nextInt();
					if(threadsRequired > 0 && threadsRequired < 26) break;
					else System.out.println("Number of threads must be between (1-25)");
				}
				ClientS client = new ClientS(userCommand);
				startTime = (new Date()).getTime();
				for(int i = 0; i < threadsRequired; i++) {
					ClientS.socket = new Socket(ipAddr, port);
					OutputStream output = ClientS.socket.getOutputStream();
					ClientS.writer = new PrintWriter(output, true);
					threads[i] = new Thread(client);
					threads[i].start();
					threads[i].join();
					ClientS.socket.close();
				}
				endTime = (new Date()).getTime();
				totalElapsedTime = endTime - startTime;
				avgElapsedTime = (double)(totalElapsedTime/threadsRequired);
				System.out.println("Total elapsed time = "+totalElapsedTime+"ms");
				System.out.println("Average elapsed time = "+avgElapsedTime+"ms");
			}
			
		} 
		catch(IOException ex) {
			System.out.println("Client exception:"+ex);
			ex.printStackTrace();
		}
		catch(Exception ex) {
			System.out.println("Client exception:"+ex);
			ex.printStackTrace();
		}

		
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			writer.println(this.command);
			InputStream input = socket.getInputStream();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	        String temp = "";
	        while((temp=reader.readLine()) != null) {
	        	System.out.println(temp);
	        }
		} catch( Exception ex) {
			System.out.println(ex);
		}

	}

}
