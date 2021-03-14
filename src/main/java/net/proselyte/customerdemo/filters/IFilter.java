package net.proselyte.customerdemo.filters;

import net.proselyte.customerdemo.model.Customer;

import java.util.List;

public interface IFilter {
     public String getAttribute();
     public String getValue();
     public String getOperator();
     public String getCondition();

     public String[] getOperatorsArray();
}
