package net.tridentsdk.conversation.cases;

import net.tridentsdk.conversation.Conversation;
import net.tridentsdk.conversation.ConversationThen;

public abstract class ConversationCase {

    private Conversation conversation;
    private ConversationThen then;

    public ConversationCase(Conversation conversation) {
        this.conversation = conversation;
    }

    public Conversation then(ConversationThen then){
        return conversation;
    }

    public abstract boolean matchCase(String message);

}
