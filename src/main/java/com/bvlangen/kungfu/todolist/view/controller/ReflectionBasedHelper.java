package com.bvlangen.kungfu.todolist.view.controller;

import com.bvlangen.kungfu.todolist.model.TodoYearWeek;
import com.bvlangen.kungfu.todolist.view.dialog.AlertBox;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Reflection based helper class
 * Used for minimizing boilerplate code for getters and setters
 */
class ReflectionBasedHelper {

    private static final Logger LOG = LoggerFactory.getLogger(ReflectionBasedHelper.class);
    private final TodoOverviewController todoOverviewController;

    ReflectionBasedHelper(final TodoOverviewController todoOverviewController) {
        this.todoOverviewController = todoOverviewController;
    }

    void addListenerForEveryCheckBoxInClass() {
        List<CheckBox> checkboxes = getCheckboxes();
        for (CheckBox checkbox : checkboxes) {
            addListener(checkbox);
        }
    }

    List<CheckBox> getCheckboxes() {
        List<CheckBox> checkboxes = new ArrayList<>();
        Field[] declaredFields = todoOverviewController.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (CheckBox.class.isAssignableFrom(declaredField.getType())) {
                declaredField.setAccessible(true);
                try {
                    checkboxes.add((CheckBox) declaredField.get(todoOverviewController));
                } catch (IllegalAccessException e) {
                    LOG.error("Unable to deterermine every checkbox by reflection", e);
                    new AlertBox(todoOverviewController.getMainApp().getPrimaryStage(), Alert.AlertType.ERROR, "Oeps", "Programma error", "Neem contact op met Bert van Langen");
                }
            }
        }
        return checkboxes;
    }

    private void addListener(final CheckBox checkBox) {
        String id = checkBox.getId();
        String setterMethod = "set" + Character.toUpperCase(id.charAt(0)) + id.substring(1);
        checkBox.selectedProperty().addListener((ov, old_val, new_val) -> {
            try {
                Method declaredMethod = TodoYearWeek.class.getDeclaredMethod(setterMethod, boolean.class);
                declaredMethod.invoke(todoOverviewController.getTodoOverviewTable().getSelectionModel().getSelectedItem(), new_val);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                LOG.error("Unable to add listerners for every checkbox", e);
                new AlertBox(todoOverviewController.getMainApp().getPrimaryStage(), Alert.AlertType.ERROR, "Oeps", "Programma error", "Neem contact op met Bert van Langen");
            }
        });
    }

    void setSelectedForCheckBox(final TodoYearWeek todoYearWeek) {
        List<CheckBox> checkboxes = getCheckboxes();
        for (CheckBox checkbox : checkboxes) {
            setSelected(checkbox, todoYearWeek);
        }
    }

    private void setSelected(final CheckBox checkBox, final TodoYearWeek todoYearWeek) {
        String id = checkBox.getId();
        String getterMethod = "get" + Character.toUpperCase(id.charAt(0)) + id.substring(1);
        try {
            Method declaredMethod = TodoYearWeek.class.getDeclaredMethod(getterMethod);
            boolean selected = (Boolean) declaredMethod.invoke(todoYearWeek);
            checkBox.setSelected(selected);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOG.error("Unable to set selected for every checkbox", e);
            new AlertBox(todoOverviewController.getMainApp().getPrimaryStage(), Alert.AlertType.ERROR, "Oeps", "Programma error", "Neem contact op met Bert van Langen");
        }
    }
}