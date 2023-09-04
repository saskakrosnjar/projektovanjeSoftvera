/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import domain.Bibliotekar;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Aleksandra
 */
public class Communication {
    
    private static Communication instance;
    private Socket socket;
    private Bibliotekar ulogovani;

    public Communication() {
        try {
            socket=new Socket("localhost", 9000);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Communication getInstance() {
        if(instance==null){
            instance=new Communication();
        }
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }

    public Bibliotekar getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Bibliotekar ulogovani) {
        this.ulogovani = ulogovani;
    }
    
    
    
}
