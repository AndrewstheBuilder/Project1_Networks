import java.net.InetAddress;
import java.net.ServerSocket;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ClientS implements Runnable{
	public int command;
	ClientS(int command){
		this.command = command;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Thread[] threads = new Thread[25];
			Scanner in = new Scanner(System.in);
			InetAddress ipAddr = InetAddress.getByName("139.62.210.153");
			System.out.println("Enter Port Number:");
			int port = in.nextInt();
			int queued = 0;
			ServerSocket serverSocket = new ServerSocket(port, queued, ipAddr);
			
			int userCommand = -1;
			while(true) {
				System.out.print("1.Date and Time\n"
						+ "2.Uptime\n"
						+ "3.Memory User\n"
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
					threads[i] = new Thread(client);
					threads[i].start();
					while(threads[i].isAlive()) {
						
					}
				}
			}
		} catch(Exception ex) {
			System.out.println(ex);
		}

		
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Command received:"+ this.command);
	}

}
