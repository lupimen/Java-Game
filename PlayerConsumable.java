
public class PlayerConsumable {

// to fill consumables list
    
    private String conName;
    private int conHealthRegen;
    private int conDamageIncrease;
    private int conNumTurns;

    public void Consumable(String conName, int conHealthRegen, int conDamageIncrease, int conNumTurns) {
        this.conName = conName;
        this.conHealthRegen = conHealthRegen;
        this.conDamageIncrease = conDamageIncrease;
        this.conNumTurns = conNumTurns;
    }

    public String getconName() {
        return conName;
    }
    public int getWepMinDamage() {
        return conHealthRegen;
    }
    public int getconDamageIncrease() {
        return conDamageIncrease;
    }
    public int getNTurns(){
        return conNumTurns;
    }
}
