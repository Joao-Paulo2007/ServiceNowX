# DIAGRAMA DE COMPONENTE

flowchart LR

    U["Solicitante"]

    UI["&lt;&lt;component&gt;&gt;<br/>Interface de Acompanhamento<br/>Principal.java / Tela"]

    SERVICE["&lt;&lt;component&gt;&gt;<br/>Componente de Chamados<br/>ServicoAplicacaoServiceNowX"]

    REPO["&lt;&lt;component&gt;&gt;<br/>Persistência de Chamados<br/>RepositorioEmMemoria"]

    U -->|"informa ID do chamado"| UI

    UI -->|"consultarStatus(idChamado)"| SERVICE

    SERVICE -->|"buscar chamado"| REPO

    REPO -->|"retorna Chamado"| SERVICE

    SERVICE -->|"status: ABERTO / ATRIBUIDO / RESOLVIDO / ESCALADO"| UI

    UI -->|"exibe status atual"| U