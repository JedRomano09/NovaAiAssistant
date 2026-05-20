package com.mycompany.nova;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class dbManager {
    
    private static final String URL = "jdbc:mysql://localhost:3306/library_db";
    private static final String USER = "root";
    private static final String PASS = "";
    private static Connection conn;

    public static Connection connect() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(URL, USER, PASS);
        }
        return conn;
    }

    
    
    public static void createUser(String firstname, String lastname, String emailaddress, String username, String password, String usertype) {
        String sql = "INSERT INTO users(firstname, lastname, emailaddress, username, password, usertype) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            connect();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, firstname);
                ps.setString(2, lastname);
                ps.setString(3, emailaddress);
                ps.setString(4, username);
                ps.setString(5, password);
                ps.setString(6, usertype);
                
                int rows = ps.executeUpdate();
                System.out.println( rows + " user added successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Create user error: " + e.getMessage());
        }
    }

    public static String getUser(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try {
            connect();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("id") 
                               + ", " + rs.getString("firstname") + " " + rs.getString("lastname") 
                               + ", " + rs.getString("username") 
                               + ", " + rs.getString("usertype");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Get user error: " + e.getMessage());
        }
        return "User not found";
    }

    public static List<String> getAllUsers() {
        List<String> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try {
            connect();
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    users.add(rs.getInt("id") 
                              + ", " + rs.getString("firstname") + " " + rs.getString("lastname") 
                              + ", " + rs.getString("username") 
                              + ", " + rs.getString("usertype"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Read users error: " + e.getMessage());
        }
        return users;
    }

    public static void updateUser(int id, String firstname, String lastname, String emailaddress, String username, String usertype) {
        String selectSql = "SELECT * FROM users WHERE id = ?";
        String updateSql = "UPDATE users SET firstname = ?, lastname = ?, emailaddress = ?, username = ?, usertype = ? WHERE id = ?";
        
        try {
            connect();
            String currentFirst = "", currentLast = "", currentEmail = "", currentUser = "", currentType = "";
            boolean userExists = false;

            try (PreparedStatement psSelect = conn.prepareStatement(selectSql)) {
                psSelect.setInt(1, id);
                try (ResultSet rs = psSelect.executeQuery()) {
                    if (rs.next()) {
                        currentFirst = rs.getString("firstname");
                        currentLast = rs.getString("lastname");
                        currentEmail = rs.getString("emailaddress");
                        currentUser = rs.getString("username");
                        currentType = rs.getString("usertype");
                        userExists = true;
                    }
                }
            }

            if (!userExists) {
                System.out.println("Update error: User ID " + id + " not found.");
                return;
            }

            try (PreparedStatement psUpdate = conn.prepareStatement(updateSql)) {
                psUpdate.setString(1, (firstname != null && !firstname.isEmpty()) ? firstname : currentFirst);
                psUpdate.setString(2, (lastname != null && !lastname.isEmpty()) ? lastname : currentLast);
                psUpdate.setString(3, (emailaddress != null && !emailaddress.isEmpty()) ? emailaddress : currentEmail);
                psUpdate.setString(4, (username != null && !username.isEmpty()) ? username : currentUser);
                psUpdate.setString(5, (usertype != null && !usertype.isEmpty()) ? usertype : currentType);
                psUpdate.setInt(6, id);

                int rows = psUpdate.executeUpdate();
                System.out.println( rows + " user updated successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Update user error: " + e.getMessage());
        }
    }

    public static boolean deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try {
            connect();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                int rows = ps.executeUpdate();
                return rows > 0;
            }
        } catch (SQLException e) {
            System.out.println("Delete user error: " + e.getMessage());
            return false;
        }
    }

   
    
    public static void createBook(String title, String author, String status) {
        String sql = "INSERT INTO books(title, author, status) VALUES (?, ?, ?)";
        try {
            connect();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, title);
                ps.setString(2, author);
                ps.setString(3, status);
                
                int rows = ps.executeUpdate();
                System.out.println( rows + " book added successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Create book error: " + e.getMessage());
        }
    }

    public static String getBook(int book_id) {
        String sql = "SELECT * FROM books WHERE book_id = ?";
        try {
            connect();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, book_id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("book_id") 
                               + ", " + rs.getString("title") 
                               + ", " + rs.getString("author") 
                               + ", " + rs.getString("status");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(" Get book error: " + e.getMessage());
        }
        return "Book not found";
    }

    public static List<String> getAllBooks() {
        List<String> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try {
            connect();
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    books.add(rs.getInt("book_id") 
                              + ", " + rs.getString("title") 
                              + ", " + rs.getString("author") 
                              + ", " + rs.getString("status"));
                }
            }
        } catch (SQLException e) {
            System.out.println(" Read books error: " + e.getMessage());
        }
        return books;
    }

    
    public static List<String> getAllAvailableBooks() {
        List<String> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE status = 'Available' OR status = 'available'";
        try {
            connect();
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    books.add(rs.getInt("book_id") 
                              + ", " + rs.getString("title") 
                              + ", " + rs.getString("author") 
                              + ", " + rs.getString("status"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Read available books error: " + e.getMessage());
        }
        return books;
    }

    public static void updateBook(int book_id, String title, String author, String status) {
        String selectSql = "SELECT * FROM books WHERE book_id = ?";
        String updateSql = "UPDATE books SET title = ?, author = ?, status = ? WHERE book_id = ?";
        
        try {
            connect();
            String currentTitle = "", currentAuthor = "", currentStatus = "";
            boolean bookExists = false;

            try (PreparedStatement psSelect = conn.prepareStatement(selectSql)) {
                psSelect.setInt(1, book_id);
                try (ResultSet rs = psSelect.executeQuery()) {
                    if (rs.next()) {
                        currentTitle = rs.getString("title");
                        currentAuthor = rs.getString("author");
                        currentStatus = rs.getString("status");
                        bookExists = true;
                    }
                }
            }

            if (!bookExists) {
                System.out.println(" Update error: Book ID " + book_id + " not found.");
                return;
            }

            try (PreparedStatement psUpdate = conn.prepareStatement(updateSql)) {
                psUpdate.setString(1, (title != null && !title.isEmpty()) ? title : currentTitle);
                psUpdate.setString(2, (author != null && !author.isEmpty()) ? author : currentAuthor);
                psUpdate.setString(3, (status != null && !status.isEmpty()) ? status : currentStatus);
                psUpdate.setInt(4, book_id);

                int rows = psUpdate.executeUpdate();
                System.out.println( rows + " book updated successfully.");
            }
        } catch (SQLException e) {
            System.out.println(" Update book error: " + e.getMessage());
        }
    }

    public static boolean deleteBook(int book_id) {
        String sql = "DELETE FROM books WHERE book_id = ?";
        try {
            connect();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, book_id);
                int rows = ps.executeUpdate();
                return rows > 0;
            }
        } catch (SQLException e) {
            System.out.println(" Delete book error: " + e.getMessage());
            return false;
        }
    }

    public static List<String> getAllAuthors() {
        List<String> authors = new ArrayList<>();
        String sql = "SELECT DISTINCT author FROM books WHERE author IS NOT NULL AND author != '' ORDER BY author ASC";
        try {
            connect();
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    authors.add(rs.getString("author"));
                }
            }
        } catch (SQLException e) {
            System.out.println(" Read authors error: " + e.getMessage());
        }
        return authors;
    }

    
    
    public static void borrowBook(int userId, int bookId) {
        String updateBookSql = "UPDATE books SET status = 'Borrowed' WHERE book_id = ? AND (status = 'Available' OR status = 'available')";
        
        try {
            connect();
            try (PreparedStatement psUpdate = conn.prepareStatement(updateBookSql)) {
                psUpdate.setInt(1, bookId);
                int updatedRows = psUpdate.executeUpdate();
                
                if (updatedRows > 0) {
                    System.out.println("Book ID " + bookId + " status successfully updated to 'Borrowed' (Approved for User ID: " + userId + ").");
                } else {
                    System.out.println("Borrow error: Book is either not found, or it is already 'Borrowed'.");
                }
            }
        } catch (SQLException e) {
            System.out.println(" Borrow transaction error: " + e.getMessage());
        }
    }

    public static List<String> getAllBorrowedBooks() {
        List<String> borrowed = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE status = 'Borrowed' OR status = 'borrowed'";
        try {
            connect();
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    borrowed.add("Book ID: " + rs.getInt("book_id") 
                                 + " | Title: " + rs.getString("title")
                                 + " | Author: " + rs.getString("author")
                                 + " | Status: " + rs.getString("status"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Read borrowed books error: " + e.getMessage());
        }
        return borrowed;
    }

    public static void disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Database connection closed cleanly.");
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
}
