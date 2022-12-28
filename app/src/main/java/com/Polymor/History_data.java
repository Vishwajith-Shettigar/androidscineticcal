package com.Polymor;

import java.io.Serializable;

public class History_data implements Serializable {
    private String result;
    private String problem;

   public History_data(String result,String problem)
   {
       this.result=result;
       this.problem=problem;

   }

    public String getResult() {
        return result;
    }

    public String getProblem() {
        return problem;
    }

}
