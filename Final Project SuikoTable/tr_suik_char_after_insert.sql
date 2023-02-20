use suikoden;

create table if not exists suik_char_audit
(
	char_weapon			varchar(25)		not null,
    att_range			varchar(10)		not null,
    action_type			varchar(50)		not null,
    action_date			datetime		not null,
    action_user			varchar(100)	not null
);

delimiter $$
create trigger suik_char_after_insert
	after insert on suik_char
    for each row
    begin
		insert into suik_char_audit
			(char_weapon, att_range, action_type, action_date, action_user)
		values
			(new.char_weapon, new.att_range, new.char_race, 'Inserted', now(), current_user());
    end $$
delimiter ;