package AbstractPackage;


import java.util.LinkedList;

import MainPackage.World;
import TowerPackage.Missile;
import UsefulPackage.*;

public abstract class Tower {
	//nb de cases accessibles autour.
	private int portee;
	//temps de recharge des missiles
	private int recharge;
	private int CompteurRecharge;
	//Coordonnée de la tour
	private Coord c;
	//image de la tour
	private String image;
	//dégats d'un missile
	private int degats;
	//vitesse d'un missile
	private double vitesse;
	//Monster ciblé
	private Monster cible;
	//image des missiles
	private String imageMissile;

	private Missile projectile;

	/**
	 * Crée une tour de coordonné c, sans cible.
	 * @param c : coordonnée de la tour.
	 */
	public Tower(Coord c) {
		this.c = c;
		this.cible=null;
	}

	/**
	 * Vérifie l'égalité entre deux objets de type Tower
	 */
	public boolean equals(Object obj) {
		Tower t = (Tower) obj;
		return c.equals(t.c);
	}

	/**
	 * Affiche la tour.
	 */
	public void draw(double a, double b) {
		StdDraw.picture(World.CoordToPos(c).x, World.CoordToPos(c).y, image, a, b);
	}

	/**
	 * Fonction abstraite qui sera instanciée dans les classes filles pour afficher le monstre sur le plateau de jeu.
	 */
	public abstract void Evolution();

	/**
	 * Foncton abstraite instanciée dans les classes filles qui permet de définir si la tour est évoluée ou non.
	 * @return TRUE si la tour est évoluée, FALSE sinon
	 */
	public abstract boolean isEvolved();

	/**
	 * retourne TRUE si la position la position p est Ã  portée de la tour, FALSE sinon
	 * @param p : la position d'un monstre
	 */
	public boolean inRange(Position p) {// p est la position d'un monstre
		Coord cm = World.PosToCoord(p);
		Coord ct = this.c;
		return (!(Math.abs(cm.x - ct.x) > this.portee || Math.abs(cm.y - ct.y) > this.portee));
	}

	/**
	 * Si la liste est vide, ne fait rien.
	 * sinon, recherche le monstre le plus proche de la tour en vérifiant s'il est atteignable.
	 * si un monstre correspond, ce sera le monstre cible.
	 * @param List : une LinkedList de Monstres.
	 */
	public void scan(LinkedList<Monster> List) {//fct appelÃ©e dans attaque()
		Monster proche = null;
		for (int i = 0; i < List.size(); i++) {
			if (inRange(List.get(i).getP())) {
				if (proche == null)
					proche = List.get(i);
				else {
					if (List.get(i).getP().dist(World.CoordToPos(this.c)) < proche.getP().dist(World.CoordToPos(this.c)))
						proche = List.get(i);
				}
			}
		}
		this.cible = proche;
	}
	/**
	 * appel la fonction scan.
	 * Sera plus définie dans les fonctions filles.
	 * @param List : une LinkedList de Monstres.
	 */
	public void attaque(LinkedList<Monster> List,Position spawn){
		scan(List);
		while (cible!=null && cible.getP().equals(spawn)){
			cible=null;
		}
	}

	//GETTERS & SETTERS
	public int getPortee() {
		return portee;
	}
	public void setPortee(int portee) {
		this.portee = portee;
	}

	public int getRecharge() {
		return recharge;
	}
	public void setRecharge(int recharge) {
		this.recharge = recharge;
	}

	public int getCompteurRecharge() {
		return CompteurRecharge;
	}
	public void setCompteurRecharge(int compteurRecharge) {
		CompteurRecharge = compteurRecharge;
	}

	public Coord getC() {
		return c;
	}
	public void setC(Coord c) {
		this.c = c;
	}

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	public int getDegats() {
		return degats;
	}
	public void setDegats(int degats) {
		this.degats = degats;
	}

	public double getVitesse() {
		return vitesse;
	}
	public void setVitesse(double vitesse) {
		this.vitesse = vitesse;
	}

	public Monster getCible() {
		return cible;
	}
	public void setCible(Monster cible) {
		this.cible = cible;
	}

	public String getImageMissile() {
		return imageMissile;
	}
	public void setImageMissile(String imageMissile) {
		this.imageMissile = imageMissile;
	}

	public Missile getProjectile() {
		return projectile;
	}

	public void setProjectile(Missile projectile) {
		this.projectile = projectile;
	}


}
