package algorithm;

import funcmin.FuncClass;
import java.util.Arrays;
import java.util.concurrent.atomic.DoubleAccumulator;

/**
 * Created by user1 on 06.12.2016.
 */
public class NelderMid {

    static private int iterationCount=0;

    private static boolean QuitCase(Simplex smpl){
        int length=smpl.v.length;
        for(int i=0;i<length;i++) {
            for (int j = i + 1; j < length; j++){
                for(int k=0;k<length-1;k++){
                    if (Math.abs(smpl.v[i].getCoordinateValue(k) - smpl.v[j].getCoordinateValue(k)) > 0.1){
                        iterationCount++;
                        if(iterationCount>1200)
                            return true;
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static ResultClass getFunctionMinimumByNMA(Simplex smpl, double alpha, double beta, double gamma,int dimensionNumber) {
        double[] f = new double[dimensionNumber + 1];
        double f_h, f_g, f_l=0, f_r, f_e, f_s, tempD;
        Vertex x_h=null, x_r;
        int i,maxElementIndex=0;
        boolean flag;

        FuncClass func=new FuncClass();
        Vertex x_e=new Vertex(dimensionNumber);
        Vertex x_s=new Vertex(dimensionNumber);
        Vertex x_l=new Vertex(dimensionNumber);
        Vertex tempV=new Vertex(dimensionNumber);

        for (i = 0; i <= dimensionNumber; i++) {
            f[i] = func.getFuncValue(smpl.v[i]);
        }
        while (!QuitCase(smpl)) {    // Проверка на условие выхода
            // Шаг 1. Сортировка
            Arrays.sort(f);

            f_h = f[dimensionNumber];
            f_g = f[dimensionNumber - 1];
            f_l = f[0];
            for(i=0;i<=dimensionNumber;i++){
                double temp;
                if((temp=func.getFuncValue(smpl.v[i]))==f[dimensionNumber]){
                    x_h = smpl.v[i];
                    maxElementIndex=i;
                }
                if(temp==f[0]){
                    x_l.setCoordinates(smpl.v[i].getCoordinates());
                }
            }

            // Шаг 2. Вычисление центра тяжести симплекса
            Vertex x_c=new Vertex(dimensionNumber);
            for (i = 0; i <= dimensionNumber; i++) {
                if(smpl.v[i]!=x_h){
                    for(int j=0;j<dimensionNumber;j++){
                        x_c.addToCoordinateValue(smpl.v[i].getCoordinateValue(j),j);
                    }
                }
            }
            for(i=0;i<dimensionNumber;i++) {
                x_c.setCoordinateValue(x_c.getCoordinateValue(i)/dimensionNumber,i);
            }

            // 3Шаг . Отражение
            x_r=new Vertex(dimensionNumber);
            for(i=0;i<dimensionNumber;i++) {
                x_r.setCoordinateValue(x_c.getCoordinateValue(i)*(1+alpha)-x_h.getCoordinateValue(i)*alpha,i);
            }
            f_r = func.getFuncValue(x_r);

            // Шаг 4.
            if (f_r <= f_l) {    // Шаг 4a.
                for(i=0;i<dimensionNumber;i++) {
                    x_e.setCoordinateValue(x_c.getCoordinateValue(i)*(1-gamma)+x_r.getCoordinateValue(i)*gamma,i);
                }
                f_e = func.getFuncValue(x_e);
                if (f_e < f_l) {
                    smpl.setCoordinates(x_e,maxElementIndex);
                    f[dimensionNumber] = f_e;
                } else {
                    smpl.setCoordinates(x_r,maxElementIndex);
                    f[dimensionNumber] = f_r;
                }
            }
            if ((f_l < f_r) && (f_r <= f_g)) {    // Шаг 4.b
                smpl.setCoordinates(x_r,maxElementIndex);
                f[dimensionNumber] = f_r;
            }
            flag = false;
            if ((f_h >= f_r) && (f_r > f_g)) {    // Шаг 4c.
                flag = true;
                tempD = f_h;
                for(i=0;i<dimensionNumber;i++) {
                    tempV.setCoordinateValue(x_h.getCoordinateValue(i),i);
                    smpl.v[maxElementIndex].setCoordinateValue(x_r.getCoordinateValue(i),i);
                    x_r.setCoordinateValue(tempV.getCoordinateValue(i),i);
                }
                f[dimensionNumber] = f_r;
                f_r = tempD;
            }
            // Шаг 4d.
            if (f_r > f_h) {
                flag = true;
            }

            if (flag) {    // Шаг 5. Сжатие
                for(i=0;i<dimensionNumber;i++) {
                    x_s.setCoordinateValue(x_h.getCoordinateValue(i)*beta +x_c.getCoordinateValue(i)*(1-beta),i);
                }
                f_s = func.getFuncValue(x_s);
                if (f_s < f_h) {    // 6.
                    tempD = f_h;
                    for(i=0;i<dimensionNumber;i++) {
                        tempV.setCoordinateValue(x_h.getCoordinateValue(i),i);
                        smpl.v[maxElementIndex].setCoordinateValue(x_s.getCoordinateValue(i),i);
                        x_s.setCoordinateValue(tempV.getCoordinateValue(i),i);
                    }
                    f[dimensionNumber] = f_s;
                    f_s = tempD;
                } else {    // Шаг 7. Глобальное сжатие
                    for (i = 0; i <= dimensionNumber; i++) {
                        for(int j=0;j<dimensionNumber;j++) {
                            smpl.v[i].setCoordinateValue(x_l.getCoordinateValue(j)+(smpl.v[i].getCoordinateValue(j)-x_l.getCoordinateValue(j))/2,j);
                        }
                    }
                }
            }
        }
        return new ResultClass(x_l.getCoordinates(),iterationCount>100 ? f_l=Double.POSITIVE_INFINITY : f_l);
    }
}