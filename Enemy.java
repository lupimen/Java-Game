public class Enemy {

	 private String enemyName;
	    private int enemyMaxHealth;
	    private int enemyMaxDamage;
	    private int enemyAccuracy;
	    // to fill enemies list
	    public Enemy(String enemyName, int enemyMaxHealth, int enemyMaxDamage, int enemyAccuracy) {
	        this.enemyName = enemyName;
	        this.enemyMaxHealth = enemyMaxHealth;
	        this.enemyMaxDamage = enemyMaxDamage;
	        this.enemyAccuracy = enemyAccuracy;
	    }

	    public String getEnemyName() {
	        return enemyName;
	    }
	    public int getEnemyMaxHealth() {
	        return enemyMaxHealth;
	    }
	    public int getEnemyMaxDamage() {
	        return enemyMaxDamage;
	    }
	    public int getEnemyAccuracy() {
	        return enemyAccuracy;
	    }
	
}
