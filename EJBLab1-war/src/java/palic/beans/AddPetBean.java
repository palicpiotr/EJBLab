/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palic.beans;

import java.io.Serializable;
import java.sql.Date;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;

import javax.inject.Named;
import palic.dao.PetStatefullLocalDAOLocal;
import palic.model.Pet;

/**
 *
 * @author Piotr
 */
@ConversationScoped //dialog context
@Named
public class AddPetBean implements Serializable {

    private PetStatefullLocalDAOLocal petStatefullLocalDAOLocal;

    public AddPetBean(){
        
    }
    @Inject
    public AddPetBean(PetStatefullLocalDAOLocal petStatefullLocalDAOLocal){
        
    }
    private int id;
    private String name;
    private String owner;
    private String species;
    private String sex;
    private Date birth;
    private Date death;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getDeath() {
        return death;
    }

    public void setDeath(Date death) {
        this.death = death;
    }

    public AddPetBean(String name, String owner, String species, String sex, Date birth, Date death) {
        this.name = name;
        this.owner = owner;
        this.species = species;
        this.sex = sex;
        this.birth = birth;
        this.death = death;
    }
    


    public String AddPet() {
        Pet pet = new Pet();
        pet.setName(name);
        pet.setOwner(owner);
        pet.setSpecies(species);
        pet.setSex(sex);
        pet.setBirth(birth);
        pet.setDeath(death);
        petStatefullLocalDAOLocal.addPet(pet);
        return "/AllPets.xhtml";
    }

}
