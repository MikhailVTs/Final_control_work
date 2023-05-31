SET SQL_SAFE_UPDATES = 0;
DELETE FROM Camels;

SELECT name, birthday, command FROM Horses
UNION SELECT  name, birthday, command FROM Donkeys;