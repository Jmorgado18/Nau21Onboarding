ONBOARDING - SIS CLAIM MANAGER	(Bem vindo/a)


Pré requisitos:

1 - Instalar homebrew: /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
2 - Instalar JetBrains Toolbox: brew install --cask jetbrains-toolbox
    1.1 - Instalar IntelliJ IDEA Ultimate a partir da Toolbox
3 - Instalar docker desktop: brew install --cask docker
    3.1 - Instalar extensão "Portainer" no Docker Desktop
4 - Lançar container de MySQL no Docker, para servir de BD para o projeto Java de onboarding.
5 - Instalar Postman: brew install --cask postman

Docker-compose exemplo de MySQL:

version: '3.8'
services:
  db:
    container_name: onboarding-mysql
    image: 'docker.io/mysql:${MYSQL_VERSION:-8.0}'
    command:
      [ "--max_connections=1000" , "--lower_case_table_names=1"]
    restart: always
    environment:
      - MYSQL_DATABASE=onboarding
      - MYSQL_ROOT_PASSWORD=password
      - MYSQL_USER= onboarding_mysql
      - MYSQL_PASSWORD= onboarding_mysql
      - MYSQL_TCP_PORT=3400
    ports:
      - "3400:3400"
    volumes:
      - 'mysql_data:/var/lib/mysql'
    stop_grace_period: 1m
volumes:
  mysql_data:
    driver: local

Este container vai ser para apagar depois do projeto de onboarding estar concluído, portanto pode mesmo ser criada a conexão com p: root u: password


PROJETO JAVA (SPRING):

Versão Java: 8
Versão Spring-Boot: 2.7.18

Objetivo:

Criar aplicação com 1 controlador rest que serve 3 endpoints:
/all-orders - (exemplo output no ficheiro "onboarding-get-all-orders-out.json")
/order/${reference} - (exemplo output no ficheiro "onboarding-get-order-by-reference-out.json")
/register - (exemplo input no ficheiro "onboarding-register-order-in.json")

Os dados devem ser persistidos em Mysql, e a estrutura de base de dados encontra-se em formato JSON no ficheiro "onboarding-db.json"

Considerações a ter:
- Alguns dados são gravados de forma diferente do que são apresentados, logo existem mapeamentos a serem feitos
- Utilizar lombok para mitigar a escrita de boiler-plate excessivo
- Podem ser utilizadas outras bibliotecas externas, como apache commons etc, para facilitar algumas tarefas
- Os dados devolvidos pelos endpoints, nunca devem ser objetos de base de dados, mas sim DTO's.


Para iniciar o projeto, basta fazer uma cópia do onboarding.zip para a máquina de desenvolvimento, fazer o unzip, e abrir o projeto no IntelliJ IDEA Ultimate.

Bom trabalho !