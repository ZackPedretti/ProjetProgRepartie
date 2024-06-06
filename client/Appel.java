import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Appel {
    public static String aide = "Raytracer : synthèse d'image par lancé de rayons (https://en.wikipedia.org/wiki/Ray_tracing_(graphics))\n\nUsage : java LancerRaytracer [fichier-scène] [largeur] [hauteur] [taille des carrés par client]\n\tfichier-scène : la description de la scène (par défaut simple.txt)\n\tlargeur : largeur de l'image calculée (par défaut 512)\n\thauteur : hauteur de l'image calculée (par défaut 512)\n";

    public static void main(String[] args) {

        try {

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

            Registry reg = LocateRegistry.getRegistry("localhost");
            ServiceDistributeur serviceDistributeur = (ServiceDistributeur) reg.lookup("rayTracing");

            Client client = new Client(serviceDistributeur, largeur, hauteur);
            ServiceClient serviceClient = (ServiceClient) UnicastRemoteObject.exportObject(client, 0);

            serviceDistributeur.enregistrerClient(serviceClient);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
