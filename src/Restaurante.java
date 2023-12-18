/**
 * Representa as retaurações do tipo Restaurante.
 * @author Marcelo Gomes
 * @author Pedro Brites Santos
 * @version 1.0
 */
public abstract class Restaurante extends Restauracao{
    protected int nDiasFuncAno;
    protected int nMesasInt;
    protected double valMedFatMesaDia;

    /**
     * Construtor Restaurante.
     * recebe nome, distrito e coordenadas gps da superclasse Empresa.
     * recebe numEmpMesa, salarioMedAnual e nMedClientDia da superclasse Restauracao.
     * @param nDiasFuncAno recebe o número de dias de funcionamento por ano do restaurante.
     * @param nMesasInt recebe o número de mesas interiores do restaurante.
     * @param valMedFatMesaDia recebe o valor médio de faturação por mesa diário do restaurante.
     */
    public Restaurante(String nome, String distrito, Gps gps, int numEmpMesa, double salarioMedAnual, double nMedClientDia, int nDiasFuncAno, int nMesasInt, double valMedFatMesaDia) {
        super(nome, distrito, gps, numEmpMesa, salarioMedAnual, nMedClientDia);
        this.nDiasFuncAno = nDiasFuncAno;
        this.nMesasInt = nMesasInt;
        this.valMedFatMesaDia = valMedFatMesaDia;
    }

    /**
     * Getter nDiasFuncAno.
     * @return nDiasFuncAno do restaurante.
     */
    public int getnDiasFuncAno() {
        return nDiasFuncAno;
    }

    /**
     * Setter nDiasFuncAno.
     * @param nDiasFuncAno recebe o nDiasFuncAno do restaurante a definir.
     */
    public void setnDiasFuncAno(int nDiasFuncAno) {
        this.nDiasFuncAno = nDiasFuncAno;
    }

    /**
     * Getter nMesasInt.
     * @return nMesas do restaurante.
     */
    public int getnMesasInt() {
        return nMesasInt;
    }

    /**
     * Setter nMesasInt.
     * @param nMesasInt recebe o nMesasInt do restaurante a definir.
     */
    public void setnMesasInt(int nMesasInt) {
        this.nMesasInt = nMesasInt;
    }

    /**
     * Getter valMedFatMesaDia.
     * @return valMedFatMesaDia do restaurante.
     */
    public double getValMedFatMesaDia() {
        return valMedFatMesaDia;
    }

    /**
     * Setter valMedFatMesaDia.
     * @param valMedFatMesaDia recebe o valMedFatMesaDia do restaurante a definir.
     */
    public void setValMedFatMesaDia(double valMedFatMesaDia) {
        this.valMedFatMesaDia = valMedFatMesaDia;
    }

    /**
     * Método abstrato apresenta empresa.
     * @return informação do restaurante a apresentar.
     */
    @Override
    public abstract String apresentaEmpresa();

    /**
     * Método abstrato despesa anual.
     * @return despesa anual do restaurante.
     */
    @Override
    public abstract double despesaAnual();

    /**
     * Método abstrato receita anual.
     * @return receita anual do restaurante.
     */
    @Override
    public abstract double receitaAnual();

    /**
     * Método abstrato lucro.
     * @return lucro do restaurante (Sim ou Não).
     */
    @Override
    public abstract boolean lucro();

}
