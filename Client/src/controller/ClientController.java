/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import communication.Communication;
import communication.Operations;
import communication.Request;
import communication.Response;
import communication.ResponseType;
import domain.Autor;
import domain.Bibliotekar;
import domain.Knjiga;
import domain.Korisnik;
import domain.StavkaUsluge;
import domain.Usluga;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 *
 * @author Aleksandra
 */
public class ClientController {
    private static ClientController instance;

    public ClientController() {
    }
    
    public static ClientController getInstance() {
        if(instance==null){
            instance=new ClientController();
        }
        return instance;
    }

    public Bibliotekar login(Bibliotekar bibliotekar) throws Exception {
        return (Bibliotekar) sendRequest(Operations.LOGIN, bibliotekar);
    }
    
    

    public void addKorisnik(Korisnik korisnik) throws Exception {
        sendRequest(Operations.ADD_KORISNIK, korisnik);
    }
    
    public void addKnjiga(Knjiga knjiga) throws Exception {
        sendRequest(Operations.ADD_KNJIGA, knjiga);
    }
    
    public void addUsluga(Usluga usluga) throws Exception {
        sendRequest(Operations.ADD_USLUGA, usluga);
    }
    
    public void deleteKorisnik(Korisnik korisnik) throws Exception {
        sendRequest(Operations.DELETE_KORISNIK, korisnik);
    }
    
    public void deleteUsluga(Usluga usluga) throws Exception {
        sendRequest(Operations.DELETE_USLUGA, usluga);
    }

    public void updateKorisnik(Korisnik korisnik) throws Exception {
        sendRequest(Operations.UPDATE_KORISNIK, korisnik);
    }
    
    public void updateKnjiga(Knjiga knjiga) throws Exception {
        sendRequest(Operations.UPDATE_KNJIGA, knjiga);
    }
    
     public void updateUsluga(Usluga usluga) throws Exception {
        sendRequest(Operations.UPDATE_USLUGA, usluga);
    }
     
    public List<Korisnik> getAllKorisnik() throws Exception {
        return (List<Korisnik>) sendRequest(Operations.GET_ALL_KORISNIK, null);
    }

    public List<Knjiga> getAllKnjiga() throws Exception {
        return (List<Knjiga>) sendRequest(Operations.GET_ALL_KNJIGA, null);
    }

    public List<Autor> getAllAutor() throws Exception {
        return (List<Autor>) sendRequest(Operations.GET_ALL_AUTOR, null);
    }

    public List<Usluga> getAllUsluga() throws Exception {
        return (List<Usluga>) sendRequest(Operations.GET_ALL_USLUGA, null);
    }
    
    public Usluga getUsluga(Usluga usluga) throws Exception {
        return (Usluga) sendRequest(Operations.GET_USLUGA, usluga);
    }
    
    private Object sendRequest(int operation, Object data) throws Exception {
        Request request=new Request(operation, data);
        
        ObjectOutputStream out= new ObjectOutputStream(Communication.getInstance().getSocket().getOutputStream());
        out.writeObject(request);
        
        ObjectInputStream in= new ObjectInputStream(Communication.getInstance().getSocket().getInputStream());
        Response response= (Response) in.readObject();
        
        if(response.getResponseType().equals(ResponseType.ERROR)){
            throw response.getException();
        }else{
            return response.getResult();
        }
        
    }

    
    


    
}
