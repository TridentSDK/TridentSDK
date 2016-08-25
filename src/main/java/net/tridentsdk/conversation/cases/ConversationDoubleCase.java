package net.tridentsdk.conversation.cases;

import net.tridentsdk.conversation.Conversation;

public class ConversationDoubleCase extends ConversationCase {

    private double message;

    public ConversationDoubleCase(Conversation conversation, double message) {
        super(conversation);
        this.message = message;
    }

    @Override
    public boolean matchCase(String message) {
        try{
            Double.parseDouble(message);
            return true;
        }catch (Exception ignored){
        }

        return false;
    }

}
