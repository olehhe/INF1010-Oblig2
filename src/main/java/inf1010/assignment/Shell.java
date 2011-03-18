package inf1010.assignment;

import java.util.Iterator;
import inf1010.lib.two.ShellBase;

public class Shell extends ShellBase {
    BinarySearchTree<Creature> mainStruct;

    Shell() {
        mainStruct = new BinarySearchTree<Creature>();
        createTestCreatures();
        inputLoop();
    } // constructor::Shell

    public static void main(String[] args) throws Exception {
        new Shell();    
    } // method::main

    protected void addAlien(String name,        String birthDate, 
                            String phoneNumber, String location) {
        EvilAlien a = new EvilAlien(name, birthDate, phoneNumber, location);
        if(mainStruct.add(a)) {
            System.out.println("shell> " + name + " was successfully added.");
        }
    } // method::addAlien
        
    protected void addHuman(String name,        String birthDate, 
                            String phoneNumber, String location) {
        Human h = new Human(name, birthDate, phoneNumber, location);
        if(mainStruct.add(h)) {
            System.out.println("shell> " + name + " was successfully added.");
        }
    } // method::addHuman
        
    protected void registerFriend(String name, String friendname) {
        Creature cA = null;
        Creature fA = null;

        for(Creature c : mainStruct) {
            if(name.equals(c.getName())) {
                cA = c;
            }
        }
        for(Creature c : mainStruct) {
            if(friendname.equals(c.getName())) {
                fA = c;
            }
        }

        if(cA != null && fA != null) {
            if(cA.registerFriend(fA)) {
                System.out.println("shell> " + cA.getName() + " is now friends "
                                + "with " + fA.getName() + ".");
            }
        }
    } // method::registerFriend
        
    protected void unregisterFriend(String name, String friendname) {
        Creature cA = null;
        Creature fA = null;

        for(Creature c : mainStruct) {
            if(name.equals(c.getName())) {
                cA = c;
            }
        }
        for(Creature c : mainStruct) {
            if(friendname.equals(c.getName())) {
                fA = c;
            }
        }

        if(cA != null && fA != null) {
            if(cA.unregisterFriend(fA)) {
                System.out.println("shell> " + cA.getName() + " is no longer "
                              + "friends with " + fA.getName() + ".");
            }
        }        
    } // method::unregisterFriend
        
    protected void list() {
        Iterator<Creature> it = mainStruct.iterator();
        System.out.printf("%n-------------------------%n%15s%n-----------------"
                        + "--------%n", "List all");

        while(it.hasNext()) {
            Creature c = it.next();
            System.out.println("  - " + c.getName());
        } 
    } // method::list
        
    protected void show(String name) {
        for(Creature c : mainStruct) {
            if(name.equals(c.getName())) {
                System.out.printf("-------------------------%n"
                      + "%-20s" + c.getName() + "%n%-20s" + c.getHomePlanet()
                      + "%n%-20s" + c.getDateOfBirth() + "%n%-20s" + c.getPhone()
                      + "%n%-20s" + c.getLocation() + "%n%-20s%n", "Name:", 
                        "Home planet:", "Date of Birth:", "Phone number:", 
                        "Location:", "Friends:");
               
                c.printFriends();
                System.out.println("-------------------------");
            }
        }
    } // method::show
        
    protected void update(String name,        String birthDate,
                          String phoneNumber, String location) {
        for(Creature c : mainStruct) {
            if(name.equals(c.getName())) {
                c.setDateOfBirth(birthDate);
                c.setPhone(phoneNumber);
                c.setLocation(location);
                System.out.println("shell> Creature successfully updated.");
                return;
            }
        }
    } // method::update
} // class::Shell
