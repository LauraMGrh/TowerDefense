package MonsterPackage;

import AbstractPackage.Monster;

import UsefulPackage.*;

public class MonsterTerrestre extends Monster {
	/**
	 * Cr�e un monstre terrestre, avec une vitesse, des points de vie et 
	 * une r�compense propre � ce type de monstre
	 * @param p : la position de base du monstre
	 */
	public MonsterTerrestre(Position p) {
		super(p);
		setSpeed(0.0045);
		setPV(12);
		setRecompense(10);
	}


	/**
	 * Affiche un monstre terrestre : un pangolin
	 * Affiche aussi la barre de vie du monstre.
	 */
	public void draw() {
		String mtlife;
		if(getPV()==12)mtlife="Images/mtfull.png";
		else if(getPV()==11)mtlife="Images/mt11.png";
		else if(getPV()==10)mtlife="Images/mt10.png";
		else if(getPV()==9)mtlife="Images/mt9.png";
		else if(getPV()==8)mtlife="Images/mt8.png";
		else if(getPV()==7)mtlife="Images/mt7.png";
		else if(getPV()==6)mtlife="Images/mt6.png";
		else if(getPV()==5)mtlife="Images/mt5.png";
		else if(getPV()==4)mtlife="Images/mt4.png";
		else if(getPV()==3)mtlife="Images/mt3.png";
		else if(getPV()==2)mtlife="Images/mt2.png";
		else
			mtlife = "Images/mt1.png";

		StdDraw.picture(getP().x, getP().y,"Images/minipangolin.png");
		StdDraw.picture(getP().x+0.03, getP().y+0.05, mtlife);

	}
}
