/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aleksandra
 */
public class Receiver {
    private Socket socket;

    public Receiver(Socket socket) {
        this.socket = socket;
    }
    
    public Object receive() throws Exception{
        try {
            ObjectInputStream in= new ObjectInputStream(socket.getInputStream());
            return in.readObject();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new Exception("Error receive object: "+ex.getMessage());
        } 
    }
}
