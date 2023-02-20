use suikoden;


INSERT INTO items VALUES 
('Gregminster Item shop', 'Medicine', 100),
('Rockland Item shop', 'Escape Talisman', 500),
('Lenankamp Item shop', 'Antitoxin', 200),
('Seika Item shop','Water Crystal',7000),
('Kaku Item shop','Holy Crystal',5000),
('Kouan Item shop','Counter Crystal', 7500);

INSERT INTO armory VALUES 
('Gregminster Armor shop', 'Bandanna', 50,1),
('Rockland Armor shop', 'Headband', 300,2),
('Lenankamp Armor shop', 'Leather Coat', 700,4),
('Seika Armor shop','Brass Armor',1000,5),
('Kaku Armor shop','Wooden Shield',300,2),
('Kouan Armor shop','Karate Uniform', 3000,10);

INSERT INTO locations VALUES 
('Gregminster','Yes','Gregminster Item shop','Gregminster Armor shop'),
('Rockland','Yes','Rockland Item shop','Rockland Armor shop'),
('Lenankamp','Yes','Lenankamp Item shop','Lenankamp Armor shop'),
('Seika','Yes','Seika Item shop','Seika Armor shop'),
('Kaku','Yes','Kaku Item shop','Kaku Armor shop'),
('Kouan', 'Yes','Kouan Item shop','Kouan Armor shop');

INSERT INTO runes VALUES 
('Water', 'Healing and Support magic useable', 'No'),
('Fire', 'Attack magic useable. Mainly vs. groups', 'No'),
('Lightning', 'Attack magic useable. Mainly vs. singles', 'No'),
('Wind', 'Attack, Healing and Effect magic useable', 'No'),
('Earth','Support and Attack magic useable','No'),
('Soul Eater', 'Attack and Instant Death magic useable', 'Yes'),
('Boar', 'DMG x2 / 1 enemy, Weapon element is taken into account for weaknesses', 'No');

INSERT INTO unite_magic VALUES 
('Scorched Earth','1300 fire and earth DMG / all enemies'),
('Storm Fang', '1000 Earth and Wind DMG / all enemies'),
('Water Dragon', '800 Wind and Water DMG / all enemies, Heals all HP / all allies, Cures all negative status / all allies'),
('Thor', '2000 Water and Lightning DMG/1 enemy, Heals all HP / all allies, Cures all negative status / all allies'),
('Blazing Camp', '1500 Lightning and Fire DMG/ all enemies');

INSERT INTO unite_attacks VALUES
('Talisman Attack','2.0x damage to one enemy'),
('Master Pupil Attack','1.0x damage to all enemies'),
('Fisherman Attack', '3.0x damage to one enemy, Participants Unbalanced'),
('Kobold Attack', '2.0x damage to one enemy'),
('Trickster Attack','1.0x damage all enemies'),
('Wild Arrow Attack','1.0x damage to all enemies, Participants Unbalanced');

INSERT INTO shops VALUES 
(1,'Gregminster','Gregminster Item shop','Gregminster Armor shop'),
(2,'Rockland','Rockland Item shop','Rockland Armor shop'),
(3,'Lenankamp','Lenankamp Item shop','Lenankamp Armor shop'),
(4,'Seika','Seika Item shop','Seika Armor shop'),
(5,'Kaku','Kaku Item shop','Kaku Armor shop'),
(6,'Kaku','Kouan Item shop','Kouan Armor shop');

INSERT INTO suik_char VALUES
('Tir McDohl', 'Tonfa', 'Medium', 'Human', 'Tenkai', 'Soul Eater'),
('Cleo', 'Arrow', 'Long', 'Human', 'Tenman', null),
('Gremio', 'Axe', 'Small', 'Human', 'Tenei', null),
('Viktor', 'Sword', 'Small', 'Human', 'Tenko', null),
('Luc', 'Staff', 'Small', 'Human', 'Tenkai', 'Wind'),
('Pahn', 'Fist', 'Fist', 'Human', 'Tentai', 'Boar');