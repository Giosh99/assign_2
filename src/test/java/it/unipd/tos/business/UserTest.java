////////////////////////////////////////////////////////////////////
// Giosue' Calgaro 1201244
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.Test;
public class UserTest {
User user;
@Before
public void setup() {
user = new User(1, "Lola", "Carpa");
}
@Test
public void test() {
user.setId(2);
user.setName("Lol");
user.setSurname("surname");
assertEquals(2, user.getId());
assertEquals("Lol", user.getName());
assertEquals("surname", user.getSurname());
}
}