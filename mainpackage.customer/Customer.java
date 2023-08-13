package mainpackage.customer;

import org.springframework.stereotype.Component;

@Component
public class Customer {

	private String nameAndSurname;

	public String getNameAndSurname() {
		return nameAndSurname;
	}
	
	public void setNameAndSurname(String nameAndSurname) {
		this.nameAndSurname = nameAndSurname;
	}
	
	public Customer() {
		
	}
	
	public Customer(String nameAndSurname) {
		setNameAndSurname(nameAndSurname);
	}
	
}
