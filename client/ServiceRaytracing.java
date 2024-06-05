import raytracer.Image;
import raytracer.Scene;

import java.io.Serializable;
import java.rmi.Remote;

public interface ServiceRaytracing extends Remote, Serializable{
    public Image calculerImage(Scene scene, int x0, int y0, int width, int height);
}