package com.bvlangen.kungfu.todolist.view.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bvlangen.kungfu.todolist.MainApp;
import com.bvlangen.kungfu.todolist.model.DayOfWeek;
import com.bvlangen.kungfu.todolist.model.Todo;
import com.bvlangen.kungfu.todolist.model.TodoWeek;
import com.bvlangen.kungfu.todolist.model.TodoYearWeek;
import com.bvlangen.kungfu.todolist.pdf.TodoListException;
import com.bvlangen.kungfu.todolist.pdf.TodoListPDF;
import com.bvlangen.kungfu.todolist.util.DateUtil;
import com.bvlangen.kungfu.todolist.view.dialog.AlertBox;


public class TodoOverviewController {

    private static final Logger LOG = LoggerFactory.getLogger(TodoOverviewController.class);

    private static final Map<String, Todo> TODO_ATTRIBUTE_NAME_TODO_MAPPING = new HashMap<>();
    static {
        TODO_ATTRIBUTE_NAME_TODO_MAPPING.put("BedOpmaken", Todo.BED_OPMAKEN);
        TODO_ATTRIBUTE_NAME_TODO_MAPPING.put("PersoonlijkeDingenOpruimen", Todo.PERSOONLIJKE_DINGEN_OPRUIMEN);
        TODO_ATTRIBUTE_NAME_TODO_MAPPING.put("VuileKlerenInDeWasDoen", Todo.VUILE_KLEREN_IN_DE_WAS_DOEN);
        TODO_ATTRIBUTE_NAME_TODO_MAPPING.put("PersoonlijkeHygiene", Todo.PERSOONLIJKE_HYGIENE);
        TODO_ATTRIBUTE_NAME_TODO_MAPPING.put("SchoneKledingEnSchoenen", Todo.SCHONE_KLEDING_EN_SCHOENEN);
        TODO_ATTRIBUTE_NAME_TODO_MAPPING.put("GezondEten", Todo.GEZOND_ETEN);
        TODO_ATTRIBUTE_NAME_TODO_MAPPING.put("HuiswerkMaken", Todo.HUISWERK_MAKEN);
        TODO_ATTRIBUTE_NAME_TODO_MAPPING.put("PeInzetGebruiken", Todo.PE_INZET_GEBRUIKEN);
        TODO_ATTRIBUTE_NAME_TODO_MAPPING.put("PeRespectTonenLerarenEnVrienden", Todo.PE_RESPECT_TONEN_LERAREN_EN_VRIENDEN);
        TODO_ATTRIBUTE_NAME_TODO_MAPPING.put("ToegewezenTakenAfmaken", Todo.TOEGEWEZEN_TAKEN_AFMAKEN);
        TODO_ATTRIBUTE_NAME_TODO_MAPPING.put("PeRespectTonenHeleGezin", Todo.PE_RESPECT_TONEN_HELE_GEZIN);
        TODO_ATTRIBUTE_NAME_TODO_MAPPING.put("JongereBroerOfZusHelpen", Todo.JONGERE_BROER_OF_ZUS_HELPEN);
        TODO_ATTRIBUTE_NAME_TODO_MAPPING.put("15MinWingTcEzcrimaTrainen", Todo.VIJFTIEN_MIN_WINGTC_EZCRIMA_TRAINEN);
        TODO_ATTRIBUTE_NAME_TODO_MAPPING.put("VoorKorteTermijnWaardevolDoelStellen", Todo.VOOR_KORTE_TERMIJN_WAARDEVOL_DOEL_STELLEN);
        TODO_ATTRIBUTE_NAME_TODO_MAPPING.put("BlijfLettenOpDeGeschrevenDoelen", Todo.BLIJF_LETTEN_OP_DE_GESCHREVEN_DOELEN);
    }

    private final ReflectionBasedHelper reflectionBasedHelper = new ReflectionBasedHelper(this);

