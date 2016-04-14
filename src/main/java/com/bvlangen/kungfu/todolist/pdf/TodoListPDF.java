package com.bvlangen.kungfu.todolist.pdf;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bvlangen.kungfu.todolist.model.DayOfWeek;
import com.bvlangen.kungfu.todolist.model.Todo;
import com.bvlangen.kungfu.todolist.model.TodoDay;
import com.bvlangen.kungfu.todolist.model.TodoWeek;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class TodoListPDF {

    private static final Logger LOG = LoggerFactory.getLogger(TodoListPDF.class);

    private static final String PDF_INPUT_FILE = "pdf/160210EWO-ToDoLijst_7-13.pdf";

    private static final HashMap<Todo, Integer> ABSOLUTE_Y_POSITION_TODO;
    private static final HashMap<DayOfWeek, Integer> ABSOLUTE_X_POSITION_DAY_OF_WEEK;
    static {
        // absolute y position initialisation
        ABSOLUTE_Y_POSITION_TODO = new HashMap<>(15);
        ABSOLUTE_Y_POSITION_TODO.put(Todo.BED_OPMAKEN,                                  504);
        ABSOLUTE_Y_POSITION_TODO.put(Todo.PERSOONLIJKE_DINGEN_OPRUIMEN,                 484);
        ABSOLUTE_Y_POSITION_TODO.put(Todo.VUILE_KLEREN_IN_DE_WAS_DOEN,                  464);
        ABSOLUTE_Y_POSITION_TODO.put(Todo.PERSOONLIJKE_HYGIENE,                         419);
        ABSOLUTE_Y_POSITION_TODO.put(Todo.SCHONE_KLEDING_EN_SCHOENEN,                   398);
        ABSOLUTE_Y_POSITION_TODO.put(Todo.GEZOND_ETEN,                                  376);
        ABSOLUTE_Y_POSITION_TODO.put(Todo.HUISWERK_MAKEN,                               328);
        ABSOLUTE_Y_POSITION_TODO.put(Todo.PE_INZET_GEBRUIKEN,                           305);
        ABSOLUTE_Y_POSITION_TODO.put(Todo.PE_RESPECT_TONEN_LERAREN_EN_VRIENDEN,         283);
        ABSOLUTE_Y_POSITION_TODO.put(Todo.TOEGEWEZEN_TAKEN_AFMAKEN,                     236);
        ABSOLUTE_Y_POSITION_TODO.put(Todo.PE_RESPECT_TONEN_HELE_GEZIN,                  214);
        ABSOLUTE_Y_POSITION_TODO.put(Todo.JONGERE_BROER_OF_ZUS_HELPEN,                  192);
        ABSOLUTE_Y_POSITION_TODO.put(Todo.VIJFTIEN_MIN_WINGTC_EZCRIMA_TRAINEN,          145);
        ABSOLUTE_Y_POSITION_TODO.put(Todo.VOOR_KORTE_TERMIJN_WAARDEVOL_DOEL_STELLEN,    123);
        ABSOLUTE_Y_POSITION_TODO.put(Todo.BLIJF_LETTEN_OP_DE_GESCHREVEN_DOELEN,         101);

        // absolute x position initialisation
        ABSOLUTE_X_POSITION_DAY_OF_WEEK = new HashMap<>(7);
        ABSOLUTE_X_POSITION_DAY_OF_WEEK.put(DayOfWeek.MAANDAG,          300);
        ABSOLUTE_X_POSITION_DAY_OF_WEEK.put(DayOfWeek.DINSDAG,          340);
        ABSOLUTE_X_POSITION_DAY_OF_WEEK.put(DayOfWeek.WOENSDAG,         380);
        ABSOLUTE_X_POSITION_DAY_OF_WEEK.put(DayOfWeek.DONDERDAG,        420);
        ABSOLUTE_X_POSITION_DAY_OF_WEEK.put(DayOfWeek.VRIJDAG,          460);
        ABSOLUTE_X_POSITION_DAY_OF_WEEK.put(DayOfWeek.ZATERDAG,         500);
        ABSOLUTE_X_POSITION_DAY_OF_WEEK.put(DayOfWeek.ZONDAG,           540);
    }
    private static final int[] POSITION_ARRAY_NAME_FIELD = {92, 674, 500, 50};
    private static final int[] POSITION_ARRAY_DATE_FIELD = {373, 674, 600, 100};

    private final TodoWeek todoWeek;

    private Image check;
    private File tempFile;
    private PdfStamper stamper;
    private PdfContentByte overContent;

    public TodoListPDF(final TodoWeek todoWeek) throws TodoListException {
        this.todoWeek = todoWeek;
        init();
    }

    /**
     * Generates the PDF document and opens it in the application
     * registered for opening PDF on the operating system
     * @throws TodoListException If generation fails
     */
    public void generatePdf() throws TodoListException {
        LOG.info("Generating PDF");

        addTextAtPosition(
                POSITION_ARRAY_NAME_FIELD[0], POSITION_ARRAY_NAME_FIELD[1],
                POSITION_ARRAY_NAME_FIELD[2], POSITION_ARRAY_NAME_FIELD[3],
                todoWeek.getNaam());

        addTextAtPosition(
                POSITION_ARRAY_DATE_FIELD[0], POSITION_ARRAY_DATE_FIELD[1],
                POSITION_ARRAY_DATE_FIELD[2], POSITION_ARRAY_DATE_FIELD[3],
                todoWeek.getDatumVanTot());

        for (TodoDay todoDay : todoWeek.getWeekdays()) {
            for (Todo todo : todoDay.getDoneToday()) {
                addCheck(todoDay, todo);
            }
        }
        closeDocument();
        openPdf();
    }

    /**
     * Opens the PDF in the application that is registered for it on the operating system.
     * @throws TodoListException If no application is registered on the operating system to open the PDF
     */
    private void openPdf() throws TodoListException {
        LOG.info("Opening PDF");
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(tempFile);
            } catch (IOException ex) {
                String message = "Opening PDF failed. There appears to be no application registered to open PDF files";
                LOG.error(message, ex);
                throw new TodoListException(message, ex);
            }
        }
    }

    private void init() throws TodoListException {
        createTempFile();
        initPdfOverContentWriting();
        initCheckImage();
    }

    private void initCheckImage() throws TodoListException {
        final URL checkImageUrl = getClass().getClassLoader().getResource("image/check.png");
        if (checkImageUrl == null) {
            LOG.error("Unable to load check image");
            throw new TodoListException("Check image cannot be loaded.");
        }
        try {
            this.check = Image.getInstance(checkImageUrl);
        } catch (BadElementException | IOException e) {
            final String message = "Unable to load image";
            LOG.error(message, e);
            throw new TodoListException(message, e);
        }
        check.scalePercent(16); // scale the image
    }

    private void initPdfOverContentWriting() throws TodoListException {
        try {
            this.stamper = new PdfStamper(new PdfReader(PDF_INPUT_FILE), new FileOutputStream( tempFile ));
        } catch (DocumentException | IOException e) {
            final String message = "Unable to create PDF overcontent";
            LOG.error(message, e);
            throw new TodoListException(message, e);
        }
        this.overContent = stamper.getOverContent(1); // first page
    }

    private void createTempFile() throws TodoListException {
        try {
            tempFile = File.createTempFile("tempfile", ".pdf");
        } catch (IOException e) {
            final String message = "Unable to create temporary file";
            LOG.error(message, e);
            throw new TodoListException(message, e);
        }
        tempFile.deleteOnExit(); // delete file on JVM exit
        LOG.info("Created temp file: {}", tempFile.getAbsolutePath());
    }

    private void addCheck(final TodoDay todoDay, final Todo todo) throws TodoListException {
        check.setAbsolutePosition(
                ABSOLUTE_X_POSITION_DAY_OF_WEEK.get(todoDay.getDayOfWeek()),
                ABSOLUTE_Y_POSITION_TODO.get(todo));
        try {
            overContent.addImage(check);
        } catch (DocumentException e) {
            final String message = "Unable to add check image to PDF";
            LOG.error(message, e);
            throw new TodoListException(message, e);
        }
    }

    private void addTextAtPosition(final int llx, final int lly, final int urx, final int ury, final String text) throws TodoListException {
        ColumnText ct = new ColumnText(overContent);
        ct.setSimpleColumn(llx, lly, urx, ury);
        ct.setText(new Phrase(text));
        try {
            ct.go();
        } catch (DocumentException e) {
            final String message = "Unable to add text at a position to the PDF";
            LOG.error(message, e);
            throw new TodoListException(message, e);
        }
    }

    private void closeDocument() throws TodoListException {
        LOG.info("Finalizing PDF");
        try {
            stamper.close();
        } catch (DocumentException | IOException e) {
            final String message = "Unable to close the overcontent stamper of the PDF";
            LOG.error(message, e);
            throw new TodoListException(message, e);
        }
    }

}
