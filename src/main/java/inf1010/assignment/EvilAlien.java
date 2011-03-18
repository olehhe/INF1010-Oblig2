package inf1010.assignment;

public class EvilAlien extends CreatureClass {

    // constructor
    EvilAlien(String name, String dateOfBirth, String phone, String location) {
	this.name = name;
	this.dateOfBirth = dateOfBirth;
	this.phone = phone;
	this.location = location;
        this.homePlanet = "Ruritania";
        this.friends = new SinglyLinkedList<Creature>();
    } // constructor::EvilAlien::4 parameters

    EvilAlien(String name) {
        this.name = name;
        this.homePlanet = "Earth";
        this.friends = new SinglyLinkedList<Creature>();
    } // constructor::EvilAlien::1 parameter
}
