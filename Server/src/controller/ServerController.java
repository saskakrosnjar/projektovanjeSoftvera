/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Autor;
import domain.Bibliotekar;
import domain.Knjiga;
import domain.Korisnik;
import domain.StavkaUsluge;
import domain.Usluga;
import java.util.ArrayList;
import so.autor.SOGetAllAutor;
import so.knjiga.SOAddKnjiga;
import so.knjiga.SOGetAllKnjiga;
import so.knjiga.SOUpdateKnjiga;
import so.korisnik.SOAddKorisnik;
import so.korisnik.SODeleteKorisnik;
import so.korisnik.SOGetAllKorisnik;
import so.korisnik.SOUpdateKorisnik;
import so.login.SOLogin;
import so.usluga.SOAddUsluga;
import so.usluga.SODeleteUsluga;
import so.usluga.SOGetAllUsluga;
import so.usluga.SOGetUsluga;
import so.usluga.SOUpdateUsluga;

/**
 *
 * @author Aleksandra
 */
public class ServerController {
    
    private static ServerController instance;
    private static Bibliotekar ulogovani;

    public static ServerController getInstance() {
        if(instance==null){
            instance=new ServerController();
        }
        return instance;
    }

    public Bibliotekar login(Bibliotekar bibliotekar) throws Exception {
        SOLogin so=new SOLogin();
        so.templateExecute(bibliotekar);
        ulogovani=so.getUlogovani();
        return so.getUlogovani();
    }

    public void addKorisnika(Korisnik korisnik) throws Exception {
        SOAddKorisnik so=new SOAddKorisnik();
        so.templateExecute(korisnik);
    }
    
    public void addKnjiga(Knjiga knjiga) throws Exception {
        SOAddKnjiga so= new SOAddKnjiga();
        so.templateExecute(knjiga);
    }
    
    public void addUsluga(Usluga usluga) throws Exception {
        SOAddUsluga so=new SOAddUsluga();
        so.templateExecute(usluga);
    }
    
    public void deleteKorisnik(Korisnik korisnik) throws Exception {
        SODeleteKorisnik so= new SODeleteKorisnik();
        so.templateExecute(korisnik);
    }
    
    public void deleteUsluga(Usluga usluga) throws Exception {
        SODeleteUsluga so=new SODeleteUsluga();
        so.templateExecute(usluga);
    }
    
    public void updateKorisnik(Korisnik korisnik) throws Exception {
        SOUpdateKorisnik so=new SOUpdateKorisnik();
        so.templateExecute(korisnik);
    }
    
    public void updateKnjiga(Knjiga knjiga) throws Exception {
        SOUpdateKnjiga so=new SOUpdateKnjiga();
        so.templateExecute(knjiga);
    }
        
     public void updateUsluga(Usluga usluga) throws Exception {
         SOUpdateUsluga so= new SOUpdateUsluga();
         so.templateExecute(usluga);
     }

    public ArrayList<Korisnik> getAllKorisnik() throws Exception {
        SOGetAllKorisnik so= new SOGetAllKorisnik();
        so.templateExecute(new Korisnik());
        return so.getLista();
    }
    
    public Object getAllUsluga() throws Exception {
        SOGetAllUsluga so=new SOGetAllUsluga();
        so.templateExecute(new Usluga());
        return so.getLista();
    }

    public Object getUsluga(Usluga usluga) throws Exception {
        SOGetUsluga so=new SOGetUsluga();
        so.templateExecute(usluga);
        return so.getUsluga();
    }
    
    public Object getAllKnjiga() throws Exception {
        SOGetAllKnjiga so=new SOGetAllKnjiga();
        so.templateExecute(new Knjiga());
        return so.getLista();
    }

    public Object getAllAutor() throws Exception {
        SOGetAllAutor so= new SOGetAllAutor();
        so.templateExecute(new Autor());
        return so.getLista();
    }

    
    
}
