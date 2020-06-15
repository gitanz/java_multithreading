import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.nio.charset.Charset;
import java.util.Random;

final public class Car {
	
	public String registration;
	
	public String make;
	
	public int price;
	
	public String mileage;
	
	public Boolean forSale;
	
	public Car() {
		
	}
	
	public Car(String make, int price, String mileage, Boolean forSale) {
		
		this.registration = Integer.toString((new Random().nextInt(1000)));
		this.make = make;
		this.price = price;
		this.mileage = mileage;
		this.forSale = forSale;
		
	}
	
	public String toString() {
		String string = "Registration :"+registration +"\t"+
				"Make :" + make +"\t"+
				"Price :" + Integer.toString(price)+"\t"+
				"Mileage :" + mileage + "\t"+
				"For sale :" + forSale ;
		return string;
	}
	
	
	public String getRegistration() {
		return registration;
	}


	public void setRegistration(String registration) {
		this.registration = registration;
	}


	public String getMake() {
		return make;
	}


	public void setMake(String make) {
		this.make = make;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getMileage() {
		return mileage;
	}


	public void setMileage(String mileage) {
		this.mileage = mileage;
	}


	public Boolean getForSale() {
		return forSale;
	}


	public void setForSale(Boolean forSale) {
		this.forSale = forSale;
	}


	public void readInputStream(DataInputStream in) {
		try {
			registration = in.readUTF();
			make = in.readUTF();
			price = in.readInt();
			mileage = in.readUTF();
			forSale = in.readBoolean();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void writeOutputStream(DataOutputStream out) {
		try {
			out.writeUTF(registration);
			out.writeUTF(make );
			out.writeInt(price);
			out.writeUTF(mileage );
			out.writeBoolean(forSale);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}


}
