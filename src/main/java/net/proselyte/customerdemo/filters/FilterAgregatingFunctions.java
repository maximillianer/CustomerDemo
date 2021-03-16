package net.proselyte.customerdemo.filters;

public class FilterAgregatingFunctions implements IFilter{

    @Override
    public String getAttribute() {
        return null;
    }

    @Override
    public String getValue() {
        return null;
    }

    @Override
    public String getOperator() {
        return null;
    }

    @Override
    public String getCondition() {
        return null;
    }

    @Override
    public String[] getOperatorsArray() {
        return new String[0];
    }

    public int CountCol (int Id){
        int CountCustomers = 0;
        return CountCustomers;
    }

    public  int ColSum (int id){
        int Colsum = 0;
        return Colsum;
    }

    public int AVGCollumn(int a){
        int AVG = 0;
        return AVG;
    }

    public int MIN (int b){
        int MIN = 0;
        return MIN;
    }

    public  int MAX (int c){
        int MAX = 0;
        return MAX;
    }
}
