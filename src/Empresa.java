import java.io.Serializable;

/**
 * Representa uma empresa.
 * @author Marcelo Gomes
 * @author Pedro Brites Santos
 * @version 1.0
 */
public abstract class Empresa implements Serializable {
    protected String nome;
    protected String distrito;
    protected Gps gps;
    protected String categoria;

    /**
     * Construtor empresa.
     * @param nome recebe o nome da empresa.
     * @param distrito recebe o distrito da empresa.
     * @param gps recebe as coordenadas gps da empresa.
     */
    public Empresa(String nome, String distrito, Gps gps) {
        this.nome = nome;
        this.distrito = distrito;
        this.gps = gps;
    }

    /**
     * Getter nome.
     * @return nome da empresa.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Setter nome.
     * @param nome recebe o nome da empresa a definir.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Getter distrito.
     * @return distrito da empresa.
     */
    public String getDistrito() {
        return distrito;
    }

    /**
     * Setter distrito.
     * @param distrito distrito da empresa a definir.
     */
    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    /**
     * Getter gps.
     * @return coordenadas gps da empresa.
     */
    public Gps getGps() {
        return gps;
    }

    /**
     * Setter gps.
     * @param gps recebe as coordenadas gps da empresa a definir.
     */
    public void setGps(Gps gps) {
        this.gps = gps;
    }

    /**
     * Getter categoria.
     * @return categoria da empresa (Restauração ou Mercearia)
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Método abstrato apresenta empresa.
     * @return informação da empresa a apresentar.
     */
    public abstract String apresentaEmpresa();

    /**
     * Método abstrato despesa anual.
     * @return despesa anual da empresa.
     */
    public abstract double despesaAnual();

    /**
     * Método abstrato receita anual.
     * @return receita anual da empresa.
     */
    public abstract double receitaAnual();

    /**
     * Método abstrato lucro.
     * @return lucro da empresa (Sim ou Não).
     */
    public abstract boolean lucro();

    /**
     * Método abstrato máximo de clientes.
     * @return número de clientes das empresas da categoria Restauração.
     */
    public abstract double maxClient();
}
