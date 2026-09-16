
# 5.2 Diagrama de sequência

```mermaid
sequenceDiagram
    actor Solicitante
    participant Tela as TelaConsultaChamado
    participant Servico as ServicoAplicacaoServiceNowX
    participant Repo as RepositorioEmMemoria~Chamado~

    Solicitante->>Tela: informa idChamado
    Tela->>Servico: consultarStatus(idChamado)
    Servico->>Repo: buscar(idChamado)
    Repo-->>Servico: chamado
    Servico-->>Tela: status formatado (StatusChamadoDTO)
    Tela-->>Solicitante: exibe status, tecnico e prazo
```
1- O Solicitante informa o número do chamado (idChamado) para a TelaConsultaChamado — é o ponto de entrada da interação.
2- A tela não faz nenhuma regra sozinha: ela apenas repassa o pedido chamando consultarStatus(idChamado) no ServicoAplicacaoServiceNowX. Isso reforça a responsabilidade já definida no projeto — regra de negócio fica no serviço, não na interface.
3- O serviço busca o chamado no RepositorioEmMemoria, que devolve o objeto Chamado (com status, técnico e prazo já salvos internamente).
4- O serviço monta a resposta formatada e a devolve para a tela.
5- A tela, por fim, exibe o resultado ao solicitante.