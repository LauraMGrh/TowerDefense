package AbstractPackage;

import java.util.LinkedList;
import java.util.List;

import UsefulPackage.*;

public abstract class Monster {
	// Position du monstre à l'instant t
	private Position p;
	// Vitesse du monstre
	private double speed;
	// Position du monstre à l'instant t+1
	private Position nextP;
	// Boolean pour savoir si le monstre a atteint le chateau du joueur
	boolean reached;
	//Vie du monstre
	private int PV;
	//Or gagne par le joueur quand le monstre est tue
	private int recompense;
	//Temps d'attente du monstre avant de sortir
	private int delai;
	//Liste des cases deja utilisées par le monstre
	private List<Coord> caseUsed = new LinkedList<Coord>();

	/**
	 * Crée un monstre de position p avec une liste vide de cases utilisées.
	 * @param p : position de base du monstre
	 */
	public Monster(Position p) {
		this.p = p;
		this.nextP = new Position(p);
		caseUsed = new LinkedList<Coord>();

	}

	/**
	 * Vérifie l'égalité entre deux objets de type Monster
	 */
	public boolean equals(Object obj) {
		Monster m=(Monster)obj;
		return p == m.p;
	}

	/**
	 * Déplace le monstre en fonction de sa vitesse sur l'axe des x et des y et de sa prochaine position.
	 */
	public void move() {
		// Mesure sur quel axe le monstre se dirige.
		double dx = nextP.x - p.x;
		double dy = nextP.y - p.y;
		if (dy + dx != 0) {
			// Mesure la distance Ã  laquelle le monstre Ã  pu se dÃ©placer.
			double ratioX = dx / (Math.abs(dx) + Math.abs(dy));
			double ratioY = dy / (Math.abs(dx) + Math.abs(dy));
			p.x += ratioX * speed;
			p.y += ratioY * speed;
		}
	}

	/**
	 * Actualise la position p et nextP du monstre en faisant appel à la fonction move().
	 */
	public void update() {
		if (delai >0) delai-=1;
		else move();
		if (caseUsed.size()>1)	draw();
	}

	/**
	 * Fonction abstraite qui sera instanciée dans les classes filles pour afficher le monstre sur le plateau de jeu.
	 */
	public abstract void draw();

	// GETTERS & SETTERS:
	public Position getP() {
		return p;
	}
	public void setP(Position p) {
		this.p = p;
	}

	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getPV() {
		return PV;
	}
	public void setPV(int pV) {
		PV = pV;
	}

	public int getDelai() {
		return delai;
	}
	public void setDelai(int delai) {
		this.delai = delai;
	}

	public int getRecompense() {
		return recompense;
	}
	public void setRecompense(int recompense) {
		this.recompense = recompense;
	}

	public Position getNextP() {
		return nextP;
	}
	public void setNextP(Position nextP) {
		this.nextP = nextP;
	}

	public boolean isReached() {
		return reached;
	}
	public void setReached(boolean reached) {
		this.reached = reached;
	}

	public List<Coord> getCaseUsed() {
		return caseUsed;
	}
	public void setCaseUsed(List<Coord> caseUsed) {
		this.caseUsed = caseUsed;
	}


}
