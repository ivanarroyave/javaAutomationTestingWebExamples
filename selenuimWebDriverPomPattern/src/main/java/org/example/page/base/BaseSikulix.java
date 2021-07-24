package org.example.page.base;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

public class BaseSikulix {

    public void clickOn(String path){
        Screen s = new Screen();

        try{
            s.wait(path);
            s.click(path);
        }
        catch(FindFailed e){
            e.printStackTrace();
        }
    }

    public void insertInto(String path, String text){
        Screen s = new Screen();
        try{
            s.wait(path);
            s.write(text);
        }
        catch(FindFailed e){
            e.printStackTrace();
        }
    }
}
