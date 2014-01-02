package mod;
import basemod.Basemod;
import basemod.Block;
import basemod.Item;
import java.util.*;
import level.Coord;


public class Standart implements Basemod {
public ArrayList<Block> blocks;
public List tiles[];
public ArrayList<Item> items;

    @Override
    public void init() {
     blocks=new ArrayList();   
      //
    }

    @Override
    public void action(int id, int id2, int act, Coord coord) {
      //
    }

}
