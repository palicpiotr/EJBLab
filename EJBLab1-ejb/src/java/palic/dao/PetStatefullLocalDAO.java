/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palic.dao;

import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import palic.model.Pet;

/**
 *
 * @author Piotr
 */
@Stateful
//@Local(PetStatefullLocalDAOLocal.class)
@ConversationScoped
public class PetStatefullLocalDAO implements PetStatefullLocalDAOLocal {

    private int count;
    @Inject
    Conversation conv;
    @Resource(lookup = "jdbc/myZoo")
    private DataSource dataSource;

    @PostConstruct
    public void initStart() {//
        count = 0;
    }
    
    public void endConv(){
        count = 0;
        if (!conv.isTransient()) {
            conv.end();
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

   

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
            stmt.setTimestamp(5, new Timestamp(pet.getBirth().getTime()));
            stmt.setTimestamp(6, new Timestamp(pet.getDeath().getTime()));
            stmt.execute();
            if (conv.isTransient()) {
                conv.begin();
            }
            count++;
            return count;
            //ResultSet rs = stmt.getGeneratedKeys();
            /* if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new RuntimeException("An error has occured in adding data to database");
            }*/
        } catch (Exception ex) {
            throw new RuntimeException("An error has occured in lisPets methos", ex);
        }
    }
    
    @Override
    public int editPet(Pet pet) throws Exception{
       try (Connection connection = dataSource.getConnection()) {
            String query = "UPDATE  pet "
                    + "set name=?, owner=?, species=?, sex=?, birth=?, death=? where pet.id=? ";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, pet.getName());
            stmt.setString(2, pet.getSpecies());
            stmt.setString(3, pet.getOwner());
            stmt.setString(4, pet.getSex());
            stmt.setTimestamp(5, new Timestamp(pet.getBirth().getTime()));
            stmt.setTimestamp(6, new Timestamp(pet.getDeath().getTime()));
            stmt.setInt(7, pet.getId());
            stmt.execute();
            endConv();           
            return count;
            //ResultSet rs = stmt.getGeneratedKeys();
            /* if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new RuntimeException("An error has occured in adding data to database");
            }*/
        } catch (Exception ex) {
            throw new Exception(ex);
        } 
    }

}
