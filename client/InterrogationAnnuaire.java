package client;

import java.util.Scanner;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

import java.rmi.RemoteException;
import java.rmi.NotBoundException;

import java.rmi.server.ServerNotActiveException;

class InterrogationAnnuaire {
    public static void main(String[] args) throws RemoteException{
    	Registry reg = LocateRegistry.getRegistry("193.50.135.205");
		 String[] list = reg.list();
		 System.out.println("Liste des services :");
		 for(int i=0 ; i<list.length; i++ ) {
			 System.out.println("* "+list[i]);
		 }
    }
}
