import raytracer.Image;
import raytracer.Scene;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceClient extends Remote {
    public void afficherImage(Image image, int x, int y) throws RemoteException;
    public Scene getScene()throws RemoteException;
    public int getLargeurDessin()throws RemoteException;
    public int getHauteurDessin()throws RemoteException;
    public int getSquareSize()throws RemoteException;
}
