# Sorte.io
> Bem vindo ao Sorte.io sua plataforma de apostas, entre com 
  com permissões diferentes e navegue no nosso sistema enquanto 
  ainda dispor de 2 coisas essenciais... 
  Sorte e Dinheiro ;)

---

## Subindo Banco via Docker
> É necessário a instalação do [Docker](https://docs.docker.com/get-docker/) e [WSL 2](https://docs.microsoft.com/en-us/windows/wsl/install)

Na raiz do diretório, navegue para a pasta [**`docker`**](./docker/), em seguida execute o comando [**`docker-compose up`**](./docker/docker-compose.yml)

    cd ./docker
    docker-compose up -d

- **MySQL** irá subir na porta **`3306`**
- [**phpMyAdmin**](http:localhost:80) irá subir na url **`80`**
    - user: **`root`**
    - password: **`ifpb`**

## Executando a aplicação
> É necessário executar [**`SorteIoApplication`**](/src/main/java/br/edu/ifpb/pweb2/sorte_io/SorteIoApplication.java)

- Será criado as tabelas necessárias no 
- **Tomcat** irá subir na porta **`8080`**
- A aplicação foi mapeada para iniciar na url [**`localhost:8080/sorte_io/`**](http:localhost:8080/sorte_io/)


## Finalizar aplicação
> É necessário para a execução de [**`SorteIoApplication`**](/src/main/java/br/edu/ifpb/pweb2/sorte_io/SorteIoApplication.java)

Em seguida execute o comando [**`docker-compose down`**](./docker/docker-compose.yml)

    docker-compose down

- **MySQL** e **phpMyAdmin** irão

Caso seja necessário remover o banco

---

<!-- ## Usuarios

- Criador de Sorteios
    - Username: `controlador`
    - Password: `controlador123`

- Apostador
    - Username: `apostador`
    - Password: `apostador123` -->

## Tabela de Requisitos:
[ ✔️  〰  ❌ ] | Pontos | Item |

    [✔️] | 05 | Implementou cadastro de clientes

    [✔️] | 05 | Implementou o login/logoutde clientes e filtro de autenticação

    [✔️] | 15 | Implementou a realização de aposta por cliente

    [〰] | 15 | Implementou a realização de sorteio por controlador

    [〰] | 15 | Implementou a conferência das apostas em um sorteio

    [✔️] | 15 | Implementou as apostas preferidas de um cliente

    [✔️] | 10 | Implementou autorização via Spring Secutiry

    [✔️] | 05 | Utilizou Bootstrap

    [✔️] | 05 | Formulários validam valores de campos e mostram mensagens de erros

    [✔️] | 05 | Usou Post-Redirect-Get

    [✔️] | 05 | Utilizou fragmentos nos templates Thymeleaf

---

    TOTAL 100 pontos
    ATUAL  70 pontos

