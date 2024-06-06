import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class AppelCalcul {
    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.getRegistry("localhost");
            ServiceDistributeur serviceDistributeur = (ServiceDistributeur) reg.lookup("rayTracing");

            Raytracing rayTracing = new Raytracing();
            ServiceRaytracing serviceCalcul = (ServiceRaytracing) UnicastRemoteObject.exportObject(rayTracing, 0);

            serviceDistributeur.enregistrerServiceCalcul(serviceCalcul);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
