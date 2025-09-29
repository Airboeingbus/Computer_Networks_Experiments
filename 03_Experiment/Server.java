import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        // Ensure the file path is passed as a command-line argument
        if (args.length != 1) {
            System.out.println("Usage: java Server <file-path>");
            return;
        }
        
        // File to be sent to the client
        String fileName = args[0];

        // Server socket on port 8000
        try (ServerSocket serverSocket = new ServerSocket(8000)) {
            System.out.println("Server is listening on port 8000...");

            // Accept client connection
            try (Socket clientSocket = serverSocket.accept()) {
                System.out.println("Client connected!");

                // Create a file input stream to read the file
                try (FileInputStream fileInputStream = new FileInputStream(fileName);
                     OutputStream outputStream = clientSocket.getOutputStream()) {

                    // Buffer to send the file in chunks
                    byte[] buffer = new byte[4096];
                    int bytesRead;

                    // Send the file data to the client
                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }

                    System.out.println("File transfer completed.");
                } catch (IOException e) {
                    System.out.println("Error reading or sending file: " + e.getMessage());
                }
            } catch (IOException e) {
                System.out.println("Error accepting client connection: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Error creating server socket: " + e.getMessage());
        }
    }
}
