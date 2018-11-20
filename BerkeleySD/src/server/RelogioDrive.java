/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Alunos
 */
public class RelogioDrive {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        RelogioServer clock = new RelogioServer();
        //RelogioInterface stub = (RelogioInterface) UnicastRemoteObject.exportObject(clock, 12345);
        
        Registry r = LocateRegistry.createRegistry(12345);
        r.rebind("RelogioInterface", clock);
        System.err.println("Servidor OK");
    }
    
}
