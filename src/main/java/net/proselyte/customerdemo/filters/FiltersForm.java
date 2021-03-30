package net.proselyte.customerdemo.filters;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

import java.util.HashMap;
import java.util.Map;

import static net.proselyte.customerdemo.database.DBArrays.*;

/**
 * Class for dynamically building search queries in a window
 *
 * @version 1.0
 * @autor Maxim Eliseev
 */
public class FiltersForm {
    int conditionGroupCount = 0; // каунтер количетсва групп
    public static Map<Integer, Group> conditionGroup = new HashMap<>(); // группы поиска
    public static Map<Integer, Integer> subConditionGroupCount = new HashMap<>(); // количество подгрупп поиска
    public static HashMap<Integer, HashMap<Integer, Group>> subConditionGroup = new HashMap<Integer, HashMap<Integer, Group>>();//значения в подгруппах

    /**
     * Method add rowGroup for window parametrs of search
     *
     * @param containerGroup
     */
    public void addConditionGroup(Group containerGroup) { // добавить поле условия
        conditionGroupCount++; // увеличивать каунтер если добавлено новое условие поиска
        int rowID = conditionGroupCount;
        int orderId = conditionGroup.size();
        Group rowGroup = new Group(containerGroup, SWT.SHADOW_ETCHED_IN);
        rowGroup.setData("ID", rowID); //установить ключ - значение для группы поиска
        rowGroup.setLocation(10, 60 + orderId * 60); // спозиционировать группу в окне
        rowGroup.setSize(630, 55);
        rowGroup.setText("Условие: " + rowID);
        conditionGroup.put(rowID, rowGroup);

        initCondition(rowGroup, orderId);
        initAttribute(rowGroup);
        initOperator(rowGroup, orderId);
        initButtonSub(containerGroup, rowGroup, rowID, orderId);
        initButtonRemove(containerGroup, rowGroup, rowID);
    }

    /**
     * Method deleted Group
     *
     * @param group
     * @param rowID
     */
    public void removeConditionGroup(Group group, int rowID) { // удалить группу поиска
        conditionGroup.remove(rowID);
        removeConditionGroupItemById(group, rowID);
    }

    /**
     * Method deleted Subgroup
     *
     * @param group
     * @param rowID
     */
    public void removeConditionGroupItemById(Group group, int rowID) { // удалить подгруппу
        for (Control ctrl : group.getChildren()) {
            if (ctrl instanceof Group) {
                int id = (int) ctrl.getData("ID");
                if (id == rowID) {
                    ctrl.dispose();
                    break;
                }
            }
        }
    }

    /**
     * Method substitution of the hunt operator in the hunt group ignoring the first group
     *
     * @param rowGroup
     * @param orderId
     */
    public void initCondition(Group rowGroup, int orderId) { // подставление оператора поиска в группу поиска игнорируя первую группу
        if (orderId > 0) {
            Combo input = new Combo(rowGroup, SWT.DROP_DOWN | SWT.READ_ONLY);
            input.setBounds(10, 20, 60, 20);
            input.setItems(SQLOperators);
            input.select(0);
            input.setData("ID", "condition");
        }
    }

    public void initBetweenCondition(Group rowGroup) { // подставление оператора поиска в группу поиска по оператору Between

        Combo input = new Combo(rowGroup, SWT.DROP_DOWN | SWT.READ_ONLY);
        input.setBounds(370, 20, 50, 20);
        input.setItems("AND");
        input.select(0);
        input.setData("ID", "betweenCondition");

    }

