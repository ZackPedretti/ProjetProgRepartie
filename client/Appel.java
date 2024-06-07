import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.Duration;
import java.time.Instant;

import raytracer.Image;

public class Appel {

    public static void main(String[] args) throws NotBoundException, IOException {
        //Créer nouveau fichier dessin aléatoire
        CreerFichierRaytracing.creerFichierDessin();

        Registry reg = LocateRegistry.getRegistry("100.64.80.220");
        ServiceDistributeur serviceDistributeur = (ServiceDistributeur) reg.lookup("rayTracing");


        Client client = new Client(serviceDistributeur, "cool.txt", 512, 512, 32);
        ServiceClient serviceClient = (ServiceClient) UnicastRemoteObject.exportObject(client, 0);

        serviceDistributeur.enregistrerClient(serviceClient);

        //Calcul de la durée de calcul de tout le dessin
        Instant debut = Instant.now();
        
        //Calcul de l'image et affichage
        serviceDistributeur.calculateImage();

        Instant fin = Instant.now();
        long duree = Duration.between(debut, fin).toMillis();
        System.out.println("Dessin calculée en :"+duree+" ms");
        return;
    }
}
