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
    private int idClient;
    
    protected RelogioServer() throws RemoteException {
        clients = new ArrayList<ClientInterface>();
        idClient = 0;
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
        client.setId(idClient++);
        this.clients.add(client);
        System.out.println("Clientes conectados");
        int i = 0, cord = 10000;
        while (i < clients.size()) {
            if (i < cord) {
                cord = i;
                clients.get(i).setCoordinator();
            }
            String name = clients.get(i).getName();
            int id = clients.get(i).getId();
            System.out.println(id + " - " + name);
            i++;
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
    
    @Override
    public void disconnect(String nome) throws RemoteException {
        for (ClientInterface client:clients){
            if (client.getName().equals(nome)) {
                System.out.println("Cliente " + nome + " desconectado");
                clients.remove(client);
                client.exit();
            }
        } 
    }
    
    @Override
    public void election() throws RemoteException {
        int coord = 1000;
        System.out.println("Eleição");
        for (ClientInterface client:clients){
            if (client.getId() < coord) {
                coord = client.getId();
            }
        }
        System.out.println("coord " + coord );
        clients.get(coord).setCoordinator();
        clients.get(coord).run();
    }
}
