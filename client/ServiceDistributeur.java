import raytracer.Scene;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ServiceDistributeur extends Remote{
    void enregistrerClient(ServiceClient c) throws RemoteException;
    void enregistrerServiceCalcul(ServiceRaytracing c) throws RemoteException;
    public void calculateImage() throws RemoteException;
}
