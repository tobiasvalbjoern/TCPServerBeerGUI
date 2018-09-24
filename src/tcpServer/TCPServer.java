package tcpServer;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;

public class TCPServer {
  public static void main(String[] args) throws Exception {
    ServerSocket serverSocket = new ServerSocket(12900, 100,
        InetAddress.getByName("localhost"));
    System.out.println("Server started  at:  " + serverSocket);

    while (true) {
      System.out.println("Waiting for a  connection...");

      final Socket activeSocket = serverSocket.accept();

      System.out.println("Received a  connection from  " + activeSocket);
      Runnable runnable = () -> handleClientRequest(activeSocket);
      new Thread(runnable).start(); // start a new thread
    }
  }

  public static void handleClientRequest(Socket socket) {
    try{
      BufferedReader socketReader = null;
      BufferedWriter socketWriter = null;
      socketReader = new BufferedReader(new InputStreamReader(
          socket.getInputStream()));
      socketWriter = new BufferedWriter(new OutputStreamWriter(
          socket.getOutputStream()));

      String inMsg = null;
      while ((inMsg = socketReader.readLine()) != null) {
    	if(inMsg.equals("q")) {
    		break;
    	}
    	
    	else if(inMsg.contains("add person"))
    	{
    		System.out.println("Received from  client: " + inMsg);
    		String outMsg = "Add person registered";
            socketWriter.write(outMsg);
            socketWriter.write("\n");
            socketWriter.flush();
    	}
    	
    	else if(inMsg.contains("add drink"))
    	{
    		System.out.println("Received from  client: " + inMsg);
    		String outMsg = "Add drink registered";
            socketWriter.write(outMsg);
            socketWriter.write("\n");
            socketWriter.flush();
    	}
    	
    	else if(inMsg.contains("cheers"))
    	{
    		System.out.println("Received from  client: " + inMsg);
    		String outMsg = "cheers registered";
            socketWriter.write(outMsg);
            socketWriter.write("\n");
            socketWriter.flush();
    	}
    	else {
        System.out.println("Received from  client: " + inMsg);
        String outMsg = "Invalid command";
        socketWriter.write(outMsg);
        socketWriter.write("\n");
        socketWriter.flush();
    	}
      }
      socket.close();
    }catch(Exception e){
      e.printStackTrace();
    }

  }
}