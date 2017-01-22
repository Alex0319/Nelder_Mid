package funcmin;

import algorithm.Vertex;

/**
 * Created by user1 on 05.12.2016.
 */
public class FuncClass {

    public double getFuncValue(Vertex v){
        return func(v.getCoordinates());
    }

    private double func(double[] x){

        switch (x.length){
            case 1: return Math.pow(x[0],2)-5*x[0]+7;//10*Math.pow(x[0],4)+5*Math.pow(x[0],2)+3;
            case 2: return Math.pow(x[0],2)+x[0]*x[1]+Math.pow(x[1],2)-3*x[0]-6*x[1];//100*Math.pow(x[1]-Math.pow(x[0],2),2)+Math.pow(1-x[0],2);
            case 3:
            case 4:
            case 5:
            case 6: return 0;
        }
        return 0;
    }
}