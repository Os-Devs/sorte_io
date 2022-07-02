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

## 1. Requisitos Funcionais

Deseja-se um software web em Spring que permita a clientes realizar jogos lotéricos estilo Megasena online. Os clientes devem ser cadastrar no site e, a partir daí, fazerem suas apostas para um determinado sorteio que acontece numa data especificada pelo controlador.

- ✔️ Existem dois perfis no sistema: o cliente e o controlador. 
- ✔️ O cliente tem seu cadastro
- ✔️ O cliente faz suas apostas (no seu carrinho de apostas), 
- ✔️ Tem suas apostas favoritas (que podem ser refeitas sem que ele precise selecionar novamente os números) 
- ✔️ Pode conferir as apostas cujos sorteios já tenham acontecido.

Cada cliente deve ter as seguintes propriedades:

- ✔️ Nome (String) 
- ✔️ Data nascimento (java.util.Date)
- ✔️ CPF (com ou sem dígitos e traços, vocês decidem) 
- ✔️ Apostas favoritas

✔️ Os únicos campos obrigatórios no cadastro de um aluno são o nome, a data de nascimentoe o CPF. 

Uma aposta é apenas um conjunto de 6 números não repetidos entre 01 e 60 (inclusive).
- ✔️ O cliente pode realizar quantas apostas quiser.Uma aposta não pode ter menos que 6 dezenas e mais que 10. 
- ✔️ Cada aposta possui um preço, de acordo com a tabela abaixo:

```
| Número de dezenas | Preço       |
| 6                 | R$ 3,00     |
| 7                 | R$ 15,00    |
| 8                 | R$ 90,00    |
| 9                 | R$ 300,00   |
| 10                | R$ 1.200,00 |
```

Um sorteio é a seleção de um conjunto de exatamente 6 números numa determinada data. São propriedades de um sorteio:

- ✔️ Número do sorteio (sequenciam começando em 1 e contínuo)
- ✔️ Dezenas sorteadas no sorteio (6 números entre 01 e 60 não repetidos)
- ✔️ Uma data e hora do sorteio
- ✔️ Valor do prêmio do sorteio em reais (ou bitcoins?)

- ✔️ O sistema deve ter uma tela de cadastro e de login para um cliente poder se cadastrar e realizar suas apostas para um sorteio futuro.
- ✔️ Uma tela de login também deve ser disponibilizada para o usuário controlador, que realiza um sorteio. 

- ✔️ Sobre os sorteios, o controlador só poderá criar um sorteio em uma data que seja posterior ao último sorteio e sempre com pelo menos 1 semana entre eles.
- ✔️ Se houver um sorteio em aberto, um novo não poderá ser criado até que este seja realizado (números sorteados).

```
???
Apostador consulta resultado do sorteio, sistema retorna se ganhou ou quantos numeros apostador acertou 
???
```

- ✔️ Uma vez que um sorteio tenha sido feito, os clientes poderão se logare pedir a conferência de suas apostas. 

---

- ❌ O sorteio dos números poderá ser feito de forma aleatória
    - ❓ Quando o controlador pedir ao sistema

---

<div style="text-align:center;"> 
    <h1>ou</h1>
</div>

- ✔️ O controlador poderá dizer quais são as dezenas, 
    - ✔️ Assim poderíamos simular um ganhador para aquele sorteio. 

---

- ✔️ Só fará jus ao prêmio quem acertar todas as 06 dezenas. (Ou a lógica que quiser)
    - 〰 Opcionalmente, o programador poderá premiar quem acertou 04 ou 03 dezenas, recebendo por isto um percentual menor do rateio do prêmio. Este requisito não é obrigatório.


## 2. Requisitos não funcionais

1. ✔️ Utilizar o Spring MVC ou Spring Boot na implementação.
2. ✔️ Utilizar Bootstrap.
3. ✔️ Qualquer banco de dados.
4. ✔️ Utilizar o Hibernate como provider de persistência.
5. ✔️ Utilizar campos de mensagens para mostrar erros em todas os formulários.
6. ✔️ Utilizar o padrão Post_Redirect_Get.
7. ✔️ Utilizar sessões para permitir o login e logout do cliente e controlador(o nome dele deve ficar no menu superior, à direita, próximo do botão “Sair” (veja sistema spring-banco).
8. ✔️ Utilizar fragmentos para os templates Thymeleaf.

## 3. Tabela de Requisitos:
[ ✔️  〰  ❌ ] | Pontos | Item |

    [✔️] | 05 | Implementou cadastro de clientes

    [✔️] | 05 | Implementou o login/logoutde clientes e filtro de autenticação

    [✔️] | 15 | Implementou a realização de aposta por cliente

    [❌] | 15 | Implementou a realização de sorteio por controlador

    [❌] | 15 | Implementou a conferência das apostas em um sorteio

    [✔️] | 15 | Implementou as apostas preferidas de um cliente

    [✔️] | 10 | Implementou autorização via Spring Secutiry

    [✔️] | 05 | Utilizou Bootstrap

    [✔️] | 05 | Formulários validam valores de campos e mostram mensagens de erros

    [✔️] | 05 | Usou Post-Redirect-Get

    [✔️] | 05 | Utilizou fragmentos nos templates Thymeleaf

---

    TOTAL 100 pontos
    ATUAL  70 pontos

## 4. Recomendações:
1. Utilizar um nome próprio para o sistema. Evite: “projetospring”, “projetofred”, “projetopweb2”, “projeto”. Prefira: “BoaSorte”, “SpringSena”, “Milionário”, “Trevoda Sorte”, “JogaFácil” etc.

2. ✔️ Usar Maven e Git.

3. Um vídeo deve ser gravado pela equipe demonstrando todas as funcionalidades (ou bugs) do sistema rodando. Funcionalidade não apresentada no vídeo será considerada não implementada. O vídeo deve mostrar também a arquitetura do código fonte. Não precisa mostrar classe a classe, só o esquema geral (“aqui implementamos controladores para não pôra lógica dentro dos controladores Spring”, “não vamos mostrar os DAOs porque o Spring gera automático” etc).

4.  Deem um nome à equipe. Mais uma vez, seja criativo. O formulário de submissão solicitará 3 coisas: nomesdosalunos, nome da equipe e link para o vídeo no Youtube ou outro site.Apenas um membro da equipe precisa submeter o formulário.

5. ✔️ Não comecem a fazer na última semana, pois não dará tempo. O melhor momento para começar foi ontem, o segundo melhor é agora. Ponto.

6. Não será possível dilatar muito o prazo da reposição pois a entrega ocorrerá na última semana de aula do semestre letivo.
