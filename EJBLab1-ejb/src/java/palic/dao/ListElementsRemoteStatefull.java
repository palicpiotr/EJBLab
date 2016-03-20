/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palic.dao;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Piotr
 */
//remote interface
@Remote
public interface ListElementsRemoteStatefull {
    
    public void addElements(int element);
    public void removeElement(int element);
    
    public List <Integer> getAllElements();
}
