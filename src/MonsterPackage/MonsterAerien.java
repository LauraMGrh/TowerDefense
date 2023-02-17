package MonsterPackage;


import AbstractPackage.Monster;
import UsefulPackage.*;

public class MonsterAerien extends Monster {
	/**
	 * Creee un monstre aerien, avec un vitesse, des points de vie et 
	 * une recompense propre a ce type de monstre
	 * @param p : la position de base du monstre
	 */
	public MonsterAerien(Position p) {
		super(p);
		setSpeed(0.0055);
		setPV(9);
		setRecompense(8);
	}

	/**
	 * Affiche un monstre aerien : une chauve-souris.
	 * Affiche aussi la barre de vie du monstre.
	 */
	public void draw() {
		String malife;
		if(getPV()==9)malife="Images/mafull.png";
		else if(getPV()==8)malife="Images/ma8.png";
		else if(getPV()==7)malife="Images/ma7.png";
		else if(getPV()==6)malife="Images/ma6.png";
		else if(getPV()==5)malife="Images/ma5.png";
		else if(getPV()==4)malife="Images/ma4.png";
		else if(getPV()==3)malife="Images/ma3.png";
		else if(getPV()==2)malife="Images/ma2.png";
		else
			malife = "Images/mt1.png";
		StdDraw.picture(getP().x, getP().y,"Images/chauvesouris.png");
		StdDraw.picture(getP().x, getP().y+0.03, malife);
	}
}
