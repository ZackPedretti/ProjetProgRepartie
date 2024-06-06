import raytracer.Scene;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class ServiceCentral {
    public static String aide = "Raytracer : synthèse d'image par lancé de rayons (https://en.wikipedia.org/wiki/Ray_tracing_(graphics))\n\nUsage : java LancerRaytracer [fichier-scène] [largeur] [hauteur] [taille des carrés par client]\n\tfichier-scène : la description de la scène (par défaut simple.txt)\n\tlargeur : largeur de l'image calculée (par défaut 512)\n\thauteur : hauteur de l'image calculée (par défaut 512)\n";

    public static void main(String[] args) throws RemoteException {

        /*
        String fichier_description = "simple.txt";

        int largeur = 512, hauteur = 512;

        int squareSize = 64;

        //<editor-fold desc="args">

        if (args.length > 0) {
            fichier_description = args[0];
            if (args.length > 1) {
                largeur = Integer.parseInt(args[1]);
                if (args.length > 2) {
                    hauteur = Integer.parseInt(args[2]);
                    if (args.length > 3) squareSize = Integer.parseInt(args[3]);
                }
            }
        } else {
            System.out.println(aide);
        }

        //</editor-fold>

         */

        Central central = new Central();

        ServiceDistributeur serviceCentral = (ServiceDistributeur) UnicastRemoteObject.exportObject(central, 0);

        Registry reg = LocateRegistry.getRegistry(1099); /* Récupération de l'annuaire */
        reg.rebind("rayTracing", serviceCentral);
    }
}
