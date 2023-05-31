package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import encapsulation_of_methods.*;


public class Pets_Repository implements Repository<Pets> {

    private CreatePets createPets;
    private Statement sqlSt;
    private ResultSet resultSet;
    private String SQLstr;
    
    public Pets_Repository() {
        this.createPets = new Pets_Create();
    };

    @Override
    public List<Pets> getAll() {
        List<Pets> farm = new ArrayList<Pets>();
        Pets pets;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()) {
                sqlSt = dbConnection.createStatement();
                SQLstr = "SELECT GenusId, Id, PetName, Birthday FROM pet_list ORDER BY Id";
                resultSet = sqlSt.executeQuery(SQLstr);
                while (resultSet.next()) {

                    Pets_genus genus = Pets_genus.getType(resultSet.getInt(1));
                    int id = resultSet.getInt(2);
                    String name = resultSet.getString(3);
                    LocalDate birthday = resultSet.getDate(4).toLocalDate();
                    
                    pets = createPets.createPet(genus, name, birthday);
                    pets.setIdPets(id);
                    farm.add(pets);
                }
                return farm;
            } 
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(Pets_Repository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }           
    }

    @Override
    public Pets getById(int petId) {
        Pets pets = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()) {

                SQLstr = "SELECT GenusId, Id, PetName, Birthday FROM pet_list WHERE Id = ?";
                PreparedStatement prepSt = dbConnection.prepareStatement(SQLstr);
                prepSt.setInt(1, petId);
                resultSet = prepSt.executeQuery();

                if (resultSet.next()) {

                    Pets_genus genus = Pets_genus.getType(resultSet.getInt(1));
                    int id = resultSet.getInt(2);
                    String name = resultSet.getString(3);
                    LocalDate birthday = resultSet.getDate(4).toLocalDate();

                    pets = createPets.createPet(genus, name, birthday);
                    pets.setIdPets(id);
                } 
                return pets;
            } 
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(Pets_Repository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public int create(Pets pets) {
        int rows;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()) {

                SQLstr = "INSERT INTO pet_list (PetName, Birthday, GenusId) SELECT ?, ?, (SELECT Id FROM pet_types WHERE Genus_name = ?)";
                PreparedStatement prepSt = dbConnection.prepareStatement(SQLstr);
                prepSt.setString(1, pets.getName());
                prepSt.setDate(2, Date.valueOf(pets.getBirthdayLocalDate())); 
                prepSt.setString(3, pets.getClass().getSimpleName());

                rows = prepSt.executeUpdate();
                return rows;
            }
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(Pets_Repository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        } 
    }

    public void train (int id, String command){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()) {
                String SQLstr = "INSERT INTO pet_command (PetId, CommandId) SELECT ?, (SELECT Id FROM commands WHERE Command_name = ?)";
                PreparedStatement prepSt = dbConnection.prepareStatement(SQLstr);
                prepSt.setInt(1, id);
                prepSt.setString(2, command);

                prepSt.executeUpdate();
            }
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(Pets_Repository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        } 
    }

    public List<String> getCommandsById (int petId, int commands_type){   

        List <String> commands = new ArrayList <>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()) {
                if (commands_type == 1){
                    SQLstr = "SELECT Command_name FROM pet_command pc JOIN commands c ON pc.CommandId = c.Id WHERE pc.PetId = ?";
                } else {
                    SQLstr = "SELECT Command_name FROM commands c JOIN Genus_command gc ON c.Id = gc.CommandId WHERE gc.GenusId = (SELECT GenusId FROM pet_list WHERE Id = ?)";
                }
                PreparedStatement prepSt = dbConnection.prepareStatement(SQLstr);
                prepSt.setInt(1, petId);
                resultSet = prepSt.executeQuery();
                while (resultSet.next()) {
                    commands.add(resultSet.getString(1));
                }
                return commands;
            }
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(Pets_Repository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        } 
    }

    @Override
    public int update(Pets pets) {
        int rows;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()) {
                SQLstr = "UPDATE pet_list SET PetName = ?, Birthday = ? WHERE Id = ?";
                PreparedStatement prepSt = dbConnection.prepareStatement(SQLstr);

                prepSt.setString(1, pets.getName());
                prepSt.setDate(2, Date.valueOf(pets.getBirthdayLocalDate())); 
                prepSt.setInt(3,pets.getIdPets());
                
                rows = prepSt.executeUpdate();
                return rows;
            }
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(Pets_Repository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        } 
    }

    @Override
    public void delete (int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()) {
                SQLstr = "DELETE FROM pet_list WHERE Id = ?";
                PreparedStatement prepSt = dbConnection.prepareStatement(SQLstr);
                prepSt.setInt(1,id);
                prepSt.executeUpdate();
            }
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(Pets_Repository.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        } 
    }

    public static Connection getConnection() throws SQLException, IOException {

        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("src/Resources/database.properties")) {

            props.load(fis);
            String url = props.getProperty("url");
            String username = props.getProperty("username");
            String password = props.getProperty("password");

            return DriverManager.getConnection(url, username, password);
        }
    }
}