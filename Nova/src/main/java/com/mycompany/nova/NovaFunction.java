package com.mycompany.nova;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class NovaFunction {
    private final dbManager db;
    private final Command dispatcher; 
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final Gson gson = new Gson();
    
    private final String apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + System.getenv("GEMINI_API_KEY");

    public NovaFunction(dbManager db) {
        this.db = db;
        this.dispatcher = new Command(db); 
    }

    public void processUserRequest(String userMessage) {
        try {
            
            String systemRules = """
                You are a strict text-to-JSON converter for a library database system.
                Your ONLY job is to output a single valid JSON object based on the user's request.
                DO NOT include markdown blocks like ```json ... 
```. 
                DO NOT include any explanations or conversational text.
                
                Expected JSON Schema:
                {
                  "function_name": "create user" | "get user" | "get all users" | "update user" | "delete user" | "create book" | "get book" | "get all books" | "get all available books" | "update book" | "delete book" | "borrow book" | "get all borrowed books" | "get all authors",
                  "arguments": []
                }
                CRITICAL RULE FOR ARGUMENTS:
                - The "arguments" field MUST ALWAYS be a JSON Array (using square brackets []).
                - DO NOT use key-value pairs (curly braces {}) inside or for the arguments.
                - Just extract the raw values (strings or integers) in order of importance or mention.
                
                Examples:
                User: "Create name John Doe" -> {"function_name": "create user", "arguments": ["John", "Doe"]}
                User: "delete user with id of one" -> {"function_name": "delete user", "arguments": [1]}
                User: "get all users" -> {"function_name": "get all users", "arguments": []}
                User: "give me all books available" -> {"function_name": "get all available books", "arguments": []}
                User: "show all books" -> {"function_name": "get all books", "arguments": []}
                User: "update book 3 status to borrowed" -> {"function_name": "update book", "arguments": [3, "borrowed"]}
                User: "add book Harry Potter by JK Rowling" -> {"function_name": "create book", "arguments": ["Harry Potter", "JK Rowling"]}
                User: "Let user 5 borrow book 12" -> {"function_name": "borrow book", "arguments": [5, 12]}
                User: "show me all borrowed books" -> {"function_name": "get all borrowed books", "arguments": []}
                User: "list all authors available" -> {"function_name": "get all authors", "arguments": []}
                """;
                 String aiPrompt = systemRules 
                   + "\n\n[CONTEXT]\nAnalyze the following user instruction and convert it strictly to the expected JSON format.\n"
                   + "\n[USER REQUEST]\n\"" + userMessage + "\"";

                 Map<String, Object> requestBody = Map.of(
                    "contents", List.of(
                Map.of("parts", List.of(Map.of("text", aiPrompt)))
                    ),
                    "generationConfig", Map.of(
                    "temperature", 0.0,
                    "responseMimeType", "application/json"
                      )
                     );

            String jsonBody = gson.toJson(requestBody);
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

            System.out.println("Sending request to AI... ");
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            
            JsonObject root = JsonParser.parseString(response.body()).getAsJsonObject();
            
            if (root.has("candidates")) {
                String rawJsonOutput = root.getAsJsonArray("candidates")
                    .get(0).getAsJsonObject()
                    .getAsJsonObject("content")
                    .getAsJsonArray("parts").get(0).getAsJsonObject()
                    .get("text").getAsString().trim();
                
                int unangCurly = rawJsonOutput.indexOf("{");
                int hulingCurly = rawJsonOutput.lastIndexOf("}");
                
                if (unangCurly != -1 && hulingCurly != -1 && hulingCurly > unangCurly) {
                    rawJsonOutput = rawJsonOutput.substring(unangCurly, hulingCurly + 1);
                } else {
                    System.out.println("Error: The output generated by the AI could not be parsed as a valid JSON object.");
                    return;
                }
                
                System.out.println("JSON: " + rawJsonOutput);
                JsonObject parsedCommand = JsonParser.parseString(rawJsonOutput).getAsJsonObject();
                
                dispatcher.dispatch(parsedCommand);
                
                } else {
                System.out.println("No response from AI.");
                System.out.println("Raw Body Feedback: " + response.body());
                }
                } catch (Exception e) {
                System.out.println("Error sa processUserRequest: " + e.getMessage());
              e.printStackTrace();
        }
    }
}