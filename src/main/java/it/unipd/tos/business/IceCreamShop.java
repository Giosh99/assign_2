////////////////////////////////////////////////////////////////////
// Giosue' Calgaro 1201244
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.ElementsType;
import it.unipd.tos.model.MenuItem;

public class IceCreamShop implements TakeAwayBill {
    private int giftCount;
    private LocalDate lastGiftDate;
    private boolean debug;

    public IceCreamShop() {
        giftCount = 0;
        lastGiftDate = LocalDate.now();
        debug = false;
    }

    @Override
    public double getOrderPrice(List<MenuItem> itemsOrdered, User user) throws TakeAwayBillException {
        if (itemsOrdered.size() > 30) {
            throw new TakeAwayBillException("Troppi elementi nell'ordinazione");
        }
        if (isGift(user)) {
            return 0;
        }
        double ris = 0;
        int iceCreams = 0;
        double totalIceCreamPudding = 0;
        for (MenuItem menuItem : itemsOrdered) {
            ris += menuItem.getPrice();
            if (menuItem.getItemType() == ElementsType.Gelato) {
                iceCreams++;
            }
            if (menuItem.getItemType() == ElementsType.Gelato || menuItem.getItemType() == ElementsType.Budino) {
                totalIceCreamPudding += menuItem.getPrice();
            }
        }
        if (iceCreams > 5) {
            double discount = getIceCreamsDiscount(itemsOrdered);
            ris -= discount;
            totalIceCreamPudding -= discount;
        }
        if (totalIceCreamPudding > 50) {
            ris -= ris * 0.1;
        }
        if (ris < 10) {
            ris += 0.5;
        }
        return ris;
    }

    private boolean isGift(User user) {
        boolean ris = false;
        LocalTime start = LocalTime.of(18, 0, 0);
        LocalTime end = LocalTime.of(19, 0, 0);
        LocalTime now;
        if (debug) {
            now = LocalTime.of(18,15,0);
        } else {
            now = LocalTime.now();
        }
        if (now.isAfter(start) && now.isBefore(end) && user.getAge() < 18) {
            if (LocalDate.now() != lastGiftDate) {
                lastGiftDate = LocalDate.now();
                giftCount = 0;
            }
            if (giftCount < 10 && new Random().nextBoolean()) {
                ris = true;
            }
        }
        return ris;
    }

    public void activateDebug() {
        debug = true;
    }
    public void deactivateDebug(){
        debug = false;
    }

    private static double getIceCreamsDiscount(List<MenuItem> itemsOrdered) {
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