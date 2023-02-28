
/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
// One file for each table from database
public class {[table]}Bean {

    // going for each field from table to do private variables
    {foreach field}
    private {[Java]} {[field]};
    {/foreach}

    // going for each field from table again for getters and setters
    {foreach field}
    public void set{[field]} ({[Java]} value) { this.{[field]} = value; }
    public {[Java]} get{[field]}() { return this.{[field]}; }
    {/foreach}

}
