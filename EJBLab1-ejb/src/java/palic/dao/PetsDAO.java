/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import palic.model.Pet;

/**
 *
 * @author Piotr
 */
@Stateless
@Local(PetsDAOLocal.class)
public class PetsDAO implements PetsDAOLocal {

    @Resource(lookup = "jdbc/myZoo")
    private DataSource dataSource;

    @Override
    public List<Pet> getPets() {
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM pet";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet res = stmt.executeQuery();
            List<Pet> pets = new ArrayList<>();
            while (res.next()) {
                Pet pet = new Pet();
                pet.setId(res.getInt(1));
                pet.setName(res.getString(2));
                pet.setOwner(res.getString(3));
                pet.setSpecies(res.getString(4));
                pet.setSex(res.getString(5));
                pet.setBirth(res.getDate(6));
                pet.setDeath(res.getDate(7));
                pets.add(pet);
            }
            return pets;
        } catch (Exception ex) {
            throw new RuntimeException("An error has occured in lisPets methos", ex);
        }

    }

}
