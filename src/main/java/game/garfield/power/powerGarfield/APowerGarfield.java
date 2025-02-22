package game.garfield.power.powerGarfield;

import game.garfield.character.Garfield.AGarfield;
import game.garfield.character.enemy.AEnemy;
import game.garfield.object.Bloc;
import game.garfield.power.APower;
import game.garfield.power.powerEnemy.APowerEnemy;

public abstract class APowerGarfield extends APower {
    public APowerGarfield(float x, float y, float vecX, float vecY, float sizeX, float sizeY, int damage, String file1, String file2, String imagePath, AGarfield owner, int lifeTime, boolean lookingRight) {
        super(x, y, vecX, vecY, sizeX, sizeY, damage, file1, file2, imagePath, owner, lifeTime, lookingRight);
    }
    
    /** 
     * @param enemy that is getting attacked
     * @return boolean true if enemy is still alive, false otherwise
     */
    public boolean attack(AEnemy enemy){
        return enemy.isAttacked(this.getDamage(),(AGarfield) owner);
    }

    
    /** 
     * this interacts with entity, if entity is of type AEnemy, it gets attacked, if it is APowerEnemy it touches the power
     * @param entity getting interacted with
     * @return boolean returns true if entity still exists in level
     */

    public abstract void touchesPower(APowerEnemy powerEnemy);
    //todo see where to put this function so that it can remove elements from the list
    @Override
    public void detectIntersection() {
        for(int i=entities.size()-1;i>=0;i--){
            if(this.getBoundsInParent().intersects(entities.get(i).getBoundsInParent())){
                if(entities.get(i) instanceof AEnemy){
                    this.attack((AEnemy)(entities.get(i)));
                }
                else if(entities.get(i) instanceof Bloc){
                    ((AGarfield) owner).getProjectiles().remove(this);
                    death();
                    return;
                } 
            }
        }
    }
    
}
