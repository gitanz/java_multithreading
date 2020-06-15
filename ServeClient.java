import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ServeClient implements Runnable{
	private Socket socket;
	private CompanyRecords companyData;
	public ServeClient(Socket s, CompanyRecords records) {
		
		socket = s;
		companyData = records;
		
	}
	
	
	@Override
	public void run() {
		while(true) {
			try {
				DataInputStream in = new DataInputStream(socket.getInputStream());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				int option = in.readInt();
				switch (option) {
				case 1:
					Car c = new Car();
					c.readInputStream(in);
					companyData.addCar(c);
					out.writeBoolean(true);
					break;
				case 2:
					companyData.carsForSale(out);
					
					break;
				case 3:
					companyData.searchByMake(in.readUTF(), out);
					break;
				case 4:
					out.writeInt(companyData.searchTotalValue());
					break;
				case 5:
					out.writeBoolean(companyData.sellCar(in.readUTF()));
					break;
				default:
					out.writeInt(-1);
					break;
				}
				
			}catch (Exception e) {
				
			}finally {
				
			}	
		}
		
	}
	
}