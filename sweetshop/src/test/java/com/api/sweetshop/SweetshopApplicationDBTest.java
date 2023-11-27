package com.api.sweetshop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SweetshopApplicationDBTest {
    private static final String[] SWEETS =
            {"Chocolate truffles", "Fondant candies", "Homemade chocolate",
                    "Dump cake", "Marquise", "Home made cookie", "Hazelnut cake"};

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        // Load the PostgreSQL JDBC driver
        Class.forName("org.postgresql.Driver");

        // Connect to the database
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/sweetshop",
                "postgres",
                "1234");
    }

    @Test
    public void testInsertSweets() throws Exception {
        Connection connection = getConnection();

        // Insert data into the database
        Statement statement = connection.createStatement();

        // Verify that the data was inserted
        ResultSet resultSet = statement.executeQuery("SELECT * FROM sweets WHERE name = 'Fondant candies'");
        assertEquals(true, resultSet.next());
        assertEquals("Fondant candies", resultSet.getString("name"));
        assertEquals("Rolled fondant is made of sugar, water, and corn syrup.", resultSet.getString("description"));
        assertEquals("10", resultSet.getString("price"));

        // Close the connection
        connection.close();
    }

    @Test
    public void testGetSweetsCount() throws Exception {
        Connection connection = getConnection();

        // Count the number of rows in the table
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM sweets");
        resultSet.next();
        int count = resultSet.getInt(1);
        assertEquals(7, count);

        // Close the connection
        connection.close();
    }

    @Test
    public void checkSweetsNames() throws Exception {
        Connection connection = getConnection();

        // Retrieve the names from the table
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT name FROM sweets");

        // Verify that the result set is not empty
        //assertTrue(resultSet.next());
        int i = 0;
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            // Verify that the name matches the expected name
            assertEquals(SWEETS[i], name);
            i++;
        }

        // Verify that the number of names in the ResultSet matches the expected number of names
        assertEquals(SWEETS.length, i);

        // Close the connection
        connection.close();
    }
}
