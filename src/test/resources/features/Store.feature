#language: pt
#author: Alana Mendes
#version: 1.0
#enconding: UTF-8

@regressivo @store
Funcionalidade: Gerenciamento de um pedido na Petstore
  Eu como administrador da loja Petstore
  Quero criar, buscar e deletar pedidos do sistema

Contexto:
  Dado que gerencio pedidos na API Petstore
@postStore
Cenario: Cadastrar pedido no sistema Petstore
  Quando envio um request de cadastro de pedido com dados validos
  Entao o usuario deve ser criado corretamente
  E o status code do request deve ser 200

@getStore
Cenario: Buscar pedido no sistema Petstore
  E existe um pedido cadastrado na api
  Quando busco esse pedido
  Entao os dados do pedido devem ser retornandos

@deleteStore
Cenario: Deletar pedido no sistema Petstore
  E existe um pedido cadastrado na api
  Quando deleto esse pedido
  Entao o pedido deve ser deletado corretamente