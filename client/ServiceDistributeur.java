package client;

import java.rmi.Remote;
import java.rmi.RemoteException;
        

public interface ServiceDistributeur extends Remote {

    public void enregistrerClient(ServiceRayTracing c) throws RemoteException;

    
}
