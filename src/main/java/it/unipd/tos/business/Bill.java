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
        int iceCreams = 0;
        double totalIceCreamPudding = 0;
        for (MenuItem menuItem : itemsOrdered) {
            ris += menuItem.getPrice();
            if (menuItem.getItemType() == ElementsType.Gelato) {
                iceCreams++;
            }
            if(menuItem.getItemType() == ElementsType.Gelato || menuItem.getItemType() == ElementsType.Budino){
                totalIceCreamPudding += menuItem.getPrice();
            }
        }
        if (iceCreams > 5) {
            double discount = getIceCreamsDiscount(itemsOrdered);
            ris -= discount;
            totalIceCreamPudding -= discount;
        }

        if(totalIceCreamPudding > 50){
            ris -= ris*0.1;
        }
        return ris;
    }

    private double getIceCreamsDiscount(List<MenuItem> itemsOrdered){
        boolean first = true;
            double lessExpensive = 0;
            for (MenuItem menuItem : itemsOrdered) {
                if (menuItem.getItemType() != ElementsType.Gelato) {
                    continue;
                }
                if (first) {
                    lessExpensive = menuItem.getPrice();
                    first = false;
                }
                if (menuItem.getPrice() < lessExpensive) {
                    lessExpensive = menuItem.getPrice();
                }
            }
            return lessExpensive / 2;
    }
}