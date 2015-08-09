package net.tridentsdk.crafting;

import net.tridentsdk.inventory.Item;

/**
 * TODO Write Description
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public class CraftTuple {

    private char reference;
    private Item source;

    public CraftTuple(char reference, Item source){
        this.reference = reference;
        this.source = source;
    }

    public Item source(){
        return source;
    }

    public char reference(){
        return reference;
    }
}
