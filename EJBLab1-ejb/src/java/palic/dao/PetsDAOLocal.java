/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palic.dao;

import javax.ejb.Local;

/**
 *
 * @author Piotr
 */
@Local
public interface PetsDAOLocal {

    void businessMethod(int id);
    
}
