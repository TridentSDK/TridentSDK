package net.tridentsdk.conversation.cases;

import net.tridentsdk.conversation.Conversation;

public class ConversationStringCase extends ConversationStringularCase<ConversationStringCase> {

    private String message;

    public ConversationStringCase(Conversation conversation, String message) {
        super(conversation);
        this.message = message;
    }

    @Override
    public boolean matchCase(String message) {
        return super.matchCaseAgainst(message, this.message);
    }

}
