package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.UntagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new UntagCommand object.
 */
public class UntagCommandParser implements Parser<UntagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UntagCommand
     * and returns an UntagCommand object for execution.
     * @param args the user input arguments
     * @return UntagCommand object to be executed
     * @throws ParseException if the user input does not conform to the expected format
     */
    public UntagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_TAG);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UntagCommand.MESSAGE_USAGE), ive);
        }

        // Handle the case where the user inputs "all" to remove all tags where null set signifies removing all tags
        if (argMultimap.getAllValues(PREFIX_TAG).size() == 1
                && argMultimap.getAllValues(PREFIX_TAG).get(0).equalsIgnoreCase("all")) {

            return new UntagCommand(index, null);
        }

        Set<Tag> tagsToRemove = argMultimap.getAllValues(PREFIX_TAG).stream()
                .flatMap(tagString -> Arrays.stream(tagString.split("\\s+")))
                .map(Tag::new)
                .collect(Collectors.toSet());

        if (tagsToRemove.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UntagCommand.MESSAGE_USAGE));
        }

        return new UntagCommand(index, tagsToRemove);
    }

}
