package inf1010.assignment;

import java.util.Iterator;

public abstract class CreatureClass implements Creature {
    
    protected SinglyLinkedList<Creature> friends;    
    protected String name, dateOfBirth, phone, location, homePlanet;
 
    //gets
    public String getName() {
        return this.name;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getLocation() {
        return this.location;
    }

    public String getHomePlanet() {
        return this.homePlanet;
    }

    //sets
    public void setDateOfBirth(String date) {
        this.dateOfBirth = date;
    }

    public void setPhone(String number) {
        this.phone = number;
    }

    public void setLocation(String loc) {
        this.location = loc;
    }

    // supporting methods to reach scope
    
    public void printFriends() {
        if(this.friends == null) return;

        Iterator<Creature> it = this.iterator();

        while(it.hasNext()) {
            String n = it.next().getName();
            System.out.println("\t- " + n);
        }
    }

    public Iterator<Creature> iterator() {
        return this.friends.iterator();
    }

    public boolean registerFriend(Creature c) {
        return this.friends.add(c);
    }

    public boolean unregisterFriend(Creature c) {
        return this.friends.remove(c);
    }

    public int compareTo(Creature c) {
        return this.name.compareTo(c.getName());
    }

    public boolean hasFriend(Creature c) {
        return this.friends.contains(c);
    }
} 
