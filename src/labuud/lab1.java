package labuud;
import java.util.Scanner;
import dataStructures.ArrayLinearList;
public class lab1 {

	public static void main(String[] args) {
		Park thepark = new Park();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter number of visitor's slots:");
        int numOfVisitorSlot = scanner.nextInt();
        scanner.nextLine(); 
        System.out.println("Please enter number of staff's slots:");
        int numOfStaffSlot = scanner.nextInt();
        scanner.nextLine();
        thepark.createVisitorSlots(numOfVisitorSlot);
        thepark.createStaffSlots(numOfStaffSlot);
		 while (true) {
	            System.out.println("Menu:");
	            System.out.println("1. List all parking slots");
	            System.out.println("2. Park a car");
	            System.out.println("3. Search for a car");
	            System.out.println("4. Add a new parking slot");
	            System.out.println("5. Remove a parking slot");
	            System.out.println("6. Remove a car from a parking slot");
	            System.out.println("7. Exit");
	            System.out.print("Enter your choice: ");
	            System.out.print("check");
	            
	            int choice = scanner.nextInt();
	            scanner.nextLine(); 
	  
	            switch (choice) {
	                case 1:
	                    thepark.listAllParkingSlots();
	                    break;
	                case 2:
	                	 System.out.print("Enter parking slot ID: ");
	                	    String slotID = scanner.nextLine();
	                	    System.out.print("Enter car ID: ");
	                	    String carID = scanner.nextLine();
	                	    System.out.print("Enter owner: ");
	                	    String owner = scanner.nextLine();
	                	    System.out.print("Is staff (true/false): ");
	                	    boolean isStaff = scanner.nextBoolean();
	                	    scanner.nextLine();

	                	    thepark.parkCar(slotID, carID, owner, isStaff);
	                	    break;
	                case 3:
	                    System.out.print("Enter car ID: ");
	                    carID = scanner.nextLine();
	                    thepark.checkCardID(carID);
	                    thepark.searchCar(carID);
	                  
	                    break;
	                case 4:
	                    System.out.print("Enter new parking slot ID: ");
	                    slotID = scanner.nextLine();
	                    thepark.addslot(slotID);
	                    
	                    break;
	                case 5:
	                    System.out.print("Enter slot ID to remove: ");
	                    
	                    String slotID1 = scanner.nextLine();
	                    scanner.nextLine(); 
	                    thepark.removeSlot(slotID1);
	                    break;

	                case 6:
	                    System.out.print("Enter car ID to remove: ");
	                    String carID1 = scanner.nextLine();
	                    scanner.nextLine(); 
	                    thepark.checkCardID(carID1);
	                    thepark.removeCar(carID1);
	                   
	                    break;
	                case 7:
	                    scanner.close();
	                    System.exit(0);
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	            }
	        }
	    }
	}
class Park extends ArrayLinearList {
   

	public void createVisitorSlots(int numSlots) {
	    for (int i = 1; i <= numSlots; i++) {
	        Slot slot = new Slot("V" + String.format("%03d", i));
	        this.add(this.size(), slot);
	    }
	}

	public void createStaffSlots(int numSlots) {
	    for (int i = 1; i <= numSlots; i++) {
	        Slot slot = new Slot("S" + String.format("%03d", i));
	        this.add(this.size(), slot);
	    }
	}

    public void listAllParkingSlots() {
      for(int i =0;i<this.size();i++) {
    	  Slot slot = (Slot) this.get(i);
    	  System.out.println("Slod ID: "+slot.sid);
    	  if(slot.car!=null) {
    		  System.out.print("- Occupied by CAR ID:  " +slot.car.cid);
    		  System.out.print(" Type: ");
    		  if(slot.car.isStaff) {
    			  System.out.println(" Staff ");
    		  }
    		  else {
    			  System.out.println(" Visitor ");
    		  }
    	  }
    	  else {
    		  System.out.println("- Empty");
    	  }
      }
    }
    public void parkCar(String slotID, String carID, String owner, boolean isStaff) {
        Slot selectedSlot = null;
        for (int i = 0; i < this.size(); i++) {
            Slot slot = (Slot) this.get(i);
            if (slot.sid.equals(slotID)) {
                selectedSlot = slot;
                break;
            }
        }

        if (selectedSlot == null) {
            System.out.println("Slot " + slotID + " not found or does not exist.");
            return; // 
        }

        // Check if the car ID is valid
        if (!checkCardID(carID)) {
            System.out.println("Car ID must start with one or more capital letters followed by 5 digits.");
            return;
        }

        if (selectedSlot.car != null) {
            System.out.println("Slot " + slotID + " is already occupied by another car.");
        } else {
            Car car = new Car(owner, carID, isStaff);
            selectedSlot.car = car;
            System.out.println("Car with ID " + carID + " has been parked in slot " + slotID);
        }
    }

