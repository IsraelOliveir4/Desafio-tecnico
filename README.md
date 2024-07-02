## Desafio Tecnico

Usando a sua linguagem de programação preferida solucione os seguintes desafios:

** Parte 1
Crie um programa que, dado uma lista de cidades e as faixas de CEP que as compõe,
responde a qual cidade um CEP pertence.

O seu programa deve receber linhas com um nome de cidade, um CEP inicial, e
um CEP final separados por vírgula, então uma linha com dois traços "--" e por fim
um CEP. O seu programa então deve responder o nome da cidade ao qual esse CEP pertence.

Entrada de exemplo:

A,10000000,20000000
B,30000000,40000000
C,50000000,60000000
D,70000000,80000000
--
11111111

Saída experada do exmeplo:
A

** Parte 2
Crie um programa que recebe uma lista de nomes de cidades
adjacentes e o custo de transporte entre elas, então deve calcular o
menor custo entre duas cidades não adjacentes.


O seu programa deve receber linhas com um nome de cidade, um CEP inicial, e
um CEP final separados por vírgula, então uma linha com dois traços "--".
Seguidos de linhas com dois nomes de cidade e um número representando o custo
de transportar uma mercadoria da primeira cidade até a segunda, então uma linha com
dois traços "--". Finalmente deve receber uma linha com dois CEPs.
O seu program deve então responder com a rota mais barata para transportar
uma mercadoria entre o primeiro e o segundo CEP dá ultima linha da entrada, e
o custo total desta rota.

Entrada de exemplo:
A,10000000,20000000
B,30000000,40000000
C,50000000,60000000
D,70000000,80000000
--
A,B,10
A,C,15
B,D,5
C,D,7
--
11111111,71111111

Saida experada do exemplo:
A,B,D
15