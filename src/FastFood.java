/**
 * Representa os restaurantes do tipo FastFood
 * @author Marcelo Gomes
 * @author Pedro Brites Santos
 * @version 1.0
 */
public class FastFood extends Restaurante{
    private double nMedDiaCliDrive;
    private double valMedDiaFatCliDrive;

    /**
     * Construtor FastFood.
     * recebe nome, distrito e coordenadas gps da superclasse Empresa.
     * recebe numEmpMesa, salarioMedAnual e nMedClientDia da superclasse Restauracao.
     * recebe nDiasFuncAno, nMesasInt e valMedFatMesaDia da superclasse Restaurante.
     * @param nMedDiaCliDrive recebe o número médio diário de clientes drive-thru do fast food.
     * @param valMedDiaFatCliDrive recebe o valor médio diário de faturação de clientes drive-thru do fast food.
     */
    public FastFood(String nome, String distrito, Gps gps, int numEmpMesa, double salarioMedAnual, double nMedClientDia, int nDiasFuncAno, int nMesasInt, double valMedFatMesaDia, double nMedDiaCliDrive, double valMedDiaFatCliDrive) {
        super(nome, distrito, gps, numEmpMesa, salarioMedAnual, nMedClientDia, nDiasFuncAno, nMesasInt, valMedFatMesaDia);
        this.nMedDiaCliDrive = nMedDiaCliDrive;
        this.valMedDiaFatCliDrive = valMedDiaFatCliDrive;
    }

    /**
     * Getter nMedDiaCliDrive.
     * @return nMedDiaCliDrive do fast food.
     */
    public double getnMedDiaCliDrive() {
        return nMedDiaCliDrive;
    }

    /**
     * Setter nMedDiaCliDrive
     * @param nMedDiaCliDrive recebe o nMedDiaCliDrive do fast food a definir.
     */
    public void setnMedDiaCliDrive(double nMedDiaCliDrive) {
        this.nMedDiaCliDrive = nMedDiaCliDrive;
    }

    /**
     * Getter valMedDiaFatCliDrive.
     * @return ValMedDiaFatCliDrive do fast food.
     */
    public double getValMedDiaFatCliDrive() {
        return valMedDiaFatCliDrive;
    }

    /**
     * Setter ValMedDiaFatCliDrive.
     * @param valMedDiaFatCliDrive recebe o ValMedDiaFatCliDrive do fast food a definir.
     */
    public void setValMedDiaFatCliDrive(double valMedDiaFatCliDrive) {
        this.valMedDiaFatCliDrive = valMedDiaFatCliDrive;
    }

    /**
     * Método para apresentar a informação pedida no ponto 1.
     * @return informação pedida sobre o fast food.
     */
    @Override
    public String apresentaEmpresa(){
        if(lucro()){
            return ("------------------------------------\nEmpresa: " + nome + "\nTipo de empresa: " + categoria + " (Fast Food)" + "\nDistrito: " + distrito +"\nDespesa Anual: " + despesaAnual() + "€\nReceita Anual: " + receitaAnual() + "€\nLucro: Sim\n");
        }else{
            return ("------------------------------------\nEmpresa: " + nome + "\nTipo de empresa: " + categoria + " (Fast Food)" + "\nDistrito: " + distrito +"\nDespesa Anual: " + despesaAnual() + "€\nReceita Anual: " + receitaAnual() + "€\nLucro: Não\n");
        }
    }

    /**
     * Método para calcular a despesa anual.
     * @return despesa anual do fast food.
     */
    @Override
    public double despesaAnual(){
        return numEmpMesa*salarioMedAnual;
    }

    /**
     * Método para calcular a receita anual.
     * @return receita anual do fast food.
     */
    @Override
    public double receitaAnual(){
        return (nMesasInt*valMedFatMesaDia+nMedDiaCliDrive*valMedDiaFatCliDrive)*nDiasFuncAno;
    }

    /**
     * Método para saber se houve lucro ou não.
     * @return lucro do fast food (sim ou não).
     */
    @Override
    public boolean lucro(){
        return receitaAnual() > despesaAnual();
    }

}
