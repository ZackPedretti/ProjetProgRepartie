import raytracer.Disp;
import raytracer.Image;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;


public interface ServiceDistributeur extends Remote{
    void enregistrerClient(ServiceRaytracing c) throws RemoteException;
    boolean executerRaytracing(ServiceRaytracing c) throws RemoteException;
    Disp getFinal() throws RemoteException;
}
