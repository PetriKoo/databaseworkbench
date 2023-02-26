# databaseworkbench

- Programmed with NetBeans
- JAXB 2.3.5 library needed
- JDK 11

main class: databaseworkbench.DatabaseWorkbench

Some explaining maybe needed for this software:
- "Field types" what you add thru settings menu, are your own generic field types, example ID, first name, lastname
- "Languages" are what you gonna program, C++, C#, Php, Java, SQL, what ever you like, add those.
- "Codes", this is where this getting interesting. your own added field types can be specified to your programming languages, 
  * for example: ID named field type can be specified to your programming languages, SQL can be "long", but in C++ it can be "long int"
  * ZipCode named field type can be in SQL varchar(5), but in java language it would be only String typed.
  * Get it?
  
When the Codes side is finished, you can with this program create not only sql files, but any programming language files you ever like.

Using program:
before anything, start with Settings menu:
- add some Field types, like ID, FirstName, LastName, what ever.
- add some Languages what you program, java? ;)


example for future:

// One file for each table from database, table would be replaced by table's name

public class {table}Bean {

    // going for each field from table to do private variables
    {foreach field}
    // java as my own named prog language and that field, well you know, table's field name
    private {[java]} {field};
    {/foreach}

    // going for each field from table again for getters and setters
    {foreach field}
    public void set{field} ({java} value) { this.{field} = value; }
    public {java} get{field}() { return this.{field}; }
    {/foreach}

}

far future:
- Import databases from server or sql files
