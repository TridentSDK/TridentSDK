package net.tridentsdk.conversation.cases;

import net.tridentsdk.conversation.Conversation;
import net.tridentsdk.conversation.ConversationExtension;

public class ConversationExtensionCase extends ConversationStringularCase<ConversationExtensionCase> {

    private ConversationExtension extension;

    public ConversationExtensionCase(Conversation conversation, ConversationExtension extension) {
        super(conversation);
        this.extension = extension;
    }

    @Override
    public boolean matchCase(String message) {
        return super.matchCaseAgainst(message, this.extension.getInputMessage());
    }

}
