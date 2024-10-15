package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.TAG_SET_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_SET_FRIENDS;
import static seedu.address.logic.commands.CommandTestUtil.TAG_SET_FRIENDS_OWESMONEY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonTaggedWithPredicate;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code FilterCommand}.
 */
public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_oneValidTag_success() {
        FilterCommand command = new FilterCommand(TAG_SET_FRIENDS);

        String expectedMessage = String.format(FilterCommand.MESSAGE_SUCCESS, TAG_SET_FRIENDS);
        PersonTaggedWithPredicate predicate = new PersonTaggedWithPredicate(TAG_SET_FRIENDS);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_twoValidTags_success() {
        FilterCommand command = new FilterCommand(TAG_SET_FRIENDS_OWESMONEY);

        String expectedMessage = String.format(FilterCommand.MESSAGE_SUCCESS, TAG_SET_FRIENDS_OWESMONEY);
        PersonTaggedWithPredicate predicate = new PersonTaggedWithPredicate(TAG_SET_FRIENDS_OWESMONEY);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }


    @Test
    public void equals() {
        FilterCommand filterTagFriends = new FilterCommand(TAG_SET_FRIENDS);
        FilterCommand filterTagFriendsOwesMoney = new FilterCommand(TAG_SET_FRIENDS_OWESMONEY);

        // same object -> returns true
        assertTrue(filterTagFriends.equals(filterTagFriends));

        // same values -> returns true
        FilterCommand filterTagFriendCopy = new FilterCommand(TAG_SET_FRIENDS);
        assertTrue(filterTagFriends.equals(filterTagFriendCopy));

        // different types -> returns false
        assertFalse(filterTagFriends.equals(1));

        // null -> returns false
        assertFalse(filterTagFriends.equals(null));

        // different set of tags -> returns false
        assertFalse(filterTagFriends.equals(filterTagFriendsOwesMoney));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        FilterCommand filterCommand = new FilterCommand(TAG_SET_FRIEND);
        String expected = FilterCommand.class.getCanonicalName() + "{tagSet=" + TAG_SET_FRIEND + "}";
        assertEquals(expected, filterCommand.toString());
    }

}
