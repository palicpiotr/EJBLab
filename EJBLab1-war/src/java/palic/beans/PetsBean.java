/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palic.beans;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import palic.dao.PetsDAOLocal;
import palic.model.Pet;

/**
 *
 * @author Piotr
 */
@RequestScoped
@Named
public class PetsBean implements Serializable {

    @EJB
    private PetsDAOLocal petDAOLocal;
  
    public List<Pet> getAllPets() {
        return this.petDAOLocal.getPets();
    }
}
