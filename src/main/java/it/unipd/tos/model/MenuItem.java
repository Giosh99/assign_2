////////////////////////////////////////////////////////////////////
// Giosue' Calgaro 1201244
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;
public class MenuItem {
private ElementsType itemType;
private String name;
private double price;
public MenuItem(ElementsType itemType,String name,double price ) {
this.itemType = itemType;
this.name = name;
this.price = price;
}
public ElementsType getItemType() {
return itemType;
}
public void setItemType(ElementsType itemType) {
this.itemType = itemType;
}
public String getName() {
return name;
}
public void setName(String name) {
this.name = name;
}
public double getPrice() {
return price;
}
public void setPrice(double price) {
this.price = price;
}
}