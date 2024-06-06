
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
    boolean calculating;

    public Central() {
        this.serviceCalculs = new ArrayList<>();
        calculating = false;
    }

    public void calculateImage() throws RemoteException {
        this.images = new HashMap<>();
        this.calculatedSquares = new ArrayList<>();
        this.scene = serviceClient.getScene();
        this.squareSize = serviceClient.getSquareSize();
        this.width = serviceClient.getLargeurDessin(); this.height = serviceClient.getHauteurDessin();
        calculating = true;

        for (ServiceRaytracing serviceRaytracing:
                serviceCalculs) {
            serviceRaytracing.start();
        }
    }

    private int[] getNewSquare() {
        if(!calculating) return null;
        int x, y = 0;
        while(y < height){
            x = 0;
            while(x < width){
                boolean isCalculated = false;
                for (int[] calculatedSquare : calculatedSquares) {
                    isCalculated = isCalculated || (x == calculatedSquare[0] && y == calculatedSquare[1]);
                }
                if(!isCalculated) return new int[]{x, y};
                x += squareSize;
            }
            y += squareSize;
        }
        calculating = false;
        return null;
    }

    public void enregistrerClient(ServiceClient c) throws RemoteException {
        this.serviceClient = c;
    }

    public void enregistrerServiceCalcul(ServiceRaytracing c) throws RemoteException {
        this.serviceCalculs.add(c);
    }

    public boolean executerRaytracing(ServiceRaytracing c) throws RemoteException {
        if(!calculating) return false;
        int[] xy = getNewSquare();
        if(xy == null) return false;
        int x0 = xy[0];
        int y0 = xy[1];
        Thread thread = new Thread()  {
            public void run() {
                try {
                    Image image = c.calculerImage(scene, x0, y0, Math.min(squareSize, width - x0), Math.min(squareSize, width - y0));
                    images.put(image, xy);
                    serviceClient.afficherImage(image, xy[0], xy[1]);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        }) ;
        thread.start();
        calculatedSquares.add(xy);
        return true;
    }
}

