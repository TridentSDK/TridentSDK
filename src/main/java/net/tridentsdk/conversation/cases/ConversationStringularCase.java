package net.tridentsdk.conversation.cases;

import net.tridentsdk.conversation.Conversation;

// TODO Find a better name...
public abstract class ConversationStringularCase<T extends ConversationStringularCase> extends ConversationCase {

    private boolean ignoreCase = true;

    public ConversationStringularCase(Conversation conversation) {
        super(conversation);
    }

    public T ignoreCase(boolean ignoreCase){
        this.ignoreCase = ignoreCase;
        return (T) this;
    }

    protected boolean matchCaseAgainst(String message, String against) {
        return ignoreCase ? message.equalsIgnoreCase(against) : message.equals(against);
    }

}
