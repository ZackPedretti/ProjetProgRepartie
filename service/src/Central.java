import java.rmi.RemoteException;
import java.util.ArrayList;

public class Central implements ServiceDistributeur {
    ArrayList<ServiceTableauBlanc> servicesClient;

    public Central() {
        this.servicesClient = new ArrayList<ServiceTableauBlanc>();
    }

    public void enregistrerClient(ServiceTableauBlanc c) throws RemoteException {
        this.servicesClient.add(c);
    }
}
