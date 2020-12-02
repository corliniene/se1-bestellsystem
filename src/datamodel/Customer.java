package datamodel;

public class Customer {
	private final String id;
	private String firstName;
	private String lastName;
	private String contact;
	
protected Customer(String id, String name, String contact ){
	this.id = id;
	this.firstName = "";
	setLastName(name);
	setContact(contact);
}

public String getId() {
	
	return this.id;
}

public String getFirstName() {
	
	return this.firstName;
}

public void setFirstName(String firstName) {
	if(firstName == null) {
		this.firstName = "";
	} else {
		this.firstName = firstName;
	}
}

public String getLastName() {
	
	return this.lastName;
}

public void setLastName(String lastName) {
	if(lastName == null) {
		this.lastName = "";
	} else {
		this.lastName = lastName;
	}
}

	


public String getContact() {
	return this.contact;

}

public void setContact(String contact) {
	if(contact == null) {
		this.contact = "";
	} else {
		this.contact = contact;
	}
}
























}
