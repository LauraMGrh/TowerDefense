package TowerPackage;

import java.util.LinkedList;

import AbstractPackage.*;
import MainPackage.World;
import UsefulPackage.*;

public class LaserTower extends Tower {
	/**
	 * Crée une tour de type tour laser avec une portee, un délai de recharge, des degats propres a ce type de tours.
	 */
	public LaserTower(Coord c){
		super(c);
		setPortee(1);
		setRecharge(15);
		setCompteurRecharge(0);
		setImage("Images/laser.png");
		setDegats(2);
	}
	/**
	 * Change certains elements de la tour :
	 * la portee de tir et les degats sont augmentés, le temps de recharge diminue et l'image de la tour change.
	 */
	public void Evolution(){
		setRecharge(5);
		setDegats(1);
		setImage("Images/laserevo.png");
	}

	/**
	 * définie si la tour est évoluée ou non.
	 * @return TRUE si la tour est évoluée, FALSE sinon
	 */
	public boolean isEvolved(){
		return getRecharge()==0;
	}

	/**
	 * Dessine un laser entre la tour et le monstre
	 * @param pm : la position du monstre cible.
	 */
	public void drawMissile(Position pm){//pm est la position du monstre
		Position pt=World.CoordToPos(getC());
		StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
		StdDraw.setPenRadius(0.01);
		StdDraw.line(pt.x, pt.y+0.03, pm.x, pm.y);
	}


	/**
	 * Si la tour n'est pas rechargée, le compteur baisse.
	 * Si la tour est prête à tirer, et si une cible a ete trouvée, on tire un laser sur le monstre
	 * Le monstre pert des points de vie et le compteur de recharge de la tour reprend.
	 * @param List : une LinkedList de monstres.
	 */
	@Override
	public void attaque(LinkedList<Monster> List,Position spawn){
		super.attaque(List,spawn);
		if (getCompteurRecharge()==0){
			if (getCible()!=null) {
				drawMissile(getCible().getP());
				getCible().setPV(getCible().getPV()-getDegats());
				setCompteurRecharge(getRecharge());
			}
		}else setCompteurRecharge(getCompteurRecharge()-1);
	}

}