    @FXML
    private TableView<TodoYearWeek> todoOverviewTable;
    @FXML
    private TableColumn<TodoYearWeek, Integer> yearColumn;
    @FXML
    private TableColumn<TodoYearWeek, Integer> weekNrColumn;
    @FXML
    private Label datum;
    @FXML
    private CheckBox maBedOpmaken, maPersoonlijkeDingenOpruimen, maVuileKlerenInDeWasDoen, maPersoonlijkeHygiene,
            maSchoneKledingEnSchoenen, maGezondEten, maHuiswerkMaken, maPeInzetGebruiken, maPeRespectTonenLerarenEnVrienden,
            maToegewezenTakenAfmaken, maPeRespectTonenHeleGezin, maJongereBroerOfZusHelpen, ma15MinWingTcEzcrimaTrainen,
            maVoorKorteTermijnWaardevolDoelStellen, maBlijfLettenOpDeGeschrevenDoelen;
    @FXML
    private CheckBox diBedOpmaken, diPersoonlijkeDingenOpruimen, diVuileKlerenInDeWasDoen, diPersoonlijkeHygiene,
            diSchoneKledingEnSchoenen, diGezondEten, diHuiswerkMaken, diPeInzetGebruiken, diPeRespectTonenLerarenEnVrienden,
            diToegewezenTakenAfmaken, diPeRespectTonenHeleGezin, diJongereBroerOfZusHelpen, di15MinWingTcEzcrimaTrainen,
            diVoorKorteTermijnWaardevolDoelStellen, diBlijfLettenOpDeGeschrevenDoelen;
    @FXML
    private CheckBox woBedOpmaken, woPersoonlijkeDingenOpruimen, woVuileKlerenInDeWasDoen, woPersoonlijkeHygiene,
            woSchoneKledingEnSchoenen, woGezondEten, woHuiswerkMaken, woPeInzetGebruiken, woPeRespectTonenLerarenEnVrienden,
            woToegewezenTakenAfmaken, woPeRespectTonenHeleGezin, woJongereBroerOfZusHelpen, wo15MinWingTcEzcrimaTrainen,
            woVoorKorteTermijnWaardevolDoelStellen, woBlijfLettenOpDeGeschrevenDoelen;
    @FXML
    private CheckBox doBedOpmaken, doPersoonlijkeDingenOpruimen, doVuileKlerenInDeWasDoen, doPersoonlijkeHygiene,
            doSchoneKledingEnSchoenen, doGezondEten, doHuiswerkMaken, doPeInzetGebruiken, doPeRespectTonenLerarenEnVrienden,
            doToegewezenTakenAfmaken, doPeRespectTonenHeleGezin, doJongereBroerOfZusHelpen, do15MinWingTcEzcrimaTrainen,
            doVoorKorteTermijnWaardevolDoelStellen, doBlijfLettenOpDeGeschrevenDoelen;
    @FXML
    private CheckBox vrBedOpmaken, vrPersoonlijkeDingenOpruimen, vrVuileKlerenInDeWasDoen, vrPersoonlijkeHygiene,
            vrSchoneKledingEnSchoenen, vrGezondEten, vrHuiswerkMaken, vrPeInzetGebruiken, vrPeRespectTonenLerarenEnVrienden,
            vrToegewezenTakenAfmaken, vrPeRespectTonenHeleGezin, vrJongereBroerOfZusHelpen, vr15MinWingTcEzcrimaTrainen,
            vrVoorKorteTermijnWaardevolDoelStellen, vrBlijfLettenOpDeGeschrevenDoelen;
    @FXML
    private CheckBox zaBedOpmaken, zaPersoonlijkeDingenOpruimen, zaVuileKlerenInDeWasDoen, zaPersoonlijkeHygiene,
            zaSchoneKledingEnSchoenen, zaGezondEten, zaHuiswerkMaken, zaPeInzetGebruiken, zaPeRespectTonenLerarenEnVrienden,
            zaToegewezenTakenAfmaken, zaPeRespectTonenHeleGezin, zaJongereBroerOfZusHelpen, za15MinWingTcEzcrimaTrainen,
            zaVoorKorteTermijnWaardevolDoelStellen, zaBlijfLettenOpDeGeschrevenDoelen;
    @FXML
    private CheckBox zoBedOpmaken, zoPersoonlijkeDingenOpruimen, zoVuileKlerenInDeWasDoen, zoPersoonlijkeHygiene,
            zoSchoneKledingEnSchoenen, zoGezondEten, zoHuiswerkMaken, zoPeInzetGebruiken, zoPeRespectTonenLerarenEnVrienden,
            zoToegewezenTakenAfmaken, zoPeRespectTonenHeleGezin, zoJongereBroerOfZusHelpen, zo15MinWingTcEzcrimaTrainen,
            zoVoorKorteTermijnWaardevolDoelStellen, zoBlijfLettenOpDeGeschrevenDoelen;

