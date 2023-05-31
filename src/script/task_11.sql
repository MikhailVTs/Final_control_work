CREATE TEMPORARY TABLE Animals AS 
SELECT *, 'Dogs' as genus FROM Dogs
UNION SELECT *, 'Cats' AS genus FROM Cats
UNION SELECT *, 'Hamsters' AS genus FROM Hamsters
UNION SELECT *, 'Horses' AS genus FROM Horses
UNION SELECT *, 'Donkeys' AS genus FROM Donkeys;

CREATE TABLE Young_animals AS
SELECT name, birthday, command, genus, 
CONCAT(CAST(TIMESTAMPDIFF(YEAR, birthday, NOW()) AS CHAR), " year ", 
CAST(MOD(TIMESTAMPDIFF(MONTH, birthday, NOW()), 12) AS CHAR), " month ") AS age
FROM Animals WHERE Birthday BETWEEN ADDDATE(curdate(), INTERVAL -3 YEAR) AND ADDDATE(CURDATE(), INTERVAL -1 YEAR);
 
SELECT * FROM Young_animals;