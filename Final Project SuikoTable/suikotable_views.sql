use suikoden;

SELECT 	char_name, char_weapon, att_range
FROM 	suik_char
order by 	att_range;

SELECT 	rune_name, true_rune
FROM 	runes
order by 	true_rune;



SELECT 	i.item_name, v.location_name, i.item_cost
FROM	items i JOIN locations v
		ON i.item_shop_name = v.default_item_shop_name
ORDER BY 	v.location_name;