////////////////////////////////////////////////////////////////////
// Giosue' Calgaro 1201244
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;
import java.util.List;
import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
public class Bill implements TakeAwayBill {
public double getOrderPrice(List<MenuItem> itemsOrdered, User user) throws TakeAwayBillException {
double res = 0;
for(MenuItem item : itemsOrdered) {
res += item.getPrice();
}
return res;
}
}