/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palic.dao;

import javax.ejb.Singleton;
import javax.ejb.ConcurrencyManagement;
import static javax.ejb.ConcurrencyManagementType.CONTAINER;
import javax.ejb.Lock;
import static javax.ejb.LockType.WRITE;



/**
 *
 * @author Piotr
 */
@Singleton 
public class CounterLocal implements CounterLocalLocal {

    private int hits = 1;
   
    
    @Override
    public int getHits(){
        return hits++;
    }
}
