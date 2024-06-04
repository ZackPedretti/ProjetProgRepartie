package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
        

public interface ServiceDistributeur extends Remote {

    public void enregistrerClient(ServiceTableauBlanc c) throws RemoteException;

    
}
