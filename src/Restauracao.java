/**
 * Representa as empresas da categoria Restauração.
 * @author Marcelo Gomes
 * @author Pedro Brites Santos
 * @version 1.0
 */
public abstract class Restauracao extends Empresa{
    protected int numEmpMesa;
    protected double salarioMedAnual;
    protected double nMedClientDia;

    /**
     * Construtor Restauracao.
     * Recebe o nome, distrito e coordenadas gps da superclasse Empresa.
     * @param numEmpMesa recebe o número de empregados de mesa da restauração.
     * @param salarioMedAnual recebe o salário médio anual da restauração.
     * @param nMedClientDia recebe o número médio de clientes por dia da restauração.
     */
    public Restauracao(String nome, String distrito, Gps gps, int numEmpMesa, double salarioMedAnual, double nMedClientDia) {
        super(nome, distrito, gps);
        this.categoria = "Restauração";
        this.numEmpMesa = numEmpMesa;
        this.salarioMedAnual = salarioMedAnual;
        this.nMedClientDia = nMedClientDia;
    }

    /**
     * Getter numEmpMesa.
     * @return numEmpMesa da restauração.
     */
    public int getNumEmpMesa() {
        return numEmpMesa;
    }

    /**
     * Setter numEmpMesa.
     * @param numEmpMesa recebe o numEmpMesa da restauração a definir.
     */
    public void setNumEmpMesa(int numEmpMesa) {
        this.numEmpMesa = numEmpMesa;
    }

    /**
     * Getter salarioMedAnual.
     * @return salarioMedAnual da restauração.
     */
    public double getSalarioMedAnual() {
        return salarioMedAnual;
    }

    /**
     * Setter salarioMedAnual.
     * @param salarioMedAnual recebe o salarioMedAnual da restauração a definir.
     */
    public void setSalarioMedAnual(double salarioMedAnual) {
        this.salarioMedAnual = salarioMedAnual;
    }

    /**
     * Getter nMedClientDia.
     * @return nMedClientDia da restauração.
     */
    public double getnMedClientDia() {
        return nMedClientDia;
    }

    /**
     * Setter nMedClientDia.
     * @param nMedClientDia recebe o nMedClientDia da restauração a definir.
     */
    public void setnMedClientDia(double nMedClientDia) {
        this.nMedClientDia = nMedClientDia;
    }

    /**
     * Método abstrato apresenta empresa.
     * @return informação da restauração a apresentar.
     */
    @Override
    public abstract String apresentaEmpresa();

    /**
     * Método abstrato despesa anual.
     * @return despesa anual da restauração.
     */
    @Override
    public abstract double despesaAnual();

    /**
     * Método abstrato receita anual.
     * @return receita anual da restauração.
     */
    @Override
    public abstract double receitaAnual();

    /**
     * Método abstrato lucro.
     * @return lucro da restauração (Sim ou Não).
     */
    @Override
    public abstract boolean lucro();

    /**
     * Método abstrato máximo de clientes.
     * @return número de clientes da Restauração.
     */
    @Override
    public double maxClient(){
        return nMedClientDia;
    }
}
