package MainPackage;

import AbstractPackage.*;
import MonsterPackage.*;
import TowerPackage.*;
import UsefulPackage.*;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;


public class World {
	// l'ensemble des monstres et tours
	LinkedList<Monster> monsters = new LinkedList<Monster>();
	List<Tower> tow = new LinkedList<Tower>();

	// Position par laquelle les monstres vont venir et position du chateau de d�fense
	Position spawn;
	Position chateau;

	// Information sur la taille du plateau de jeu
	int width;
	int height;
	static int nbSquareX;
	static int nbSquareY;
	double squareWidth;
	double squareHeight;
	int[][] fond;

	//numéro de la vague de monstre actuelle 
	static int vague=0;

	//nombre de base monstres
	static int nombremonsters;

	// Nombre de points de vie et d'or du joueur
	int life = 20;
	int gold = 200;


	// Commande sur laquelle le joueur appuie (sur le clavier)
	char key;

	// Condition pour terminer,commencer et mettre en pause la partie et savoir si on est dans tel ou tel menu...
	boolean end = false;
	boolean start = false;
	boolean pause= false;
	boolean menu = false;
	boolean win = false;
	boolean exit = false;
	boolean restart = false;

	/**
	 * Initialisation du monde en fonction de la largeur, la hauteur et le nombre de
	 * cases donn�es
	 * 
	 * @param width
	 * @param height
	 * @param nbSquareX
	 * @param nbSquareY
	 * @param startSquareX
	 * @param startSquareY
	 */
	public World(int width, int height, int nbSquareX, int nbSquareY, int startSquareX, int startSquareY) {
		this.width = width;
		this.height = height;
		World.nbSquareX = nbSquareX;
		World.nbSquareY = nbSquareY;
		squareWidth = (double) 1 / nbSquareX;
		squareHeight = (double) 1 / nbSquareY;
		spawn = new Position(startSquareX * squareWidth + squareWidth / 2,
				startSquareY * squareHeight + squareHeight / 2);
		StdDraw.setCanvasSize(width, height);
		StdDraw.enableDoubleBuffering();
		monsters=new LinkedList<Monster>();
		this.tow = new LinkedList<Tower>();
		this.fond = new int[nbSquareX][nbSquareY];
		for (int i = 0; i < nbSquareY; i++)
			for (int j = 0; j < nbSquareX; j++)
				fond[j][i] = 0;
	}

	/**
	 * D�finit le d�cors du plateau de jeu.
	 */
	public void drawBackground() {
		StdDraw.picture(0.5, 0.5, "Images/back.jpg");
	}

	/**
	 * Initialise le chemin sur la position du point de d�part des monstres. 
	 * Cette fonction permet d'afficher une route qui sera diff�rente du d�cors.
	 */
	public void drawPath() {
		// CONSTRUCTION du chemin,spawn,chateau
		// chateau
		fond[10][10] = 2;
		// spawn
		fond[1][10] = 3;
		// chemin
		for (int k = 1; k < nbSquareY - 1; k++)
			fond[0][k] = 1;
		for (int k = 1; k < nbSquareX - 8; k++)
			fond[k][1] = 1;
		for (int k = 1; k < nbSquareY - 5; k++)
			fond[nbSquareX - 9][k] = 1;
		for (int k = 3; k < nbSquareX - 4; k++)
			fond[k][6] = 1;
		for (int k = 1; k < nbSquareY - 5; k++)
			fond[nbSquareX - 5][k] = 1;
		for (int k = 8; k < nbSquareX - 1; k++)
			fond[k][1] = 1;
		for (int k = 1; k < nbSquareY - 2; k++)
			fond[nbSquareX - 2][k] = 1;
		// Cases pour empecher le placement des tours
		fond[2][9] = fond[2][10] = fond[1][9] = 4;// autour du spawn
		fond[10][11] = fond[11][11] = fond[11][10] = fond[9][11] = fond[9][10] = fond[9][9] = fond[11][9] = 4;// autour
		// du
		// chateau

		// AFFICHAGE du chemin,spawn,chateau
		// 0: Herbe / 1: chemin / 2:Chateau / 3:Spawn /4:RIEN autour des batiments
		for (int i = 0; i < nbSquareX; i++)
			for (int j = 0; j < nbSquareY; j++)
				if (fond[i][j] == 1)
					StdDraw.picture(i * squareWidth + squareWidth / 2, j * squareHeight + squareHeight / 2, "Images/way.jpg");
				else if (fond[i][j] == 2)
					StdDraw.picture(i * squareWidth + squareWidth / 2, j * squareHeight + squareHeight / 2,
							"Images/hopital.png");
				else if (fond[i][j] == 3)
					StdDraw.picture(i * squareWidth + squareWidth / 2, j * squareHeight + squareHeight / 2,
							"Images/spawn.png");

	}
	
