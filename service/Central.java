package service;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Central implements ServiceDistributeur {
    ArrayList<ServiceTableauBlanc> servicesClient;
    ArrayList<Dessin> dessins;

    public Central() {
        this.servicesClient = new ArrayList<ServiceTableauBlanc>();
        this.dessins = new ArrayList<Dessin>();
    }

    public void enregistrerClient(ServiceTableauBlanc c) throws RemoteException {
        this.servicesClient.add(c);
        for (Dessin dessin : this.dessins) {
            c.afficherMessage(dessin);
        }
    }
    public void distribuerMessage(Dessin d)  {
        this.dessins.add(d);
        for (ServiceTableauBlanc service : this.servicesClient)  {
            try {
                service.afficherMessage(d);
            }
            catch (Exception e) {
                this.servicesClient.remove(service);
                e.printStackTrace();
            }
        }
    }
}

