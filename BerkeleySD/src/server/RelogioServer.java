/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import client.Client;
import client.ClientInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Alunos
 */
public class RelogioServer extends UnicastRemoteObject implements RelogioInterface{
    private ArrayList<ClientInterface> clients;
    
    protected RelogioServer() throws RemoteException {
        clients = new ArrayList<ClientInterface>();
    }
    
    @Override
    public int getTime() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getFormattedTime() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTime(int time) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getDifference() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDifference(int diff) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void randonize() throws RemoteException {
        System.out.println("Randonize");
        for (ClientInterface client:clients){
            client.randonize();
        }
    }

    @Override
    public void registry(ClientInterface client) throws RemoteException {
        this.clients.add(client);
        System.out.println("Clientes conectados");
        int i = 0;
        while (i < clients.size()) {
            String name = clients.get(i++).getName();
            System.out.println(name);
        }
    }

    @Override
    public void synchronize() throws RemoteException {
        System.out.println("synchronize");
        int total = 0;
        for (ClientInterface client:clients){
            total += client.getTime();
        }
        
        int media = total / clients.size();
        System.out.println("Media: " + media);
        
        for (ClientInterface client:clients){
            int diff = 0;
            diff = client.getTime() - media;
            System.out.println("Diferenca do server " + client.getName() + ": " + diff);
            client.setTime(diff);
        }
        
    }
    
    @Override
    public void showTime() throws RemoteException {
        System.out.println("Showtime");
        for (ClientInterface client:clients){
            client.showTime();
        }
    }
}
