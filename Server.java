import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Server {

	public static void main(String[] args) {
		
		ExecutorService service = Executors.newFixedThreadPool(50);	
		
		Semaphore clientSemaphore = new Semaphore(50);
		
		CompanyRecords companyData = new CompanyRecords();
		try {
			ServerSocket server = new ServerSocket(5000);
			
			while(true) {
				
				try {
					
					Socket clientConnection = server.accept();
					clientSemaphore.acquire();
					
					service.submit(new ServeClient(clientConnection, companyData));
					
					
				
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				
				} finally{
					clientSemaphore.release();
				}
				
				
			
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}

