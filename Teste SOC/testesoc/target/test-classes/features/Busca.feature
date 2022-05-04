# language: pt

Funcionalidade: Busca no blog da SOC

    Cenario: Um usuario entra no blog da soc e realiza uma busca no site
    Dado usuario acessa o blog 
    Quando realiza uma pesquisa no site
    Entao eh redirecionado para pagina com os resultados da pesquisa

    Cenario: Um usuario entra na Rede SOC para buscar um Credenciado
    Dado usuario buscando credenciado no site
    E acessando o menu funcionalidades
    E acessando o submenu redesocnet
    E clicando em buscar credenciados
    Quando filtra por localizacao
    Entao exibe o perfil do credenciado

