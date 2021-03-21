package net.proselyte.customerdemo.SWT.metods.SWTManager;

import net.proselyte.customerdemo.database.QueryBuilder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import static net.proselyte.customerdemo.SWT.metods.SWTTable.SetPlaceholder;
import static net.proselyte.customerdemo.database.DBArrays.*;

public class SWTDemoMethods { // класс первой реализации графического интерфейса - не используется, оставлен для презентации

   public static void addConditionRow(Group group) { // метод добавления полей и условия поиска
       QueryBuilder.conditionRows += 1;
       Group rowGroup = new Group(group, SWT.SHADOW_ETCHED_IN);
       rowGroup.setLocation(10, 60 + (QueryBuilder.conditionRows - 1) * 60);
       rowGroup.setSize(630, 55);
       rowGroup.setText("Условие: " + QueryBuilder.conditionRows);

       if (QueryBuilder.conditionRows > 1) {
           Combo attributeOperator = new Combo(rowGroup, SWT.DROP_DOWN);
           attributeOperator.setBounds(10, 20, 60, 20);
           attributeOperator.setItems(SQLOperators);
           attributeOperator.select(0);
           QueryBuilder.conditionsOperators.put(QueryBuilder.conditionRows, attributeOperator);

           Button add2 = new Button(group, SWT.NONE);   //кнопка добавления промежуточных полей и условия поиска
           add2.setText("добавить промежуточное условие");
           add2.setBounds(300, 20, 200, 30);
           add2.addSelectionListener(new SelectionAdapter() {
               @Override
               public void widgetSelected(SelectionEvent arg0) {
                   addConditionRow(group);
               }
           });
       }

       Combo attributeName = new Combo(rowGroup, SWT.DROP_DOWN);
       attributeName.setBounds(80, 20, 100, 20);
       attributeName.setItems(SQLValues);
       SetPlaceholder(attributeName, "поиск по");
       QueryBuilder.conditionsAttributes.put(QueryBuilder.conditionRows, attributeName);


       Button add = new Button(rowGroup, SWT.NONE);
       add.setText("OK");
       add.setBounds(190, 20, 25, 25);
       add.addSelectionListener(new SelectionAdapter() {
           @Override
           public void widgetSelected(SelectionEvent arg0) {
               String it = attributeName.getText();
               if (it.equals("first_name") || it.equals("last_name")) {
                   Text attributeValue = new Text(rowGroup, SWT.BORDER);
                   attributeValue.setBounds(230, 20, 120, 23);
                   QueryBuilder.conditionsValues.put(QueryBuilder.conditionRows, attributeValue);
               } else if (it.equals("date_of_birth")) {
                   Combo yearValue = new Combo(rowGroup, SWT.DROP_DOWN); // поля ввода для года
                   yearValue.setBounds(230, 20, 45, 23);
                   yearValue.setItems(Years);
                   SetPlaceholder(yearValue, "Год");
                   QueryBuilder.conditionsAttributes.put(QueryBuilder.conditionRows, yearValue);


                   Combo monthValue = new Combo(rowGroup, SWT.DROP_DOWN); // поля ввода для месяца
                   monthValue.setBounds(280, 20, 40, 23);
                   monthValue.setItems(Months);
                   SetPlaceholder(monthValue, "м");
                   QueryBuilder.conditionsAttributes.put(QueryBuilder.conditionRows, monthValue);


                   Combo daysValue = new Combo(rowGroup, SWT.DROP_DOWN); // поля ввода для числа
                   daysValue.setBounds(325, 20, 40, 23);
                   daysValue.setItems(Days);
                   SetPlaceholder(daysValue, "ч");
                   QueryBuilder.conditionsAttributes.put(QueryBuilder.conditionRows, daysValue);


                   Button period = new Button(rowGroup, SWT.NONE); // кнопка для добавления условия
                   period.setText("в период по");
                   period.setBounds(370, 20, 75, 25);
                   period.addSelectionListener(new SelectionAdapter() {
                       @Override
                       public void widgetSelected(SelectionEvent arg0) {
                           Combo yearValue = new Combo(rowGroup, SWT.DROP_DOWN);// поля ввода для года
                           yearValue.setBounds(450, 20, 45, 23);
                           yearValue.setItems(Years);
                           SetPlaceholder(yearValue, "Год");

                           Combo monthValue = new Combo(rowGroup, SWT.DROP_DOWN);// поля ввода для месяца
                           monthValue.setBounds(505, 20, 40, 23);
                           monthValue.setItems(Months);
                           SetPlaceholder(monthValue, "м");

                           Combo daysValue = new Combo(rowGroup, SWT.DROP_DOWN); // поля ввода для числа
                           daysValue.setBounds(550, 20, 40, 23);
                           daysValue.setItems(Days);
                           SetPlaceholder(daysValue, "ч");

                       }
                   });

                   // QueryBuilder.conditionsValues.put(QueryBuilder.conditionRows, searchOfDateOfBirth);

               } else if (it.equals("budget")) { // подстановка поля если выбран бюджет
                   Text attributeValuebudget = new Text(rowGroup, SWT.BORDER);
                   attributeValuebudget.setBounds(310, 20, 120, 23);
                   QueryBuilder.conditionsValues.put(QueryBuilder.conditionRows, attributeValuebudget);

                   Combo attributeOperatorMoreAndLess = new Combo(rowGroup, SWT.DROP_DOWN); //оператор для поиска по бюджету
                   attributeOperatorMoreAndLess.setBounds(230, 20, 60, 20);
                   String[] itemsOperator1 = new String[]{"<=", ">=", "="};
                   attributeOperatorMoreAndLess.setItems(itemsOperator1);
                   attributeOperatorMoreAndLess.select(0);
                   QueryBuilder.conditionsOperators.put(QueryBuilder.conditionRows, attributeOperatorMoreAndLess);

               }
           }
       });

       Button remove = new Button(rowGroup, SWT.NONE); // кнопка удаления group
       remove.setText("X");
       remove.setBounds(595, 20, 25, 25);
       remove.addSelectionListener(new SelectionAdapter() {
           @Override
           public void widgetSelected(SelectionEvent arg0) {

           }
       });
   }

