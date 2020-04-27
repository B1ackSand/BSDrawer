package gui;
import java.awt.*;

/**
 * @author BlackSand
 */
public class MyButton extends Button {
    String name;
    public MyButton(String n){
        super();
        this.name = n;
    }

    @Override
    public String getName(){
        return name;
    }
}
