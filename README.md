# Projeto-Fast-Food

Fiz um sistema de venda de Fast-Food completo(ou quase), Esse projeto foi bem interessante e dessafiador me ensinou a lidar melhor com strings e tabelas na marra.

Esse projeto foi muito interessante de fazer e estudar, foi uma alegria terminar ele e ver tudo funcionando da forma que deveria.

Melhorei bastante no conceito em separar funcionalidades e manipular entidades.

_________________________________________________________________________________

Os formatos das requisições são esses, Use o postman ou insomnia para fazer requisições: 

http://localhost:8080/clients, Post e Get estão nesse Http.

http://localhost:8080/clients/{id}, Http Get passando um Id para encontrar um Cliente em especifico

Para criar um Cliente do Fast-Food:

{
	"firstName": "McDonalds",		
 	// nome da empressa
	"lastName": "Mc",			
 	// Abreviação
	"email": "company@gmail.com",		
 	// Email (Unico)
  	"password": "1234"
   	// Senha da conta
	"balance": 1000,			
 	// Dinheiro da empressa
	"typeAccount": "COMPANY"		
 	// Tipo da conta criada, "COMPANY" para empressas e ter acesso total, "CLIENT" para clientes.
}
________________________________________________________________________________

http://localhost:8080/auth/login

Para logar e pegar o Token recebido para fazer outras requisições
{
    "email": "company@gmail.com",
    "password": "1234"
}
________________________________________________________________________________

http://localhost:8080/menu/food, Post está nesse Http, get em /menu/getAll

Para criar um lanche/Produto:

{
    "foodName": "Hamburguer",			
    // Nome do lanche/produto \**
    "description": "Hamburguer clássico",	
    // Descrição do lanche/produto criado \**
    "stock": 3,					
    // Quantidade de lanches/produtos \**
    "price": 15					
    // Preço do lanche/produto \**
}
_________________________________________________________________________________

http://localhost:8080/transaction, Post está nesse Http, get em /transaction/getAll

Para realizar a compra dos lanches/produtos, enviando dinheiro do cliente para a empressa

{
    "companyId": 1,				
    // Id Da empressa \**
    "clientId": 2,				
    // Id do cliente \**
    "foods": "Hamburguer"			
    // Produtos comprados, Usando a "," como separador para mandar mais de 1 por vez \**
}
