package algorithm;

/**
 * Created by user1 on 06.12.2016.
 */
public class Simplex {
    public Vertex[] v;

    public Simplex(int count){
        v=new Vertex[count+1];
        for(int i=0;i<v.length;i++){
            v[i]=new Vertex(count);
        }
    }

    public void setCoordinates(Vertex source,int index){
        for(int i=0;i<v.length-1;i++) {
            v[index].setCoordinateValue(source.getCoordinateValue(i),i);
        }
    }
}