    /**
     * Method value substitution
     *
     * @param rowGroup
     */
    public void initAttribute(Group rowGroup) { // подставление значения
        Combo input = new Combo(rowGroup, SWT.DROP_DOWN | SWT.READ_ONLY);
        input.setBounds(80, 20, 120, 20);
        input.setItems(SQLValues);
        input.setData("ID", "attribute");
        final String VALUE = "условие";
        input.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
        input.addPaintListener(new PaintListener() {
            @Override
            public void paintControl(PaintEvent event) {
                if (input.getText().isEmpty()) {
                    int x = 2; // indent some pixels
                    int y = 2; // center vertically
                    event.gc.drawText(VALUE, x, y);
                }
            }
        });
        input.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                initValue(rowGroup);
                String typex = input.getText();
                if (typex.equals("first_name") || typex.equals("last_name")) {

                }
                System.out.println("выбрано: " + typex);

            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {

            }
        });
    }

    /**
     * Method substitution of an operator from an array of values into DBArrays
     *
     * @param rowGroup
     * @param orderId
     */
    public void initOperator(Group rowGroup, int orderId) { // подставление оператора из массива значений в DBArrays
        Combo input = new Combo(rowGroup, SWT.DROP_DOWN | SWT.READ_ONLY);
        input.setBounds(210, 20, 60, 20);
        input.setItems(SQLMathematicOperators);
        input.setData("ID", "operator");
        final String VALUE = "оператор";
        input.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
        input.addPaintListener(new PaintListener() {
            @Override
            public void paintControl(PaintEvent event) {
                if (input.getText().isEmpty()) {
                    int x = 2; // indent some pixels
                    int y = 2; // center vertically
                    event.gc.drawText(VALUE, x, y);
                }
            }
        });
        input.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                String typex = input.getText();
                if (typex.equals("BETWEEN")) {
                    System.out.println("выбраано условие: " + typex);
                    initBetweenCondition(rowGroup);
                    initValueBetween(rowGroup);

                }
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {

            }
        });
    }


    /**
     * Method substitution search field by value
     *
     * @param rowGroup
     */
    public void initValue(Group rowGroup) { // подставление поле поиска по значению
        Text input = new Text(rowGroup, SWT.BORDER);
        input.setBounds(280, 20, 90, 23);
        input.setData("ID", "value");

    }

    public void initValueBetween(Group rowGroup) { // подставление поле поиска по значению
        Text input = new Text(rowGroup, SWT.BORDER);
        input.setBounds(420, 20, 80, 23);
        input.setData("ID", "betweenvalue");

    }

    /**
     * Method substitution of the add subcondition button
     *
     * @param containerGroup
     * @param rowGroup
     * @param rowID
     * @param orderID
     * @return btn
     */
    public Button initButtonSub(Group containerGroup, Group rowGroup, int rowID, int orderID) { // подставление кнопки добавления подусловия
        Button btn = new Button(rowGroup, SWT.NONE);
        btn.setText("+");
        btn.setBounds(570, 20, 25, 25);
        btn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                addSubConditionGroup(containerGroup, rowGroup, rowID, orderID);
                render(containerGroup);
            }
        });
        return btn;
    }

    /**
     * Method substitution of the delete hunt group button
     *
     * @param containerGroup
     * @param rowGroup
     * @param rowID
     * @return btn
     */
    public Button initButtonRemove(Group containerGroup, Group rowGroup, int rowID) { // подставление кнопки удаления группы поиска
        Button btn = new Button(rowGroup, SWT.NONE);
        btn.setText("X");
        btn.setBounds(595, 20, 25, 25);
        btn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                removeConditionGroup(containerGroup, rowID);
                render(containerGroup);
            }
        });
        return btn;
    }

    /**
     * Method add a search subcondition field
     *
     * @param containerGroup
     * @param rowGroup
     * @param rowID
     * @param orderID
     */
    public void addSubConditionGroup(Group containerGroup, Group rowGroup, int rowID, int orderID) { // добавить поле подусловия поиска
        int subID = 0;
        if (subConditionGroupCount.containsKey(rowID)) {
            subID = subConditionGroupCount.get(rowID);
        }
        subID++;

        int orderId = 0;
        if (subConditionGroup.containsKey(rowID)) {
            orderId = subConditionGroup.get(rowID).size();
        }
        Group subGroup = new Group(rowGroup, SWT.SHADOW_ETCHED_IN);
        subGroup.setData("ID", rowID + "-" + subID);
        subGroup.setLocation(80, (55 + orderId * 55));
        subGroup.setSize(540, 55);
        subGroup.setText("Под-условие: " + subID);

        if (subConditionGroup.containsKey(rowID)) {
            subConditionGroup.get(rowID).put(subID, subGroup);
        } else {
            HashMap<Integer, Group> map = new HashMap<Integer, Group>();
            map.put(subID, subGroup);
            subConditionGroup.put(rowID, map);
        }
        subConditionGroupCount.put(rowID, subID);

        initCondition(subGroup, orderId + 1);
        initAttribute(subGroup);
        initOperator(subGroup, orderId);
        initValue(subGroup);
        initButtonSubRemove(containerGroup, rowGroup, rowID, subGroup, subID);
    }

    /**
     * Method adding a button to delete a search subgroup
     *
     * @param containerGroup
     * @param rowGroup
     * @param rowID
     * @param subGroup
     * @param subID
     * @return btn
     */
    public Button initButtonSubRemove(Group containerGroup, Group rowGroup, int rowID, Group subGroup, int subID) { // добавление кнопки удаления подгруппы поиска
        Button btn = new Button(subGroup, SWT.NONE);
        btn.setText("X");
        btn.setBounds(505, 20, 25, 25);
        btn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                removeSubConditionGroup(rowGroup, rowID, subID);
                render(containerGroup);
            }
        });
        return btn;
    }

    /**
     * System delete search subgroup method
     *
     * @param group
     * @param rowID
     * @param subID
     */
    public void removeSubConditionGroup(Group group, int rowID, int subID) { // метод системного удаления подгруппы поиска
        subConditionGroup.get(rowID).remove(subID);
        removeSubConditionGroupItemById(group, rowID, subID);
    }

    /**
     * a method to visually delete a search subgroup
     *
     * @param group
     * @param rowID
     * @param subID
     */
    public void removeSubConditionGroupItemById(Group group, int rowID, int subID) { // метод визуального удаления подгруппы поиска
        for (Control ctrl : group.getChildren()) {
            if (ctrl instanceof Group) {
                String id = (String) ctrl.getData("ID");
                if (id.equals(rowID + "-" + subID)) {
                    ctrl.dispose();
                    break;
                }
            }
        }
    }

    public void renderSearch() {

    }

    /**
     * Method for redrawing content when adding and removing hunt groups
     *
     * @param containerGroup
     */
    public void render(Group containerGroup) { // метод перерисовки контента при добавлении и удалении групп поиска
        int rowY = 0;
        for (Map.Entry<Integer, Group> entry : conditionGroup.entrySet()) {
            Group group = entry.getValue();

            int rowID = (int) group.getData("ID");
            int countSub = 0;

            if (subConditionGroup.containsKey(rowID)) {
                HashMap<Integer, Group> subGroups = subConditionGroup.get(rowID);
                int index = 0;
                countSub = subGroups.size();
                for (Map.Entry<Integer, Group> entry2 : subGroups.entrySet()) {
                    Group subGroup = entry2.getValue();
                    subGroup.setLocation(80, (60 + index * 60));
                    index++;
                }
            }
            int height = 55 + 60 * countSub;
            if (countSub > 0) {
                height += 10;
            }
            group.setSize(630, height);

            rowY += 60;
            group.setLocation(10, rowY);
            rowY += height - 55;
        }
    }
}