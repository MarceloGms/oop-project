/**
 * Representa as mercearias do tipo Mercado.
 * @author Marcelo Gomes
 * @author Pedro Brites Santos
 * @version 1.0
 */
public class Mercado extends Mercearia{
    private String tipo;
    private double areaCorr;
    private double valMedFatAnoArea;

    /**
     *
     * recebe nome, distrito e coordenadas gps da superclasse empresa.
     * recebe custAnoLimp da superclasse Mercearia.
     * @param tipo recebe o tipo de mercado (mini, super ou hiper).
     * @param areaCorr recebe a área dos corredores do mercado.
     * @param valMedFatAnoArea recebe o valor médio de faturação anual por área do mercado.
     */
    public Mercado(String nome, String distrito, Gps gps, double custAnoLimp, String tipo, double areaCorr, double valMedFatAnoArea) {
        super(nome, distrito, gps, custAnoLimp);
        this.tipo = tipo;
        this.areaCorr = areaCorr;
        this.valMedFatAnoArea = valMedFatAnoArea;
    }

    /**
     * Getter tipo.
     * @return tipo de mercado.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Setter tipo.
     * @param tipo recebe o tipo de mercado a definir (mini ,super ou hiper).
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Getter areaCorr.
     * @return areaCorr do mercado.
     */

    public double getAreaCorr() {
        return areaCorr;
    }

    /**
     * Setter areaCorr.
     * @param areaCorr recebe a areaCorr do mercado a definir.
     */
    public void setAreaCorr(double areaCorr) {
        this.areaCorr = areaCorr;
    }

    /**
     * Getter valMedFatAnoArea.
     * @return valMedFatAnoArea do mercado.
     */
    public double getValMedFatAnoArea() {
        return valMedFatAnoArea;
    }

    /**
     * Setter valMedFatAnoArea
     * @param valMedFatAnoArea recebe o valMedFatAnoArea do mercado a defiinir.
     */
    public void setValMedFatAnoArea(double valMedFatAnoArea) {
        this.valMedFatAnoArea = valMedFatAnoArea;
    }

    /**
     * Método para apresentar a informação pedida no ponto 1.
     * @return informação pedida sobre o mercado.
     */
    @Override
    public String apresentaEmpresa(){
        if(lucro()){
            return ("------------------------------------\nEmpresa: " + nome + "\nTipo de empresa: " + categoria + " (" + tipo + " Mercado)" + "\nDistrito: " + distrito +"\nDespesa Anual: " + despesaAnual() + "€\nReceita Anual: " + receitaAnual() + "€\nLucro: Sim\n");
        }else{
            return ("------------------------------------\nEmpresa: " + nome + "\nTipo de empresa: " + categoria + " (" + tipo + " Mercado)" + "\nDistrito: " + distrito +"\nDespesa Anual: " + despesaAnual() + "€\nReceita Anual: " + receitaAnual() + "€\nLucro: Não\n");
        }
    }

    /**
     * Método para calcular a despesa anual.
     * @return despesa anual do mercado.
     */
    @Override
    public double despesaAnual(){
        return custAnoLimp;
    }

    /**
     * Método para calcular a receita anual.
     * @return receita anual do mercado.
     */
    @Override
    public double receitaAnual(){
        return areaCorr*valMedFatAnoArea;
    }

    /**
     * Método para saber se houve lucro ou não.
     * @return lucro do mercado (sim ou não).
     */
    @Override
    public boolean lucro(){
        return receitaAnual() > despesaAnual();
    }

}
