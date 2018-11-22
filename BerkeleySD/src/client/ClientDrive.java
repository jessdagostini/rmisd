/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import server.RelogioInterface;

/**
 *
 * @author Alunos
 */
public class ClientDrive {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();
        Registry r = LocateRegistry.getRegistry("192.168.0.17", 12345);
        RelogioInterface stub = (RelogioInterface) r.lookup("RelogioInterface");
        Client client = new Client(name, stub);
        client.run();
    }
}