    public void searchCar(String carID) {
    	 if (!checkCardID(carID)) {
    	        System.out.println("CarID must start UpWord and length is 6.");
    	        return;
    	    }
        for (int i = 0; i < this.size(); i++) {
            Slot slot = (Slot)this.get(i);
            if (slot.car != null && slot.car.cid.equals(carID)) {
                System.out.println("Car found in Slot " + slot.sid + ":");
                System.out.println("Slot ID: " + slot.sid);
                System.out.println("Car ID: " + slot.car.cid);
                System.out.println("Owner: " + slot.car.owner);
                System.out.print("Type: ");
                if (slot.car.isStaff) {
                    System.out.println("Staff");
                } else {
                    System.out.println("Visitor");
                }

                return; 
            }
        }
        System.out.println("Car with ID " + carID + " not found in any slot.");
    }
    public void addslot(String slotID){
        Slot slot = new Slot(slotID);
        slot.sid = slotID;
        this.add(this.size(),slot);

        System.out.println("New slot with ID " + slotID + " has been added.");
  
    }
    public void removeSlot(String slotID) {
        int i = 0;
        while (i < this.size()) {
            Slot slot = (Slot) this.get(i);
            if (slot.sid.equals(slotID)) {
                if (slot.car == null) {
                    this.remove(i);
                    System.out.println("Slot " + slotID + " has been removed.");
                } else {
                    System.out.println("Cannot remove Slot " + slotID + " because it's occupied by a car.");
                }
                return;
            }
            i++;
        }
        System.out.println("Slot " + slotID + " not found or does not exist.");
    }


    public void removeCar(String carID) {
    	 if (!checkCardID(carID)) {
    	        System.out.println("CarID must start UpWord and length is 6.");
    	        return;
    	    }
        for (int i = 0; i < this.size(); i++) {
            Slot slot = (Slot)this.get(i);
            if (slot.car != null && slot.car.cid.equals(carID)) {
                slot.car = null;
                System.out.println("Car with ID " + carID + " has been removed from slot " + slot.sid);
                return;
            }
        }
        System.out.println("Car with ID " + carID + " not found in any slot.");
    }
   
    public boolean checkCardID(String carID) {
        char[] UpWorld = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'P', 'W', 'V', 'X', 'Y', 'Z'};

        if (carID.length() != 6) {
            return false; // REGEX ASHIGLA REGEX N AMARHAN YM BNLE IIM OLON YM ASHIGLAH SHAARDLAGGU
            
        }

        char firstChar = carID.charAt(0);
        boolean isCapitalLetter = false;

        for (int i = 0; i < UpWorld.length; i++) {
            if (firstChar == UpWorld[i]) {
                isCapitalLetter = true;
                break;
            }
        }

        if (!isCapitalLetter) {
            return false; 
        }
        for (int i = 1; i < carID.length(); i++) {
            char digit = carID.charAt(i);
            if (digit < '0' || digit > '9') {
                return false;
            }
        }
        return true;
    }

}
class Slot {
    String sid;
    Car car;
    public Slot(String sid) {
        this.sid = sid;
    }
}
class Car{
    String owner;
    String cid;
    boolean isStaff;
    
    public Car(String owner, String cid, boolean isStaff) {
        this.owner = owner;
        this.cid = cid;
        this.isStaff = isStaff;
    }
}
