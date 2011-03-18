package inf1010.assignment;

public class Human extends CreatureClass {

    // constructor
    Human(String name, String dateOfBirth, String phone, String location) {
	this.name = name;
	this.dateOfBirth = dateOfBirth;
	this.phone = phone;
	this.location = location;
        this.homePlanet = "Earth";
        this.friends = new SinglyLinkedList<Creature>();
    } // constructor::Human::4 parameters

    Human(String name) {
        this.name = name;
        this.homePlanet = "Earth";
        this.friends = new SinglyLinkedList<Creature>();
    } // constructor::Human::1 parameter
}
