////////////////////////////////////////////////////////////////////
// Giosue' Calgaro 1201244
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
public class MenuItemTest {
private MenuItem item;
@Before
public void setup(){
item = new MenuItem(ElementsType.Gelato, "Nafta", 3.0D);
}
@Test
public void test(){
item.setItemType(ElementsType.Gelato);
item.setName("Cola");
item.setPrice(2.0D);
assertEquals(ElementsType.Gelato, item.getItemType());
assertEquals("Cola", item.getName());
assertEquals(2.0D, item.getPrice(), 0);
}
}