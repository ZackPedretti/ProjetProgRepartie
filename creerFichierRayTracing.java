import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe permettant de générer un fichier de scène pour le raytracer
 */
public class creerFichierRayTracing {

    public static void main(String[] args) throws IOException {
        // on crée un fichier grâce à un BufferedWriter
        BufferedWriter writer = new BufferedWriter(new FileWriter("cool.txt"));

        // on définit un matériau au début
        writer.write(creerMateriau());

        // on crée des objets aléatoires parmi ceux-ci dessous
        String[] types = {"sphere","polygone","materiau","boite"};

        // on utilise un boolean pour éviter de créer plusieurs matériaux à la suite
        boolean mat = true;

        // à chaque itération, on crée un objet aléatoire
        int iteration = (int) Math.round(Math.random()*20);
        if (iteration < 10) {
            iteration = 7;
        }

        for (int i = 0; i < iteration; i++) {
            String type = types[(int) Math.round(Math.random()*3)];
            switch (type) {
                case "sphere":
                    writer.write(creerSphere());
                    writer.newLine();
                    mat = false;
                    break;
                case "polygone":
                    writer.write(creerPolygone());
                    writer.newLine();
                    mat = false;
                    break;
                case "materiau":
                    if (!mat) {
                        writer.write(creerMateriau());
                        mat = true;
                    } else {
                        i--;
                    }
                    break;
                case "boite":
                    writer.write(creerBoite());
                    writer.newLine();
                    mat = false;
                    break;
            }
        }

        // on finit par une source unique et on clôt le fichier
        writer.write("source 0.25 3.0 -3.0  1.0 1.0 1.0");
        writer.close();

    }

    private static String creerMateriau(){
        String s = new String("materiau ");
        for (int i = 0; i < 9; i++) {
            s += randomArrondi(1) - randomArrondi(1) + " ";
        }
        s += randomArrondi(100) - randomArrondi(100) + "\n";
        return s;
    }

    private static String creerSphere(){
        String s = new String("sphere ");
        for (int i = 0; i < 4; i++) {
            s += randomArrondi(2) - randomArrondi(2) + " ";
        }
        return s + "\n";
    }

    private static String creerSource(){
        String s = new String("source ");
        for (int i = 0; i < 6; i++) {
            s += randomArrondi(1) - randomArrondi(1) + " ";
        }
        return s + "\n";
    }

    private static String creerPolygone(){
        String s = new String("polygone ");
        int cotes = (int) Math.round(Math.random()*8);
        if (cotes < 3) {
            cotes = 3;
        }
        s += cotes + " ";
        for (int i = 0; i < 3*cotes; i++) {
            s += randomArrondi(1) - randomArrondi(1) + " ";
        }
        return s + "\n";
    }

    /**
     * Crée une boite aléatoirement
     * @return des lignes de texte pour le ray tracing
     */
    // On utilise ces lignes dans le fichier fourni afin de faire une boite en regardant les coordonnées qui change
    //polygone 4  0.75 0.5 -1     0.75 0.5 -1.5     1.0 0.5 -1.5      1.0 0.5 -1.0
    //polygone 4  0.75 0.75 -1    1.0 0.75 -1       1.0 0.75 -1.5     0.75 0.75 -1.5
    //polygone 4  0.75 0.5 -1     0.75 0.75 -1.0    0.75 0.75 -1.5    0.75 0.5 -1.5
    //polygone 4  1.0 0.5 -1      1.0 0.5 -1.5      1.0 0.75 -1.5     1.0 0.75 -1
    private static String creerBoite(){
        String s = new String("polygone 4 ");
        double[] coords = new double[12];
        // Premier polygone aléatoire
        for (int i = 0; i < 12; i++) {
            coords[i] = randomArrondi(1) - randomArrondi(1);
            s += coords[i] + " ";
        }
        double taille = randomArrondi(1);
        // On crée les 3 autres polygones en aditionnant ou soustrayant la taille
        s += "\npolygone 4 " + coords[0] + " " + (coords[1]+taille) + " " + coords[2]
                + " " + (coords[3]+taille) + " " + (coords[4]+taille) + " " + (coords[5]+taille*2)
                + " " + coords[6] + " " + (coords[7]+taille) + (coords[8]+taille)
                + " " + (coords[9]-taille) + " " + (coords[10]+taille) + " " + (coords[11]-taille*2) + "\n";

        s += "polygone 4 " + coords[0] + " " + coords[1] + " " + coords[2]
                + " " + coords[3] + " " + (coords[4]+taille) + " " + (coords[5]+taille*2)
                + " " + (coords[6]-taille)  + " " + (coords[7]+taille) + coords[8]
                + " " + (coords[9]-taille) + " " + coords[10] + " " + (coords[11]-taille*2) + "\n";

        s += "polygone 4 " + (coords[0]+taille) + " " + coords[1] + " " + coords[2]
                + " " + (coords[3]+taille) + " " + coords[4] + " " + coords[5]
                + " " + coords[6] + " " + (coords[7]+taille)  + coords[8]
                + " " + coords[9] + " " + (coords[10]+taille)  + " " + coords[11] + "\n";

        return s;
    }

    private static double randomArrondi(int multi){
        return ((int)Math.round(Math.random()*multi*100))/100.0;
    }
}
