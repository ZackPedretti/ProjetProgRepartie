import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceTableauBlanc extends Remote{
    public void afficherMessage(Dessin d);
}