package com.bvlangen.kungfu.todolist.view.dialog;

import java.util.Optional;

import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class UsernameInputDialog {

    private boolean opslaanIsPressed;
    private String usernameInput;

    public UsernameInputDialog(final Stage owner, final String username) {
        TextInputDialog dialog = new TextInputDialog(username);
        dialog.setTitle("Stel ToDo lijst eigenaar in");
        dialog.setHeaderText("Deze ToDo lijst is van");
        dialog.setContentText("Naam:");


        DialogPane dialogPane = dialog.getDialogPane();

        dialogPane.setPrefSize(350, 100);

        // center on parent
        Scene dialogScene = dialogPane.getScene();
        dialogScene.getStylesheets().add("style/DarkTheme.css");
        dialog.setX(owner.getX() + Math.abs(owner.getWidth() - dialogScene.getWidth()) / 3.0);
        dialog.setY(owner.getY() + Math.abs(owner.getHeight() - dialogScene.getHeight()) / 2.5);

        final ButtonType buttonTypeCancel = new ButtonType("Annuleren", ButtonBar.ButtonData.CANCEL_CLOSE);
        final ButtonType buttonTypeOpslaan = new ButtonType("Opslaan", ButtonBar.ButtonData.OK_DONE);
        dialogPane.getButtonTypes().setAll(buttonTypeOpslaan, buttonTypeCancel);

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            opslaanIsPressed = true;
            usernameInput = result.get();
        }
    }

    public boolean opslaanButtonPressed() {
        return opslaanIsPressed;
    }

    public String getUsernameInput() {
        return usernameInput;
    }
}
