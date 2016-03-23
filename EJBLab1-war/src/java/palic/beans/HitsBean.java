/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palic.beans;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import palic.dao.CounterLocalLocal;

/**
 *
 * @author Piotr
 */
@SessionScoped
public class HitsBean implements Serializable{
  
    @EJB
    private CounterLocalLocal counter;
    
    private int hitCount;
    
    public HitsBean(){
        this.hitCount = 0;
    }

    public int getHitCount() {
        hitCount= counter.getHits();
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }
    
    
}
