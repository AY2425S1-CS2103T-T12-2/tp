package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2425s1-cs2103t-t12-2.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = """
            Replace UPPERCASE content with relevant details. Details in Square Brackets are OPTIONAL to provide.
                1. help: Shows a basic help message with a link to the PlanPerfect User Guide for advanced support.
                2. add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG1 TAG2 ...]: Adds a contact with up to 6 tags.
                3. list: Shows all saved contacts.
                4. edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS]: Edits a contact’s personal detail(s).
                5. tag INDEX t/TAG1 [TAG2 ...] : Adds between 1 to 6 tags to a contact.
                6. untag INDEX t/TAG [TAG2 ...] : Removes the specified tags from a contact.
                7. filter t/TAG [TAG2 ...]: Filters contacts by the specified tag(s).
                8. find KEYWORD1 [KEYWORD2] ... : Finds contacts with any of the given keywords in their name.
                9. delete INDEX: Deletes the contact in the specified index.
                10. clear: Clears all contacts (following a confirmation message).
                11. exit: Exits the program.
            \n
            For more detailed help, refer to the PlanPerfect User Guide:""" + ' ' + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
