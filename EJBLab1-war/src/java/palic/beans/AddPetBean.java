/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palic.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;
import palic.dao.PetStatefullLocalDAOLocal;
import palic.model.Pet;

/**
 *
 * @author Piotr
 */
//@ConversationScoped //dialog context
@SessionScoped
@Named
public class AddPetBean implements Serializable {

    @EJB
    private PetStatefullLocalDAOLocal petStatefullLocalDAOLocal;

  
     @PostConstruct
    private void initBean() {
        count = 0;
        pet = new Pet();
    }
    private int count;
    private Pet pet;
    private int id;
    private int localId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
   public String add() {
        
        //java.sql.Date newDate = new java.sql.Date(new Date().getTime());
        java.sql.Date newDate = new java.sql.Date(pet.getBirth().getTime());
        java.sql.Date newDate1 = new java.sql.Date(pet.getDeath().getTime());
        pet.setBirth(newDate);
        pet.setBirth(newDate1);
        this.count = petStatefullLocalDAOLocal.addPet(pet);
        return "/AllPets.xhtml";
    }

   public String toEdit(int id) throws Exception{
        localId = id;
        return "/EditPets.xhtml";
    }

    public String editPet() throws Exception {   
        this.petStatefullLocalDAOLocal.editPet(pet);
        return "/AllPets.xhtml";
    }
}
