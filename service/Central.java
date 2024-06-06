
import raytracer.Image;
import raytracer.Scene;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public class Central implements ServiceDistributeur {
    ServiceClient serviceClient;
    ArrayList<ServiceRaytracing> serviceCalculs;
    Scene scene;
    int width, height;
    HashMap<Image, int[]> images;
    int squareSize;
    ArrayList<int[]> calculatedSquares;

    public Central() {
        this.serviceCalculs = new ArrayList<>();
    }

    //Méthode pour calculer toute une image
    public void calculateImage() throws RemoteException {
        this.images = new HashMap<>();
        this.calculatedSquares = new ArrayList<>();
        this.scene = serviceClient.getScene();
        this.squareSize = serviceClient.getSquareSize();
        this.width = serviceClient.getLargeurDessin(); this.height = serviceClient.getHauteurDessin();
        boolean calculating = true;

        //On calcul l'image tant qu'il y a des carrés à calculer et qu'il y a des services calculs
        while (calculating) {
            if (this.serviceCalculs.isEmpty()) {
                calculating = false;
                System.out.println("Calcul impossible car aucun service Calcul disponible");
            }
            for (ServiceRaytracing serviceRaytracing : this.serviceCalculs) {
                //Si le service n'est pas déjà en train de calculer quoiquec
                if (!serviceRaytracing.isCalculating()) {
                    int[] xy = getNewSquare();
                    if(xy == null) {
                        calculating = false;
                        break;
                    }
                    else {
                        int x0 = xy[0];
                        int y0 = xy[1];
                        Thread thread = new Thread() {
                            public void run() {
                                try {
                                    //Calcul de l'image par un serviceCalcul
                                    Image image = serviceRaytracing.calculerImage(scene, x0, y0, Math.min(squareSize, width - x0), Math.min(squareSize, width - y0));
                                    //Enregistrement de l'image dans la liste des images déjà calculées
                                    images.put(image, xy);
                                    //Envoie de l'image au client pour qu'il l'affiche
                                    serviceClient.afficherImage(image, xy[0], xy[1]);
                                    calculatedSquares.add(xy);
                                    serviceRaytracing.setCalculating(false);
                                }
                                catch (RemoteException e) {
                                    //Le serviceCalcul n'a pas répondu dans un temps raissonable, on le supprime de la liste
                                    serviceCalculs.remove(serviceRaytracing);
                                    throw new RuntimeException(e);
                                }
                            }
                        };
                        serviceRaytracing.setCalculating(true);
                        thread.start();
                    }
                }
            }
        }
    }

    //Méthode pour récuperérer un nouveau carré (une nouvelle portion de l'image) pas encore calculer
    private int[] getNewSquare() {
        int x, y = 0;
        while(y < height){
            x = 0;
            while(x < width){
                boolean isAlreadyCalculated = false;
                for (int[] calculatedSquare : calculatedSquares) {
                    isAlreadyCalculated = isAlreadyCalculated || (x == calculatedSquare[0] && y == calculatedSquare[1]);
                }
                if(!isAlreadyCalculated) {
                    return new int[]{x, y};
                }
                x += squareSize;
            }
            y += squareSize;
        }
        return null;
    }

    public void enregistrerClient(ServiceClient c) throws RemoteException {
        this.serviceClient = c;
        System.out.println("Nouveau client connecté");
    }

    public void enregistrerServiceCalcul(ServiceRaytracing c) throws RemoteException {
        this.serviceCalculs.add(c);
        System.out.println("Nouveau service calcul connecté");
    }
}

