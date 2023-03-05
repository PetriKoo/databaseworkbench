
/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
// One file for each table from database
public class {[table]}Bean {

    // going for each field from table to do private variables
    {foreach field}
    private {[field.lang.Java]} {[field.name]}; // {[field.label]}
    {/fieldforeach}

    // going for each field from table again for getters and setters
    {foreach field}
    public void set{[field.name]} ({[field.lang.Java]} value) { this.{[field.name]} = value; }
    public {[field.lang.Java]} get{[field.name]}() { return this.{[field.name]}; }
    {/fieldforeach}

}
