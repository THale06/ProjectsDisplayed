USE suikoden;

Drop procedure if exists sp_armor_defense;
delimiter $$

create procedure sp_armor_defense
(
	armor_defense_to_check	int
)
Begin

	Declare sum_armor_defence		int;
    Select	sum(armor_cost + armor_def)
		into total_increase
	From	armory;
        if total_cost > 15
    then
		select concat('Total cost: $', sum_armor_defence) as message;
	else
		select 'Total cost minimal' as message;
	end if;
    
End $$


use suikoden;

delimiter $$

drop procedure if exists sp_items_cost;

create procedure sp_items_cost
(
		items_cost_to_check		int
)
begin
	declare 	terms_id_var	int;
    
    select 		item_cost 
		into 	item_cost_var
	from		items
    where		item_cost = items_cost_to_check;
    
    case 		items_cost_var
		when 1 then
			select 'less then 1000 bits' as Totals;
		when 2 then
			select 'less then 2000 bits' as Totals;
		when 3 then
			select 'less then 3000 bits' as Totals;
		else
			select 'more then 3000 bits' as Totals;
	end case;

End  $$