/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductData;

/**
 *
 * @author DSYS
 */
public class Supplies {
    private int SupplyID;
    private String SupplyName;
    private String Category;
    private int stock;
    private double price;

    public Supplies(int SupplyID,String SupplyName, String Category,double price ,int stock ) {
        this.SupplyID =  SupplyID;
        this.SupplyName=SupplyName;
        this.Category = Category;
        this.stock = stock;
        this.price = price;
    }

    public int getSupplyID() {
        return  SupplyID;
    }

    public void setSupplyID(int SupplyID) {
        this. SupplyID = SupplyID;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getSupplyName() {
        return SupplyName;
    }

    public void setSupplyName(String SupplyName) {
        this.SupplyName = SupplyName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
