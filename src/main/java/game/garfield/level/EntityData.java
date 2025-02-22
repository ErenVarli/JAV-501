package game.garfield.level;

public class EntityData {
    private int id;
    private float x;
    private float y;
    private float vecX;
    private float vecY;
    private float sizeX;
    private float sizeY;
    private int heal;
    private String apparance;
    private int hp;

    public int getId() {
        return id;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getVecX() {
        return vecX;
    }

    public float getVecY() {
        return vecY;
    }

    public float getSizeX() {
        return sizeX;
    }

    public float getSizeY() {
        return sizeY;
    }

    public int getHeal() {
        return heal;
    }

    public String getApparance() {
        return apparance;
    }

    public int getHp() {
        return hp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setVecX(float vecX) {
        this.vecX = vecX;
    }

    public void setVecY(float vecY) {
        this.vecY = vecY;
    }

    public void setSizeX(float sizeX) {
        this.sizeX = sizeX;
    }

    public void setSizeY(float sizeY) {
        this.sizeY = sizeY;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    public void setApparance(String apparance) {
        this.apparance = apparance;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