   private static void addUnderConditionRow(Group group, int conditionRows1) { // добавление подусловия
       conditionRows1 += 1;
       Group rowGroup = new Group(group, SWT.SHADOW_ETCHED_IN);
       rowGroup.setLocation(10, 60 + (conditionRows1 - 1) * 60);
       rowGroup.setSize(430, 55);
       rowGroup.setText("Условие: " + conditionRows1);

       if (conditionRows1 > 1) {
           conditionRows1 += 1;

           Combo attributeOperator = new Combo(rowGroup, SWT.DROP_DOWN); // оператор "и", "или"
           attributeOperator.setBounds(10, 20, 60, 20);
           attributeOperator.setItems(SQLOperators);
           attributeOperator.select(0);
           QueryBuilder.conditionsOperators.put(conditionRows1, attributeOperator);

           Combo attributeName = new Combo(rowGroup, SWT.DROP_DOWN); // поле поиска
           attributeName.setBounds(100, 20, 120, 20);
           attributeName.setItems(SQLValues);
           SetPlaceholder(attributeName, "Выберете поле...");
           QueryBuilder.conditionsAttributes.put(conditionRows1, attributeName);

           Text attributeValue = new Text(rowGroup, SWT.BORDER);
           attributeValue.setBounds(230, 20, 120, 23);
           QueryBuilder.conditionsValues.put(conditionRows1, attributeValue);

           Button add = new Button(rowGroup, SWT.NONE); // добавление подусловия
           add.setText("+");
           add.setBounds(360, 20, 25, 25);
           int finalConditionRows1 = conditionRows1;
           add.addSelectionListener(new SelectionAdapter() {
               @Override
               public void widgetSelected(SelectionEvent arg0) {
                   addUnderConditionRow(group, finalConditionRows1 - 1);
               }
           });


           Button remove = new Button(rowGroup, SWT.NONE); // удаление подусловия
           remove.setText("X");
           remove.setBounds(390, 20, 25, 25);
           QueryBuilder.conditionsValues.put(conditionRows1, attributeValue);
           int finalConditionRows = conditionRows1;
           remove.addSelectionListener(new SelectionAdapter() {
               @Override
               public void widgetSelected(SelectionEvent arg0) {
                   removeConditionRow(group, finalConditionRows);
               }
           });

       }


   }

   public static void removeConditionRow(Group group, Integer finalConditionRows) { // тут будет метод удаления полей и условия поиска

   }

}
