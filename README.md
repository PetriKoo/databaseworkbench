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

what works so far on a template between {foreach field} and {/fieldforeach}:</br>
- {[field.name]}</br>
- {[field.label]}</br>
- {[field.description]}</br>
- {[field.default_value]}</br>
- {[field.lang.*]} * = what lang you have added</br>
- {[field.auto_increments]} if true, then adds what value has been set on settings -> miscellaneous</br>
- {[field.not_null]} if true, then adds what value has been set on settings -> miscellaneous</br>
- {[field.unique]} if true, then adds what value has been set on settings -> miscellaneous</br>
- {[field.primary_key]} if true, then adds what value has been set on settings -> miscellaneous</br>
