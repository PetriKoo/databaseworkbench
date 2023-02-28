public class TestOneFile {
    public TestOneFile() {
        
    }
}
{foreach table}
class {[table]} {
	
	{foreach field}
	private {[Java]} {[field]};
	{/fieldforeach}
	{foreach field}
	void set{[field]} ({[Java]} oValue) { this.{[field]} = oValue; }
	{[Java]} get{[field]}() { return this.{[field]}; }
	{/fieldforeach}
}
{/tableforeach}
