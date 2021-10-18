import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.time.LocalDateTime;
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
			System.out.println("Enter Port Number:");
			int port = in.nextInt();
			
			int userCommand = -1;
			while(true) {
				System.out.print("1.Date and Time\n"
						+ "2.Uptime\n"
						+ "3.Memory Use\n"
						+ "4.Netstat\n"
						+ "5.Current Users\n"
						+ "6.Running Processes\n"
						+ "7.Quit\n");
				userCommand = in.nextInt();
				if(userCommand ==7 ) break;
				System.out.println("How many times do you want to run this command?");
				int threadsRequired = in.nextInt();
				LocalDateTime startTime = LocalDateTime.now();
				ClientS client = new ClientS(userCommand);
				for(int i = 0; i < threadsRequired; i++) {
					String hostName = "CNT4505D.ccec.unf.edu";
					ClientS.socket = new Socket(hostName, port);
					OutputStream output = ClientS.socket.getOutputStream();
					ClientS.writer = new PrintWriter(output, true);
					threads[i] = new Thread(client);
					threads[i].start();
					while(threads[i].isAlive()) {
						
					}
					ClientS.socket.close();
				}
				
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
	        //System.out.println("OUTPUT FROM SERVER:");
	        String temp = "";
	        while((temp=reader.readLine()) != null) {
	        	System.out.println(temp);
	        }
		} catch( Exception ex) {
			System.out.println(ex);
		}

	}

}
