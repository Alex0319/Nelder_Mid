import algorithm.NelderMid;
import algorithm.ResultClass;
import algorithm.Simplex;

import java.util.Scanner;

/**
 * Created by user1 on 05.12.2016.
 */
public class MainClass {

    private static Scanner scanner =new Scanner(System.in);

    private static double getInputValue(int dotNumber,int coordinate){
        System.out.printf("Input simplex dot %d coordinate %d: ",dotNumber,coordinate);
        return scanner.nextDouble();
    }

    static public void main(String args[]) {
        try{
            int dimension=0;
            do {
                System.out.printf("Input space dimension from 1 to 6: ");
                dimension = scanner.nextInt();
            }
            while(dimension<1 || dimension>6);
            Simplex smpl=new Simplex(dimension);
            for (int i=0;i<smpl.v.length;i++){
                for(int j=0;j<dimension;j++){
                    smpl.v[i].setCoordinateValue(getInputValue(i+1,j+1),j);
                }
            }
            ResultClass result=NelderMid.getFunctionMinimumByNMA(smpl,1,0.5,2,dimension);
            System.out.print(result.getFuncResult());
        }
        catch (Exception exc){
            System.out.printf("Incorrect input data");
        }
    }
}