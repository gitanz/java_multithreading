import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class CompanyRecords {
	
	private ArrayList<Car> cars = new ArrayList<Car>();
	private Lock lock = new ReentrantLock();
	
	public boolean addCar(Car car) {
		boolean added = true;
		
		lock.lock();
		try {
			cars.add(car);

		}catch (Exception e) {
			System.out.println("exception");
			added = false;
		}finally {
			lock.unlock();
		}
		return added;
	}
	
	public boolean sellCar(String registration) {
		lock.lock();
		boolean sold = true;
		try {
			long count = cars.stream().filter(car->car.registration.equals(registration)).map(c->c.forSale=false).count();
			if(count == 0 )
				sold = false;
		}catch (Exception e) {
			sold = false;
		}finally {
			lock.unlock();
		}
		return sold;
		
	}
	
	public void carsForSale(DataOutputStream out) {
		lock.lock();
		String sales = "";
		try {
			String carsForSale = cars.stream().filter(car -> car.forSale == true).map(car->car.toString()).collect(Collectors.joining("\n"));
			out.writeUTF(carsForSale);
		}catch(Exception e) {
			
		}finally {
			lock.unlock();
		}
	}
	
	public void searchByMake(String make, DataOutputStream out) {
		lock.lock();
		try {
			String searchResults = cars.stream().filter(car -> car.make.equals(make) && car.getForSale()== true).map(car->car.toString()).collect(Collectors.joining("\n"));
			out.writeUTF(searchResults);	
		}catch(Exception e) {
			
		}finally {
			lock.unlock();	
		}
			
		
		
	}
	
	public int searchTotalValue() {
		lock.lock();
		int totalValue = 0;
		try {
			totalValue = cars.stream().filter(car-> car.getForSale() == true).map(car-> car.getPrice()).reduce(0, (price_a, price_b) -> price_a + price_b);
			
		}catch (Exception e) {
			totalValue = 0 ;
		}finally {
			lock.unlock();
		}
		return totalValue;
		
	}

}
