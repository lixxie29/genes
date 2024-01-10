package repository;

import domain.Gene;
import org.sqlite.SQLiteDataSource;
import java.sql.*;
import java.util.ArrayList;

public class Repository {
    private static final String JDBC_URL = "jdbc:sqlite:data/test_db.db";

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null)
            openConnection();
        return conn;
    }

    private static void openConnection()
    {
        try
        {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection()
    {
        try
        {
            conn.close();
            conn = null;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Gene> getAll(){

        ArrayList<Gene> genes= new ArrayList<>();;
        try {
            openConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM genesdatabase");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                Gene w = new Gene(
                        rs.getString("name"),
                        rs.getString("organism"),
                        rs.getString("function"),
                        rs.getString("sequence")
                );
                genes.add(w);
            }
            rs.close();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return genes;
    }

    public void UpdateSchema(String newFunction,String newSequence, String name){
        try {
            openConnection();
            PreparedStatement statement = conn.prepareStatement("UPDATE genesdatabase  set function=?,sequence=? WHERE name=?");

            statement.setString(1, newFunction);
            statement.setString(2, newSequence);
            statement.setString(3,name);

            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
