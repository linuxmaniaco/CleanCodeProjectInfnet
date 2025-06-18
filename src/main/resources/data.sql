-- Inserir 130 carros, distribuídos entre 30 fabricantes (assumindo que os fabricantes com id de 1 a 30 já existem)

INSERT INTO carro (modelo, ano, cor, cavalos_de_potencia, fabricante, pais) VALUES
-- Fabricante 1 (Volkswagen)
('Golf', 2020, 'Preto', 150, 'Volkswagen', 'Alemanha'),
('Polo', 2019, 'Branco', 130, 'Volkswagen', 'Alemanha'),
('Passat', 2021, 'Prata', 180, 'Volkswagen', 'Alemanha'),
('Tiguan', 2022, 'Azul', 200, 'Volkswagen', 'Alemanha'),
('Fusca', 1980, 'Preto', 150, 'Volkswagen', 'Alemanha'),
('Fusca', 1980, 'Azul', 150, 'Volkswagen', 'Alemanha'),
('Fusca', 1985, 'Amarelo', 150, 'Volkswagen', 'Alemanha'),
('Fusca', 1985, 'Verde', 150, 'Volkswagen', 'Alemanha'),
('Fusca', 1966, 'Vermelho', 150, 'Volkswagen', 'Alemanha'),

-- Fabricante 2 (Ford)
('Fusion', 2021, 'Vermelho', 245, 'Ford', 'EUA'),
('Focus', 2020, 'Cinza', 150, 'Ford', 'EUA'),
('Mustang', 2022, 'Amarelo', 450, 'Ford', 'EUA'),

-- Fabricante 3 (Toyota)
('Corolla', 2022, 'Branco', 160, 'Toyota', 'Japão'),
('Camry', 2021, 'Azul', 250, 'Toyota', 'Japão'),
('Hilux', 2020, 'Preto', 230, 'Toyota', 'Japão'),
('Yaris', 2022, 'Prata', 140, 'Toyota', 'Japão'),
('RAV4', 2021, 'Verde', 200, 'Toyota', 'Japão'),

-- Fabricante 4 (Fiat)
('Uno', 2020, 'Cinza', 75, 'Fiat', 'Itália'),
('Palio', 2019, 'Branco', 85, 'Fiat', 'Itália'),
('Toro', 2022, 'Vermelho', 180, 'Fiat', 'Itália'),
('500', 2021, 'Amarelo', 100, 'Fiat', 'Itália'),

-- Fabricante 5 (BMW)
('X5', 2022, 'Preto', 330, 'BMW', 'Alemanha'),
('320i', 2021, 'Prata', 250, 'BMW', 'Alemanha'),
('M3', 2023, 'Vermelho', 500, 'BMW', 'Alemanha'),
('330e', 2021, 'Azul', 250, 'BMW', 'Alemanha'),

-- Fabricante 6 (Honda)
('Civic', 2021, 'Prata', 180, 'Honda', 'Japão'),
('CR-V', 2020, 'Branco', 220, 'Honda', 'Japão'),
('HR-V', 2021, 'Azul', 180, 'Honda', 'Japão'),
('Fit', 2020, 'Cinza', 130, 'Honda', 'Japão'),

-- Fabricante 7 (Chevrolet)
('Cruze', 2020, 'Preto', 160, 'Chevrolet', 'EUA'),
('Onix', 2021, 'Vermelho', 115, 'Chevrolet', 'EUA'),
('Camaro', 2023, 'Amarelo', 350, 'Chevrolet', 'EUA'),
('Trailblazer', 2022, 'Prata', 250, 'Chevrolet', 'EUA'),
('Equinox', 2021, 'Azul', 220, 'Chevrolet', 'EUA'),

-- Fabricante 8 (Hyundai)
('Creta', 2021, 'Branco', 150, 'Hyundai', 'Coreia do Sul'),
('Elantra', 2021, 'Cinza', 160, 'Hyundai', 'Coreia do Sul'),
('Tucson', 2020, 'Preto', 190, 'Hyundai', 'Coreia do Sul'),
('HB20', 2022, 'Vermelho', 120, 'Hyundai', 'Coreia do Sul'),
('Kona', 2022, 'Azul', 200, 'Hyundai', 'Coreia do Sul'),

-- Fabricante 9 (Renault)
('Duster', 2020, 'Prata', 150, 'Renault', 'França'),
('Sandero', 2021, 'Amarelo', 110, 'Renault', 'França'),
('Kwid', 2021, 'Vermelho', 70, 'Renault', 'França'),
('Captur', 2022, 'Branco', 170, 'Renault', 'França'),

-- Fabricante 10 (Peugeot)
('208', 2020, 'Preto', 130, 'Peugeot', 'França'),
('3008', 2021, 'Cinza', 180, 'Peugeot', 'França'),
('5008', 2021, 'Azul', 220, 'Peugeot', 'França'),

-- Fabricante 11 (Mercedes-Benz)
('A-Class', 2021, 'Vermelho', 250, 'Mercedes-Benz', 'Alemanha'),
('C-Class', 2022, 'Branco', 300, 'Mercedes-Benz', 'Alemanha'),
('E-Class', 2021, 'Prata', 350, 'Mercedes-Benz', 'Alemanha'),

-- Fabricante 12 (Audi)
('A3', 2020, 'Preto', 210, 'Audi', 'Alemanha'),
('A4', 2021, 'Cinza', 250, 'Audi', 'Alemanha'),
('Q5', 2021, 'Azul', 280, 'Audi', 'Alemanha'),

