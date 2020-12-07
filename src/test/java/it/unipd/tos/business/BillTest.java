////////////////////////////////////////////////////////////////////
// Giosue' Calgaro 1201244
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import it.unipd.tos.model.ElementsType;
import it.unipd.tos.model.MenuItem;
import java.util.List;
import java.util.ArrayList;
import it.unipd.tos.business.exception.*;
public class BillTest {
User user;
List<MenuItem> items;
Bill bill;
@Before
public void setUp() {
user = new User(1, "G", "C");
bill = new Bill();
items = new ArrayList<MenuItem>();
String[] itemsName = {"Pinguino","Coppa Nafta", "Ciocco", "Liquirizia", "Coca", "Sprite"};
ElementsType[] types = {ElementsType.Gelato, ElementsType.Gelato, ElementsType.Budino, ElementsType.Gelato, ElementsType.Bevanda, ElementsType.Bevanda };
double[] prices = {1.5D,1D, 2D, 1.5D, 2.5D, 3D};
for(int i = 0; i<6; i++) {
items.add(new MenuItem(types[i],itemsName[i], prices[i]));
}
}
@Test
public void testGetOrderPrice() {
double ris = 0;
try {
	ris  = bill.getOrderPrice(items, user);
} catch (TakeAwayBillException e) {
	e.printStackTrace();
} 
assertEquals(11.5D, ris, 0);
}
}