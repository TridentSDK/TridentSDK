package net.tridentsdk.conversation.cases;

import net.tridentsdk.conversation.Conversation;

public class ConversationIntegerCase extends ConversationCase {

    private int message;

    public ConversationIntegerCase(Conversation conversation, int message) {
        super(conversation);
        this.message = message;
    }

    @Override
    public boolean matchCase(String message) {
        try{
            Integer.parseInt(message);
            return true;
        }catch (Exception ignored){
        }

        return false;
    }

}
