package MonsterPackage;

import AbstractPackage.Monster;
import UsefulPackage.*;


public class MonsterBoss extends Monster {
	/**
	 * Crée un monstre boss, avec un vitesse lente, et beaucoup de points de vie 
	 * @param p : la position de base du monstre
	 */
	public MonsterBoss(Position p) {
		super(p);
		setSpeed(0.005);
		setPV(500);
		setRecompense(500);
		setDelai(150);
	}

	/**
	 * Affichage du monstre boss : un corona-virus.
	 * Affiche aussi la barre de vie du monstre.
	 */
	public void draw() {
		String blife;
		if(getPV()>475)blife="Images/blfull.png";
		else if(getPV()>450 && getPV()<=475)blife="Images/bl19.png";
		else if(getPV()>425 && getPV()<=450)blife="Images/bl18.png";
		else if(getPV()>400 && getPV()<=425)blife="Images/bl17.png";
		else if(getPV()>375 && getPV()<=400)blife="Images/bl16.png";
		else if(getPV()>350 && getPV()<=375)blife="Images/bl15.png";
		else if(getPV()>325 && getPV()<=350)blife="Images/bl14.png";
		else if(getPV()>300 && getPV()<=325)blife="Images/bl13.png";
		else if(getPV()>275 && getPV()<=300)blife="Images/bl12.png";
		else if(getPV()>250 && getPV()<=275)blife="Images/bl11.png";
		else if(getPV()>225 && getPV()<=250)blife="Images/bl10.png";
		else if(getPV()>200 && getPV()<=225)blife="Images/bl9.png";
		else if(getPV()>175 && getPV()<=200)blife="Images/bl8.png";
		else if(getPV()>150 && getPV()<=175)blife="Images/bl7.png";
		else if(getPV()>125 && getPV()<=150)blife="Images/bl6.png";
		else if(getPV()>100 && getPV()<=125)blife="Images/bl5.png";
		else if(getPV()>75 && getPV()<=100)blife="Images/bl4.png";
		else if(getPV()>50 && getPV()<=75)blife="Images/bl3.png";
		else if(getPV()>25 && getPV()<=50)blife="Images/bl2.png";
		else if(getPV()>0 && getPV()<=25)blife = "Images/bl1.png";
		else
			blife = "Images/bl0.png";
		StdDraw.picture(getP().x, getP().y,"Images/Boss.png");
		StdDraw.picture(0.5, 0.75, blife);
	}
}
