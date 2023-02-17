package MainPackage;

public class Main {

  public static void main(String[] args) {
    int width = 996;
    int height = 996;
    int nbSquareX = 12;
    int nbSquareY = 12;
    int startX = 1;
    int startY = 10;

    World w = new World(width, height, nbSquareX, nbSquareY, startX, startY);

    // Lancement de la boucle principale du jeu
    w.run();
  }
}
