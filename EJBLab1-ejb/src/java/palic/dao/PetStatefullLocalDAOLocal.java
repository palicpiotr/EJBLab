/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palic.dao;

import javax.ejb.Local;
import palic.model.Pet;

/**
 *
 * @author Piotr
 */
@Local
public interface PetStatefullLocalDAOLocal {
    
    public int addPet(Pet pet);
}
