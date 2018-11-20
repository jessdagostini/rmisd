/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import client.ClientInterface;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Alunos
 */
public interface RelogioInterface extends Remote {
    public int getTime() throws RemoteException;
    public String getFormattedTime() throws RemoteException;
    public void setTime(int time) throws RemoteException;
    public int getDifference() throws RemoteException;
    public void setDifference(int diff) throws RemoteException;
    public void randonize() throws RemoteException;
    public void registry(ClientInterface client) throws RemoteException;
    public void synchronize() throws RemoteException;
    public void showTime() throws RemoteException;
}
