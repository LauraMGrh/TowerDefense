package TowerPackage;

import AbstractPackage.*;
import MonsterPackage.MonsterAerien;
import UsefulPackage.*;
import MainPackage.World;

import java.util.ArrayList;
import java.util.LinkedList;


public class BombTower extends Tower {
	/**
	 * Cr�e une tour de type Tour de bombes avec une port�e, un d�lai de recharge, des d�gats et
	 * une vitesse de missiles propre � ce type de tours.
	 */
	public BombTower(Coord c){
		super(c);
		setPortee(1);
		setRecharge(60);
		setCompteurRecharge(0);
		setImage("Images/canon2.png");
		setDegats(3);
		setVitesse(0.03);
		setImageMissile("Images/bombe.png");
		setProjectile(new Missile(getC(), getVitesse(), getImageMissile(), null));
	}

	/**
	 * Change certains �l�ments de la tour :
	 * la port�e de tir et les d�gats sont augment�s, le temps de recharge est diminu� et l'image de la tour change.
	 */
	public void Evolution(){
		setPortee(2);
		setRecharge(45);
		setDegats(4);
		setImage("Images/canonUP2.png");
	}
	/**
	 * d�finie si la tour est �volu�e ou non.
	 * @return TRUE si la tour est �volu�e, FALSE sinon
	 */
	public boolean isEvolved(){
		return getRecharge()==22;
	}

	/**
	 * Fonction similaire � celle instanci�e dans la class M�re, mais ne prend en compte que les listes 
	 * de monstres terrestres.
	 * Une tour de bombe de peut pas tirer sur un monstre a�rien.
	 */
	@Override
	public void scan(LinkedList<Monster> List) {
		Monster proche = null;
		if (List.isEmpty() || List.get(0) instanceof MonsterAerien)
			return;
		else {
			for (int i = 0; i < List.size(); i++) {
				if (inRange(List.get(i).getP())) {
					if (proche == null)
						proche = List.get(i);
					else {
						if (List.get(i).getP().dist(World.CoordToPos(getC())) < proche.getP().dist(World.CoordToPos(getC())))
							proche = List.get(i);
					}
				}
			}
			setCible(proche);
		}
	}

	/**
	 * Retourne une liste des coordonn�es des cases affect�es par la bombe.
	 * @param pm : la position du monstre cible
	 * @return une ArrayList de Coordonn�s.
	 */
	public ArrayList<Coord> bombeEffet (Position pm) {// p est la position d'un monstre
		ArrayList<Coord> liste= new ArrayList<Coord>();
		Coord cm = World.PosToCoord(pm);
		liste.add(cm);
		liste.add(new Coord(cm.x,cm.y-1));
		liste.add(new Coord(cm.x,cm.y+1));
		liste.add(new Coord(cm.x+1,cm.y));
		liste.add(new Coord(cm.x-1,cm.y));
		liste.add(new Coord(cm.x,cm.y-2));
		liste.add(new Coord(cm.x,cm.y+2));
		liste.add(new Coord(cm.x+2,cm.y));
		liste.add(new Coord(cm.x-2,cm.y));
		liste.add(new Coord(cm.x+1,cm.y+1));
		liste.add(new Coord(cm.x+1,cm.y-1));
		liste.add(new Coord(cm.x-1,cm.y+1));
		liste.add(new Coord(cm.x-1,cm.y-1));
		return liste;
	}

	/**
	 * Si la tour n'est pas recharg�e, le compteur baisse.
	 * Si la tour est pr�te à tirer, et si une cible � �t� trouv�e : cr�ee un missile qui part de 
	 * la tour pour arriver sur la case du monstre cible.
	 * Tout les monstre qui se trouvent sur cette case vont perdre des points de vie et 
	 * le compteur de recharge de la tour reprend.
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
						for (int i = 0; i < List.size(); i++) {
							if (bombeEffet(getCible().getP()).contains(World.PosToCoord(List.get(i).getP())))
								List.get(i).setPV(List.get(i).getPV() - getDegats());
						}
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
					for (int i = 0; i < List.size(); i++) {
						if (bombeEffet(monstreCible.getP()).contains(World.PosToCoord(List.get(i).getP())))
							List.get(i).setPV(List.get(i).getPV() - getDegats());
					}
					setCompteurRecharge(getRecharge());
					setProjectile(new Missile(getC(), getVitesse(), getImageMissile(), null));
					if (monstreCible.getPV()<=0) setCible(null);
				}
			}
		} else
			setCompteurRecharge(getCompteurRecharge() - 1);
	}
}

