/**
 * Representa os restaurantes do tipo RestLocal.
 * @author Marcelo Gomes
 * @author Pedro Brites Santos
 * @version 1.0
 */
public class RestLocal extends Restaurante{
    private int nMesasEsplan;
    private double custLiAnoMesa;

    /**
     * Construtor RestLocal.
     * recebe nome, distrito e coordenadas gps da superclasse Empresa.
     * recebe numEmpMesa, salarioMedAnual e nMedClientDia da superclasse Restauracao.
     * recebe nDiasFuncAno, nMesasInt e valMedFatMesaDia da superclasse Restaurante.
     * @param nMesasEsplan recebe o número de mesas de esplanada do restaurante local.
     * @param custLiAnoMesa recebe o custo de licença anual por mesa anual do restaurante local.
     */
    public RestLocal(String nome, String distrito, Gps gps, int numEmpMesa, double salarioMedAnual, double nMedClientDia, int nDiasFuncAno, int nMesasInt, double valMedFatMesaDia, int nMesasEsplan, double custLiAnoMesa) {
        super(nome, distrito, gps, numEmpMesa, salarioMedAnual, nMedClientDia, nDiasFuncAno, nMesasInt, valMedFatMesaDia);
        this.nMesasEsplan = nMesasEsplan;
        this.custLiAnoMesa = custLiAnoMesa;
    }

    /**
     * Getter nMesasEsplan.
     * @return nMesasEsplan do restaurante local.
     */
    public int getnMesasEsplan() {
        return nMesasEsplan;
    }

    /**
     * Setter nMesasEsplan.
     * @param nMesasEsplan recebe o nMesasEsplan do restaurante local a definir.
     */
    public void setnMesasEsplan(int nMesasEsplan) {
        this.nMesasEsplan = nMesasEsplan;
    }

    /**
     * Getter custLiAnoMesa.
     * @return CustLiAnoMesa do restaurante local.
     */
    public double getCustLiAnoMesa() {
        return custLiAnoMesa;
    }

    /**
     * Setter custLiAnoMesa.
     * @param custLiAnoMesa recebe o custLiAnoMesa do restaurante local a definir.
     */
    public void setCustLiAnoMesa(double custLiAnoMesa) {
        this.custLiAnoMesa = custLiAnoMesa;
    }

    /**
     * Método para apresentar a informação pedida no ponto 1.
     * @return informação pedida sobre o restaurante local.
     */
    @Override
    public String apresentaEmpresa(){
        if(lucro()){
            return ("------------------------------------\nEmpresa: " + nome + "\nTipo de empresa: " + categoria + " (Restaurante Local)" + "\nDistrito: " + distrito +"\nDespesa Anual: " + despesaAnual() + "€\nReceita Anual: " + receitaAnual() + "€\nLucro: Sim\n");
        }else{
            return ("------------------------------------\nEmpresa: " + nome + "\nTipo de empresa: " + categoria + " (Restaurante Local)" + "\nDistrito: " + distrito +"\nDespesa Anual: " + despesaAnual() + "€\nReceita Anual: " + receitaAnual() + "€\nLucro: Não\n");
        }
    }

    /**
     * Método para calcular a despesa anual.
     * @return despesa anual do restaurante local.
     */
    @Override
    public double despesaAnual(){
        return numEmpMesa*salarioMedAnual+nMesasEsplan*custLiAnoMesa;
    }

    /**
     * Método para calcular a receita anual.
     * @return receita anual do restaurante local.
     */
    @Override
    public double receitaAnual(){
        return (nMesasInt+nMesasEsplan)*valMedFatMesaDia*nDiasFuncAno;
    }

    /**
     * Método para saber se houve lucro ou não.
     * @return lucro do restaurante local (sim ou não).
     */
    @Override
    public boolean lucro(){
        return receitaAnual() > despesaAnual();
    }

}
