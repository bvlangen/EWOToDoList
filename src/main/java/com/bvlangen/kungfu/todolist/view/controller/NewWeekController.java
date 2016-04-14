package com.bvlangen.kungfu.todolist.view.controller;

import com.bvlangen.kungfu.todolist.model.TodoYearWeek;
import com.bvlangen.kungfu.todolist.view.dialog.AlertBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class NewWeekController {

    @FXML
    private ChoiceBox<Integer> yearChoiceBox;

    @FXML
    private ChoiceBox<Integer> weekNrChoiceBox;

    private Stage dialogStage;

    private TodoYearWeek todoYearWeek;

    private boolean okClicked = false;

    private ObservableList<TodoYearWeek> todoYearWeeks;

    /**
     * Initializes the controller class. This method is automatically called after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     *            The dialog stage
     */
    public void setDialogStage(final Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the new week to be edited in the dialog.
     *
     * @param todoYearWeek
     *            The new week
     */
    public void setDefaultAddNextTodoYearWeek(final TodoYearWeek todoYearWeek) {
        this.todoYearWeek = todoYearWeek;

        Integer[] weekNumbers = new Integer[53];
        for ( int i = 1; i < 53; i++ ) {
            weekNumbers[i] = i;
        }

        // Default options; previous, current and the next year
        final Integer[] years = { todoYearWeek.getYear() - 1, todoYearWeek.getYear(), todoYearWeek.getYear() + 1 };
        yearChoiceBox.setItems( FXCollections.observableArrayList(years));
        yearChoiceBox.getSelectionModel().select(1); // default select current year
        weekNrChoiceBox.setItems( FXCollections.observableArrayList(weekNumbers));
        weekNrChoiceBox.getSelectionModel().select( todoYearWeek.getWeekNr());
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return boolean indicating if the OK button is clicked
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            todoYearWeek.setYear(yearChoiceBox.getSelectionModel().getSelectedItem());
            todoYearWeek.setWeekNr(weekNrChoiceBox.getSelectionModel().getSelectedItem());

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        for (TodoYearWeek yearWeek : todoYearWeeks) {
            int yearSelected = yearChoiceBox.getSelectionModel().getSelectedItem();
            int weekNrSelect = weekNrChoiceBox.getSelectionModel().getSelectedItem();
            if (yearSelected == yearWeek.getYear() && weekNrSelect == yearWeek.getWeekNr()) {
                // Already added todos week,show the error message.
                new AlertBox( (Stage) dialogStage.getOwner(), Alert.AlertType.ERROR,
                              "ToDo week kan niet worden toegevoegd", "Deze ToDo week bestaat al",
                              "Kies een ToDo week die nog niet bestaat" );
                return false;
            }
        }
        return true;
    }

    public void setTodoYearWeeks( final ObservableList<TodoYearWeek> todoYearWeeks ) {
        this.todoYearWeeks = todoYearWeeks;
    }
}
