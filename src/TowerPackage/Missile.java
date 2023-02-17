package TowerPackage;

import MainPackage.World;
import UsefulPackage.*;

public class Missile {
	private Position pActuelle;
	private Position pCible;
	private double vitesseMissile;
	private String image;

	/**
	 * Crée un missile qui partira de la tour jusqu'au monstre cible.
	 * @param ct : coordonnée de la tour de départ
	 * @param vitesse du missile
	 * @param image du missile
	 * @param pm : position du monstre cible
	 */
	public Missile(Coord ct , double vitesse, String image, Position pm){
		this.pActuelle=new Position (World.CoordToPos(ct).x, World.CoordToPos(ct).y+0.03);
		this.pCible=pm;
		this.vitesseMissile=vitesse;
		this.image=image;
	}

	/**
	 * Actualise la position du missile en fonction de sa position cible.
	 */
	public void deplacement() {
		// Mesure sur quel axe le missile se dirige.
		double dx = pCible.x - pActuelle.x;
		double dy = pCible.y - pActuelle.y;
		if (pActuelle!=pCible) {
			// Mesure la distance Ã  laquelle le missile Ã  pu se dÃ©placer.
			double ratioX = dx / (Math.abs(dx) + Math.abs(dy));
			double ratioY = dy / (Math.abs(dx) + Math.abs(dy));
			pActuelle.x += ratioX * this.vitesseMissile;
			pActuelle.y += ratioY * this.vitesseMissile;
			//Au cas ou le missile dÃ©passe le monstre:
			if(dx<0 && pActuelle.x<pCible.x) {
				pActuelle.x = pCible.x;
			}if(dx>0 && pActuelle.x>pCible.x) {
				pActuelle.x = pCible.x;
			}if(dy<0 && pActuelle.y<pCible.y) {
				pActuelle.y = pCible.y;
			}if(dy>0 && pActuelle.y>pCible.y) {
				pActuelle.y = pCible.y;
			}
		}
	}
	/**
	 * fait appel à la fonction deplacement() ci-dessus et drawMissile() ci-dessous.
	 */
	public void update() {
		drawMissile();
		deplacement();
	}

	/**
	 * affiche le missile
	 */
	private void drawMissile(){
		StdDraw.picture(pActuelle.x, pActuelle.y,this.image);
	}

	//GETTERS & SETTERS
	public Position getpActuelle() {
		return pActuelle;
	}
	public void setpActuelle(Position pActuelle) {
		this.pActuelle = pActuelle;
	}
	
	public Position getpCible() {
		return pCible;
	}
	public void setpCible(Position pCible) {
		this.pCible = pCible;
	}
}
