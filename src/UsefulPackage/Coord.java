package UsefulPackage;

public class Coord {
	public int x; //abscisse de la case
	public int y; //ordonnée de la case

	/**
	 * Crée une coordonnee d'absisse x et d'ordonnée y
	 * @param x ,@param y
	 */
	public Coord(int x, int y){
		this.x = x;
		this.y = y;
	}

	/**
	 * Vérifie l'égalite entre deux objets de type Coord
	 */
	public boolean equals(Object obj) {
		Coord c=(Coord)obj;
		return x == c.x && y == c.y;
	}

	/**
	 * Retourne la position de la case sur l'axe des x et des y
	 */
	public String toString() {
		return "(" + Integer.toString(x) + "," + Integer.toString(y) + ")";
	}

}
