package game.garfield.level;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import game.garfield.AEntity;
import game.garfield.App;
import game.garfield.character.Garfield.GarfieldDefault;
import game.garfield.character.Garfield.GarfieldFire;
import game.garfield.character.Garfield.GarfieldIce;
import game.garfield.character.Garfield.GarfieldRock;
import game.garfield.character.enemy.EnemyFire;
import game.garfield.character.enemy.EnemyIce;
import game.garfield.character.enemy.EnemyRock;
import game.garfield.object.Background;
import game.garfield.object.Bloc;
import game.garfield.object.Crate;
import game.garfield.object.End;
import game.garfield.object.Floor;
import game.garfield.object.Kibble;
import game.garfield.util.Position;

@JsonIgnoreProperties
public class Level {
    private App instance;
    private Level nextLevel;
    private Level prevLevel;
    private boolean main = false;
    private String entitiesFile;
    private List<AEntity> entities;

    public Level(String entitiesFile, Level next, Level prev, App instance) {
        this.entitiesFile = entitiesFile;
        this.nextLevel = next;
        this.prevLevel = prev;
        entities = new ArrayList<>();
        this.instance=instance;
    }

    public Level getNextLevel() {
        return nextLevel;
    }

    public Level getPrevLevel() {
        return prevLevel;
    }

    public List<AEntity> getEntities() {
        return entities;
    }

    public void setNextLevel(Level next){
        this.nextLevel=next;
    }
    public void setPrevLevel(Level prev){
        this.prevLevel=prev;
    }

    public boolean isMain() {
        return main;
    }

    public void newMain() {
        this.main = false;
    }

    public List<AEntity> current() {
        if (main) {
            return entities;
        }
        main = true;
        if (nextLevel != null) {
            nextLevel.newMain();
            nextLevel.load();
        }
        if (prevLevel != null) {
            prevLevel.newMain();
            prevLevel.load();
        }
        return load();
    }

    public List<AEntity> load() {
        if (!entities.isEmpty()) {
            return entities;
        }
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("garfield/src/main/resources/game/level/" + this.entitiesFile);
        
        EntityDataList dataEntityList = new EntityDataList();
        try {
            dataEntityList = mapper.readValue(file, EntityDataList.class);
        } catch (Exception e) {
            System.out.println("error to read file : " + this.entitiesFile + "\n" + e+ System.getProperty("user.dir"));
        }

        for (int i = 0; i < dataEntityList.getListData().size(); i++) {
            EntityData a = dataEntityList.getListData().get(i);
            switch (a.getId()) {
                case 1:
                    entities.add(new GarfieldDefault(new Position(a.getX(), a.getY()), a.getHp(), entities, instance));
                    break;
                case 2:
                    entities.add(new GarfieldFire(new Position(a.getX(), a.getY()), a.getHp(), entities, instance));
                    break;
                case 3:
                    entities.add(new GarfieldRock(new Position(a.getX(), a.getY()), a.getHp(), entities, instance));
                    break;
                case 4:
                    entities.add(new GarfieldIce(new Position(a.getX(), a.getY()), a.getHp(), entities, instance));
                    break;
                case 11:
                    entities.add(new EnemyFire(a.getX(), a.getY(), a.getVecX(), a.getVecY(), entities));
                    break;
                case 12:
                    entities.add(new EnemyIce(a.getX(), a.getY(), a.getVecX(), a.getVecY(), entities));
                    break;
                case 13:
                    entities.add(new EnemyRock(a.getX(), a.getY(), a.getVecX(), a.getVecY(), entities));
                    break;
                case 21:
                    entities.add(new Bloc(a.getX(), a.getY(), a.getSizeX(), a.getSizeY(), a.getApparance(), entities));
                    break;
                case 22:
                    entities.add(new Background(a.getX(), a.getY(), a.getSizeX(), a.getSizeY(), a.getApparance(), entities));
                    break;
                case 23:
                    entities.add(new Crate(a.getX(), a.getY(), a.getVecX(), a.getVecY(), a.getSizeX(), a.getSizeY(), a.getApparance(), entities));
                    break;
                case 24:
                    entities.add(new Kibble(a.getX(), a.getY(), a.getHeal(), entities));
                    break;
                case 25:
                    entities.add(new Floor(a.getX(), a.getY(), a.getSizeX(), a.getApparance(), entities));
                    break;
                case 26:
                    entities.add(new End(a.getX(), a.getY(), a.getSizeX(), a.getSizeY(), entities));
                    break;
                default:

            }
        }
        return entities;
    }

}