	/**
	 * Fonction qui cherche et change la prochaine position nextP du monstre en fonction des cases d�ja utilis�es
	 * par celui-ci.
	 * @param m : un monstre
	 * @param i : l'it�rateur des monstres
	 */
	public void caseSuivante(Monster m,Iterator<Monster> i) {
		if (m instanceof MonsterBoss && m.getPV()<=0) win=true;
		if (m.getPV()<=0) {
			gold+=m.getRecompense();
			i.remove();
			monsters.remove(m);
			return;
		}
		Coord c=PosToCoord(m.getP());
		int x = c.x;
		int y = c.y;
		if (caseExist(x, y - 1) && !m.getCaseUsed().contains(new Coord(x, y-1)) && fond[x][y - 1] == 1) {
			y-=1;
		} else if (caseExist(x + 1, y)  && !m.getCaseUsed().contains(new Coord(x+1, y)) && fond[x + 1][y] == 1) {
			x+=1;
		} else if (caseExist(x, y + 1) && !m.getCaseUsed().contains(new Coord(x, y+1)) && fond[x][y + 1] == 1) {
			y+=1;
		} else if (caseExist(x-1, y) && !m.getCaseUsed().contains(new Coord(x-1, y)) && fond[x-1][y] == 1){
			x-=1;
		}else if(caseExist(x, y+1) && fond[x][y+1] == 2){
			m.setReached(true);
			if (m instanceof MonsterBoss) life =0;
			i.remove();
			monsters.remove(m);
			life-=1;

		}else return;
		Coord elmt=new Coord(x, y);
		m.getCaseUsed().add(elmt);
		m.setNextP(CoordToPos(elmt));

	}

	/**
	 * Vérifie si une case existe bien sur le plateau
	 * @param x,y : les coordonn�es d'une case
	 * @return TRUE si la case existe, FALSE sinon
	 */
	public static boolean caseExist(int x, int y) {
		return (x >= 0 && x < nbSquareX - 1) && (y >= 0 && y < nbSquareY-1);
	}

	/**
	 * Renvoie le coordonn� de la case du plateau correspondant � la position p
	 * @param p : une position
	 * @return le coordonn� correspondant
	 */
	public static Coord PosToCoord(Position p) {
		int x = (int) Math.floor(p.x * nbSquareX);
		int y = (int) Math.floor(p.y * nbSquareY);
		return new Coord(x, y);
	}

	/**
	 * Renvoie la position du milieu de la case de coordonn�es c
	 * @param c : une coordonn�e
	 * @return la position correspondante
	 */
	public static Position CoordToPos(Coord c) {
		Double x = (c.x + 0.5) / nbSquareX;
		Double y = (c.y + 0.5) / nbSquareY;
		return new Position(x, y);
	}

	/**
	 * retourne le nombre de monstres dans la vague actuelle.
	 */
	public int nombreBase(){
		if (vague % 2 == 1) // vague de monstre terrestre
			nombremonsters = 4 + 2 * (vague - 1);
		else
			nombremonsters = 3 + 2 * (vague - 1);
		return nombremonsters;
	}

