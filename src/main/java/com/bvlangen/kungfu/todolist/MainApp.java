package com.bvlangen.kungfu.todolist;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.prefs.Preferences;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bvlangen.kungfu.todolist.model.TodoListWrapper;
import com.bvlangen.kungfu.todolist.model.TodoYearWeek;
import com.bvlangen.kungfu.todolist.view.controller.NewWeekController;
import com.bvlangen.kungfu.todolist.view.controller.RootLayoutController;
import com.bvlangen.kungfu.todolist.view.controller.TodoOverviewController;
import com.bvlangen.kungfu.todolist.view.dialog.AlertBox;

public class MainApp extends Application {

    private static final Logger LOG = LoggerFactory.getLogger(MainApp.class);

    private static final String APP_NAME = "EWO ToDo Lijst v1.0";
    private static final String PREFERENCES_FILE_PATH = "filePathEwoToDoList";
    private static final String PREFERENCES_USERNAME = "usernameEwoToDoList";
    private static final String PREFERENCES_AUTOSAVE = "autoSaveEwoToDoList";

    private static final boolean AUTO_SAVE_DEFAULT = true;

    private Stage primaryStage;
    private BorderPane rootLayout;
    private RootLayoutController rootLayoutController;

    private String username;
    private boolean autoSave;

    private final ObservableList<TodoYearWeek> todoYearWeeks = FXCollections.observableArrayList();
    public MainApp() {
    }

    public ObservableList<TodoYearWeek> getTodoYearWeeks() {
        todoYearWeeks.sort(TodoYearWeek.getTodoYearWeekComparator());
        return todoYearWeeks;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.autoSave = AUTO_SAVE_DEFAULT;
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(APP_NAME);

        // Capture close event and inform user if the todos list has not been saved yet
        primaryStage.setOnCloseRequest(closeEventHandler);

        InputStream appIcon = getClass().getClassLoader().getResourceAsStream("image/app-icon-todo.png");
        this.primaryStage.getIcons().add(new Image(appIcon));

        initRootLayout();

        showTodoOverview();
    }

    /**
     * Handles closing of the window by usage of the X iso the menu item Exit
     */
    private final EventHandler<WindowEvent> closeEventHandler = event -> {
        if (MainApp.getTodoListFilePath() == null || autoSave) {
            // Todos file has not been saved, inform user when no file has been saved or auto save
            rootLayoutController.handleExit();
        }
    };

    /**
     * Returns the main stage.
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Initializes the root layout and tries to load the last opened todos file.
     */
    private void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("fxml/RootLayout.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().add("style/DarkTheme.css");
            primaryStage.setScene(scene);

            // Try to load the last username (if stored)
            String usernameLoaded = getTodoListUsername();
            if (usernameLoaded != null) {
                username = usernameLoaded;
            }

            // Try to load the autoSave option
            autoSave = getAutoSave();

            // Give the controller access to the main app.
            rootLayoutController = loader.getController();
            rootLayoutController.setMainApp(this);

            // Disable resize (cause we need the space)
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            LOG.error("Unable to init root layout", e);
        }

