import java.util.Scanner;
import java.rmi.server.UnicastRemoteObject;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

import java.rmi.RemoteException;
import java.rmi.NotBoundException;

class Appel {
    public static void main(String[] args) {
    try {
	    Registry reg = LocateRegistry.getRegistry("localhost");
	    ServiceDistributeur serviceDistributeur = (ServiceDistributeur) reg.lookup("tableauBlanc");

	    TableauBlanc tableauBlanc = new TableauBlanc(serviceDistributeur);
	    ServiceTableauBlanc client = (ServiceTableauBlanc) UnicastRemoteObject.exportObject(tableauBlanc, 0);    
	    
	    serviceDistributeur.enregistrerClient(client);
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    	}
    }
}
