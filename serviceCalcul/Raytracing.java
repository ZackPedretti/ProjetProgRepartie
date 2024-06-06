import raytracer.Image;
import raytracer.Scene;

import java.rmi.RemoteException;
import java.time.Duration;
import java.time.Instant;

public class Raytracing implements ServiceRaytracing {
    boolean isCalculating;

    public Raytracing() {
        this.isCalculating = false;
    }

    //Méthode pour calculer une portion de l'image (un carré)
    public Image calculerImage(Scene scene, int x, int y, int width, int height) throws RemoteException{
        Instant debut = Instant.now();
        System.out.println("Calcul de l'image :\n - Coordonnées : "+x+","+y
                +"\n - Taille "+ width + "x" + height);
        Image image = scene.compute(x, y, width, height);
        Instant fin = Instant.now();

        long duree = Duration.between(debut, fin).toMillis();

        System.out.println("Image calculée en :"+duree+" ms");
        return image;
    }

    public boolean isCalculating() throws RemoteException{
        return this.isCalculating;
    }

    public void setCalculating(boolean bool) throws RemoteException{
        this.isCalculating = bool;
    }
}