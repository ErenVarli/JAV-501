package game.garfield.level;

import java.util.ArrayList;
import java.util.List;


public class EntityDataList {
    private  List<EntityData> listData;

    public EntityDataList(){
        listData =new ArrayList<>();
    }

    public List<EntityData> getListData() {
        return listData;
    }

    public void setListData(List<EntityData> list) {
        this.listData = list;
    }
}
