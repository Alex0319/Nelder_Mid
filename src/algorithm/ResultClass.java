package algorithm;

import java.util.Arrays;

/**
 * Created by user1 on 09.12.2016.
 */
public class ResultClass {
    private double funcResult;
    private double[] funcArguments;

    public ResultClass(double[] funcArguments, double funcResult){
        this.funcResult=funcResult;
        this.funcArguments = funcArguments;
    }

    public String getFuncResult(){
        if(Double.isInfinite(funcResult))
            return "Function minimum not found";
        String result="Arguments: ";
        for(int i=0;i<funcArguments.length;i++){
            result+=String.format("%.10f  ",funcArguments[i]);
        }
        return result+String.format("\nFunction minimum: %.15f",funcResult);
    }
}