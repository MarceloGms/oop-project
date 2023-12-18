/**
 * Representa as restaurações do tipo Pastelaria.
 * @author Marcelo Gomes
 * @author Pedro Brites Santos
 * @version 1.0
 */
public class Pastelaria extends Restauracao{
    private double nMedBolosDia;
    private double valMedFatAnoBoloDia;

    /**
     * Construtor Pastelaria.
     * recebe nome, distrito e coordenadas gps da superclasse Empresa.
     * recebe numEmpMesa, salarioMedAnual e nMedClientDia da superclasse Restauracao.
     * @param nMedBolosDia recebe o número médio de bolos por dia da pastelaria.
     * @param valMedFatAnoBoloDia recebe o valor médio de faturação anual de bolos por dia da pastelaria.
     */
    public Pastelaria(String nome, String distrito, Gps gps, int numEmpMesa, double salarioMedAnual, double nMedClientDia, double nMedBolosDia, double valMedFatAnoBoloDia) {
        super(nome, distrito, gps, numEmpMesa, salarioMedAnual, nMedClientDia);
        this.nMedBolosDia = nMedBolosDia;
        this.valMedFatAnoBoloDia = valMedFatAnoBoloDia;
    }

    /**
     * Getter nMedBolosDia.
     * @return nMedBolosDia da pastelaria.
     */
    public double getnMedBolosDia() {
        return nMedBolosDia;
    }

    /**
     * Setter nMedBolosDia.
     * @param nMedBolosDia recebe o nMedBolosDia da pastelaria a definir.
     */
    public void setnMedBolosDia(int nMedBolosDia) {
        this.nMedBolosDia = nMedBolosDia;
    }

    /**
     * Getter valMedFatAnoBoloDia.
     * @return ValMedFatAnoBoloDia da pastelaria.
     */
    public double getValMedFatAnoBoloDia() {
        return valMedFatAnoBoloDia;
    }

    /**
     * Setter valMedFatAnoBoloDia.
     * @param valMedFatAnoBoloDia recebe o valMedFatAnoBoloDia da pastelaria a definir.
     */
    public void setValMedFatAnoBoloDia(double valMedFatAnoBoloDia) {
        this.valMedFatAnoBoloDia = valMedFatAnoBoloDia;
    }

    /**
     * Método para apresentar a informação pedida no ponto 1.
     * @return informação pedida sobre a pastelaria.
     */
    @Override
    public String apresentaEmpresa(){
        if(lucro()){
            return ("------------------------------------\nEmpresa: " + nome + "\nTipo de empresa: " + categoria + " (Pastelaria)" + "\nDistrito: " + distrito +"\nDespesa Anual: " + despesaAnual() + "€\nReceita Anual: " + receitaAnual() + "€\nLucro: Sim\n");
        }else{
            return ("------------------------------------\nEmpresa: " + nome + "\nTipo de empresa: " + categoria + " (Pastelaria)" + "\nDistrito: " + distrito +"\nDespesa Anual: " + despesaAnual() + "€\nReceita Anual: " + receitaAnual() + "€\nLucro: Não\n");
        }
    }

    /**
     * Método para calcular a despesa anual.
     * @return despesa anual da pastelaria.
     */
    @Override
    public double despesaAnual(){
        return numEmpMesa*salarioMedAnual;
    }

    /**
     * Método para calcular a receita anual.
     * @return receita anual da pastelaria.
     */
    @Override
    public double receitaAnual(){
        return nMedBolosDia*valMedFatAnoBoloDia;
    }

    /**
     * Método para saber se houve lucro ou não.
     * @return lucro da pastelaria (sim ou não).
     */
    @Override
    public boolean lucro(){
        return receitaAnual() > despesaAnual();
    }

}
