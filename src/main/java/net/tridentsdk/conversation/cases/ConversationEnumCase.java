package net.tridentsdk.conversation.cases;

import net.tridentsdk.conversation.Conversation;
import net.tridentsdk.conversation.ConversationEnum;

public class ConversationEnumCase extends ConversationStringularCase<ConversationEnumCase> {

    private Class<? extends Enum<? extends ConversationEnum>> enumClass;

    public ConversationEnumCase(Conversation conversation, Class<? extends Enum<? extends ConversationEnum>> enumClass) {
        super(conversation);
        this.enumClass = enumClass;
    }

    @Override
    public boolean matchCase(String message) {
        for(Enum<? extends ConversationEnum> constant : enumClass.getEnumConstants()){
            if(matchCaseAgainst(message, ((ConversationEnum) constant).getInputMessage())){
                return true;
            }
        }

        return false;
    }

}