    // reference to main application
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public TodoOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the todos year/week table with two columns.
        yearColumn.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asObject());
        weekNrColumn.setCellValueFactory(cellData -> cellData.getValue().weekNrProperty().asObject());

        // Clear todos detail
        showTodoDetail(null);

        // Listen for selection changes and show the todos details when changed.
        todoOverviewTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showTodoDetail(newValue));

        // Add lister for every checkbox. This uses refection to do so
        reflectionBasedHelper.addListenerForEveryCheckBoxInClass();

        // Select current year/week or when not available first row
        Platform.runLater(() -> {
            ObservableList<TodoYearWeek> items = todoOverviewTable.getItems();
            if (!items.isEmpty()) {
                boolean currentYearWeekItemSelected = false;
                for (TodoYearWeek item : items) {
                    final LocalDate now = LocalDate.now();
                    if (DateUtil.getYear(now) == item.getYear() &&
                            DateUtil.getWeekOfYear(now) == item.getWeekNr()) {
                        todoOverviewTable.requestFocus();
                        todoOverviewTable.getSelectionModel().select(item);
                        currentYearWeekItemSelected = true;
                        break;
                    }
                }
                if (!currentYearWeekItemSelected) {
                    todoOverviewTable.requestFocus();
                    todoOverviewTable.getSelectionModel().selectLast();
                    //todoOverviewTable.getFocusModel().focus(1);
                }
            }
        });
    }

    private void showTodoDetail(final TodoYearWeek todoYearWeek) {
        if (todoYearWeek != null) {
            datum.setText(todoYearWeek.getWeekDateFromTo());
            reflectionBasedHelper.setSelectedForCheckBox(todoYearWeek);
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to add
     * details for a new todos week.
     */
    @FXML
    private void handleNewWeek() {
        final TodoYearWeek newTodoYearWeek = new TodoYearWeek(LocalDate.now());
        boolean okClicked = mainApp.showAddWeekDialog(newTodoYearWeek);
        if (okClicked) {
            mainApp.getTodoYearWeeks().add(newTodoYearWeek);
            // sort the table again after adding the new item
            mainApp.getTodoYearWeeks().sort(TodoYearWeek.getTodoYearWeekComparator());
        }
    }

    /**
     * Called when the user clicks the maak PDF button. Creates a todos PDF
     * and opens it
     */
    @FXML
    private void handleMaakPdf() {

        if (!usernameSet()) return;

        final String weekDateFromTo = todoOverviewTable.getSelectionModel().getSelectedItem().getWeekDateFromTo();
        final TodoWeek todoWeek = new TodoWeek(mainApp.getUsername(), weekDateFromTo);
        for (CheckBox checkbox : reflectionBasedHelper.getCheckboxes()) {
            final String id = checkbox.getId();
            final String dag = id.substring(0,2);
            final String todoAttributeName = id.substring(2);

            final Todo todo = TODO_ATTRIBUTE_NAME_TODO_MAPPING.get(todoAttributeName);
            switch (dag) {
                case "ma":
                    initPdfField(todoWeek, checkbox, DayOfWeek.MAANDAG, todo);
                    break;
                case "di":
                    initPdfField(todoWeek, checkbox, DayOfWeek.DINSDAG, todo);
                    break;
                case "wo":
                    initPdfField(todoWeek, checkbox, DayOfWeek.WOENSDAG, todo);
                    break;
                case "do":
                    initPdfField(todoWeek, checkbox, DayOfWeek.DONDERDAG, todo);
                    break;
                case "vr":
                    initPdfField(todoWeek, checkbox, DayOfWeek.VRIJDAG, todo);
                    break;
                case "za":
                    initPdfField(todoWeek, checkbox, DayOfWeek.ZATERDAG, todo);
                    break;
                case "zo":
                    initPdfField(todoWeek, checkbox, DayOfWeek.ZONDAG, todo);
                    break;
                default:
                    LOG.error("Unable to determine the weekday of the checkbox variable. Cannot generate PDF");
                    new AlertBox(getMainApp().getPrimaryStage(), Alert.AlertType.ERROR, "Oeps", "Programma error", "Neem contact op met Bert van Langen");
                    return;
            }
        }

        final TodoListPDF todoListPDF;
        try {
            todoListPDF = new TodoListPDF(todoWeek);
            todoListPDF.generatePdf();
        } catch (TodoListException e) {
            LOG.error("Unable to generate PDF; sure there's a program associated like Adobe?", e);
            new AlertBox(mainApp.getPrimaryStage(), Alert.AlertType.ERROR, "Kan geen PDF openen", "Geen programma gevonden om een PDF mee te openen",
                    "Zorg ervoor dat u een PDF reader heeft geinstalleerd, zoals Adobe Acrobat Reader");
        }

    }

    private boolean usernameSet() {
        if (mainApp.getUsername() == null) {
            new AlertBox(mainApp.getPrimaryStage(), Alert.AlertType.WARNING,
                    "ToDo lijst eigenaar niet bekend",
                    "Stel de eigenaar van de ToDo lijst in",
                    "Ga naar menu - Instellingen - Gebruiker om de gebruiker in te stellen");
            return false;
        }
        return true;
    }

    private static void initPdfField(final TodoWeek todoWeek, final CheckBox checkbox, final DayOfWeek dayOfWeek, final Todo todo) {
        if (checkbox.isSelected()) {
            todoWeek.getTodoDay(dayOfWeek).done(todo);
        }
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp The main application
     */
    public void setMainApp(final MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        todoOverviewTable.setItems(mainApp.getTodoYearWeeks());
    }

    TableView<TodoYearWeek> getTodoOverviewTable() {
        return todoOverviewTable;
    }

    public MainApp getMainApp() {
        return mainApp;
    }
}
