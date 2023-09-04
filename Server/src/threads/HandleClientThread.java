/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import communication.Operations;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.ResponseType;
import communication.Sender;
import controller.ServerController;
import domain.Bibliotekar;
import domain.Knjiga;
import domain.Korisnik;
import domain.Usluga;
import java.net.Socket;

/**
 *
 * @author Aleksandra
 */
public class HandleClientThread extends Thread{
    private Socket socket;

    
    public HandleClientThread(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        try {
            while(!socket.isClosed()){
                Request request=(Request) new Receiver(socket).receive();
                Response response=handleRequest(request);
                new Sender(socket).send(response);
            } 
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request request) {
        Response response = new Response(ResponseType.SUCCESS, null, null);
        try {
            switch (request.getOperation()) {
                case Operations.LOGIN:
                    Bibliotekar bibliotekar = (Bibliotekar) request.getArgument();
                    Bibliotekar ulogovani = ServerController.getInstance().login(bibliotekar);
                    response.setResult(ulogovani);
                    break;
                case Operations.ADD_KORISNIK:
                    ServerController.getInstance().addKorisnika((Korisnik) request.getArgument());
                    break;
                case Operations.GET_ALL_KORISNIK:
                    response.setResult(ServerController.getInstance().getAllKorisnik());
                    break;
                case Operations.DELETE_KORISNIK:
                    ServerController.getInstance().deleteKorisnik((Korisnik)request.getArgument());
                    break;
                case Operations.UPDATE_KORISNIK:
                    ServerController.getInstance().updateKorisnik((Korisnik) request.getArgument());
                    break;
                case Operations.GET_ALL_KNJIGA:
                    response.setResult(ServerController.getInstance().getAllKnjiga());
                    break;
                case Operations.GET_USLUGA:
                    response.setResult(ServerController.getInstance().getUsluga((Usluga) request.getArgument()));
                    break;
                case Operations.ADD_KNJIGA:
                    ServerController.getInstance().addKnjiga((Knjiga)request.getArgument());
                    break;
                case Operations.GET_ALL_AUTOR:
                    response.setResult(ServerController.getInstance().getAllAutor());
                    break;
                case Operations.ADD_USLUGA:
                    ServerController.getInstance().addUsluga((Usluga) request.getArgument());
                    break;
                case Operations.GET_ALL_USLUGA:
                    response.setResult(ServerController.getInstance().getAllUsluga());
                    break;
                case Operations.UPDATE_USLUGA:
                    ServerController.getInstance().updateUsluga((Usluga) request.getArgument());
                    break;
                case Operations.DELETE_USLUGA:
                    ServerController.getInstance().deleteUsluga((Usluga) request.getArgument());
                    break;
                case Operations.UPDATE_KNJIGA:
                    ServerController.getInstance().updateKnjiga((Knjiga) request.getArgument());
                    break;
                default:
                    return null;
            }
        } catch (Exception e) {
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

   
    
}
