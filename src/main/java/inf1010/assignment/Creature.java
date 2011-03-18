package inf1010.assignment;

import java.util.Iterator;

public interface Creature extends Comparable<Creature> {

    // get-prototypes
    String getName();
    String getDateOfBirth(); 
    String getPhone();
    String getLocation();
    String getHomePlanet();

    // set-prototypes
    void setDateOfBirth(String date);
    void setPhone(String number); 
    void setLocation(String loc); 

    // support methods to reach scope
    boolean registerFriend(Creature c);
    boolean unregisterFriend(Creature c);
    boolean hasFriend(Creature c);
    void printFriends();
    Iterator<Creature> iterator();
}
