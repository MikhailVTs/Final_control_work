USE Mans_friends;



CREATE TABLE Animals
(
	IdAnimals INT AUTO_INCREMENT PRIMARY KEY, 
	nameAnimals VARCHAR(45)
);

INSERT INTO Animals (nameAnimals)
VALUES ('Pets'), ('Pack_animals'); 



CREATE TABLE Pets
(
	IdPets INT AUTO_INCREMENT PRIMARY KEY,
    name_genus VARCHAR (45),
    Animals_id INT,
    FOREIGN KEY (Animals_id) REFERENCES Animals (IdAnimals) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Pets (name_genus, Animals_id)
VALUES ('Dogs', 1), ('Cats', 1), ('Hamsters', 1); 


CREATE TABLE Dogs 
(       
    IdDogs INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(45),
    command VARCHAR(45),
    birthday DATE,
    Pets_id int,
    Foreign KEY (Pets_id) REFERENCES Pets (IdPets) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Cats 
(       
    IdCats INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(45),
    command VARCHAR(45),
    birthday DATE,
    Pets_id int,
    Foreign KEY (Pets_id) REFERENCES Pets (IdPets) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Hamsters 
(       
    IdHamsters INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(45),
    command VARCHAR(45), 
    birthday DATE,
    Pets_id int,
    Foreign KEY (Pets_id) REFERENCES Pets (IdPets) ON DELETE CASCADE ON UPDATE CASCADE
);



CREATE TABLE Pack_animals
(
	IdPack_animals INT AUTO_INCREMENT PRIMARY KEY,
    name_genus VARCHAR (45),
    Animals_id INT,
    FOREIGN KEY (Animals_id) REFERENCES Animals (IdAnimals) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Pack_animals (name_genus, Animals_id)
VALUES ('Horses', 2), ('Camels', 2), ('Donkeys', 2); 


CREATE TABLE Horses 
(       
    IdHorses INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(45),
    command VARCHAR(45), 
    birthday DATE,
    Pack_animals_id int,
    Foreign KEY (Pack_animals_id) REFERENCES Pack_animals (IdPack_animals) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Camels 
(       
    IdCamels INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(45),
    command VARCHAR(45), 
    birthday DATE,
    Pack_animals_id int,
    Foreign KEY (Pack_animals_id) REFERENCES Pack_animals (IdPack_animals) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Donkeys 
(       
    IdDonkeys INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(45),
    command VARCHAR(45), 
    birthday DATE,
    Pack_animals_id int,
    Foreign KEY (Pack_animals_id) REFERENCES Pack_animals (IdPack_animals) ON DELETE CASCADE ON UPDATE CASCADE
);