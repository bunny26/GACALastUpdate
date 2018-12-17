/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

/**
 *
 * @author Faro
 */
public class City {
    int x;
    int y;
    int Kota;
    
    // Constructs a randomly placed city
    public City(){
        this.x = (int)(Math.random()*200);
        this.y = (int)(Math.random()*200);
    }
    
    // Constructs a city at chosen x, y location
    public City(int x, int y, int Kota){
        this.x = x;
        this.y = y;
        this.Kota = Kota;
    }
    
    // Gets city's x coordinate
    public int getX(){
        return this.x;
    }
    
    // Gets city's y coordinate
    public int getY(){
        return this.y;
    }
    
    public int getKota(){
        return this.Kota;
    }
    
    @Override 
    public String toString(){
        return " " +getKota();
    }
    
    // Gets the distance to given city
    public double distanceTo(City city){
        int xDistance = Math.abs(getX() - city.getX());
        int yDistance = Math.abs(getY() - city.getY());
        double distance = Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );
        
        return distance;
    }
    
    /*@Override
    public String toString(){
        return getX()+", "+getY();
    }*/

}
