CREATE TABLE employee(
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    nome TEXT NOT NULL,
    data_nascimento DATE NOT NULL,
    salario NUMERIC(10,2) NOT NULL,
    funcao TEXT NOT NULL
);

