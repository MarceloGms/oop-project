/**
 * Representa as empresas da categoria Mercearia.
 * @author Marcelo Gomes
 * @author Pedro Brites Santos
 * @version 1.0
 */
public abstract class Mercearia extends Empresa{
    protected double custAnoLimp;

    /**
     * Construtor Mercearia.
     * Recebe o nome, distrito e coordenadas gps da superclasse Empresa.
     * @param custAnoLimp recebe o custo anual de limpeza da mercearia.
     */
    public Mercearia(String nome, String distrito, Gps gps, double custAnoLimp) {
        super(nome, distrito, gps);
        this.categoria = "Mercearia";
        this.custAnoLimp = custAnoLimp;
    }

    /**
     * Getter custAnoLimp.
     * @return custAnoLimp da mercearia.
     */
    public double getCustAnoLimp() {
        return custAnoLimp;
    }

    /**
     * Setter custAnoLimp.
     * @param custAnoLimp recebe o custAnoLimp da mercearia a definir.
     */
    public void setCustAnoLimp(double custAnoLimp) {
        this.custAnoLimp = custAnoLimp;
    }

    /**
     * Método abstrato apresenta empresa.
     * @return informação da mercearia a apresentar.
     */
    @Override
    public abstract String apresentaEmpresa();

    /**
     * Método abstrato despesa anual.
     * @return despesa anual da mercearia.
     */
    @Override
    public abstract double despesaAnual();

    /**
     * Método abstrato receita anual.
     * @return receita anual da mercearia.
     */
    @Override
    public abstract double receitaAnual();

    /**
     * Método abstrato lucro.
     * @return lucro da mercearia (Sim ou Não).
     */
    @Override
    public abstract boolean lucro();

    /**
     * Método abstrato máximo de clientes.
     * @return -1 visto que estas empresas são da categoria Mercearia e não interessam para o ponto 3.
     */
    @Override
    public double maxClient(){
        return -1;
    }
}
