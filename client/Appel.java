package client;

import java.rmi.Remote;
import java.rmi.server.UnicastRemoteObject;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

class Appel {
    public static void main(String[] args) {
    try {
	    Registry reg = LocateRegistry.getRegistry("100.64.80.52");
	    ServiceDistributeur serviceDistributeur = (ServiceDistributeur) reg.lookup("rayTracing");

	    RayTracing rayTracing = new RayTracing(serviceDistributeur);
	    ServiceRayTracing client = (ServiceRayTracing) UnicastRemoteObject.exportObject(rayTracing, 0);
	    
	    serviceDistributeur.enregistrerClient(client);
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    	}
    }
}
