package service;

import client.ServiceDistributeur;
import client.ServiceRayTracing;

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

    @Override
    public void enregistrerClient(ServiceRayTracing c) throws RemoteException {

    }
}
