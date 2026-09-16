# ADR-0002 — Consulta de status do chamado pelo solicitante

## Status
Proposto (Aula 08)

## Contexto
O solicitante abre um chamado no ServiceNowX, mas não tem nenhuma forma de acompanhar
em que ponto do atendimento ele está. O status do chamado (`ABERTO`, `ATRIBUIDO`,
`RESOLVIDO`, `ESCALADO`) já é controlado internamente pelo sistema — os métodos
`abrirChamado`, `atribuir`, `resolver` e `escalar` de `ServicoAplicacaoServiceNowX` já
atualizam o campo `Chamado.status` — mas essa informação nunca é exposta a quem abriu o
chamado. Hoje, para saber a situação, o solicitante precisaria perguntar diretamente à
equipe técnica.

Esse é o requisito RF-08-01: *"o solicitante precisa acompanhar o status do seu chamado,
para saber se ele está aberto, atribuído, resolvido ou escalado."*


| Leitura do requisito | |
|---|---|
| Quem? | Solicitante (usuário que abriu o chamado) |
| Faz o quê? | Acompanhar |
| O quê? | Status do chamado |
| Resultado esperado | Visualizar a situação atual do atendimento |
| Valores possíveis | `ABERTO`, `ATRIBUIDO`, `RESOLVIDO`, `ESCALADO` |

## Decisão
Expor a consulta de status como uma nova operação de leitura no componente de Chamados já
existente, adicionando o método `consultarStatus(idChamado)` em
`ServicoAplicacaoServiceNowX`, que busca o chamado no `RepositorioEmMemoria<Chamado>` e
devolve status, técnico responsável e prazo de SLA formatados.

Não será criado nenhum serviço, componente ou repositório novo: a funcionalidade reaproveita
integralmente as classes `Chamado`, `Tecnico` e `RepositorioEmMemoria` já existentes no
projeto.

## Alternativas consideradas

- **A — Reaproveitar o serviço existente (escolhida):** adicionar `consultarStatus` em
  `ServicoAplicacaoServiceNowX`. Menor esforço, nenhuma duplicação de regra, mantém o status
  como fonte única de verdade.
- **B — Criar um `ConsultaChamadoService` separado, só de leitura:** isolaria melhor
  leitura de escrita (CQRS simplificado), mas para o tamanho atual do projeto adicionaria
  uma classe e uma dependência a mais sem ganho real de manutenção agora.
- **C — Consultar `Chamado` diretamente pela interface, sem passar por um serviço:**
  mais rápido de implementar, mas quebraria a responsabilidade já estabelecida de que
  regras de negócio ficam no serviço, não na tela.

## Consequências

**Ganhos:**
- O solicitante passa a enxergar o status sem depender de contato manual com a equipe.
- Nenhuma classe nova é criada; o componente de Chamados continua sendo o único responsável
  pelo ciclo de vida do chamado (abrir, atribuir, resolver, escalar e agora também consultar).

**Custos / riscos:**
- `ServicoAplicacaoServiceNowX` acumula mais uma responsabilidade (agora também expõe
  leitura, além de escrita), o que pode justificar a Alternativa B no futuro, se o serviço
  continuar crescendo.
- Não há tratamento hoje para chamado inexistente além de retornar `null` — a interface
  precisará lidar com esse caso (ver fluxo alternativo da Aula 08).

## Rastreabilidade
Relacionado a RF-08-01 (README-Aula-08.md, seções 1 e 2) e ao componente de Chamados
descrito em `docs/atividades/arquitetura/componentes.md`.