-- CREATE THE Suikoden DATABASE
-- *******************************************

-- create the database
DROP DATABASE IF EXISTS suikoden;
CREATE DATABASE suikoden;

-- select the database
USE suikoden;

-- create the tables
CREATE TABLE items
(
	item_shop_name		varchar(25)		not null	PRIMARY KEY,
	item_name			varchar(25)		not null,
    item_cost			int				not null

);

CREATE TABLE armory
(
	armory_shop_name	varchar(25)		not null	PRIMARY KEY,
	armor_name			varchar(25)		not	null,
    armor_cost			int				not null,
    armor_def			int				not null
);

CREATE TABLE locations
(
	location_name		varchar(25)		not null	PRIMARY KEY,
	inn					varchar(3)		not null,
    default_item_shop_name		varchar(25)		not null,
    default_armory_shop_name	varchar(25)		not null,
    CONSTRAINT suikoden_fk_items
    FOREIGN KEY (default_item_shop_name)
    REFERENCES items (item_shop_name),
    
    CONSTRAINT suikoden_fk_armory
    FOREIGN KEY (default_armory_shop_name)
    REFERENCES armory (armory_shop_name)

);

CREATE TABLE runes
(
	rune_name			varchar(25)		not null	PRIMARY KEY,
    rune_desc			varchar(75)		not null,
    true_rune			varchar(3)		not null

);

CREATE TABLE unite_magic
(
	magic_comb_name		varchar(25)		not null	PRIMARY KEY,
    magic_att			varchar(150)	not null

);

CREATE TABLE unite_attacks
(
	unite_attack_name	varchar(25)		not null	PRIMARY KEY,
    damage				varchar(75)		not null

);

CREATE TABLE shops
(
	shop_number				int				not null	auto_increment	PRIMARY KEY,
    def_location_name		varchar(25)		not null,
    def_item_shop_name		varchar(25)		not null,
    def_armory_shop_name	varchar(25)		not null,
    
	CONSTRAINT suikoden_fok_items
    FOREIGN KEY (def_item_shop_name)
    REFERENCES items (item_shop_name),
    
    CONSTRAINT suikoden_fok_armory
    FOREIGN KEY (def_armory_shop_name)
    REFERENCES armory (armory_shop_name),
    
	CONSTRAINT suikoden_fok_locations
    FOREIGN KEY (def_location_name)
    REFERENCES locations (location_name)
);
CREATE TABLE suik_char
(
	char_name			varchar(25)		not null	PRIMARY KEY,
    char_weapon			varchar(25)		not null,
    att_range			varchar(10)		not null,
    char_race			varchar(25)		not null,
    char_star			varchar(15)		not null,
    default_rune_name	varchar(25)		null,
    
    CONSTRAINT suikoden_runes
    FOREIGN KEY (default_rune_name)
    REFERENCES runes (rune_name)
);
