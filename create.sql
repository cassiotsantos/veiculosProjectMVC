DROP TABLE IF EXISTS Veiculo;

CREATE TABLE Veiculo
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    cor             VARCHAR(80)  NOT NULL,
    marca            VARCHAR(255) NOT NULL

);

INSERT INTO Veiculo(cor, marca)
VALUES ('Amarela', 'FERRARI');
INSERT INTO Veiculo(cor, marca)
VALUES ('Branco','FIAT');
INSERT INTO Veiculo(cor, marca)
VALUES ('Preto','TOYOTA');
INSERT INTO Veiculo(cor, marca)
VALUES ('Verde','FORD');