package labuud;
import java.util.Scanner; 
import dataStructures.ArrayLinearList;
public class lab1 {

	public static void main(String[] args) {
		Park thepark = new Park();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Зорчигчийн зогсоолын дугаарыг оруул."); //Please enter number of visitor's slots
        int numOfVisitorSlot = scanner.nextInt();
        scanner.nextLine(); 
        System.out.println("Ажилчдын зогсоолын дугаарыг оруул.");
        int numOfStaffSlot = scanner.nextInt();
        scanner.nextLine();
        thepark.createVisitorSlots(numOfVisitorSlot);
        thepark.createStaffSlots(numOfStaffSlot);
		 while (true) {
	            System.out.println("Цэс:");
	            System.out.println("1. Бүх машины зогсоолын жагсаалт");
	            System.out.println("2. Машин байршуулах");
	            System.out.println("3. Машин хайх");
	            System.out.println("4. Шинэ зогсоол нэмэх");
	            System.out.println("5. Зогсоол хасах");
	            System.out.println("6. Зогсоолоос машин гаргах");
	            System.out.println("7. Гарах");
	            System.out.print("Сонголтоо оруулна уу: ");
	            int choice = scanner.nextInt();
	            scanner.nextLine(); 
	  
	            switch (choice) {
	                case 1:
	                    thepark.listAllParkingSlots();
	                    break;
	                case 2:
	                	 System.out.print("Зогсоллын дугаар оруул: ");
	                	    String slotID = scanner.nextLine();
	                	    System.out.print("Машины дугаар оруул: ");
	                	    String carID = scanner.nextLine();
	                	    System.out.print("Эзэмшигчийн нэр оруул");
	                	    String owner = scanner.nextLine();
	                	    System.out.print("Ажилчин мөн эсэх (true/false): ");
	                	    boolean isStaff = scanner.nextBoolean();
	                	    scanner.nextLine();

	                	    thepark.parkCar(slotID, carID, owner, isStaff);
	                	    break;
	                case 3:
	                    System.out.print("Машины дугаар оруул: ");
	                    carID = scanner.nextLine();
	                    thepark.checkCardID(carID);
	                    thepark.searchCar(carID);
	                  
	                    break;
	                case 4:
	                    System.out.print("Шинэ зогсоолын дугаар оруул: ");
	                    slotID = scanner.nextLine();
	                    thepark.addslot(slotID);
	                    
	                    break;
	                case 5:
	                    System.out.print("Устгах зогсоолын дугаарыг оруул: ");
	                    
	                    String slotID1 = scanner.nextLine();
	                    scanner.nextLine(); 
	                    thepark.removeSlot(slotID1);
	                    break;

	                case 6:
	                    System.out.print("Гаргах машины дугаарыг оруул: ");
	                    String carID1 = scanner.nextLine();
	                    scanner.nextLine(); 
	                    thepark.checkCardID(carID1);
	                    thepark.removeCar(carID1);
	                   
	                    break;
	                case 7:
	                    scanner.close();
	                    System.exit(0);
	                default:
	                    System.out.println("Алдаатай утга.Дахин оруулна уу");
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
    	  System.out.println("Зогсоолын дугаар: "+slot.sid);
    	  if(slot.car!=null) {
    		  System.out.print("- Энэ зогсоолд байгаа машины дугаар:  " +slot.car.cid);
    		  System.out.print(" Tөрөл: ");
    		  if(slot.car.isStaff) {
    			  System.out.println(" Ажилчин ");
    		  }
    		  else {
    			  System.out.println(" Зочин ");
    		  }
    	  }
    	  else {
    		  System.out.println("- Хоосон");
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
            System.out.println( slotID + " Энэ дугаартай зогсоол олдсонгүй.");
            return; // 
        }

        // Check if the car ID is valid
        if (!checkCardID(carID)) {
            System.out.println("Машины дугаар нь том үсгээр эхэлж араасаа 5-н оронтой дугаартай байна.");
            return;
        }

        if (selectedSlot.car != null) {
            System.out.println(slotID + " Энэ дугаартай зогсоолд машин байна");
        } else {
            Car car = new Car(owner, carID, isStaff);
            selectedSlot.car = car;
            System.out.println( carID + " Энэ дугаартай машин  " + slotID +"-р зогсоолд байна" );
        }
    }

    public void searchCar(String carID) {
    	 if (!checkCardID(carID)) {
    	        System.out.println("Машины дугаар том үсгээр эхэлж араасаа 5 оронтой тоо байна");
    	        return;
    	    }
        for (int i = 0; i < this.size(); i++) {
            Slot slot = (Slot)this.get(i);
            if (slot.car != null && slot.car.cid.equals(carID)) {
                System.out.println("Машин энэ зогсоолд байна" + slot.sid);
                System.out.println("Машины зогсоол: " + slot.sid);
                System.out.println("Машины дугаар: " + slot.car.cid);
                System.out.println("Эзэн: " + slot.car.owner);
                System.out.print("Tөрөл: ");
                if (slot.car.isStaff) {
                    System.out.println("Ажилчин");
                } else {
                    System.out.println("Зочин");
                }

                return; 
            }
        }
        System.out.println( carID + "Ийм дугаартай машин олдсонгүй .");
    }
    public void addslot(String slotID){
        Slot slot = new Slot(slotID);
        slot.sid = slotID;
        this.add(this.size(),slot);

        System.out.println(slotID + "Энэ зогсоол нэмэгдлээ.");
  
    }
    public void removeSlot(String slotID) {
        int i = 0;
        while (i < this.size()) {
            Slot slot = (Slot) this.get(i);
            if (slot.sid.equals(slotID)) {
                if (slot.car == null) {
                    this.remove(i);
                    System.out.println(slotID + "Энэ зогсоол хасагдлаа.");
                } else {
                    System.out.println(slotID + "Энэ зогсоолыг хасж болохгүй учир нь машин зогсож байна.");
                }
                return;
            }
            i++;
        }
        System.out.println( slotID + " Энэ зогсоол олдсонгүй");
    }


    public void removeCar(String carID) {
    	 if (!checkCardID(carID)) {
    	        System.out.println("Машины дугаар шаардлага хангахгүй байна");
    	        return;
    	    }
        for (int i = 0; i < this.size(); i++) {
            Slot slot = (Slot)this.get(i);
            if (slot.car != null && slot.car.cid.equals(carID)) {
                slot.car = null;
                System.out.println( carID + " Энэхүү машин " + slot.sid +"энэ зогсоолоос гарлаа");
                return;
            }
        }
        System.out.println(carID + " Энэ машин олдсонгүй.");
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
        this.isStaff = isStaff; // hello 14:28
    }
}
