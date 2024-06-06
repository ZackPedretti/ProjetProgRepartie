import raytracer.Disp;
import raytracer.Image;
import raytracer.Scene;

public class Client implements ServiceClient{
    String nomFichierDessin;
    int largeurDessin;
    int hauteurDessin;
    Disp disp;
    Scene scene;
    ServiceDistributeur distributeur;
    int squareSize;

    public Client(ServiceDistributeur distributeur, String fichierDessin, int largeur, int hauteur, int squareSize){
        this.nomFichierDessin = fichierDessin;
        this.largeurDessin = largeur;
        this.hauteurDessin = hauteur;
        this.distributeur = distributeur;
        this.disp = new Disp("RayTracing", largeur, hauteur);
        this.scene =  new Scene(fichierDessin, largeur, hauteur);
        this.squareSize = squareSize;
    }
    @Override
    public void afficherImage(Image image, int x, int y) {
        this.disp.setImage(image, x, y);
    }

    public Scene getScene() {
        return scene;
    }

    public int getLargeurDessin() {
        return largeurDessin;
    }

    public int getHauteurDessin() {
        return hauteurDessin;
    }

    public int getSquareSize(){
        return squareSize;
    }
}
