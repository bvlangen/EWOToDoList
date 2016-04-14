package com.bvlangen.kungfu.todolist.view.controller;

import com.bvlangen.kungfu.todolist.MainApp;
import com.bvlangen.kungfu.todolist.view.dialog.AlertBox;
import com.bvlangen.kungfu.todolist.view.dialog.UsernameInputDialog;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckMenuItem;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 */
public class RootLayoutController {

    @FXML
    private CheckMenuItem autoSave;

    // Reference to the main application
    private MainApp mainApp;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // bind autoSave to the property on the mainApp
        autoSave.selectedProperty().addListener((arg0, arg1, arg2) -> {
            mainApp.setTodoListAutoSave(arg0.getValue());
        });
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp The main app
     */
    public void setMainApp(final MainApp mainApp) {
        this.mainApp = mainApp;
        this.autoSave.selectedProperty().setValue(mainApp.isAutoSave());
    }

    /**
     * Creates an empty todos list.
     */
    @FXML
    private void handleNew() {
        mainApp.initNewTodoList();
    }

    /**
     * Opens a FileChooser to let the user select an todos list to load.
     */
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            mainApp.loadTodosDataFromFile(file);
        }
    }

    /**
     * Saves the file to the todos file that is currently open. If there is no
     * open file, the "save as" dialog is shown.
     */
    @FXML
    private void handleSave() {
        File todosFile = MainApp.getTodoListFilePath();
        if (todosFile != null) {
            mainApp.saveTodosDataToFile(todosFile);
        } else {
            handleSaveAs();
        }
    }

    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainApp.saveTodosDataToFile(file);
        }
    }

    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
        new AlertBox(mainApp.getPrimaryStage(), Alert.AlertType.INFORMATION, MainApp.getAppName(),
                "Informatie over deze app",
                "Auteur: Bert van Langen\nWebsite: http://blog.bertvanlangen.com");
    }

    /**
     * Opens the username setting dialog
     */
    @FXML
    private void handleUsernameSetting() {
        UsernameInputDialog usernameInputDialog = new UsernameInputDialog(mainApp.getPrimaryStage(), mainApp.getUsername());
        if (usernameInputDialog.opslaanButtonPressed()) {
            mainApp.setTodoListUsername(usernameInputDialog.getUsernameInput());
        }
    }

    /**
     * Closes the application. If the todos list has not been saved yet, it informs the users
     * before closing the application.
     * When autoSave is on, it saves the todos list before quitting
     */
    @FXML
    public void handleExit() {
        if (MainApp.getTodoListFilePath() == null) {
            // no ToDos list saved yet
            AlertBox alertBox = new AlertBox(mainApp.getPrimaryStage(), Alert.AlertType.CONFIRMATION,
                    "Niet opgelagen wijzigingen",
                    "De ToDo lijst is nog niet opgelagen",
                    "Wil je de ToDo lijst opslaan?");

            if (alertBox.okButtonPressed()) {
                handleSaveAs();
            }
        } else if (mainApp.isAutoSave()) {
            handleSave();
        }
        System.exit(0);
    }
}
