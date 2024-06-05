import java.util.Scanner;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

import java.rmi.RemoteException;
import java.rmi.NotBoundException;

import java.rmi.server.ServerNotActiveException;

class InterrogationAnnuaire {
    public static void main(String[] args) throws RemoteException, NotBoundException, ServerNotActiveException {
    	Registry reg = LocateRegistry.getRegistry("localhost");
		 String[] list = reg.list();
		 System.out.println("Liste des services:");
		 for(int i=0 ; i<list.length; i++ ) {
			 System.out.println("* "+list[i]);
	 }
    }
}
