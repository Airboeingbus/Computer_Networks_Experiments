import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        // Ensure the destination file path is passed as a command-line argument
        if (args.length != 1) {
            System.out.println("Usage: java Client <destination-file-path>");
            return;
        }
        
        // File to be saved on the client side
        String destinationFile = args[0];

        // Connect to the server on localhost, port 8000
        try (Socket socket = new Socket("localhost", 8000)) {
            System.out.println("Connected to server...");

            // Create a file output stream to write the received file
            try (FileOutputStream fileOutputStream = new FileOutputStream(destinationFile);
                 InputStream inputStream = socket.getInputStream()) {

                // Buffer to read file data in chunks
                byte[] buffer = new byte[4096];
                int bytesRead;

                // Receive the file data from the server
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }

                System.out.println("File received and saved as: " + destinationFile);
            } catch (IOException e) {
                System.out.println("Error receiving or writing file: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Error connecting to server: " + e.getMessage());
        }
    }
}
