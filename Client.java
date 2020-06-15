import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	
	public static void main(String[] args) {	
		
			try {
				Socket socket = new Socket("127.0.0.1", 5000);
				
				process(socket);
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}			
						
	}
	
	public static void process(Socket socket) {
		showMenu();
		Scanner scanInput = new Scanner(System.in);
		int menuOption = scanInput.nextInt();
		scanInput.nextLine();
		DataInputStream in;
		DataOutputStream out;
		
		switch (menuOption) {
		case 1:
				write(socket, new Car("Ford Fiesta", 25000, "3000", true));
				write(socket, new Car("Ford Focus", 24000, "2500", true));
				write(socket, new Car("Ford Mondeo", 23000, "1600", true));
				write(socket, new Car("Ford Mustang", 22000, "7899", true));
				write(socket, new Car("Ford B-Max", 21000, "1230", true));
				write(socket, new Car("Ford C-Max", 20000, "2345", true));
				write(socket, new Car("Toyota Scarlett", 19000, "1235", true));
				write(socket, new Car("Toyota Avensis", 18000, "1345", true));
				write(socket, new Car("Ferrari", 17000, "1234", true));
				write(socket, new Car("Mitsubishi Lancer", 16000, "1245", true));
				write(socket, new Car("Ford D-Max", 15000, "4213", true));
				write(socket, new Car("Ford E-Max", 14000, "4321", true));
				write(socket, new Car("Ford F-Max", 13000, "5132", true));
				write(socket, new Car("Ford G-Max", 12000, "1234", true));
				write(socket, new Car("Ford Mustang", 1000, "5421", true));
				process(socket);
			break;
			
		case 2:
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				out.writeInt(2);
				System.out.println(in.readUTF());	
			}catch(IOException e) {
				
			}				
			process(socket);

			
		case 3:
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				out.writeInt(3);
				System.out.println("Type in car make: ");
				String makeSearch = scanInput.nextLine();
				out.writeUTF(makeSearch);
				System.out.println(in.readUTF());	
			}catch(IOException e) {
				
			}finally {
				process(socket);
			}
			
		case 4:
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				out.writeInt(4);
				int totalValue = in.readInt();
				System.out.println(totalValue);	
			}catch(IOException e) {
				
			}finally {
				process(socket);
			}
			
		case 5:
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				out.writeInt(5);
				System.out.println("Type in car registration: ");
				
				out.writeUTF(scanInput.nextLine());
				if(in.readBoolean()) {
					System.out.println("Car sold successfully.");
				}else {
					System.out.println("Car not found.");
				}
			}catch(Exception e) {
				
			}finally {
				process(socket);
			}
			
		default:
			break;
		}
	}
	
	public static void write(Socket socket, Car car) {
		try {
			
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			
			out.writeInt(1);
			car.writeOutputStream(out);
			if(in.readBoolean()==true) {
				System.out.println(car.make+" added successfully");
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void showMenu() {
		
		
		System.out.println("---------------------------------------------");
		System.out.println("-------------------- Menu -------------------");
		System.out.println("---------------------------------------------");
		System.out.println("---------------------------------------------");
		System.out.println("1. Add a Car ------------------------ Press 1");
		System.out.println("2. Cars for Sale -------------------- Press 2");
		System.out.println("3. Search By Make ------------------- Press 3");
		System.out.println("4. Search Total Value --------------- Press 4");
		System.out.println("5. Sell a Car ----------------------- Press 5");
		
	}
	
}
