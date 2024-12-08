/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserData;

import java.util.ArrayList;
import UserData.Login;

/**
 *
 * @author DSYS
 */

// Interface to define the operations for managing cashier accounts in the system
public interface Ilogin {
    public abstract boolean add(Login log);
    public abstract boolean delete(int UserID);
    public abstract boolean update(Login log);
    public abstract ArrayList<Login> Search(int UserID);
    public abstract ArrayList<Login> getAll();
    
}
