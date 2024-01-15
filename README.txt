Url pra fazer requisições: http://localhost:8080/clientes GET vera todos os clientes cadastrados.
			   http://localhost:8080/clientes POST para cadastrar um cliente.
			   http://localhost:8080/transactions POST para realizar as transações entre os clientes.
					

Formato das requisições para cadastrar Clientes em JSON:

{
    "firstName": "David",
    "lastName": "Lima",
    "balance": 40,
    "email": "david222222@gmail.com",
    "documento": "1234567812222",
    "clientType": "MERCHANT" <------ Existem 2 tipos de Clientes "MERCHANT" ou "COMMON", 
}

Formato das requisições para realizar transações entre Clientes em JSON:

{
    "senderId": 1,
    "receiverId": 2,
    "value": 10 
}
