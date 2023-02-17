package TowerPackage;

import java.util.LinkedList;

import AbstractPackage.*;
import UsefulPackage.*;

public class ArrowTower extends Tower{
	/**
	 * Cr�e une tour de type Tour d'archers avec une port�e, un d�lai de recharge, des d�gats et
	 * une vitesse de missiles propre � ce type de tours.
	 */
	public ArrowTower(Coord c){
		super(c);
		setPortee(1);
		setRecharge(30);
		setCompteurRecharge(0);
		setImage("Images/Archers.png");
		setDegats(2);
		setVitesse(0.05);
		setImageMissile("Images/arrow.png");
		setProjectile(new Missile(getC(), getVitesse(), getImageMissile(), null));
	}
	/**
	 * Change certains �l�ments de la tour :
	 * la port�e de tir et les d�gats sont augment�s, le temps de recherge diminu� et l'image de la tour change.
	 */
	public void Evolution(){
		setPortee(2);
		setRecharge(25);
		setDegats(3);
		setImage("Images/ArchersUP.png");
	}

	/**
	 * d�finie si la tour est �volu�e ou non.
	 * @return TRUE si la tour est �volu�e, FALSE sinon
	 */
	public boolean isEvolved(){
		return getRecharge()==17;
	}

	/**
	 * Si la tour n'est pas recharg�e, le compteur baisse.
	 * Si la tour est prête à tirer, et si une cible � �t� trouv��, cr�e un missile qui part de 
	 * la tour pour arriver � la position du monstre cible. 
	 * Le monstre perd des points de vie et le compteur de recharge de la tour reprend.
	 * @param List : une LinkedList de Monstres.
	 */
	@Override
	public void attaque(LinkedList<Monster> List,Position spawn) {
		if (getCompteurRecharge() == 0) {
			if (getProjectile().getpCible() == null) {
				super.attaque(List,spawn);
				if (getCible() != null) {
					Monster monstreCible = getCible();
					getProjectile().setpCible(new Position(monstreCible.getP()));
					if (!getProjectile().getpActuelle().equals(getProjectile().getpCible())) {
						getProjectile().update();
					} else {
						monstreCible.setPV(monstreCible.getPV() - getDegats());
						setCompteurRecharge(getRecharge());
						setProjectile(new Missile(getC(), getVitesse(), getImageMissile(), null));
						if (monstreCible.getPV()<=0) setCible(null);
					}
				}
			} else {
				Monster monstreCible = getCible();
				if (!getProjectile().getpActuelle().equals(getProjectile().getpCible())) {
					getProjectile().update();
				} else {
					monstreCible.setPV(monstreCible.getPV() - getDegats());
					setCompteurRecharge(getRecharge());
					setProjectile(new Missile(getC(), getVitesse(), getImageMissile(), null));
					if (monstreCible.getPV()<=0) setCible(null);
				}
			}
		} else
			setCompteurRecharge(getCompteurRecharge() - 1);
	}



}
