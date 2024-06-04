package client;

import java.util.Scanner;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

import java.rmi.RemoteException;
import java.rmi.NotBoundException;

import java.rmi.server.ServerNotActiveException;

class InterrogationAnnuaire {
    public static void main(String[] args) throws RemoteException{
    	Registry reg = LocateRegistry.getRegistry("100.64.80.224");
	 String[] list = reg.list();
	 System.out.println("Liste des Service du professeur :");
	 for(int i=0 ; i<list.length; i++ ) {
	     System.out.println("* "+list[i]);
	 }
	 Registry regIUT = LocateRegistry.getRegistry("charlemagne.iutnc.univ-lorraine.fr", 3333);
	 String[] listIUT = regIUT.list();
	 System.out.println("Liste des Service IUT :");
	 for(int i=0 ; i<listIUT.length; i++ ) {
	     System.out.println("* "+listIUT[i]);
	 }
    }
}