        // Try to load last opened todos file.
        File file = getTodoListFilePath();
        if (file != null) {
            loadTodosDataFromFile(file);
        } else {
            // No file found
            new AlertBox(getPrimaryStage(), Alert.AlertType.WARNING,
                    "Geen opgeslagen ToDo lijst gevonden",
                    "Nieuwe ToDo lijst wordt aangemaakt",
                    "Wil je een bestaande ToDo lijst openen, kies dan de menu optie 'Bestand - Openen'");
            getTodoYearWeeks().add(new TodoYearWeek());
        }
    }

    /**
     * Shows the todos overview inside the root layout.
     */
    private void showTodoOverview() {
        try {
            // Load overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("fxml/TodoOverview.fxml"));
            AnchorPane todoOverview = loader.load();

            // Set overview into the center of root layout.
            rootLayout.setCenter(todoOverview);

            // Give the controller access to the main app.
            TodoOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            LOG.error("Unable to show todos overview", e);
        }
    }

    /**
     * Opens a dialog to add a new todos week. If the user
     * clicks OK, the changes are saved into the provided todoyearweek object and true
     * is returned.
     *
     * @param todoYearWeek the todoYearWeek object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showAddWeekDialog(final TodoYearWeek todoYearWeek) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("fxml/NewWeek.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Voeg een nieuwe ToDo week toe");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);

            // center on parent page (primary stage)
            dialogStage.setX(primaryStage.getX() + Math.abs(primaryStage.getWidth() - page.getWidth()) / 3.0);
            dialogStage.setY(primaryStage.getY() + Math.abs(primaryStage.getHeight() - page.getHeight()) / 2.5);

            Scene scene = new Scene(page);
            scene.getStylesheets().add("style/DarkTheme.css");
            dialogStage.setScene(scene);


            // Set the todoYearWeek into the controller.
            NewWeekController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setTodoYearWeeks(todoYearWeeks);
            controller.setDefaultAddNextTodoYearWeek(todoYearWeek);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            LOG.error("Unable to show add week dialog", e);
            return false;
        }
    }

    /**
     * Returns the todos list file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     *
     * @return File
     */
    public static File getTodoListFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get(PREFERENCES_FILE_PATH, null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Returns the todos list username.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     *
     * @return String username
     */
    private static String getTodoListUsername() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        return prefs.get(PREFERENCES_USERNAME, null);
    }

    /**
     * Returns the autoSave option from the menu
     * The preference is read from the OS specific registry. If no such
     * preference can be found, the default is returned.
     *
     * @return boolean The autoSave option
     */
    private static boolean getAutoSave() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        return prefs.getBoolean(PREFERENCES_AUTOSAVE, AUTO_SAVE_DEFAULT);
    }

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     *
     * @param file the file or null to remove the path
     */
    private void setTodoListFilePath(final File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put(PREFERENCES_FILE_PATH, file.getPath());

            // Update the stage title.
            primaryStage.setTitle(APP_NAME + " - " + file.getName());
        } else {
            prefs.remove(PREFERENCES_FILE_PATH);

            // Update the stage title.
            primaryStage.setTitle(APP_NAME);
        }
    }

    /**
     * Sets the username of the currently loaded user. The path is persisted in
     * the OS specific registry.
     *
     * @param username the name of the user or null to remove the path
     */
    public void setTodoListUsername(final String username) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (username != null && !username.trim().equals("")) {
            prefs.put(PREFERENCES_USERNAME, username);
            this.username = username;
        } else {
            prefs.remove(PREFERENCES_USERNAME);
            this.username = null;
        }
    }

    /**
     * Sets the autoSave (of the todos list) menu option. The path is persisted in
     * the OS specific registry.
     *
     * @param todoListAutoSave boolean value indicating autoSave or not
     */
    public void setTodoListAutoSave(final boolean todoListAutoSave) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        prefs.putBoolean(PREFERENCES_AUTOSAVE, todoListAutoSave);
        this.autoSave = todoListAutoSave;
    }

    /**
     * Loads todos data from the specified file. The current todos data will
     * be replaced.
     *
     * @param file The todos file
     */
    public void loadTodosDataFromFile(final File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(TodoListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            TodoListWrapper wrapper = (TodoListWrapper) um.unmarshal(file);

            todoYearWeeks.clear();
            if (wrapper.getTodoYearWeeks() != null) {
                todoYearWeeks.addAll(wrapper.getTodoYearWeeks());
            } else {
                todoYearWeeks.add(new TodoYearWeek());
            }

            // Save the file path to the registry.
            setTodoListFilePath(file);

        } catch (Exception e) { // catches ANY exception
            LOG.error("Unable to load file: " + file.getPath(), e);
            new AlertBox(getPrimaryStage(), Alert.AlertType.ERROR, "Error",
                    "Kan de todo lijst data niet laden",
                    "Kan de data niet laden van bestand:\n" + file.getPath());
        }
    }

    /**
     * Saves the current todos data to the specified file.
     *
     * @param file The todos file
     */
    public void saveTodosDataToFile(final File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(TodoListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our todos data.
            TodoListWrapper wrapper = new TodoListWrapper();
            wrapper.setTodoYearWeeks(todoYearWeeks);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);

            // Save the file path to the registry.
            setTodoListFilePath(file);
        } catch (Exception e) { // catches ANY exception
            LOG.error("Unable to save file: " + file.getPath(), e);
            new AlertBox(getPrimaryStage(), Alert.AlertType.ERROR, "Error",
                    "Kan de ToDo lijst niet opslaan",
                    "Kan de data niet opslaan naar bestand:\n" + file.getPath());
        }
    }

    /**
     * Initializes a new Totos list
     */
    public void initNewTodoList() {
        getTodoYearWeeks().clear();
        setTodoListFilePath(null);
        getTodoYearWeeks().add(new TodoYearWeek());
        showTodoOverview();
    }

    public String getUsername() {
        return username;
    }

    public static String getAppName() {
        return APP_NAME;
    }

    public boolean isAutoSave() {
        return autoSave;
    }
}
