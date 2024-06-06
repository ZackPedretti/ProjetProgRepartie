import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Appel {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry reg = LocateRegistry.getRegistry("localhost");
        ServiceDistributeur serviceDistributeur = (ServiceDistributeur) reg.lookup("rayTracing");

        Client client = new Client(serviceDistributeur, "simple.txt", 512, 512, 64);
        ServiceClient serviceClient = (ServiceClient) UnicastRemoteObject.exportObject(client, 0);

        serviceDistributeur.enregistrerClient(serviceClient);

        serviceDistributeur.calculateImage();
    }
}