	/**
	 * Remplie la liste 'monsters' selon le numero de la vague actuelle
	 * Si le numero est impair : la vague sera faite de monstres terrestres
	 * Si le numero est pair : la vague sera faite de monstres aériens
	 * A la vague 20 : le boss final.
	 */
	public void vague() {
		if(vague!=0) {
			if (vague==20){//Le boss final
				monsters = new LinkedList<Monster>();
				MonsterBoss boss= new MonsterBoss(new Position(spawn));
				monsters.add(boss);
			}
			else if (vague % 2 == 1) {// vague de monstre terrestre
				int delay=150;
				if (vague==1) delay=200;
				monsters = new LinkedList<Monster>();
				for (int i = 0; i < nombreBase(); i++) {
					monsters.add(new MonsterTerrestre(new Position(spawn)));
					monsters.get(i).setDelai(delay);
					delay+=40-vague;
				}
			} else {// vague de monstre aérien
				int delay=150;
				monsters = new LinkedList<Monster>();
				for (int i = 0; i < nombreBase(); i++) {
					monsters.add(new MonsterAerien(new Position(spawn)));
					monsters.get(i).setDelai(delay);
					delay+=33-vague;
				}
			}
		}
	}
	/**
	 * Fonction appellée dans 'update' qui appelle la vague suivante quand la liste 'monsters' est vide.
	 */
	public void vagues(){
		while(vague<21){
			if (monsters.isEmpty()){
				vague();
				vague+=1;
			}
			else
				return;
		}
	}

	/**
	 * Fonction appellée dans 'update' qui appelle les attaques des tours présentent sur le plateau
	 */
	public void towerFight(){
		for (int i=0; i<tow.size();i++){
			tow.get(i).attaque(monsters,spawn);
		}
	}


	/**
	 * Affiche des informations sur l'�cran telles que les points de vie du joueur, 
	 * son or et le num�ro de la vague de monstres actuelle
	 */
	public void drawInfos() {
		StdDraw.picture(0.438, 0.88, "Images/infos2.png");
		Display.toPictureGold(gold);
		Display.toPictureLife(life);
		Display.toPicVag(vague);
		if(nombremonsters!=0 && monsters.size()>0)Display.pourcentageVague(monsters.size(), nombremonsters,vague);
	}

	/**
	 * Fonction qui récupère le positionnement de la souris et permet d'afficher une
	 * image de tour en temps réel lorsque le joueur appuie sur une des touches
	 * permettant la construction d'une tour.
	 * Affiche les tours déja existances sur le plateau.
	 */
	public void drawMouse() {
		double normalizedX = (int) (StdDraw.mouseX() / squareWidth) * squareWidth + squareWidth / 2;
		double normalizedY = (int) (StdDraw.mouseY() / squareHeight) * squareHeight + squareHeight / 2;
		if (normalizedX < 12 && normalizedY < 12) {
			String image = null;
			switch (key) {
			case 'a':
				image = "Images/Archers.png";
				break;
			case 'b':
				image = "Images/canon2.png";
				break;
			case 'l':
				image = "Images/laser.png";
				break;
			}
			Position pos = new Position(normalizedX, normalizedY);

			if (!pause){
				if (image != null)
				if ((!tow.contains(new ArrowTower(PosToCoord(pos)))))
					if (PosToCoord(pos).x < 12 && PosToCoord(pos).y < 12) // Sinon bug out of bond quand on sort de la fenetre
						if (fond[PosToCoord(pos).x][PosToCoord(pos).y] == 0)
							StdDraw.picture(normalizedX, normalizedY, image, squareWidth, squareHeight);
			}
		}
		for (int i = 0; i < tow.size(); i++) {
			tow.get(i).draw(squareWidth, squareHeight);
		}
	}

	/**
	 * Pour chaque monstre de la liste de monstres de la vague, utilise la fonction
	 * update() qui appelle les fonctions run() et draw() de Monster. 
	 */
	public void updateMonsters() {
		Iterator<Monster> i = monsters.iterator();
		Monster m;
		int r=monsters.size();
		while (i.hasNext() && r!=0) {
			m = i.next();
			if (m.isReached()) r-=1;
			caseSuivante(m,i);
			m.update();
		}
	}

	/**
	 * Met � jour toutes les informations du plateau de jeu ainsi que les
	 * d�placements des monstres et les attaques des tours.
	 * 
	 * @return les points de vie restants du joueur
	 */
	public int update() {
		drawBackground();
		drawPath();
		drawMouse();
		updateMonsters();
		vagues();
		drawInfos();
		towerFight();
		return life;
	}

