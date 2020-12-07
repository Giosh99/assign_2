////////////////////////////////////////////////////////////////////
// Giosue' Calgaro 1201244
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;
import java.util.List;
import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.ElementsType;
import it.unipd.tos.model.MenuItem;
public class Bill implements TakeAwayBill {

    @Override
    public double getOrderPrice(List<MenuItem> itemsOrdered, User user) throws TakeAwayBillException {
        double ris = 0;
        for (MenuItem menuItem : itemsOrdered) {
            ris += menuItem.getPrice();
        }
        if (itemsOrdered.size() > 5) {
            boolean first = true;
            double lessExpensive = 0;
            for (MenuItem menuItem : itemsOrdered) {
                if (first) {
                    lessExpensive = menuItem.getPrice();
                    first = false;
                }
                if (menuItem.getPrice() < lessExpensive) {
                    lessExpensive = menuItem.getPrice();
                }
            }
            ris -= lessExpensive / 2;
        }
        return ris;
    }
}