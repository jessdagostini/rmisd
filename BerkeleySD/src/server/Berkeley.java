/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Alunos
 */
public class Berkeley {
    private int average;
    private int total;

    private ArrayList<RelogioServer> entities;

    public Berkeley(ArrayList entities) {

        this.entities = entities;
    }

    public void syncronize() throws RemoteException {

        average = total = 0;

        for (RelogioServer entity : entities) {
            entity.setDifference(entity.getTime() - entities.get(0).getTime());
            total += entity.getDifference();
        }

        average = total / entities.size();

        for (RelogioServer entity : entities) {
            int newTime = average + (entity.getDifference() * (-1));
            entity.setTime(newTime);
        }
    }

    public void syncronize(ArrayList entities) throws RemoteException {

        setEntities(entities);

        syncronize();
    }
    
    public static ArrayList<RelogioServer> fastSync(ArrayList entities) throws RemoteException {
        
        Berkeley self = new Berkeley(entities);
        
        self.syncronize();
        
        return self.getEntities();
    }

    public ArrayList<RelogioServer> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<RelogioServer> entities) {
        this.entities = entities;
    }
}