	/**
	 * Recommence le jeu.
	 */
	public void restart(){
		life=20;
		gold=200;
		vague=0;
		tow.clear();
		monsters.clear();
	}

	/**
	 * Récupère la touche appuyée par l'utilisateur et affiche les informations pour
	 * la touche séléctionnée
	 * 
	 * @param key la touche utilis�e par le joueur
	 */
	public void keyPress(char key) {
		key = Character.toLowerCase(key);
		this.key = key;
		switch (key) {
		case 'a':
			System.out.println("Arrow Tower selected (50 gold).");
			break;
		case 'b':
			System.out.println("Bomb Tower selected (60 gold).");
			break;
		case 'l' : 
			System.out.println("Laser Tower selected (400 gold).");
			break;
		case 'e':
			System.out.println("Evolution selected (100 gold).");
			break;
		case 'm':
			System.out.println("Need money? Selling mode activated");
			break;
		case 's':
			System.out.println("Starting game!");
			start = true;
			break;
		case 'p':
			if(start) {
				System.out.println("A little break?");
				if (!pause) pause = true;
				else if (pause && !menu)pause=false;
				break;}
		case 'c' :
			System.out.println("Here are the controls:");
			printCommands();
			if (!menu) menu = true;
			else menu=false;
			break;
		case 'r':
			restart = true;
			break;
		case 'q':
			exit = true;
			break;
		case 'y':
			if(exit)System.exit(0);
			if(restart)
				restart();
				restart =false;
			break;
		case 'n':
			exit = false;
			restart =false;
			break;
		case '+':
			System.out.println("a bug in the matriiix...here is your gold");
			gold=999;
			break;
		case 'v':
			System.out.println("Want to see how the next wave works? Here it is");
			monsters = new LinkedList<Monster>();
			break;
		}
	}

	/**
	 * Vérifie lorsque l'utilisateur clique sur sa souris qu'il peut: 
	 * - Ajouter une tour à la position indiquée par la souris. 
	 * - Améliorer une tour existante.
	 * - Vendre une tour existante.
	 * 
	 * @param x
	 * @param y
	 */
	public void mouseClick(double x, double y) {
		Position p = new Position(x,y);
		Coord c = PosToCoord(p);
		if(c.x<12 && c.y<12) {
			switch (key) {
			case 'a':
				if (gold<50) {
					System.out.println("Pas assez d'or pour construire une tour d'archers");
					break;
				}
				else if (fond[c.x][c.y]!=0){
					System.out.println("Vous devez poser la tour sur l'herbe");
					break;
				}
				else if (tow.contains(new ArrowTower(c)) ){
					System.out.println("Il y a déja une tour sur cette case");
					break;
				}
				else {
					tow.add(new ArrowTower(c));
					System.out.println("Vous avez posé une tour en "+c.toString());
					gold-=50;
					break;
				}
			case 'b':
				if (gold<60) {
					System.out.println("Pas assez d'or pour construire une tour de bombes");
					break;
				}
				else if (fond[c.x][c.y]!=0){
					System.out.println("Vous devez poser la tour sur l'herbe");
					break;
				}
				else if (tow.contains(new BombTower(c)) ){
					System.out.println("Il y a déja une tour sur cette case");
					break;
				}
				else {
					tow.add(new BombTower(c));
					System.out.println("Vous avez posé une tour en "+c.toString());
					gold-=60;
					break;
				}
			case 'l':
				if (gold<400) {
					System.out.println("Pas assez d'or pour construire une tour lasers");
					break;
				}
				else if (fond[c.x][c.y]!=0){
					System.out.println("Vous devez poser la tour sur l'herbe");
					break;
				}
				else if (tow.contains(new LaserTower(c)) ){
					System.out.println("Il y a déja une tour sur cette case");
					break;
				}
				else {
					tow.add(new LaserTower(c));
					System.out.println("Vous avez pos� une tour en "+c.toString());
					gold-=400;
					break;
				}	
			case 'e':
				if (gold < 100)
					System.out.println("Vous n'avez pas assez d'or.");
				else {
					if (tow.contains(new ArrowTower(c))) {
						Tower towerToEvolve= tow.get(tow.indexOf(new ArrowTower(c)));
						towerToEvolve.Evolution();
						System.out.println("La tour en " + c.toString() + " est �volu�e");
						gold -= 100;
					}else
						System.out.println("Il n'y a pas de tour sur cette case");
				}
				break;
			case 'm':
				if (tow.contains(new ArrowTower(c))) {
					Tower towerToSell= tow.get(tow.indexOf( new ArrowTower(c)));
					tow.remove(tow.indexOf(towerToSell));
					if (towerToSell.isEvolved()) gold+=50;
					if (towerToSell instanceof ArrowTower) gold+=25;
					else if (towerToSell instanceof BombTower) gold+=30;
					else gold+=250;
				}
			}
		}
	}

