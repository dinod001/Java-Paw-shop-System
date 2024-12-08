/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductData;

import ProductData.Supplies;
import java.util.ArrayList;

/**
 *
 * @author DSYS
 */

//// Interface to define the operations for managing supplies in the system
public interface Isupply {
    public abstract boolean  AddSupplies(Supplies supplyDetails);
    public abstract ArrayList<Supplies> ViewSupplies();
    public abstract boolean  DeleteSupplies(int supplyID);
    public abstract ArrayList<Supplies> SearchSupplies(String Category);
    public abstract boolean  UpdateSupplies(Supplies supply);
    
}
