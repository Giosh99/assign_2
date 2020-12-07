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
	
private List<MenuItem> itemsOrdered;
private User user = new User(1, "G", "C");
private String[] names = { "Pinguino", "Coppa Nafta", "Nocciolata", "Wafer", "Caramello", "Pistacchio", "Cola", "Sprite", "IceGold", "GodPudd"};
private double[] prices = { 7.3D, 5.0D, 5.6D, 2.6D, 8.0D, 6.4D, 3.0D, 2.0D, 20.0D, 34.0D};
private ElementsType[] types = { ElementsType.Gelato, ElementsType.Gelato, ElementsType.Budino, ElementsType.Gelato,
		ElementsType.Gelato, ElementsType.Gelato, ElementsType.Gelato, ElementsType.Bevanda, ElementsType.Gelato, ElementsType.Budino };
private Bill bill = new Bill();

@Test
public void testgetOrderPriceWithDiscount() {
    itemsOrdered = new ArrayList<MenuItem>();
    for (int i = 0; i < 8; i++)
        itemsOrdered.add(new MenuItem(types[i], names[i], prices[i]));
    double ris = 0;
    try {
        ris = bill.getOrderPrice(itemsOrdered, user);
    } catch (TakeAwayBillException e) {
        e.printStackTrace();
    }
    assertEquals(38.6D, ris, 0);
}

@Test
public void testgetOrderPriceWithNoDiscount() {
    itemsOrdered = new ArrayList<MenuItem>();
    for (int i = 0; i < 4; i++)
    itemsOrdered.add(new MenuItem(types[i], names[i], prices[i]));
    double ris = 0;
    try {
        ris = bill.getOrderPrice(itemsOrdered,user);
    } catch (TakeAwayBillException e) {
        e.printStackTrace();
    }
    assertEquals(20.5D, ris, 0);
}

@Test
public void testgetOrderPriceOver50(){
    itemsOrdered = new ArrayList<MenuItem>();
    for (int i = 0; i < 10; i++)
        itemsOrdered.add(new MenuItem(types[i], names[i], prices[i]));
    double ris = 0;
    try {
        ris = bill.getOrderPrice(itemsOrdered, user);
    } catch (TakeAwayBillException e) {
        e.printStackTrace();
    }
    assertEquals(83.34D, ris, 0);
}
@Test(expected = TakeAwayBillException.class)
public void moreThan30ElementsExceptionTest() throws TakeAwayBillException{
    itemsOrdered = new ArrayList<MenuItem>();
    for(int j = 0; j < 4; j++)
        for (int i = 0; i < 10; i++)
            itemsOrdered.add(new MenuItem(types[i], names[i], prices[i]));
    
    @SuppressWarnings("unused")
    double ris = bill.getOrderPrice(itemsOrdered, user);
}

@Test
public void commissioneOrderPriceInferiore10Test(){
    itemsOrdered = new ArrayList<MenuItem>();
    itemsOrdered.add(new MenuItem(types[0], names[0], prices[0]));
    double ris = 0;
    try {
        ris = bill.getOrderPrice(itemsOrdered, user);
    } catch (Exception e) {
        e.printStackTrace();
    }
    assertEquals(7.8, ris, 0);
}
}