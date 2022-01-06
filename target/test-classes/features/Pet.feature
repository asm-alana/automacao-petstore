#language: pt
#author: Alana Mendes
#version: 1.0
#enconding: UTF-8

@regressivo @pets
Funcionalidade: Gerenciamento de um animal na Petstore
  Eu como administrador da loja Petstore
  Quero cadastrar, atualizar e deletar animais do sistema

  Contexto:
    Dado que estou no sistema Petstore

  @post
  Cenario: Cadastra novo pet na API Petstore
    Quando envio um request de cadastro de pet com dados validos
    Entao o pet deve ser criado corretamente
    E o status code do request deve ser 200

  @getComSucesso
  Cenario: Busca uma pet existente na API Petstore
    E existe um pet cadastrado na api
    Quando buscar esse pet
    Entao os dados do pet devem ser retornados
    E o status code do request deve ser 200

  @getSemSucesso
    Cenario: Busca um pet inexistente na API Petstore
    Quando buscar um pet com id igual a 0
    Entao deve ser retornada a mensagem "Pet not found"
    E o status code do request deve ser 404

  @put
  Cenario: Alterar um pet existente na API Petstore
    E existe um pet cadastrado na api
    Quando altero os dados do pet
    Entao o pet deve ser alterado com sucesso
    E o status code do request deve ser 200

  @delete
  Cenario: Deletar um pet existente na API Petstore
    E existe um pet cadastrado na api
    Quando deleto esse pet
    Entao o pet deve ser deletado corretamente
    E o status code do request deve ser 200