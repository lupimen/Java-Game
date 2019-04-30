import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class TextGame {

    //create various lists 
    List<Enemy> enemy = new ArrayList<Enemy>();
    List<Weapon> weapon = new ArrayList<Weapon>();
    List<Consumable> Consumable = new ArrayList<Consumable>();

    //Player inventory lists
    List<PlayerWeapon> playerWeapon = new ArrayList<PlayerWeapon>();
    List<Consumable> playerConsumable = new ArrayList<Consumable>();
    int currentWeapon = 0;

    //Random number generator to select items from list
    Random listSelector = new Random();
    Random rng = new Random();
    int consCheck;
    //scanner for user input
    Scanner userInput = new Scanner(System.in);
    //current enemy
    int enemyIndex;

    //String variable for player name
    String playerName;

    //variable for numbers of consumables
    int nHealth;
    int nPoison;

    //player alive check
    boolean playerAlive = true;

    //Out of combat player input
    String menuChoice;
    String useHPotion;
    
     //player character base stats, liable to change for balance
    int pMaxHealth = 100;
    int pDamage = 30;
    int pAccuracy = 50;
    //current weapon

    //Enemy Start Health, Will be filled with enemy stats from the called random enemy object
    int enemyHealth;
    
    //current health for player charecter
    int playerHealth;
    //Who gets first turn
    int checkFirstGo;
    Boolean enemyStart = false;

    //check hit variable
    int toHit;

    //general loop boolean
    Boolean loop = false;

    //general temp variable
    int temp = 0;


    public static void main(String[] args){
        TextGame textGame = new TextGame();

        // populate all lists
        textGame.popEnemy();
        textGame.popWeapon();
        textGame.popConsumable();

        //Welcome the player to the game
        textGame.Welcome();
       
        textGame.NewGameItems();

        //textGame.test();

        textGame.runGame();   

       
    }


    //tester method to check outputs follow through corectly
    public void test(){
        System.out.println(playerWeapon.get(0).getWepNameP());
    }

    //populating enemies list with base stats
    public void popEnemy(){
        enemy.add(new Enemy("Thief", 70, 15, 80));
        enemy.add(new Enemy("Warrior", 100, 30, 55));
        enemy.add(new Enemy("goblin", 50, 20, 60));
        enemy.add(new Enemy("Orc", 120, 35, 45));
        enemy.add(new Enemy("Troll", 175, 55, 25));
        enemy.add(new Enemy("Slime", 230, 10, 45));

    }
    //populating weapons list with base stats
     public void popWeapon(){
        weapon.add(new Weapon("Dagger", 5, 15, 80));
        weapon.add(new Weapon("Short Sword", 10, 25, 70));
        weapon.add(new Weapon("Hatchet", 8, 17, 75));
        weapon.add(new Weapon("Axe", 15, 30, 60));
        weapon.add(new Weapon("Long Sword", 17, 35, 55));
        weapon.add(new Weapon("Pole Axe", 5, 15, 80));
        weapon.add(new Weapon("Club", 30, 50, 30));
    }
    //populating consumable items list with base stats
    public void popConsumable() {
        Consumable.add(new Consumable("Health", 30, 0, 1));
        Consumable.add(new Consumable("Poison", 0, 10, 3));
    }

    //welcomes the player on startup
    public void Welcome(){
        System.out.println("please enter you name to begin");
        playerName = userInput.nextLine();
        System.out.println("Welcome, " + playerName + ", to this basic and fairly generic text experiance. I hope you have an average time");
        
    }

    public void NewGameItems(){

        //Setting player starting health
        playerHealth = pMaxHealth;

        //Starting weapon
        //-3 prevents long sword, pole axe and club from being given at the start
        int index = listSelector.nextInt(weapon.size()-3);
        playerWeapon.add(new PlayerWeapon(weapon.get(index).getWepName(),weapon.get(index).getWepMinDamage(),weapon.get(index).getWepMaxDamage(),weapon.get(index).getWepAccuracy()));

        System.out.println("For you journy you have been given: ");
        //Starting consumable
        consCheck = rng.nextInt(100)+1;
        if (1<=consCheck && consCheck<=50){
            nHealth++;
            System.out.println("1 Health potion");
           // System.out.println(nHealth);
        } else {
            nPoison++;
            System.out.println("1 Poison Vial");
            //System.out.println(nPoison);
        }
        System.out.println("Name         Min Damage    Max Damage    Accuracy");
        System.out.println(playerWeapon.get(0).getWepNameP() + "     " + playerWeapon.get(0).getWepMinDamageP() +"             "+ playerWeapon.get(0).getWepMaxDamageP() +"            "+ playerWeapon.get(0).getWepAccuracyP());
    }
    
    public void runGame(){
        //gameplay loop repeates until player is considered dead. Occurs by losing a fight or quitting the game
        while (playerAlive) {
            System.out.println("You have a moment of peace, what do you do?");
            System.out.println("I : Check Inventory");
            System.out.println("H : Use a healing potion, if you have one");
            System.out.println("F : Go to next fight");
            System.out.println("Q : Exit game");
            menuChoice = userInput.nextLine();

            if (menuChoice.equalsIgnoreCase("I")) {
                InventoryManager();
               //System.out.println("I to be input");
            } else if (menuChoice.equalsIgnoreCase("H")){
                HPotionManager();
               //System.out.println("H to be input");
            } else if (menuChoice.equalsIgnoreCase("F")){
                FightManager();
               //System.out.println("F to be input");
            } else if (menuChoice.equalsIgnoreCase("Q")){
                playerAlive = false;
            } else {
                System.out.println("Invalid Choice, please learn to read before playing text games...");
            }
        }


    }

    public void InventoryManager(){
        
        System.out.println("Weapons:");
        System.out.println("       Name      Min Damage    Max Damage    Accuracy");
        //print all weapons currently owned by the player
        for (int i=0 ;i<playerWeapon.size(); ++i){
            System.out.println(i +"      "+playerWeapon.get(i).getWepNameP() + "     " + playerWeapon.get(i).getWepMinDamageP() +"             "+ playerWeapon.get(i).getWepMaxDamageP() +"            "+ playerWeapon.get(i).getWepAccuracyP());
        }

        System.out.println("Consumables");
        if (nHealth >= 1){
            System.out.println(nHealth + " Health Potion/s");
        }
        if (nPoison >= 1){
            System.out.println(nPoison + " Poison vial/s");
        }
        loop = true;
        while (loop){
            System.out.println("Current weapon: " + playerWeapon.get(currentWeapon).getWepNameP());
            System.out.println("Would you like to change weapon?");
            menuChoice = userInput.nextLine();
            //changing weapon
            if (menuChoice.equalsIgnoreCase("Y")){
                System.out.println("Please choose index (leftmost) number for the weapon you want");
                temp = userInput.nextInt();
                if (0<=temp && temp < playerWeapon.size())
                    currentWeapon = temp;
                    System.out.println("Weapon switched to " + playerWeapon.get(currentWeapon).getWepNameP());
                    loop = false;
                } else {
                    System.out.println("Invalid selection");

            }if (menuChoice.equalsIgnoreCase("N")){
                loop = false;

            }else{
                System.out.println("Invalid selection, please choose 'Y' or 'N'");
            }
        }

    }

    public void HPotionManager(){
        //called when player uses a healing potion, manages the user inputs for this action
        System.out.println("Health = " + playerHealth);

        System.out.println("Use potion? Y/N");
            useHPotion = userInput.nextLine();
        if (useHPotion.equalsIgnoreCase("Y")) {

            //If plays has no health potions tell them
            if (nHealth == 0){
                System.out.println("You do not have any left");
                //If played has health potions and has taken damage
            } else if (nHealth > 0 && playerHealth <100) {
                nHealth = nHealth -1;
                playerHealth = (playerHealth + Consumable.get(0).getConHealthRegen());
                System.out.println("Refreshing!");
                //If played has health potions but has not taken damage
            } else {
                System.out.println("You do not need to use that right now");
            }
            //If player heals passed Max health check and return to max value
            if (playerHealth > pMaxHealth) {
                playerHealth = pMaxHealth;
            }
        }
        //System.out.println("Refreshing!");
        //System.out.println("Health = " + playerHealth);
    }

    public void FightManager(){
        System.out.println("You Encounter an enemy");
        
        //Gets random enemy from the enemy list and presents its name and battle message to the player
        enemyIndex = listSelector.nextInt(enemy.size());
        System.out.println("Oh no, a wild " + enemy.get(enemyIndex).getEnemyName() + " Appears");
        checkFirstGo = rng.nextInt(100)+1;
        //Sets the enemy health variable to this specific enemy type
        enemyHealth = enemy.get(enemyIndex).getEnemyMaxHealth();

 //combat phase, checkFirstGo is a 50/50 random to determine who attacks first, liable to change with added feature
        if (checkFirstGo <= 50) {
            System.out.println("You did not see them comming, they hit first");
            enemyStart = true;

            System.out.println(enemyHealth + playerHealth);
        }else {
            enemyStart = false;
        } //turns continue as long as both player and enemy are still alive
        while (enemyHealth > 0 && playerHealth > 0){
            System.out.println(enemy.get(enemyIndex).getEnemyName() + "   " + enemyHealth);

            System.out.println(playerName + "   " + playerHealth);
            
            if (enemyStart){
                EnemyTurn();
                PlayerTurn();
            } else{
                PlayerTurn();
                EnemyTurn();
            }
        }

        if (enemyHealth <= 0) {
            System.out.println("congratulations, you have defeated the " + enemy.get(enemyIndex).getEnemyName());

            if (rng.nextInt(100)+1 >70){
                if (rng.nextInt(100)+1 >50){
                    System.out.println("you have found a health potion");
                    nHealth++;
                } else{
                    boolean wepCheck = true;
                    int index = listSelector.nextInt(weapon.size());
                    //loop check if found weapon matches one already owned by player, if it does a potion is found instead
                    for (int i = 0; i < playerWeapon.size(); i++) {
                        if (weapon.get(index).getWepName() == playerWeapon.get(i).getWepNameP());{
                            System.out.println("you have found a health potion");
                            nHealth++;
                            wepCheck = false;
                        }
                    }
                    if (wepCheck){
                        playerWeapon.add(new PlayerWeapon(weapon.get(index).getWepName(),weapon.get(index).getWepMinDamage(),weapon.get(index).getWepMaxDamage(),weapon.get(index).getWepAccuracy()));
                    }
                    
                }
                
            }
        } else if (playerHealth <= 0) {
            System.out.println("Sadly you have died, game over");
            System.out.println("Please play again");
            playerAlive = false;
        }
    }
     

    public void EnemyTurn(){
        System.out.println("The " + enemy.get(enemyIndex).getEnemyName() + " Takes a swing at you");
        // enemy rolls to hit, if they roll below their accuracy they hit, if not they miss
        toHit = (rng.nextInt(100)+1);
        if (enemy.get(enemyIndex).getEnemyAccuracy()< toHit){
            //Enemy misses
            System.out.println("Enemy missed you");
        } else {
         //enemy hits
        //Damage is decided
        System.out.println("enemy hit");
        playerHealth = playerHealth - rng.nextInt(enemy.get(enemyIndex).getEnemyMaxDamage());
        }
    }

    public void PlayerTurn(){
        loop=true;
        while (loop){
        System.out.println("What would you like to do?");
        System.out.println("A : Attack");
        System.out.println("C : use Consumeable  (Poison not fully implemented)");
        //System.out.println("R : Run"); NOT IMPLIMENTED
        
        menuChoice = userInput.nextLine();
        
        
            if (menuChoice.equalsIgnoreCase("A")) {
                System.out.println("You Take a swing at the " + enemy.get(enemyIndex).getEnemyName());
                // Player rolls to hit, if they roll below their combined accuracy with weapon they hit, if not they miss
                //Weapon index 0 until inventory and weapon changing system implemented
                toHit = (rng.nextInt(100)+1);
                if (pAccuracy + (pAccuracy*playerWeapon.get(0).getWepAccuracyP()/100) < toHit){
                    //You miss
                    System.out.println("You missed");
                    loop = false;
                
                } else {
                    //Player Hits
                    //Damage is decided, weapon index 0 until weapon chaning implemented.
                    System.out.println("You hit");
                    enemyHealth = enemyHealth - rng.nextInt((playerWeapon.get(currentWeapon).getWepMaxDamageP()-playerWeapon.get(currentWeapon).getWepMinDamageP())+playerWeapon.get(currentWeapon).getWepMinDamageP());
                    loop = false;
                }
                } else if (menuChoice.equalsIgnoreCase("C")){
                //only health avaliable, poison not implimented
                HPotionManager();
                loop = false;
        //  }else if (menuChoice.equalsIgnoreCase("R")){
                
                } else{
                    System.out.print("Invalid imput");
                }
        }
    }
}
// //**********combat system still being implemented***************/
