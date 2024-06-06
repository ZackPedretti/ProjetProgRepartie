import java.rmi.server.UnicastRemoteObject;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

class Appel {
    public static void main(String[] args) {
    try {
	    Registry reg = LocateRegistry.getRegistry("localhost");
	    ServiceDistributeur serviceDistributeur = (ServiceDistributeur) reg.lookup("rayTracing");

	    Raytracing rayTracing = new Raytracing(serviceDistributeur);
	    ServiceRaytracing client = (ServiceRaytracing) UnicastRemoteObject.exportObject(rayTracing, 0);
	    
	    serviceDistributeur.enregistrerClient(client);
	    }
	catch (Exception e) {
		e.printStackTrace();
		}
    }
}
