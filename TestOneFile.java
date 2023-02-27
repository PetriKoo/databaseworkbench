public class TestOneFile {
    public TestOneFile() {
        
    }
}
/*
{foreach table}
    class {[table]}Bean { 
        {foreach field}
            private {[Java]} {[field]};
        {/foreach}
        {foreach field}
            {[Java]} get{[field]}() { return this.{[field]}}
            void set{[field]}({[Java]} oValue) {this.{[field]} = oValue; }
        {/foreach}
    }
{/foreach}
*/