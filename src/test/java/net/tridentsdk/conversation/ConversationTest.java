package net.tridentsdk.conversation;

import net.tridentsdk.meta.ChatColor;
import org.junit.Test;

import java.util.regex.Pattern;

public class ConversationTest {

    @Test
    public void testConversation() throws Exception {
        ConversationThen stringThen = () -> System.out.println("Entered The String");
        ConversationThen integerThen = () -> System.out.println("Entered The Integer");
        ConversationThen doubleThen = () -> System.out.println("Entered The Double");
        ConversationThen extensionThen = () -> System.out.println("Entered The Extension");
        ConversationThen patternThen = () -> System.out.println("Entered The Pattern");
        ConversationThen enumThen = () -> System.out.println("Enum returned false, because...");

        ConversationExtension extension = () -> "Some dynamic or static message";

        Conversation.builder().
                prefix("[XYZ]").
                color(ChatColor.AQUA).
                caseIf("Something").ignoreCase(false).then(stringThen).
                caseIf(12345).then(integerThen).
                caseIf(123.45).then(doubleThen).
                caseIf(extension).ignoreCase(true).then(extensionThen).
                caseIf(ConversationTestEnum.class).then(enumThen).
                caseIf(Pattern.compile("[a-zA-Z0-9]")).then(patternThen).
                suffix("...").
                start(null /* Player Object */);
    }

    public enum ConversationTestEnum implements ConversationEnum {

        SWORD, AXE, SHOVEL;

        @Override
        public String getInputMessage() {
            return this.name();
        }

        @Override
        public boolean execute() {
            System.out.println("Entered " + this.name());
            return true;
        }

    }

}