# __Descrição da aplicação__

A aplicação Back-End foi desenvolvida para atender uma clínica hospitalar, utilizando a linguagem Java e o framework Spring . A API adota uma arquitetura modular , com separação de responsabilidades em diferentes módulos, o que facilita a manutenção, escalabilidade e testes do sistema. Os principais módulos de aplicação são: 

* __Controladores:__ Responsáveis pela coleta e processamento das requisições HTTP. Cada driver mapeia as rotas da API, invoca os serviços correspondentes e retorna as respostas de forma estruturada. São eles que lidam com as interações externas e a comunicação entre a camada de apresentação e a lógica de negócios. 

* __Modelos:__ Representam as entidades de negócio da aplicação, com mapeamento direto para o banco de dados através da JPA (Java Persistence API). Essas entidades encapsulam os dados necessários para as operações CRUD, como informações de pacientes, médicos, e agendamentos. 

* __Repositórios:__ Utiliza Spring Data JPA para realizar operações no banco de dados de forma abstrata. Eles são responsáveis pela comunicação direta com o banco de dados, revisões e modificações nas entidades. O Spring Data JPA facilita o uso de queries customizadas e consultas nativas quando necessário. 

* __Serviços:__ Implementamos uma lógica de negócios da aplicação, orquestrando as operações de manipulação de dados entre os controladores e os repositórios. São também responsáveis por realizar a validação das regras de negócio antes de executar qualquer operação no banco de dados, como verificação de disponibilidade de horários para consultas e validação de agendamentos. 

* __DTOs (Data Transfer Objects):__ São objetos usados para transferir dados entre camadas da aplicação de maneira eficiente, minimizando a quantidade de dados trafegados nas requisições e respostas. A utilização de DTOs também ajuda 

* __Infraestrutura:__ Módulo responsável pela configuração e integração de componentes de suporte, como o sistema de segurança, gerenciamento de abordagens, configuração de conexões com o banco de dados, e implementação de aspectos não funcionais da aplicação (ex.: registro, auditorias , etc.). 

 

# __Funcionalidades:__ 

* __CRUD de clientes, profissionais e consultas.__

* __Sistema de agendamento.__

* __Validações dos agendamentos de consultas__

# Tecnologias:  

* __Linguagem Java,__ 

* __Projeto com MAVE,__ 

* __Spring Boot,__

* __Spring Data JPA,__  

* __MySQL,__

* __Spring Security,__ 

* __Spring Doc__ 

 

# Segurança e Autenticação 

A aplicação utiliza a tecnologia do Spring Security  que  realiza o gerenciamento de autenticação e autorização. A autenticação é feita através de JWT (JSON Web Tokens) , onde o token gerado após o login do usuário é utilizado para validar as requisições subsequentes. O token contém informações criptografadas sobre a identidade do usuário e suas permissões, garantindo que apenas usuários autenticados possam acessar rotas protegidas. 

_Além disso, a API possui um sistema de funções de usuário , com dois perfis principais:_ 

* __USUÁRIO :__ Permite ao usuário realizar operações CRUD apenas nos dados que lhe pertencem, como agendar, consultar ou alterar suas consultas médicas. 

* __ADMIN :__ Permite ao administrador realizar operações CRUD em todos os dados do sistema, incluindo gestão de pacientes, médicos, e agendamentos. O ADMIN também tem permissão para cadastrar novos usuários e gerenciar o acesso ao sistema. 

# Banco de Dados 

A aplicação utiliza o banco de dados MySQL , que armazena informações sobre pacientes , médicos , consultas e outros dados essenciais para a operação da clínica. A estrutura do banco foi projetada para garantir a integridade referencial entre os dados e eficiência nas consultas, utilizando chaves estrangeiras, índices e procedimentos armazenados quando necessário. 

_A API permite a execução das operações CRUD sobre as principais entidades da aplicação:_ 

* __Pacientes :__ Cadastro, atualização e consulta de informações pessoais, histórico médico e consultas agendadas. 

* __Médicos :__ Cadastro, atualização e consulta de dados profissionais, especialidades e horários disponíveis. 

* __Consultas :__ Agendamento, reagendamento e cancelamento de consultas médicas, com validações automáticas para evitar agendamentos conflitantes. 

# Validações de Regras de Negócio 

A aplicação aplica várias validações de regras de negócio para garantir a integridade dos dados e a execução correta das transações. Exemplos incluem: 

Validação de Disponibilidade : Antes de agendar uma consulta, a API verifica a disponibilidade do médico e do paciente no horário solicitado. 

Restrições de Agendamento : Um paciente não pode agendar múltiplas consultas no mesmo horário ou com o mesmo médico. 

Validação de Dados : Verificação de dados obrigatórios (ex.: nome, CPF, dados de nascimento de pacientes) e formatos corretos para certos campos (ex.: CPF, e-mail). 

# Documentação da API 

A API é completamente documentada utilizando o SpringDoc , gerando uma documentação interativa e de fácil acesso, onde os desenvolvedores podem consultar detalhes sobre os endpoints, parâmetros, respostas esperadas e exemplos de requisições. A documentação inclui: 

Descrição dos endpoints e seus métodos HTTP (GET, POST, PUT, DELETE). 

Detalhes sobre os parâmetros esperados (parâmetros de consulta, corpo, etc.). 

Exemplo de respostas com códigos de status HTTP e mensagens de erro. 

Autenticação via JWT, com exemplos de como obter e utilizar o token. 

 

# Dependências presentes no arquivo pom.xml: 

* Thymeleaf 

* Starter-Web 

* Lombok 

* Validation 

* Flyway 

* JPA  

* Mysql-connector 

* Spring Security 

* spring-security-test 

* Auth0 (TokenJWT) 

* Devtools 

* Spring Doc 

 

# Configurações da conexão com Banco de dados no arquivo .properties: 

 

spring.datasource.url=jdbc:mysql://localhost:3306/clinica 

spring.datasource.username=root 

spring.datasource.password=Carlos 

spring.jpa.hibernate.ddl-auto=update 

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver 

spring.jpa.show-sql=true 

spring.jpa.properties.hibernate.format_sql=true 

# Conclusão 

A aplicação foi projetada para ser robusta, escalável e segura, atendendo às necessidades específicas de uma clínica hospitalar. Com a utilização do Spring Boot, Spring Security, Spring Data JPA e MySQL, a solução oferece uma estrutura sólida e fácil de manter, ao mesmo tempo em que oferece segurança, desempenho e flexibilidade para crescer conforme a demanda. 
