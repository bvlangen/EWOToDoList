package com.bvlangen.kungfu.todolist.view.dialog;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;

import java.util.Optional;

public final class AlertBox {

    private final Optional<ButtonType> result;
    private final ButtonType buttonTypeJa = new ButtonType("Ja", ButtonBar.ButtonData.OK_DONE);

    public AlertBox(final Stage owner, final Alert.AlertType alertType, final String title, final String headerMsg, final String content) {
        Alert alert = new Alert(alertType);

        alert.setTitle(title);
        alert.setHeaderText(headerMsg);
        alert.setContentText(content);

        if (alertType.equals(Alert.AlertType.CONFIRMATION)) {
            final ButtonType buttonTypeCancel = new ButtonType("Nee", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeJa, buttonTypeCancel);
        }

        DialogPane dialog = alert.getDialogPane();

        // center on parent
        Scene dialogScene = dialog.getScene();
        dialogScene.getStylesheets().add("style/DarkTheme.css");
        alert.setX(owner.getX() + Math.abs(owner.getWidth() - dialogScene.getWidth()) / 3.0);
        alert.setY(owner.getY() + Math.abs(owner.getHeight() - dialogScene.getHeight()) / 2.5);

        result = alert.showAndWait();
    }

    public boolean okButtonPressed() {
        return result != null && result.isPresent() && result.get() == buttonTypeJa;
    }
}
