This Rest API has the function of controlling employees within a company. Being able to add, edit, delete and display a list of employees. May increase salary increases, older employees and various features


API Endpoints

GET /employees - Listar todos os funcionários

GET /employees/ordem/alfabetica - Separar por ordem alfabética

GET employees/separado/por/funcao - Separar funcionários por seus cargos

GET /employees/nascidos/10/12 - Listar funcionários nascidos nos meses 10 e 12

GET /employees/mais/velho - Listar o funcionário mais velho

GET /employees/salarios/minimos - Imprimir a quantidade de salários minímos que cada funcionário ganha

POST /employees - Adicionar um novo funcionário, digitando seu nome, data_nascimento, salario e funcao

POST /employees/aumento/salarial - Adicionando 10% do salário de cada funcionário

PUT /employees - Editar os dados de um funcionário, digitando seu ID, nome, data_nascimento, salario e funcao

PUT /employees/salario - Realizando a soma de todos os salários registrados no banco de dados

DEL /employees/{id} - Deletando através do ID do funcionário

DATABASE:

Esse projeto foi desenvolvido com a utilização do postgreSQL