-- Fabricante 13 (Nissan)
('Altima', 2020, 'Prata', 240, 'Nissan', 'Japão'),
('Sentra', 2021, 'Vermelho', 180, 'Nissan', 'Japão'),
('Rogue', 2022, 'Preto', 200, 'Nissan', 'Japão'),

-- Fabricante 14 (Kia)
('Seltos', 2021, 'Amarelo', 180, 'Kia', 'Coreia do Sul'),
('Sportage', 2020, 'Branco', 220, 'Kia', 'Coreia do Sul'),
('Soul', 2022, 'Vermelho', 150, 'Kia', 'Coreia do Sul'),

-- Fabricante 15 (Mitsubishi)
('Outlander', 2021, 'Azul', 200, 'Mitsubishi', 'Japão'),
('L200', 2022, 'Preto', 230, 'Mitsubishi', 'Japão'),
('ASX', 2021, 'Prata', 170, 'Mitsubishi', 'Japão'),

-- Fabricante 16 (Chrysler)
('Pacifica', 2021, 'Branco', 250, 'Chrysler', 'EUA'),
('Voyager', 2020, 'Preto', 240, 'Chrysler', 'EUA'),

-- Fabricante 17 (Mazda)
('CX-5', 2022, 'Vermelho', 220, 'Mazda', 'Japão'),
('Mazda3', 2021, 'Cinza', 180, 'Mazda', 'Japão'),

-- Fabricante 18 (Subaru)
('Outback', 2020, 'Azul', 250, 'Subaru', 'Japão'),
('Impreza', 2021, 'Preto', 170, 'Subaru', 'Japão'),

-- Fabricante 19 (Land Rover)
('Discovery', 2021, 'Branco', 300, 'Land Rover', 'Reino Unido'),
('Defender', 2022, 'Prata', 320, 'Land Rover', 'Reino Unido'),

-- Fabricante 20 (Jaguar)
('F-Type', 2020, 'Amarelo', 450, 'Jaguar', 'Reino Unido'),
('XE', 2021, 'Azul', 300, 'Jaguar', 'Reino Unido'),

-- Fabricante 21 (Ferrari)
('488', 2020, 'Vermelho', 600, 'Ferrari', 'Itália'),
('Roma', 2021, 'Branco', 620, 'Ferrari', 'Itália'),

-- Fabricante 22 (Lamborghini)
('Huracan', 2021, 'Verde', 640, 'Lamborghini', 'Itália'),
('Aventador', 2022, 'Preto', 700, 'Lamborghini', 'Itália'),

-- Fabricante 23 (Porsche)
('911', 2021, 'Cinza', 500, 'Porsche', 'Alemanha'),
('Cayenne', 2022, 'Prata', 550, 'Porsche', 'Alemanha'),

-- Fabricante 24 (Tesla)
('Model S', 2020, 'Preto', 500, 'Tesla', 'EUA'),
('Model 3', 2021, 'Branco', 400, 'Tesla', 'EUA'),

-- Fabricante 25 (Volvo)
('XC90', 2021, 'Azul', 300, 'Volvo', 'Suécia'),
('XC60', 2021, 'Cinza', 250, 'Volvo', 'Suécia'),

-- Fabricante 26 (Alfa Romeo)
('Giulia', 2021, 'Vermelho', 280, 'Alfa Romeo', 'Itália'),
('Stelvio', 2022, 'Preto', 320, 'Alfa Romeo', 'Itália'),

-- Fabricante 27 (Aston Martin)
('DB11', 2020, 'Verde', 500, 'Aston Martin', 'Reino Unido'),
('Vantage', 2021, 'Azul', 510, 'Aston Martin', 'Reino Unido'),

-- Fabricante 28 (Bentley)
('Continental GT', 2021, 'Prata', 630, 'Bentley', 'Reino Unido'),
('Bentayga', 2022, 'Branco', 650, 'Bentley', 'Reino Unido'),

-- Fabricante 29 (McLaren)
('720S', 2021, 'Amarelo', 720, 'McLaren', 'Reino Unido'),
('GT', 2022, 'Verde', 620, 'McLaren', 'Reino Unido'),

-- Fabricante 30 (Mini)
('Cooper S', 2021, 'Vermelho', 190, 'Mini', 'Reino Unido'),
('Countryman', 2022, 'Preto', 220, 'Mini', 'Reino Unido');


INSERT INTO usuario (nome, cargo, email, avatar, password) VALUES
                                                               ('Wile E. Coyote', 'Engenheiro de Produtos', 'wile@acme.com', 'https://s3.amazonaws.com/comicgeeks/characters/avatars/10927.jpg', 'senha123'),
                                                               ('Road Runner', 'Testador de Produtos', 'roadrunner@acme.com', 'https://i.pinimg.com/474x/52/89/a7/5289a705d3199d01da547c58c217a21d.jpg', 'senha123'),
                                                               ('Daffy Duck', 'Gerente de Marketing', 'daffy@acme.com', 'https://www.gravatar.com/avatar/daf4b5b81342fe587d5043b3e58a0180?s=200', 'senha123'),
                                                               ('Bugs Bunny', 'Diretor de TI', 'bugs@acme.com', 'https://www.gravatar.com/avatar/9f0c2cb2d5f8264e2c35d92f3655f2b3?s=200', 'senha123'),
                                                               ('Elmer Fudd', 'Analista de Suporte', 'elmer@acme.com', 'https://www.gravatar.com/avatar/6be1f3c98be3886b5473b7b947fe82ad?s=200', 'senha123');
