package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Adds tags to an existing person in the address book
 */
public class TagCommand extends Command {
    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds new tags to the person "
            + "identified by the index number used in the displayed person list. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TAG + "TAG... \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAG + "friends" + " "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_TAG_PERSON_SUCCESS = "Tagged person: %1$s with tags: %2$s";

    private final Index targetIndex;
    private final Set<Tag> addedTags;


    /**
     * @param index The Index of the person to tag
     * @param tagList The Set of Tags to add to the person
     */
    public TagCommand(Index index, Set<Tag> tagList) {
        this.targetIndex = index;
        this.addedTags = tagList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToTag = lastShownList.get(targetIndex.getZeroBased());
        String addedTagsString = tagSetToString(addedTags);
        // Union of existing tags and new tags
        addedTags.addAll(personToTag.getTags());

        // Creating new Person
        Person newPerson = new Person(personToTag.getName(), personToTag.getPhone(),
                personToTag.getEmail(), personToTag.getAddress(), addedTags);

        // Updating addressBook
        model.setPerson(personToTag, newPerson);
        return new CommandResult(String.format(MESSAGE_TAG_PERSON_SUCCESS, personToTag.getName(), addedTagsString));
    }

    /**
     * Utility method to convert Set of Tags to strings delimited by commas
     * @param tagList The Set of Tags to convert
     * @return String of tags delimited by commas
     */
    public static String tagSetToString(Set<Tag> tagList) {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<Tag> it = tagList.iterator();
        for (int i = 0; i < tagList.size(); i++) {
            Tag t = it.next();
            stringBuilder.append(t);
            if (i != tagList.size() - 1) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TagCommand)) {
            return false;
        }

        TagCommand otherTagCommand = (TagCommand) other;
        return Objects.equals(this.targetIndex, otherTagCommand.targetIndex)
                && Objects.equals(this.addedTags, otherTagCommand.addedTags);
    }
}