	/**
	 * Comme son nom l'indique, cette fonction permet d'afficher dans le terminal
	 * les différentes possibilitées offertes au joueur pour intéragir avec le
	 * clavier
	 */
	public void printCommands() {
		System.out.println("Press A to select an Arrow Tower (cost 50 gold).");
		System.out.println("Press B to select a Bomb Tower (cost 60 gold).");
		System.out.println("Press L to select a Laser Tower (cost 500 gold).");
		System.out.println("Click in the galaxy to build it.");
		System.out.println("Press E to update a tower (cost 100 gold).");
		System.out.println("Press M to sell a tower (half the tower price).");
		System.out.println("Press P to pause the game and then C to see the controls.");
		System.out.println("Press R to restart the game.");
		System.out.println("Press S to start.");
		System.out.println("Press Q to exit the game.");
	}

	public void quit(){
		while(exit) {
			StdDraw.picture(0.5, 0.5, "Images/exit.png");
			StdDraw.show();
			if(start) {
				drawBackground();
				drawPath();
				drawMouse();
				drawInfos();
				}
			if (StdDraw.hasNextKeyTyped()) {
				keyPress(StdDraw.nextKeyTyped());
			}
		}
	}

	public void commandes(){
		while (menu){
			StdDraw.picture(0.5, 0.5, "Images/controls.png");
			StdDraw.show();
			if (StdDraw.hasNextKeyTyped()) {
				keyPress(StdDraw.nextKeyTyped());
			}
		}
	}
	/**
	 * Récupère la touche entrée au clavier ainsi que la position de la souris et
	 * met à jour le plateau en fonction de ces interractions
	 */
	public void run() {
		printCommands();
		while (!end) {
			StdDraw.clear();
			while (!start){
				StdDraw.picture(0.5, 0.5, "Images/start.jpg");
				StdDraw.show();
				if (StdDraw.hasNextKeyTyped()) {
					keyPress(StdDraw.nextKeyTyped());
				}
				commandes();
				quit();
			}
			if (start) {
				update();
				StdDraw.show();
				StdDraw.pause(20);
			}
			if (StdDraw.hasNextKeyTyped()) {
				keyPress(StdDraw.nextKeyTyped());
			}
			if (StdDraw.isMousePressed()) {
				mouseClick(StdDraw.mouseX(), StdDraw.mouseY());
				StdDraw.pause(50);
			}
			while(restart) {
				StdDraw.picture(0.5, 0.5, "Images/restart.png");
				StdDraw.show();
				drawBackground();
				drawPath();
				drawMouse();
				drawInfos();
				if (StdDraw.hasNextKeyTyped()) {
					keyPress(StdDraw.nextKeyTyped());
				}
				commandes();
				quit();
			}
			while (pause){
				StdDraw.picture(0.5, 0.5, "Images/pause.png");
				StdDraw.show();
				drawBackground();
				drawPath();
				drawMouse();
				drawInfos();
				if (StdDraw.hasNextKeyTyped()) {
					keyPress(StdDraw.nextKeyTyped());
				}
				commandes();
				quit();
			}
			quit();
			if (life==0) {
				StdDraw.picture(0.5, 0.5, "Images/over.jpg");
				StdDraw.show();
				StdDraw.pause(3000);
				end=true;
			}
			if (win){
				StdDraw.picture(0.5, 0.5, "Images/win.jpg");
				StdDraw.show();
				StdDraw.pause(3000);
				end=true;
			}
		}
		System.exit(0);
	}
}