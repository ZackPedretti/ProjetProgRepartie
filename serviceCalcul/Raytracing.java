import raytracer.Disp;
import raytracer.Image;
import raytracer.Scene;

import java.rmi.RemoteException;
import java.time.Duration;
import java.time.Instant;

public class Raytracing implements ServiceRaytracing {

    private ServiceDistributeur distributeur;

    public Raytracing(ServiceDistributeur distributeur) throws RemoteException {
        this.distributeur = distributeur;
    }

    public void start() throws RemoteException {
        while(distributeur.executerRaytracing(this));
    }

    @Override
    public Image calculerImage(Scene scene, int x0, int y0, int width, int height) throws RemoteException{
        Instant debut = Instant.now();
        System.out.println("Calcul de l'image :\n - Coordonnées : "+x0+","+y0
                +"\n - Taille "+ width + "x" + height);
        Image image = scene.compute(x0, y0, width, height);
        Instant fin = Instant.now();

        long duree = Duration.between(debut, fin).toMillis();

        System.out.println("Image calculée en :"+duree+" ms");

        return image;
    }
}