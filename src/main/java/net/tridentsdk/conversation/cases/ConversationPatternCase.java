package net.tridentsdk.conversation.cases;

import net.tridentsdk.conversation.Conversation;

import java.util.regex.Pattern;

public class ConversationPatternCase extends ConversationCase {

    private Pattern pattern;

    public ConversationPatternCase(Conversation conversation, Pattern pattern) {
        super(conversation);
        this.pattern = pattern;
    }

    @Override
    public boolean matchCase(String message) {
        return pattern.matcher(message).find();
    }

}
