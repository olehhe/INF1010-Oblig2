package inf1010.assignment;

import java.util.Iterator;
import org.testng.annotations.*;
import static org.testng.Assert.*;


@Test
public class HumanTest {

	///////////////////////////////////////////////////////////////////////////
	//
	// Nedenfor finner du 2 test-metoder som er halvferdige. Fjern kommentaren
	// rundt test-metodene, og følg instruksjonene i enden av hver av
	// test-metodene for å fullføre testene.
	//
	// I eksemplene nedenfor regner vi med at du har laget en Human-klasse med
	// en konstruktør som ser slik ut:
	//
	//     Human(String name)
	//
	// Altså må du endre eksemplene slik at de passer til din Human-klasse.
	//
	// Testene viser bruk av assertTrue() og assertEquals(). Hvis du ønsker å
	// benytte andre assert*-metoder, eller vil se dokumentasjonen, se:
	// http://testng.org/javadocs/org/testng/Assert.html
	//
	// Merk at det eneste som er nytt med denne koden er at det står
	// @Test over metodene. Det er bare for at testsystemet skal kunne skille
	// test-metoder fra vanlige metoder. Ellers er det bare helt vanlig
	// javakode. Hver av disse @Test metodene kjøres uavhengig av hverandre.
	//
	// Under metodene som allerede finnes, skal du lage 3 testmetoder som 
	// tester funksjonaliteten for å:
	//    - registrere venner
	//    - avregistrere venner
	//    - sjekke om et vesen har en gitt Creature som venn.
	// Navnene på disse metodene velger du selv. For at de skal 
	// kjøres må de annoteres med @Test slik som med compareTo og iterator.
	//
	///////////////////////////////////////////////////////////////////////////


	@Test (description = "test if compareTo works accordingly")
	public void compareTo() {
		Creature superman = new Human("Superman");
		Creature batman = new Human("Batman");
		Creature superman_wannabe = new Human("Superman");

		// Slik kan du teste om resultatet fra compareTo stemmer:
		assertTrue(superman.compareTo(superman_wannabe) == 0);

		// Lag tester for de andre to mulige utfallene av compareTo()
                assertFalse(superman.compareTo(superman_wannabe) < 0);
                assertFalse(superman.compareTo(superman_wannabe) > 0);
	}

	
	@Test (description = "test if iterator works accordingly")
	public void iterator() {
		Creature superman = new Human("Superman");
		Creature batman = new Human("Batman");
                Creature spiderman = new Human("Spiderman");
                Creature wonderwoman = new Human("Wonderwoman");

		// Her regner vi med at din metode for å registrere venner på et
		// vesen heter registerFriend(). Dette må du endre hvis du har
		// valgt et annet navn på metoden.
		superman.registerFriend(spiderman);
                superman.registerFriend(wonderwoman);
                superman.registerFriend(batman);

		// Tester at første element i iteratoren til superman er batman:
		Iterator<Creature> it = superman.iterator();
		assertEquals(it.next(), batman);
                assertEquals(it.next(), spiderman);
                assertEquals(it.next(), wonderwoman);

		// Utvid denne testen slik at superman har minst 3 venner,
		// og test at iteratoren inneholder alle vennene.
                
	}

        @Test (description = "test if friends are added accordingly")
        public void registerFriend() {
            assertTrue(true, "friend was successfully added");
            assertFalse(false, "friend already exists");
            Creature superman = new Human("Superman");
            Creature batman = new Human("Batman");
            Creature wonderwoman = new Human("Wonderwoman");

            assertTrue(superman.registerFriend(batman));
            assertFalse(superman.registerFriend(batman));
            assertTrue(batman.registerFriend(wonderwoman));
        }

        @Test (description = "test if friends are removed accordingly")
        public void unregisterFriend() {
            assertTrue(true, "friend was successfully removed");
            assertFalse(false, "friend does not exist");

            Creature superman = new Human("Superman");
            Creature batman = new Human("Batman");
            Creature wonderwoman = new Human("Wonderwoman");

            superman.registerFriend(batman);
            batman.registerFriend(wonderwoman);

            assertTrue(superman.unregisterFriend(batman));
            assertFalse(superman.unregisterFriend(batman));
            assertTrue(batman.unregisterFriend(wonderwoman));
        }

        @Test(description = "test if a given friend is registered") 
        public void contains() {
            assertTrue(true, "friend exists");
            assertFalse(false, "friend does not exist");

            Creature superman = new Human("Superman");
            Creature batman = new Human("Batman");
            Creature wonderwoman = new Human("Wonderwoman");

            if(superman.registerFriend(batman)) {
                if(batman.registerFriend(wonderwoman)) {
                    assertTrue(superman.hasFriend(batman));
                    assertTrue(batman.hasFriend(wonderwoman));

                    superman.unregisterFriend(batman);

                    assertFalse(superman.hasFriend(batman));
                }
            }
        }
}
