/**
 * Representa as mercearias do tipo Frutaria.
 * @author Marcelo Gomes
 * @author Pedro Brites Santos
 * @version 1.0
 */
public class Frutaria extends Mercearia{
    private int nProd;
    private double valMedFatAnoProd;

    /**
     * Construtor Frutaria.
     * recebe nome, distrito e coordenadas gps da superclasse Empresa.
     * recebe custAnoLimp da superclasse Mercearia.
     * @param nProd recebe o número de produtos da frutaria.
     * @param valMedFatAnoProd recebe o valor médio de faturação anual por produto da frutaria.
     */
    public Frutaria(String nome, String distrito, Gps gps, double custAnoLimp, int nProd, double valMedFatAnoProd) {
        super(nome, distrito, gps, custAnoLimp);
        this.nProd = nProd;
        this.valMedFatAnoProd = valMedFatAnoProd;
    }

    /**
     * Getter nProd.
     * @return nProd da frutaria.
     */
    public int getnProd() {
        return nProd;
    }

    /**
     * Setter nProd.
     * @param nProd recebe o nProd da frutaria a definir.
     */
    public void setnProd(int nProd) {
        this.nProd = nProd;
    }

    /**
     * Getter valMedFatAnoProd.
     * @return valMedFatAnoProd da frutaria.
     */
    public double getValMedFatAnoProd() {
        return valMedFatAnoProd;
    }

    /**
     * Setter valMedFatAnoProd.
     * @param valMedFatAnoProd recebe o valMedFatAnoProd da frutaria a definir.
     */
    public void setValMedFatAnoProd(double valMedFatAnoProd) {
        this.valMedFatAnoProd = valMedFatAnoProd;
    }

    /**
     * Método para apresentar a informação pedida no ponto 1.
     * @return informação pedida sobre a frutaria.
     */
    @Override
    public String apresentaEmpresa(){
        if(lucro()){
            return ("------------------------------------\nEmpresa: " + nome + "\nTipo de empresa: " + categoria + " (Frutaria)" + "\nDistrito: " + distrito +"\nDespesa Anual: " + despesaAnual() + "€\nReceita Anual: " + receitaAnual() + "€\nLucro: Sim\n");
        }else{
            return ("------------------------------------\nEmpresa: " + nome + "\nTipo de empresa: " + categoria + " (Frutaria)" + "\nDistrito: " + distrito +"\nDespesa Anual: " + despesaAnual() + "€\nReceita Anual: " + receitaAnual() + "€\nLucro: Não\n");
        }
    }

    /**
     * Método para calcular a despesa anual.
     * @return despesa anual da frutaria.
     */
    @Override
    public double despesaAnual(){
        return custAnoLimp;
    }

    /**
     * Método para calcular a receita anual.
     * @return receita anual da frutaria.
     */
    @Override
    public double receitaAnual(){
        return nProd*valMedFatAnoProd;
    }

    /**
     * Método para saber se houve lucro ou não.
     * @return lucro da frutaria (sim ou não).
     */
    @Override
    public boolean lucro(){
        return receitaAnual() > despesaAnual();
    }

}
