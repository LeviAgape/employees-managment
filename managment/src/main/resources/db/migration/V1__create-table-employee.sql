CREATE TABLE employee(
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    name TEXT NOT NULL,
    born INT NOT NULL,
    salary INT NOT NULL,
    role TEXT NOT NULL
);