
public class PlayerWeapon {

	 private String wepNameP;
	    private int wepMinDamageP;
	    private int wepMaxDamageP;
	    private int wepAccuracyP;
	    // to fill weapons list
	    public PlayerWeapon(String wepNameP, int wepMinDamageP, int wepMaxDamageP, int wepAccuracyP) {
	        this.wepNameP = wepNameP;
	        this.wepMinDamageP = wepMinDamageP;
	        this.wepMaxDamageP = wepMaxDamageP;
	        this.wepAccuracyP = wepAccuracyP;
	    }

	    public String getWepNameP() {
	        return wepNameP;
	    }
	    public int getWepMinDamageP() {
	        return wepMinDamageP;
	    }
	    public int getWepMaxDamageP() {
	        return wepMaxDamageP;
	    }
	    public int getWepAccuracyP() {
	        return wepAccuracyP;
	    }
	
}
