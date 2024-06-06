import raytracer.Disp;
import raytracer.Image;

public class Client implements ServiceClient{

    private Disp disp;
    ServiceDistributeur distributeur;

    public Client(ServiceDistributeur distributeur, int width, int height){
        this.distributeur = distributeur;
        disp = new Disp("rayTracing", width, height);
    }
    @Override
    public void afficherImage(Image image, int x, int y) {
        disp.setImage(image, x, y);
    }
}
