# Sistemas Distribuidos
#### Projeto de Cassiane Nodari, Jessica Dagostini e Marisa Richter

Este projeto está dividido em dois pacotes:
- Pacote Servidor (server)
- Pacote Cliente (client)

O Servidor é responsável pela coordenação dos relógios dos clientes conectados. Além disso, ele também é responsável pela eleição de um novo coordenador (quando solicitado pelo cliente). O pacote possui os seguintes fontes:
- RelogioDrive - Responsável pela criação do objeto RMI
- RelogioInterface - Interface do servidor
- RelogioServer - Servidor em si

Para iniciá-lo, deve-se executar o `RelogioDrive`. Ele é responsável por instanciar o objeto do servidor e abrir a conexão RMI para os clientes.

O cliente, por sua vez, executa chamadas ao servidor de relogio. Este pacote tambem e composto por tres arquivos:
- ClientDrive - Responsável pela criação do objeto RMI
- Client - Interface do Cliente
- ClientInterface - Cliente em si

Para iniciá-lo, deve-se executar o `ClientDrive`. Ele é responsável por instanciar o objeto do cliente e conectar com o servidor. Ao executá-lo, o primeiro cliente a se conectar com servidor será setado como coordenador. Este poderá executar 3 funcionalidades:

1. Synchronize - Para sincronizar os relógios dos servidores conectados
2. Randonize - Para setar um horário arbitrário em cada um dos clientes, a fim de demosntrar o funcionamento do Berkeley
3. Show Time - Para visualizar o horário atual do cliente

Ao selecionar uma opção diferente destas 3, o cliente em questão e finalizado.

Os outros clientes conectados, por sua vez, somente visualizarão uma opção de `Election`, para solicitar uma eleição. Caso haja um servidor coordenador, será retornado para o cliente que solicitou a eleiçao que ja existe um coordenador. Caso contrario, executara o algoritmo de eleicao do valentao.
