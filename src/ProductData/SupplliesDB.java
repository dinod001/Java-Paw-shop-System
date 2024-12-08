/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductData;

import ProductData.Supplies;
import MainGUI.MainGUI;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author DSYS
 */
//// This class handles the data access for supplies, including adding, viewing, and deleting supplies from a file.
public class SupplliesDB implements Isupply {

    File file = new File("ProductDetails.txt");
    private DefaultTableModel TableModel = new DefaultTableModel();
    Supplies supplys;

    // Following method gets supply details from the main GUI insert function and stores them in the file
    @Override
    public boolean AddSupplies(Supplies SupplyDetails) {
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(SupplyDetails.getSupplyID()
                    + ":" + SupplyDetails.getSupplyName()
                    + ":" + SupplyDetails.getCategory()
                    + ":" + SupplyDetails.getPrice()
                    + ":" + SupplyDetails.getStock() + "\n");
            fileWriter.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(SupplliesDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // Following method fetches data from the text file and returns them as objects to display them within the table
    @Override
    public ArrayList<Supplies> ViewSupplies() {
        try {
            Scanner sc = new Scanner(file);
            ArrayList<Supplies> supplyList = new ArrayList<Supplies>();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String arr[] = line.split(":");
                int supplyID = Integer.valueOf(arr[0]);
                String supplyname = arr[1];
                String cateogory = arr[2];
                double price = Double.valueOf(arr[3]);
                String qty = arr[4];
                int stock = Integer.parseInt(qty.trim());
                //int stock = Integer.valueOf(arr[4]);
                supplys = new Supplies(supplyID, supplyname, cateogory, price, stock);
                supplyList.add(supplys);
            }
            return supplyList;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SupplliesDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Following method deletes data from the text file using supplyID received from the main GUI delete function
    @Override
    public boolean DeleteSupplies(int supplyID) {
        boolean SupplyFound = false;
        ArrayList<Supplies> supplylist = ViewSupplies();
        for (int i = 0; i < supplylist.size(); i++) {
            Supplies supply = supplylist.get(i);
            if (supply.getSupplyID() == supplyID) {
                supplylist.remove(i);  // Remove the product by index
                SupplyFound = true;
                break;
            }
        }
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (Supplies supply : supplylist) {
                fileWriter.write(supply.getSupplyID()
                        + ":" + supply.getSupplyName()
                        + ":" + supply.getCategory()
                        + ":" + supply.getPrice()
                        + ":" + supply.getStock() + "\n");
            }
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(SupplliesDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (SupplyFound) {
            return true;
        }
        return false;
    }

    // Following method searches for supplies in the text file based on the provided category and return them as a object to display
    @Override
    public ArrayList<Supplies> SearchSupplies(String Category) {
        ArrayList<Supplies> supplylist = ViewSupplies();
        ArrayList<Supplies> Searchedlist = new ArrayList<Supplies>();
        for (int i = 0; i < supplylist.size(); i++) {
            Supplies supply = supplylist.get(i);
            if (supply.getCategory().trim().equalsIgnoreCase(Category.trim())) {
                int supplyID = supply.getSupplyID();
                String supplyname = supply.getSupplyName();
                String category = supply.getCategory();
                double price = supply.getPrice();
                int stock = supply.getStock();
                supply = new Supplies(supplyID, supplyname, Category, price, stock);
                Searchedlist.add(supply);
            }

        }
        return Searchedlist;
    }
// Following method updates existing supply details. This method retrieves updated supply details from MainGUI update function
// and rewrites them in the text file.
 @Override
    public boolean UpdateSupplies(Supplies supply) {
        boolean supplyFound = false;
        ArrayList<Supplies> supplylist = ViewSupplies();
        for (int i = 0; i < supplylist.size(); i++) {
            Supplies supplys = supplylist.get(i);
            if (supplys.getSupplyID() == supply.getSupplyID()) {
                supplylist.set(i, supply);
                supplyFound = true;
                break;  // Exit the loop after deleting the product
            }
        }
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (Supplies supplys : supplylist) {
                fileWriter.write(supplys.getSupplyID()
                        + ":" + supplys.getSupplyName()
                        + ":" + supplys.getCategory()
                        + ":" + supplys.getPrice()
                        + ":" + supplys.getStock() + "\n");
            }
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(SupplliesDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (supplyFound) {
            return true;
        }
        return false;
    }
}
