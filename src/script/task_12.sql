SELECT horse.name, horse.Birthday, horse.Command, pack_animals.name_genus, young_animals.age 
FROM Horses horse

LEFT JOIN Young_animals young_animals ON young_animals.name = horse.name
LEFT JOIN Pack_animals pack_animals ON pack_animals.IdPack_animals = horse.Pack_animals_id
UNION 

SELECT donkey.name, donkey.Birthday, donkey.Command, pack_animals.name_genus, young_animals.age 
FROM Donkeys donkey 

LEFT JOIN Young_animals young_animals ON young_animals.name = donkey.name
LEFT JOIN Pack_animals pack_animals ON pack_animals.IdPack_animals = donkey.Pack_animals_id
UNION

SELECT cat.name, cat.Birthday, cat.Command, pets.name_genus, young_animals.age 
FROM Cats cat

LEFT JOIN Young_animals young_animals ON young_animals.name = cat.name
LEFT JOIN Pets pets ON pets.IdPets = cat.Pets_id
UNION

SELECT dog.name, dog.Birthday, dog.Command, pets.name_genus, young_animals.age 
FROM Dogs dog

LEFT JOIN Young_animals young_animals ON young_animals.name = dog.name
LEFT JOIN Pets pets ON pets.IdPets = dog.Pets_id
UNION

SELECT hamster.name, hamster.Birthday, hamster.Command, pets.name_genus, young_animals.age 
FROM Hamsters hamster
LEFT JOIN Young_animals young_animals ON young_animals.name = hamster.name
LEFT JOIN Pets pets ON pets.IdPets = hamster.Pets_id;