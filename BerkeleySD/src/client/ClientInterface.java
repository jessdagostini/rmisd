/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author Alunos
 */
public interface ClientInterface extends Remote {
    void showTime() throws RemoteException;
    public String getName() throws RemoteException;
    public int getTime() throws RemoteException;
    public void setTime(int diff) throws RemoteException;
    public void setId(int id) throws RemoteException;
    public int getId() throws RemoteException;
    public void setCoordinator() throws RemoteException;
    public void randonize() throws RemoteException;
    public String getFormattedTime() throws RemoteException;
    public void run() throws RemoteException;
    public void exit() throws RemoteException;
    public boolean isCoordinator() throws RemoteException;
}
