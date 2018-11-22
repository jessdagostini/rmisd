/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import server.RelogioInterface;

/**
 *
 * @author Alunos
 */
public class Client extends UnicastRemoteObject implements ClientInterface {
    private RelogioInterface clockServer;
    private String name = null;
    private int hora = 0;
    private int id;
    private Boolean coordinator = false;
    
    protected Client(String name, RelogioInterface clockServer) throws RemoteException {
        this.clockServer = clockServer;
        this.name = name;
        randonize();
        showTime();
        clockServer.registry(this);
    }

    @Override
    public void showTime() throws RemoteException {
        System.out.println("Cliente: " + this.name);
        System.out.println("Tempo em minutos: " + getFormattedTime());
    }

    @Override
    public String getName() throws RemoteException {
        return this.name;
    }
    
    @Override
    public void setId(int id) throws RemoteException {
        this.id = id;
        System.out.println("ID Cliente: " + this.id);
    }
    
    @Override
    public int getId() throws RemoteException {
        return this.id;
    }
    
    public void setCoordinator() throws RemoteException {
        this.coordinator = true;
    }
    
    @Override
    public int getTime() throws RemoteException {
        return this.hora;
    }
    
    @Override
    public void setTime(int diff) throws RemoteException {
        this.hora += -(diff);
        showTime();
    }
    
    @Override
    public final void randonize() throws RemoteException {

        int i = (int) (Math.random() * 23);
        int j = (int) (Math.random() * 59);
        
        this.hora = (i * 60) + j;
    }
    
    @Override
    public String getFormattedTime() throws RemoteException {
        
        String formatted;
        
        int hour = hora / 60;
        int minutes = hora % 60;
        
        formatted = hour + ":" + minutes;
        
        return formatted;
    }
    
    public void run() throws RemoteException {
        if (coordinator) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Escolha uma ação:\n"
                        + "1 - Synchronize\n 2 - Randonize\n 3 - Show Time");
            while (scan.hasNext()) {
                int action;
                action = Integer.parseInt(scan.nextLine());
                switch(action) {
                    case 1:
                        this.clockServer.synchronize();
                        break;

                    case 2:
                        this.clockServer.randonize();
                        break;

                    case 3:
                        this.clockServer.showTime();
                        break;

                    default:
                        this.clockServer.showTime();
                        break;
                }
            System.out.println("Escolha uma ação:\n"
                        + "1 - Synchronize\n2 - Randonize\n3 - Show Time");
            }
        }
    }
}
