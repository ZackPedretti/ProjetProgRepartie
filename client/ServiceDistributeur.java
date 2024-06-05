
import service.Dessin;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceDistributeur extends Remote {
    public void enregistrerClient(ServiceRayTracing c) throws RemoteException;
    public void distribuerMessage(Dessin d) throws RemoteException;
}
