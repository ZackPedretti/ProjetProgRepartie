
import raytracer.Disp;
import raytracer.Image;
import raytracer.Scene;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public class Central implements ServiceDistributeur {
    ArrayList<ServiceRaytracing> servicesClient;
    Scene scene;
    int width, height;
    HashMap<Image, int[]> images;
    int squareSize;
    ArrayList<int[]> calculatedSquares;

    public Central(Scene scene, int squareSize, int width, int height) {
        this.servicesClient = new ArrayList<ServiceRaytracing>();
        this.images = new HashMap<>();
        this.scene = scene;
        this.squareSize = squareSize;
        this.calculatedSquares = new ArrayList<>();
        this.width = width; this.height = height;
    }

    private int[] getNewSquare(){
        int x, y = 0;
        while(y < height){
            x = 0;
            while(x < width){
                if(!calculatedSquares.contains(new int[]{x, y})) return new int[]{x, y};
                x += squareSize;
            }
            y += squareSize;
        }
        return null;
    }

    public void enregistrerClient(ServiceRaytracing c) throws RemoteException {
        this.servicesClient.add(c);
    }

    public boolean executerRaytracing(ServiceRaytracing c){
        int[] xy = getNewSquare();
        if(xy == null) return false;
        int x0 = xy[0];
        int y0 = xy[1];
        images.put(c.calculerImage(scene, x0, y0, Math.min(squareSize, width - x0), Math.min(squareSize, width - y0)), xy);
        calculatedSquares.add(xy);
        return true;
    }

    public Disp getFinal() {
        Disp disp = new Disp("Image finale", width, height);
        for(Image i : images.keySet()){
            disp.setImage(i, images.get(i)[0], images.get(i)[0]);
        }
        return disp;
    }


}

