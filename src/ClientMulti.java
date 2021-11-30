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

public class ClientMulti extends Thread{
	Socket socket;
	OutputStream output;
	BufferedReader reader;
	PrintWriter writer;
	int command;
	int threadNum;
	ClientMulti(InetAddress address, int port, int threadNum, int command){
		try{
			this.socket = new Socket(address, port);
			this.output = socket.getOutputStream();
			this.writer = new PrintWriter(output, true);
			this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.command = command;
			this.threadNum = threadNum;
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
				
				//make sure input command is within correct bounds
				while(true) {
					System.out.println("How many times do you want to run this command?");
					threadsRequired = in.nextInt();
					if(threadsRequired > 0 && threadsRequired < 26) break;
					else System.out.println("Number of threads must be between (1-25)");
				}
				
				//intialize threads
				for(int i = 0; i < threadsRequired; i++) {
					ClientMulti clients = new ClientMulti(ipAddr, port, (i+1), userCommand);
					threads[i] = new Thread(clients);
				}
				
				//start threads and timer
				startTime = (new Date()).getTime();
				for(int i = 0; i < threadsRequired; i++) {
					threads[i].start();
				}
				
				//join threads
				for(int i = 0; i < threadsRequired; i++) {
					threads[i].join();
				}
				//end timer
				endTime = (new Date()).getTime();
				
				totalElapsedTime = endTime - startTime;
				avgElapsedTime = (double)(totalElapsedTime/threadsRequired);
				System.out.println("Total elapsed time = "+totalElapsedTime+"ms");
				System.out.println("Average elapsed time = "+avgElapsedTime+"ms");
				//clients.socket.close();
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
			System.out.println("Running thread "+threadNum);
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
