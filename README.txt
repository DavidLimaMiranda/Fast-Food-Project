# Projeto-Fast-Food

Fiz um sistema de venda de Fast-Food completo(ou quase), Esse projeto foi bem interessante e dessafiador me ensinou a lidar melhor com strings e tabelas na marra.

Esse projeto foi muito interessante de fazer e estudar, foi uma alegria terminar ele e ver tudo funcionando da forma que deveria.

Melhorei bastante no conceito em separar funcionalidades e manipular entidades.

_________________________________________________________________________________

Os formatos das requisições são esses, Use o postman ou insomnia para fazer requisições: 

http://localhost:8080/clients, Post e Get estão nesse Http.

http://localhost:8080/MakeOrder/{id}, Http Get passando um Id para encontrar um cliente/empresa em especifico

Pra Criar A empressa de Fast-Food:

{
	"firstName": "McDonalds",		// nome da empressa
	"lastName": "Mc",			// Abreviação
	"email": "company@gmail.com",		// Email (Unico)
	"balance": 1000,			// Dinheiro da empressa
	"typeAccount": "COMPANY"		// Tipo da conta criada, Company para empressas
}

Pra Criar os Respectivos Clientes:

{
    "firstName": "David", 			// Nome do cliente
    "lastName": "Lima",				// Ultimo nome do cliente 
    "email": "david@gmail.com",			// Email (Unico)
    "balance": 40,				// Dinheiro do cliente
    "typeAccount": "CLIENT"			// Tipo da conta criada, Client para clientes
}

________________________________________________________________________________

http://localhost:8080/MakeOrder, Post e Get estão nesse Http

Pra criar Um lanche/Produto:

{
    "foodName": "Hamburguer",			// Nome do lanche/produto
    "description": "Hamburguer clássico",	// Descrição do lanche/produto criado
    "stock": 3,					// Quantidade de lanches/produtos
    "price": 15					// Preço do lanche/produto
}
_________________________________________________________________________________

http://localhost:8080/makeRequest, Post e Get estão nesse Http

Pra Fazer a compra dos lanches/produtos, enviando dinheiro do cliente para a empressa

{
    "companyId": 1,				// Id Da empressa
    "clientId": 2,				// Id do cliente
    "foods": "Hamburguer"			// Lanches/Produtos comprados, Usando a "," como separador pra mandar mais de 1 por vez
}

