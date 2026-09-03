package br.edu.servicenowx.servico;
import br.edu.servicenowx.modelo.*;
import br.edu.servicenowx.repositorio.*;
import br.edu.servicenowx.legado.*;
public class ServicoAplicacaoServiceNowX {
    public final RepositorioEmMemoria<Chamado> chamados=new RepositorioEmMemoria<>();
    public final RepositorioEmMemoria<Tecnico> tecnicos=new RepositorioEmMemoria<>();
    private final ApiDiretorioLegada diretorio=new ApiDiretorioLegada();
    private final ApiMonitoramentoLegada monitoramento=new ApiMonitoramentoLegada();
    private final ApiNotificacaoLegada notificacoes=new ApiNotificacaoLegada();
    private final ApiSuporteFornecedorLegada fornecedores=new ApiSuporteFornecedorLegada();
    public Chamado abrirChamado(String id,String solicitante,String categoria,String prioridade,String abertoEm){
        String usuario=diretorio.localizarUsuario(solicitante); System.out.println("DIRETORIO="+usuario);
        Chamado chamado=new Chamado(id,solicitante,categoria,prioridade,abertoEm);
        chamado.prazoEm=calcularPrazo(prioridade,abertoEm);
        chamados.salvar(id,chamado); return chamado;
    }
    private String calcularPrazo(String prioridade,String abertoEm){
        if("CRITICA".equals(prioridade)) return "2026-08-11T01:00:00";
        if("ALTA".equals(prioridade)) return "2026-08-11T12:00:00";
        return "2026-08-12T18:00:00";
    }
    public void atribuir(String idChamado){
        Chamado chamado=chamados.buscar(idChamado); if(chamado==null)return;
        Tecnico tecnico=tecnicos.listarTodos().stream().findFirst().orElse(null);
        if(tecnico!=null){chamado.idTecnico=tecnico.id; tecnico.chamadosAtivos++; tecnico.disponivel=false; chamado.status="ATRIBUIDO";}
    }
    public void resolver(String idChamado){
        Chamado chamado=chamados.buscar(idChamado); if(chamado==null)return;
        chamado.status="RESOLVIDO";
        notificacoes.enviar("EMAIL","user@exemplo.com","Chamado "+idChamado+" resolvido");
    }
    public void escalar(String idChamado){
        Chamado chamado=chamados.buscar(idChamado); if(chamado==null)return;
        fornecedores.abrirCaso("FORNECEDOR-X","Chamado "+idChamado+" "+chamado.categoria); chamado.status="ESCALADO";
    }
    public Chamado registrarAlertaMonitoramento(String origem,String mensagem){
        String bruto=monitoramento.criarIncidente(origem,mensagem); String id="MON-"+origem;
        Chamado chamado=new Chamado(id,"monitoramento",origem,"CRITICA","2026-08-10T23:00:00");
        chamado.historico.add(bruto); chamados.salvar(id,chamado); return chamado;
    }
}
