/**
 * Representa as retaurações do tipo Cafe.
 * @author Marcelo Gomes
 * @author Pedro Brites Santos
 * @version 1.0
 */
public class Cafe extends Restauracao{
    private double nMedCafesDia;
    private double valMedFatAnoCafeDia;

    /**
     * Construtor Cafe.
     * recebe nome, distrito e coordenadas gps da superclasse Empresa.
     * recebe numEmpMesa, salarioMedAnual e nMedClientDia da superclasse Restauracao.
     * @param nMedCafesDia recebe o número médio de cafés por dia do café.
     * @param valMedFatAnoCafeDia recebe o valor médio de faturação anual por café diário.
     */
    public Cafe(String nome, String distrito, Gps gps, int numEmpMesa, double salarioMedAnual, double nMedClientDia, double nMedCafesDia, double valMedFatAnoCafeDia) {
        super(nome, distrito, gps, numEmpMesa, salarioMedAnual, nMedClientDia);
        this.nMedCafesDia = nMedCafesDia;
        this.valMedFatAnoCafeDia = valMedFatAnoCafeDia;
    }

    /**
     * Getter nMedCafesDia.
     * @return nMedCafesDia do café.
     */
    public double getnMedCafesDia() {
        return nMedCafesDia;
    }

    /**
     * Setter nMedCafesDia.
     * @param nMedCafesDia recebe o nMedCafesDia do café a definir.
     */
    public void setnMedCafesDia(double nMedCafesDia) {
        this.nMedCafesDia = nMedCafesDia;
    }

    /**
     * Getter valMedFatAnoCafeDia.
     * @return valMedFatAnoCafeDia do café.
     */
    public double getValMedFatAnoCafeDia() {
        return valMedFatAnoCafeDia;
    }

    /**
     * Setter valMedFatAnoCafeDia.
     * @param valMedFatAnoCafeDia recebe o valMedFatAnoCafeDia do café a definir.
     */
    public void setValMedFatAnoCafeDia(double valMedFatAnoCafeDia) {
        this.valMedFatAnoCafeDia = valMedFatAnoCafeDia;
    }

    /**
     * Método para apresentar a informação pedida no ponto 1.
     * @return informação pedida sobre o café.
     */
    @Override
    public String apresentaEmpresa(){
        if(lucro()){
            return ("------------------------------------\nEmpresa: " + nome + "\nTipo de empresa: " + categoria + " (Café)" + "\nDistrito: " + distrito +"\nDespesa Anual: " + despesaAnual() + "€\nReceita Anual: " + receitaAnual() + "€\nLucro: Sim\n");
        }else{
            return ("------------------------------------\nEmpresa: " + nome + "\nTipo de empresa: " + categoria + " (Café)" + "\nDistrito: " + distrito +"\nDespesa Anual: " + despesaAnual() + "€\nReceita Anual: " + receitaAnual() + "€\nLucro: Não\n");
        }
    }

    /**
     * Método para calcular a despesa anual.
     * @return despesa anual do café.
     */
    @Override
    public double despesaAnual(){
        return numEmpMesa * salarioMedAnual;
    }

    /**
     * Método para calcular a receita anual.
     * @return receita anual do café.
     */
    @Override
    public double receitaAnual(){
        return nMedCafesDia * valMedFatAnoCafeDia;
    }

    /**
     * Método para saber se houve lucro ou não.
     * @return lucro do café (sim ou não).
     */
    @Override
    public boolean lucro(){
        return receitaAnual() > despesaAnual();
    }

}
