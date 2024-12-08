/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserData;

import ProductData.SupplliesDB;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DSYS
 */
public class LoginDB implements Ilogin {

    File file = new File("UserDetails.txt");
    private DefaultTableModel TableModel = new DefaultTableModel();
    Login logins;

    // Following method gets new user account details from the Managecashiers GUI and writes them into the file.
    @Override
    public boolean add(Login log) {
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(log.getUserID()
                    + ":" + log.getUsername()
                    + ":" + log.getPassword() + "\n");
            fileWriter.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // Following method deletes a cashier account from the file using the User ID.
    @Override
    public boolean delete(int UserID) {
        boolean productFound = false;
        ArrayList<Login> Loginlist = getAll();
        for (int i = 0; i < Loginlist.size(); i++) {
            Login logins = Loginlist.get(i);
            if (logins.getUserID() == UserID) {
                Loginlist.remove(i);  // Remove the product by index
                productFound = true;
                break;
            }
        }
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (Login logdetails : Loginlist) {
                fileWriter.write(logdetails.getUserID()
                        + ":" + logdetails.getUsername()
                        + ":" + logdetails.getPassword() + "\n");
            }
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (productFound) {
            return true;
        }
        return false;
    }

    // Following method gets details from the text file and passes them as objects to Managecashiers GUI view function to display in the table
    @Override
    public ArrayList<Login> getAll() {
        try {
            Scanner sc = new Scanner(file);
            ArrayList<Login> LogintList = new ArrayList<Login>();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String arr[] = line.split(":");
                int UserID = Integer.valueOf(arr[0]);
                String Username = arr[1];
                String Passowrd = arr[2];
                logins = new Login(UserID, Username, Passowrd);
                LogintList.add(logins);
            }
            return LogintList;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Following method gets updated details from the Manager GUI update function and rewrites them in the file.
    @Override
    public boolean update(Login log) {
        boolean IDFound = false;
        ArrayList<Login> loginlist = getAll();
        for (int i = 0; i < loginlist.size(); i++) {
            Login logins = loginlist.get(i);
            if (logins.getUserID() == log.getUserID()) {
                loginlist.set(i, log);
                IDFound = true;
                break;  // Exit the loop after deleting the product
            }
        }
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (Login logdetails : loginlist) {
                fileWriter.write(logdetails.getUserID()
                        + ":" + logdetails.getUsername()
                        + ":" + logdetails.getPassword() + "\n");
            }
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(SupplliesDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (IDFound) {
            return true;
        }
        return false;
    }

    /* Following method searches for a cashier account based on the user ID, pass them particluar details to Managecashiers GUI
     seacrh function to displa them*/
    @Override
    public ArrayList<Login> Search(int UserID) {
        ArrayList<Login> loginlist = getAll();
        ArrayList<Login> Searchedlist = new ArrayList<Login>();
        for (int i = 0; i < loginlist.size(); i++) {
            Login logins = loginlist.get(i);
            if (logins.getUserID() == UserID) {
                int UserId = Integer.valueOf(logins.getUserID());
                String Username = logins.getUsername();
                String password = logins.getPassword();
                logins = new Login(UserId, Username, password);
                Searchedlist.add(logins);
            }

        }
        return Searchedlist;
    }

}
