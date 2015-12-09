package ssu.insadomapp;

import android.util.Log;

/**
 * Created by Marc on 02/12/2015.
 */
public class Home {

    private long id;
    private String name;
    private String URL;

    public long getId() {
        return id;
    }

    public String getName(){
        Log.i("DEBUG_MUNIER", "Home getter : String  " + this.name);

        return name;
    }

    public String getURL(){
        return URL;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        Log.i("DEBUG_MUNIER", "Home : String  " + name);

        this.name = new String(name);
        Log.i("DEBUG_MUNIER", "Home setter : String  " + this.name);
    }

    public void setURL(String URL) {
        this.URL = new String(URL);

    }

    // Sera utilisée par ArrayAdapter dans la ListView
    @Override
    public String toString() {
        return "\n the name is : " + name + "the URL is : " + URL +" The id is : " + id;
    }

}
