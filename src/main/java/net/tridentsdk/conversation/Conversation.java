package net.tridentsdk.conversation;

import net.tridentsdk.conversation.cases.*;
import net.tridentsdk.entity.living.Player;
import net.tridentsdk.meta.ChatColor;
import net.tridentsdk.meta.Message;
import net.tridentsdk.meta.MessageBuilder;

import java.util.HashMap;
import java.util.regex.Pattern;

public class Conversation {

    private Message prefix = null;
    private Message suffix = null;
    private ChatColor color = null;

    private HashMap<Integer, ConversationCase> cases = new HashMap<>();

    public static Conversation builder(){
        return new Conversation();
    }

    public ConversationStringCase caseIf(String message){
        return new ConversationStringCase(this, message);
    }

    public ConversationIntegerCase caseIf(int message){
        return new ConversationIntegerCase(this, message);
    }

    public ConversationDoubleCase caseIf(double message){
        return new ConversationDoubleCase(this, message);
    }

    public ConversationExtensionCase caseIf(ConversationExtension extension){
        return new ConversationExtensionCase(this, extension);
    }

    public ConversationEnumCase caseIf(Class<? extends Enum<? extends ConversationEnum>> enumClass){
        return new ConversationEnumCase(this, enumClass);
    }

    public ConversationPatternCase caseIf(Pattern pattern) {
        return new ConversationPatternCase(this, pattern);
    }

    public Message prefix(){
        return prefix;
    }

    public Conversation prefix(String prefix){
        this.prefix = new Message().text(prefix);
        return this;
    }

    public Conversation prefix(Message prefix){
        this.prefix = prefix;
        return this;
    }

    public Message suffix(){
        return suffix;
    }

    public Conversation suffix(String suffix){
        this.suffix = new Message().text(suffix);
        return this;
    }

    public Conversation suffix(Message suffix){
        this.suffix = suffix;
        return this;
    }

    public ChatColor color(){
        return color;
    }

    public Conversation color(ChatColor color){
        this.color = color;
        return this;
    }

    public void start(Player player){
        // TODO Implement
    }

    protected MessageBuilder getFinalMessage(Message message){
        MessageBuilder finalMessage = new MessageBuilder("");

        if(prefix != null){
            finalMessage.then(prefix);
        }

        if(color != null){
            message.color(color);
        }

        finalMessage.then(message);

        if(suffix != null){
            finalMessage.then(suffix);
        }

        finalMessage.build();

        return finalMessage;
    }

}
