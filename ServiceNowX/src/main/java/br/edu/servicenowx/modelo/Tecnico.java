package br.edu.servicenowx.modelo;
public class Tecnico {
    public String id;
    public String nome;
    public String especialidade;
    public boolean disponivel=true;
    public int chamadosAtivos;
    public Tecnico(String id,String nome,String especialidade){this.id=id;this.nome=nome;this.especialidade=especialidade;}
}
