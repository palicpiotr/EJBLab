/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.sql.DataSource;
import palic.model.Pet;

/**
 *
 * @author Piotr
 */
@Stateful
@Local(PetStatefullLocalDAOLocal.class)
public class PetStatefullLocalDAO implements PetStatefullLocalDAOLocal {

    @Resource(lookup = "jdbc/myZoo")
    private DataSource dataSource;

    @Override
    public int addPet(Pet pet) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "INSERT INTO PET "
                    + "(name, owner, species, sex, birth, death)"
                    + "VALUES(?,?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, pet.getName());
            stmt.setString(2, pet.getSpecies());
            stmt.setString(3, pet.getOwner());
            stmt.setString(4, pet.getSex());
            stmt.setDate(5, pet.getBirth());
            stmt.setDate(6, pet.getDeath());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new RuntimeException("An error has occured in adding data to database");
            }
        } catch (Exception ex) {
            throw new RuntimeException("An error has occured in lisPets methos", ex);
        }
    }
}
