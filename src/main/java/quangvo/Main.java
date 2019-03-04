package quangvo;
import static spark.Spark.*;
import java.util.HashMap;
import java.util.Map;

import java.util.Date;

/**
 * A simple java class that can create, get, update and delete contacts in an Address Book.
 * Will try input in JSON format, implement getAllContacts(), and write JUnit test
 * if I have more time!
 */
public class Main {
	
	//A Map (or an Address Book) that holds all the contacts
	private static Map<String, Contact> contacts = new HashMap<String, Contact>();
	
	public static void main(String[] args) {
							
        addContact(); //call GET
        getContact(); //call POST
        updateContact(); //call PUT
        deleteContact(); //call DELETE
        
    }
	
	/**
	 * POST query for adding a new contact
	 * can be accessed at "POST http://localhost:4567/contact"
	 * Example with queryParams:
	 * http://localhost:4567/contact?firstName="Quang"&lastName="Vo"&phone="703-935-9763"&email="qvo8@masonlive.gmu.edu&address="Annandale, VA"
	 */
	public static void addContact(){
	
		post("/contact", (req, res) -> {

	        String firstName = req.queryParams("firstName");
	        String lastName = req.queryParams("lastName");
	        String phone = req.queryParams("phone");
	        String email = req.queryParams("email");
	        String address = req.queryParams("address");
		
	        //create a new contact
            Contact contact = new Contact(firstName, lastName, phone, email, address);

            //put it into Address Book
            contacts.put(firstName, contact);

            res.status(201); // 201 Created
            return "Contact with name '" + firstName + "' created";
        });
	}
	
	/**
	 * GET details of a contact with the specified by a unique name
	 * can be accessed at "GET http://localhost:4567/contact/:name"
	 */
	public static void getContact(){
        get("/contact/:name", (req,res)->{
        	
        	String name = req.params(":name");
        	
        	//check to see if there is a contact with the specified name in the address book
            Contact contact = contacts.get(name);
            
            if (contact != null) {
                return contact.toString();
            } else {
                res.status(404); // 404 Not found
                return "Contact not found";
            }
        });
	}
	
	/**
	 * GET /contact?pageSize={}&page={}&query={}
	 * This one works with Elasticsearch
	 * If have more time will definitely do it!
	 */
	public static void getAllContacts(){
		
	}
	
	/**
	 * PUT to update information of a contact specified by a unique name
	 * can be accessed at "PUT http://localhost:4567/contact/:name"
	 * Example with queryParams
	 * http://localhost:4567/contact/:name?phone="777-888-999"&email="qvo8@masonlive.gmu.edu&address="Vienna, VA"
	 */
	public static void updateContact(){
		put("/contact/:name", (req, res) -> {
			
			String name = req.params(":name");
			
			//check to see if there is a contact with the specified name in the address book
            Contact contact = contacts.get(name);
            
            if (contact != null) {
            	
    	        String newPhone = req.queryParams("phone");
    	        String newEmail = req.queryParams("email");
    	        String newAddress = req.queryParams("address");

                if(newPhone != null) {
                	contact.setPhone(newPhone);
                }
                if (newEmail != null) {
                    contact.setEmail(newEmail);
                }
                if (newAddress != null) {
                    contact.setAddress(newAddress);
                }
                return "Contact with name '" + name + "' updated";
                
            } else {
                res.status(404); // 404 Not found
                return "Contact not found";
            }
        });
	}
	
	/**
	 * DELETE a contact specified by a unique name
	 * can be accessed at "DELETE http://localhost:4567/contact/:name"
	 */
	public static void deleteContact(){
		delete("/contact/:name", (req, res) -> {
			
            String name = req.params(":name");
            
            //check to see if there is a contact with the specified name in the address book
            Contact contact = contacts.remove(name);
            
            if (contact != null) {
                return "Contact with name '" + name + "' deleted";
            } else {
                res.status(404); // 404 Not found
                return "Contact not found";
            }
        });
	}
	
	
}
