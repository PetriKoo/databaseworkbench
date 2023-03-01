public class TestOneFile {
    public TestOneFile() {
        
    }
}
{foreach table}
class {[table]} {
	
	{foreach field}
	private {[field.lang.Java]} {[field.name]};
	{/fieldforeach}
	{foreach field}
	void set{[field.name]} ({[field.lang.Java]} oValue) { this.{[field.name]} = oValue; }
	{[field.lang.Java]} get{[field.name]}() { return this.{[field.name]}; }
	{/fieldforeach}
}
{/tableforeach}
