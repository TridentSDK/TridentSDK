package net.tridentsdk.plugin.Command;

import net.tridentsdk.plugin.TridentPlugin;

/**
 * Methods in this must be formatted as a public method returning void, and take three arguments:
 *
 * CommandIssuer issuer (or a more specific version if you qualify it in SubCommandDescription)
 * String arguments - the arguments to this subcommand
 * String alias - the text that makes up this command and the subcommand
 */
public class SubCommandHandler {
    protected TridentPlugin owner;

    public SubCommandHandler() {

    }

    public TridentPlugin getOwner() {
        return this.owner;
    }
}
