![](https://i.imgur.com/kz4z3S7.jpg) 
![Licença](https://img.shields.io/badge/license-MIT-green)
![Badge em Desenvolvimento](https://img.shields.io/badge/release%20date-november/08-yellow)
![Gradle Version](https://img.shields.io/badge/gradle-8.4.0-blue)
![Java Version](https://img.shields.io/badge/java-17-blue)

# <h1 align="center">FIAP Play</h1>

Apresentamos o **FIAP Play**, uma poderosa ferramenta que revolucionará a forma como você gerencia e assiste vídeos na internet. Com nosso sistema em sua mão, você poderá fazer uploads de vídeos e
disponibilizar para seus usuários assistirem.

## 📄 Índice

* [Descrição do Projeto](#descrição-do-projeto)
* [Arquitetos Responsáveis](#arquitetos-responsáveis)
* [Funcionalidades](#funcionalidades)
* [Acesso ao Projeto](#acesso-ao-projeto)
* [Execução do Projeto](#execução-do-projeto)
* [Tecnologias utilizadas](#tecnologias-utilizadas)
* [Acesso ao Banco de Dados](#acesso-ao-banco-de-dados)
* [Relatório Técnico](#relatório-técnico)
* [Desafios](#desafios)
* [Documentação Técnica](#documentação-técnica)

## Descrição do Projeto

O projeto consiste na construção de uma solução de streaming de vídeo. Os usuários poderão fazer o upload de vídeos com título, decrição, URL e data de publicação.
A lsitagem de vídeos é paginada e ordenavél por data de publicação. É possível fazer buscas por título e data de publicação. Também é possível favoritar os vídeos.

## Arquitetos Responsáveis

| [<img src="https://avatars.githubusercontent.com/u/42851702?v=4" width=115><br><sub>Lucas Mendes</sub>](https://github.com/Luzeraaa) | [<img src="https://avatars.githubusercontent.com/u/56560361?v=4" width=115><br><sub>Aderson Neto</sub>](https://github.com/avcneto) | [<img src="https://avatars.githubusercontent.com/u/19624216?v=4" width=115><br><sub>Felipe Chimin</sub>](https://github.com/flpchimin) | [<img src="https://avatars.githubusercontent.com/u/52970727?v=4" width=115><br><sub>Gustavo Makimori</sub>](https://github.com/gyfmaki) | [<img src="https://avatars.githubusercontent.com/u/88151987?v=4" width=115><br><sub>Pedro Paratelli</sub>](https://github.com/PedroParatelli) |
|:------------------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------------------------:|

## Funcionalidades

Cadastrar, editar e consultar vídeos.  
Favoritar vídeos.

## Acesso ao projeto

Você pode [acessar o código fonte do projeto inicial](https://github.com/avcneto/postech-video-streaming)
ou [baixá-lo](https://github.com/avcneto/postech-video-streaming/archive/refs/heads/main.zip).


## Execução do Projeto

Após baixar o projeto, você pode abrir com a IDE de preferência e configurar as variáveis de ambiente para acessar o
banco de dados.

1. Fazer o [download](https://github.com/avcneto/postech-video-streaming/archive/refs/heads/main.zip);
2. Instalar Docker Desktop (Caso esteja em ambiente Windowns instalar WSL);
3. Abrir com IDE de preferência;
4. Configurar as varíaveis de ambiente para acessar o banco de dados (D:\Projeto_Fase4\src\main\resources\application.yaml):
    * MONGO_INITDB_ROOT_DATABASE=video-streaming
    * MONGO_INITDB_ROOT_PASSWORD=my-password
    * MONGO_INITDB_ROOT_USERNAME=admin
5. Executar o projeto.

## Acesso ao Banco de Dados

A persistência de dados será realizado através do banco de dados MongoDB para dados cadastrais. 
Já os vídeos ficarão armazenados no MinIO.

- Para acessar o admin precisa utilizar as credenciais:
- ``user: admin``
- ``password: my-password``

[Acesso ao admin do MinIO](http://localhost:9001/browse/)
[Acesso ao site do MinIO](https://min.io/)

## Tecnologias utilizadas

- Java 17 (Versão atualizada e estável da linguagem Java)
- Gradle (Ferramenta amplamente adotada para gerenciamento de dependências)
- Kotlin (Linguagem de programação)
- Spring: Boot, Webflux, Security, MVC, Data JPA (Frameworks populares para desenvolvimento de aplicativos Java)
- JPA (Java Persistence API) (Especificação padrão para persistência de dados em Java)
- Lombok (Biblioteca para reduzir a verbosidade do código e automatizar tarefas comuns)
- Jakarta Bean Validation (Especificação para validação de dados em Java)
- Docker
- MinIO


<div style="display: inline_block"><br>
<img src=https://raw.githubusercontent.com/github/explore/5b3600551e122a3277c2c5368af2ad5725ffa9a1/topics/java/java.png width="65" height="60"
/>
<img src=https://www.eclipse.org/community/eclipse_newsletter/2015/may/images/gradlephant.png width="60" height="55"
/>
<img src=https://repository-images.githubusercontent.com/389429650/7105a193-ad96-45cc-a3be-87cdfda75ebe width="60" height="55"
/>
<img src=https://th.bing.com/th/id/R.d8469eae9c8a4aa8ba0104a9d636d5f8?rik=WXdhpHKO0QTl6g&riu=http%3a%2f%2fhmkcode.github.io%2fimages%2fspring%2fspring.png&ehk=l%2b%2fhOIEAi407AyPHHjQT0NnUHU%2fH%2bjQzbnquLbAEdSI%3d&risl=&pid=ImgRaw&r=0 width="60" height="55" width="60" height="55"
/>
<img src=https://i.imgur.com/LjohcGj.png width="70" height="55" width="70" height="55"
/>
<img src=https://www.mundodocker.com.br/wp-content/uploads/2015/06/docker_facebook_share.png width="60" height="55" width="60" height="55"
/>
<img src=https://oopy.lazyrockets.com/api/v2/notion/image?src=https:%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F3ed7a304-a24b-4c45-831f-1755950e4260%2Flombok.png&blockId=552b6017-489d-4bcd-bb44-803f5e94bac9&width=256   width="60" height="55"
/>
<img src=https://i.imgur.com/xvCUS6W.png   width="60" height="55"
/>
</div>

## Relatório Técnico

A arquitetura utilizada neste projeto baseia-se nos de conceitos Clean Archtecture que
promove uma estrutura modular e independente de frameworks, priorizando a separação de preocupações e facilitando a manutenção, teste e escalabilidade do sistema. É uma abordagem que enfatiza a organização do código em camadas bem definidas, com regras de negócio no núcleo e detalhes de infraestrutura nas bordas, 
visando a criação de sistemas robustos e flexíveis.

A versão 17 do Java foi escolhida como base para o projeto devido à sua estabilidade e atualização no momento do
desenvolvimento. Para facilitar a configuração e o gerenciamento de dependências, o projeto adotou o Gradle, que possui
uma estrutura simples e ampla biblioteca de plugins. Além disso, o Gradle possui uma vasta integração com repositórios
centrais e uma
documentação extensa, tornando-o uma escolha popular e confiável para a construção e gerenciamento de projetos Java.

O Spring WebFlux é uma estrutura reativa do Spring Framework, concebida para construir aplicativos escaláveis e assíncronos. Ao adotar a programação reativa, utilizando tipos como Mono e Flux,
o WebFlux lida eficientemente com várias solicitações simultâneas, garantindo alto desempenho mesmo sob carga intensiva. Sua flexibilidade é evidente na escolha entre diferentes backends, como servidores baseados em Servlet ou Netty. 
A integração fácil com projetos Spring, incluindo o Spring Boot, simplifica o desenvolvimento de aplicativos reativos. Em resumo, o Spring WebFlux é valioso para construir sistemas robustos, escaláveis e eficientes em cenários onde a concorrência e a assincronia são cruciais.

Para de reduzir a verbosidade e os famosos códigos boilerplates do código, além de automatizar a geração de getters,
setters, construtores e outros métodos comuns, o projeto utilizou o Lombok, uma biblioteca para Java. O Lombok também
fornece a anotação Slf4j para logar erros internos da aplicação, mantendo-os ocultos do usuário final.

Para validar e garantir a integridade dos dados no aplicativo Java, foi utilizado o Jakarta Bean Validation (
anteriormente conhecida como Bean Validation 2.0).
Essa abordagem eficiente permite verificar se os dados inseridos atendem a padrões específicos. O uso do @Validator com expressões regulares ajuda a manter a consistência dos
dados e reduzir erros ou entradas inválidas, oferecendo uma forma poderosa e flexível de validação de dados no projeto.

Para garantir a persistência de dados, foi implementada uma instância do MongoDB(NoSQL) em um contêiner Docker,
proporcionando isolamento eficiente de responsabilidades, portabilidade, escalabilidade, facilidade de backup e
segurança, otimizando o desenvolvimento e a manutenção da aplicação. Já o armazenamento dos vídeos foi realizado no MinIO que é um servidor de armazenamento de objetos baseado em nuvem, que permite armazenar dados não estruturados, sem hierarquia. Ele é compatível com os principais recursos do S3 da AWS, 
que utiliza buckets para organizar objetos. Os objetos são dados binários, como fotos, vídeos, logs, backups ou imagens de container/VM. O MinIO é uma solução de alto desempenho, open source e escalável, que pode ser instalada em diversas plataformas, como Linux, Windows, MacOS e Docker.

## Desafios

- Incluir as regras de validações bem como seus regexs.
- Tratamento de exceções para possíveis erros durante o consumo das APIs.
- Determinação das responsabilidades dos membros da equipe.
- Subir o bando de dados em container Docker.
- Utilização do WebFlux.
- Teste unitários (classe de teste)

## Documentação Técnica

***

### Disclaimer

Postman Collection: [Collection](src/main/resources/doc/video-streaming.postman_collection.json)

Postman Documentation: [Documentation](https://documenter.getpostman.com/view/16265912/2s9YysCMDJ)

![](https://i.imgur.com/L0pe30T.png)
