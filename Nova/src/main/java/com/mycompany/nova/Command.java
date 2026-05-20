package com.mycompany.nova;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.List;
import javax.swing.JOptionPane;

public class Command {
    private final dbManager db;

    public Command(dbManager db) {
        this.db = db;
    }

    public void dispatch(JsonObject parsedCommand) {
        if (!parsedCommand.has("function_name")) {
            System.out.println("Invalid command: Missing function_name.");
            return;
        }

        String functionName = parsedCommand.get("function_name").getAsString().trim().toLowerCase().replace(" ", "");
        JsonArray arguments = parsedCommand.getAsJsonArray("arguments");

        try {
            switch (functionName) {
                case "createuser":
                    if (confirmAction("Are you sure you want to REGISTER this new user?")) {
                        String firstName = (arguments.size() > 0) ? arguments.get(0).getAsString() : "";
                        String lastName  = (arguments.size() > 1) ? arguments.get(1).getAsString() : "";
                        String email     = (arguments.size() > 2) ? arguments.get(2).getAsString() : "";
                        String username  = (arguments.size() > 3) ? arguments.get(3).getAsString() : "";
                        String password  = (arguments.size() > 4) ? arguments.get(4).getAsString() : "";
                        String userType  = (arguments.size() > 5) ? arguments.get(5).getAsString() : "Student";

                        dbManager.createUser(firstName, lastName, email, username, password, userType);
                    }
                    break;
                    
                case "getuser": 
                    if (arguments.size() > 0) {
                        int id = arguments.get(0).getAsInt();
                        System.out.println(dbManager.getUser(id));
                    } else {
                        System.out.println("Error: Missing user ID in prompt.");
                    }
                    break;
                    
                case "getallusers":
                    dbManager.getAllUsers().forEach(System.out::println);
                    break;
                    
                case "updateuser": 
                    if (arguments.size() > 0) {
                        int userId = arguments.get(0).getAsInt();
                        if (confirmAction("Are you sure you want to UPDATE this User ID: " + userId + "?")) {
                            String firstName = (arguments.size() > 1) ? arguments.get(1).getAsString() : null;
                            String lastName  = (arguments.size() > 2) ? arguments.get(2).getAsString() : null;
                            String email     = (arguments.size() > 3) ? arguments.get(3).getAsString() : null;
                            String username  = (arguments.size() > 4) ? arguments.get(4).getAsString() : null;
                            String userType  = (arguments.size() > 5) ? arguments.get(5).getAsString() : null;

                            dbManager.updateUser(userId, firstName, lastName, email, username, userType);
                        }
                    } else {
                        System.out.println("Error: Missing user ID for update.");
                    }
                    break;
                    
                case "deleteuser": 
                    if (arguments.size() > 0) {
                        int deleteId = arguments.get(0).getAsInt();
                        if (confirmAction(" DANGER: Are you sure you want to DELETE this User ID: " + deleteId + "?")) {
                            boolean userDeleted = dbManager.deleteUser(deleteId);
                            System.out.println(userDeleted ? "Deleted user successfully" : " User not found");
                        }
                    } else {
                        System.out.println("Error: Missing user ID for deletion.");
                    }
                    break;
                    
                case "createbook": 
                    if (confirmAction("Are you sure you want to ADD this new book?")) {
                        String title  = (arguments.size() > 0) ? arguments.get(0).getAsString() : "";
                        String author = (arguments.size() > 1) ? arguments.get(1).getAsString() : "";
                        String status = (arguments.size() > 2) ? arguments.get(2).getAsString() : "Available";

                        dbManager.createBook(title, author, status);
                    }
                    break;
                    
                case "getbook": 
                    if (arguments.size() > 0) {
                        int bookId = arguments.get(0).getAsInt();
                        System.out.println(dbManager.getBook(bookId));
                    } else {
                        System.out.println("Error: Missing book_id in prompt.");
                    }
                    break;
                    
                case "getallbooks":
                    dbManager.getAllBooks().forEach(System.out::println);
                    break;

                
                case "getallavailablebooks":
                    List<String> availableBooks = dbManager.getAllAvailableBooks();
                    if (availableBooks.isEmpty()) {
                        System.out.println("No available books found in the database.");
                    } else {
                        System.out.println("\n=== AVAILABLE BOOKS ===");
                        availableBooks.forEach(System.out::println);
                        System.out.println("==========================\n");
                    }
                    break;
                    
                case "updatebook": 
                    if (arguments.size() > 0) {
                        int bookId = arguments.get(0).getAsInt();
                        if (confirmAction("Are you sure you want to UPDATE this Book ID: " + bookId + "?")) {
                            String title  = (arguments.size() > 1) ? arguments.get(1).getAsString() : null;
                            String author = (arguments.size() > 2) ? arguments.get(2).getAsString() : null;
                            String status = (arguments.size() > 3) ? arguments.get(3).getAsString() : null;

                            dbManager.updateBook(bookId, title, author, status);
                        }
                    } else {
                        System.out.println("Error: Missing book_id for update.");
                    }
                    break;
                    
                case "deletebook": 
                    if (arguments.size() > 0) {
                        int deleteBookId = arguments.get(0).getAsInt();
                        if (confirmAction("DANGER: Are you sure you want to DELETE this Book ID: " + deleteBookId + "?")) {
                            boolean bookDeleted = dbManager.deleteBook(deleteBookId);
                            System.out.println(bookDeleted ? "Deleted book successfully" : "Book not found");
                        }
                    } else {
                        System.out.println("Error: Missing book_id for deletion.");
                    }
                    break;

                case "getallauthors":
                    List<String> authorList = dbManager.getAllAuthors();
                    if (authorList.isEmpty()) {
                        System.out.println("No authors found in the database.");
                    } else {
                        System.out.println("\n=== LIST OF AUTHORS ===");
                        authorList.forEach(System.out::println);
                        System.out.println("==========================\n");
                    }
                    break;

                case "borrowbook":
                    if (arguments.size() > 1) {
                        int userId = arguments.get(0).getAsInt();
                        int bookId = arguments.get(1).getAsInt();
                        
                        if (confirmAction("Are you sure you want to BORROW Book ID " + bookId + " for User ID " + userId + "?")) {
                            dbManager.borrowBook(userId, bookId);
                        }
                    } else {
                        System.out.println(" Error: Missing user ID or book ID for borrowing.");
                    }
                    break;

                case "getallborrowedbooks":
                    List<String> borrowedList = dbManager.getAllBorrowedBooks();
                    if (borrowedList.isEmpty()) {
                        System.out.println("No currently borrowed books found in the database.");
                    } else {
                        System.out.println("\n=== BORROWED BOOKS IN SYSTEM ===");
                        borrowedList.forEach(System.out::println);
                        System.out.println("===================================\n");
                    }
                    break;
                    
                default:
                    System.out.println("Can't find database action: " + functionName);
            }
        } catch (Exception e) {
            System.out.println("Failed to execute database operation: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean confirmAction(String message) {
        int response = JOptionPane.showConfirmDialog(
            null, 
            message, 
            "Nova Execution Safety System", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE
        );
        return response == JOptionPane.YES_OPTION;
    }
}