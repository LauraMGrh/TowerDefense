package UsefulPackage;

//Pour afficher les int gold et life avec de meilleurs graphismes!
public class Display {
	/** Décompose un int en unités centaines dizaines
	 *@param x un int
	 *@return un tableau de double correspondant aux chiffres des unités, dizaines, centaines de x
	 */
	
	
	public static double[] toP10(int x) {
		double[] chiffres = new double[3];
		for(int i = 0; i<3 ;i++){
			if(i==0) chiffres[2]=x%10;
			else if(i==1) chiffres[1]= Math.floor(x/Math.pow(10,i))%Math.pow(10,i);
			else
				chiffres[0]= Math.floor(x/Math.pow(10,i))%Math.pow(10,i);
		}
		return chiffres;
	}

	/**Prends un int qui sera affiché avec de meilleurs graphismes pour les golds!
	 * @param n 
	 */
	public static void toPictureGold(int n) {
		if(n>999) n=999;
		toP10(n);
		if (n<=0) n=0;
		int centaines = (int) toP10(n)[2];
		int dizaines = (int) toP10(n)[1];
		int unites = (int) toP10(n)[0];
		String cent;
		String diz;
		String unit;

		if(centaines==0) cent="Images/g0.png";
		else if(centaines==1) cent="Images/g1.png";
		else if(centaines==2) cent="Images/g2.png";
		else if(centaines==3) cent="Images/g3.png";
		else if(centaines==4) cent="Images/g4.png";
		else if(centaines==5) cent="Images/g5.png";
		else if(centaines==6) cent="Images/g6.png";
		else if(centaines==7) cent="Images/g7.png";
		else if(centaines==8) cent="Images/g8.png";
		else cent="Images/g9.png";          

		if(dizaines==0) diz="Images/g0.png";
		else if(dizaines==1) diz="Images/g1.png";
		else if(dizaines==2) diz="Images/g2.png";
		else if(dizaines==3) diz="Images/g3.png";
		else if(dizaines==4) diz="Images/g4.png";
		else if(dizaines==5) diz="Images/g5.png";
		else if(dizaines==6) diz="Images/g6.png";
		else if(dizaines==7) diz="Images/g7.png";
		else if(dizaines==8) diz="Images/g8.png";
		else diz="Images/g9.png";

		if(unites==0) unit="Images/g0.png";
		else if(unites==1) unit="Images/g1.png";
		else if(unites==2) unit="Images/g2.png";
		else if(unites==3) unit="Images/g3.png";
		else if(unites==4) unit="Images/g4.png";
		else if(unites==5) unit="Images/g5.png";
		else if(unites==6) unit="Images/g6.png";
		else if(unites==7) unit="Images/g7.png";
		else if(unites==8) unit="Images/g8.png";
		else unit="Images/g9.png";

		StdDraw.picture(0.67, 0.86, cent);
		StdDraw.picture(0.61, 0.86, diz);
		StdDraw.picture(0.55, 0.86, unit);


	}

	/**Prends un int qui sera affiché avec de meilleurs graphismes pour les points de vie!
	 * @param n 
	 */
	public static void toPictureLife(int n) {
		toP10(n);
		if (n<=0) n=0;
		int dizaines = (int) toP10(n)[1];
		int unites = (int) toP10(n)[2];
		String diz;
		String unit;
		if(dizaines==0) diz="Images/l0.png";
		else if(dizaines==1) diz="Images/l1.png";
		else if(dizaines==2) diz="Images/l2.png";
		else if(dizaines==3) diz="Images/l3.png";
		else if(dizaines==4) diz="Images/l4.png";
		else if(dizaines==5) diz="Images/l5.png";
		else if(dizaines==6) diz="Images/l6.png";
		else if(dizaines==7) diz="Images/l7.png";
		else if(dizaines==8) diz="Images/l8.png";
		else diz="Images/l9.png";
		if(unites==0) unit="Images/l0.png";
		else if(unites==1) unit="Images/l1.png";
		else if(unites==2) unit="Images/l2.png";
		else if(unites==3) unit="Images/l3.png";
		else if(unites==4) unit="Images/l4.png";
		else if(unites==5) unit="Images/l5.png";
		else if(unites==6) unit="Images/l6.png";
		else if(unites==7) unit="Images/l7.png";
		else if(unites==8) unit="Images/l8.png";
		else unit="Images/l9.png";
		StdDraw.picture(0.563, 0.957, diz);
		StdDraw.picture(0.62, 0.957, unit);
	}

	/**Prends un int correspondant au numéro de la vague et affiche à quelle vague le joueur se trouve!
	 * @param v 
	 */
	public static void toPicVag(int v) {
		int vag =v-1;
		toP10(vag);
		if (vag<=0) vag=0;
		int dizaines = (int) toP10(vag)[1];
		int unites = (int) toP10(vag)[2];
		String diz;
		String unit;
		if(dizaines==0) diz="Images/v0.png";
		else if(dizaines==1) diz="Images/v1.png";
		else if(dizaines==2) diz="Images/v2.png";
		else if(dizaines==3) diz="Images/v3.png";
		else if(dizaines==4) diz="Images/v4.png";
		else if(dizaines==5) diz="Images/v5.png";
		else if(dizaines==6) diz="Images/v6.png";
		else if(dizaines==7) diz="Images/v7.png";
		else if(dizaines==8) diz="Images/v8.png";
		else diz="Images/v9.png";

		if(unites==0) unit="Images/v0.png";
		else if(unites==1) unit="Images/v1.png";
		else if(unites==2) unit="Images/v2.png";
		else if(unites==3) unit="Images/v3.png";
		else if(unites==4) unit="Images/v4.png";
		else if(unites==5) unit="Images/v5.png";
		else if(unites==6) unit="Images/v6.png";
		else if(unites==7) unit="Images/v7.png";
		else if(unites==8) unit="Images/v8.png";
		else unit="Images/v9.png";


		StdDraw.picture(0.93, 0.958, diz);
		StdDraw.picture(0.965, 0.958, unit);
		StdDraw.picture(0.82, 0.96, "Images/vague.png");

	}
	
	/**Affiche la barre d'avancée de la vague (pourcentage de monstres restants)!
	 * @param nbActuel le nombre de monstre actuellement dans la vague
	 * @param nbTotal le nombre de monstre qu'il doit y avoir au total dans cette vague
	 * @param vague le numéro de la vague
	 */
	public static void pourcentageVague(int nbActuel , int nbTotal, int vague) {
		String im="";
		
		float division = (float)nbActuel/nbTotal;
		float pourcent=division*100;
		if(vague < 20) {
		if(pourcent>=90)im="Images/100p.png";
		else if(pourcent>=80 && pourcent<90)im="Images/90p.png";
		else if(pourcent>=70 && pourcent<80)im="Images/80p.png";
		else if(pourcent>=60 && pourcent<70)im="Images/70p.png";
		else if(pourcent>=50 && pourcent<60)im="Images/60p.png";
		else if(pourcent>=40 && pourcent<50)im="Images/50p.png";
		else if(pourcent>=30 && pourcent<40)im="Images/40p.png";
		else if(pourcent>=20 && pourcent<30)im="Images/30p.png";
		else if(pourcent>=10 && pourcent<20)im="Images/20p.png";
		else if(pourcent>0 && pourcent<10)im="Images/10p.png";
		else if(pourcent<=0)im="Images/0p.png";
		StdDraw.picture(0.5, 0.78,im);
		StdDraw.picture(0.5, 0.75, "Images/monstersalive.png");
		}
	}
}
