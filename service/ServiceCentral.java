package service;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
	
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class ServiceCentral {
    public static void main(String[] args) throws RemoteException {
        Central central = new Central();
    
        ServiceDistributeur serviceCentral = (ServiceDistributeur) UnicastRemoteObject.exportObject(central, 0);

        Registry reg = LocateRegistry.getRegistry(1099); /* Récupération de l'annuaire */
        reg.rebind("tableauBlanc", serviceCentral);
    }
}
