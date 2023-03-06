{foreach table}
CREATE TABLE {[table]} (
    {foreach field}
	{[field.name]} {[field.lang.MySQL]} {[field.not_null]} {[field.default]} {[field.auto_increment]} {[field.primary_key]}{[not_last.comma]}{/fieldforeach}{[if.comma.A]}
    {foreach foreignkey}{[change.if.comma.A]}
        CONSTRAINT {[key.name]} FOREIGN KEY ({[key.field.name]}) REFERENCES {[key.foreign.table]}({[key.foreign.field]}){[not_last.comma]}{/foreignkeyforeach}    
);
{/tableforeach}