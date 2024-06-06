import raytracer.Disp;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ServiceDistributeur extends Remote{
    void enregistrerClient(ServiceRaytracing c) throws RemoteException;
    boolean executerRaytracing(ServiceRaytracing c) throws RemoteException;
    Disp getFinal() throws RemoteException;
}
