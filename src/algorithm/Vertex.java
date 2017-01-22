package algorithm;

import java.util.Arrays;

/**
 * Created by user1 on 06.12.2016.
 */
public class Vertex {

    private double[] coordinates;

    public Vertex(int dimension){
        this.coordinates=new double[dimension];
    }

    public double[] getCoordinates(){
        return coordinates;
    }

    public void addToCoordinateValue(double value,int index){
        coordinates[index]+=value;
    }

    public double getCoordinateValue(int index){
        return coordinates[index];
    }

    public void setCoordinateValue(double value,int index){
        coordinates[index]=value;
    }

    public void setCoordinates(double[] coordinates){
        if(coordinates!=null)
            this.coordinates=Arrays.copyOf(coordinates,coordinates.length);
    }
}

interface Print2{
    void p1();
}

interface Print extends Print2{
    void p();
}

class A implements Print{
    public void p(){

    }

    public void p1(){}

    A(){
        super();
    }
}

