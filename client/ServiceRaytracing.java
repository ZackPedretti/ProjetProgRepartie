import raytracer.Image;
import raytracer.Scene;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceRaytracing extends Remote{
    public Image calculerImage(Scene scene, int x0, int y0, int width, int height) throws RemoteException;
    boolean isCalculating()throws RemoteException;
    void setCalculating(boolean bool) throws RemoteException;
}