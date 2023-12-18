/**
 * Projeto StarThrive
 * @author Marcelo Gomes
 * @author Pedro Brites Santos
 * @version 1.0
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

/**
 * Representa a StarThrive, onde as empresas são geridas.
 */
public class StarThrive {
    public static void main(String[] args) {
        new GUI();
    }

    /**
     * Método para eliminar uma empresa, à escolha, do Array List de empresas.
     * @param empresas recebe o Array List de empresas.
     * @param nome recebe o nome da empresa a eliminar.
     * @return 0 se a empresa não foi encontrada, 1 se a empresa foi eliminada com sucesso.
     */
    public static int eliminaEmpresa(ArrayList<Empresa> empresas, String nome) {
        int count = 0;
        for (int i = 0; i < empresas.size(); ++i) {
            if (nome.equalsIgnoreCase(empresas.get(i).getNome())) {
                ++count;
                empresas.remove(i);
            }
        }
        return count;
    }

    /**
     * Método para listar todas as empresas.
     * Percorre o Array List e chama o método apresentaEmpresa() para cada uma das empresas.
     * @param empresas recebe o Array List de empresas.
     * @return os dados das empresas ou "Não existem empresas" caso o Array List esteja vazio.
     */
    public static String listaEmpresas(ArrayList<Empresa> empresas) {
        String fim = "";
        int count = 0;
        for (int i = 0; i < empresas.size(); ++i) {
            fim += empresas.get(i).apresentaEmpresa();
            ++count;
        }
        if (count == 0) fim = "Não existem empresas";
        return fim;
    }

    /**
     * Método correspondente ao ponto 2.
     * Lista as empresas com maior receita, menor despesa e maior lucro para os tipos Restauração e Mercearia.
     * @param empresas recebe o Array List de empresas.
     * @return nome e receita, despesa ou lucro das empresas a apresentar.
     */
    public static String apresentaEmpresaPersonalizada(ArrayList<Empresa> empresas) {
        int maxReceitaRestIndex = 0;
        int maxReceitaMercIndex = 0;
        int minDespesaRestIndex = 0;
        int minDespesaMercIndex = 0;
        int maxLucroRestIndex = 0;
        int maxLucroMercIndex = 0;
        double maxLucroRest = 0;
        double maxLucroMerc = 0;

        for (int i = 0; i < empresas.size(); ++i) {
            if (empresas.get(i).getCategoria().equals("Restauração")) {
                double lucroRest = empresas.get(i).receitaAnual() - empresas.get(i).despesaAnual();
                if (empresas.get(i).receitaAnual() > empresas.get(maxReceitaRestIndex).receitaAnual()) {
                    maxReceitaRestIndex = i;
                }
                if (empresas.get(i).despesaAnual() < empresas.get(minDespesaRestIndex).despesaAnual()) {
                    minDespesaRestIndex = i;
                }
                if (lucroRest > maxLucroRest) {
                    maxLucroRestIndex = i;
                    maxLucroRest = lucroRest;
                }
            } else {
                double lucroMerc = (empresas.get(i).receitaAnual()) - (empresas.get(i).despesaAnual());
                if (empresas.get(i).receitaAnual() > empresas.get(maxReceitaMercIndex).receitaAnual()) {
                    maxReceitaMercIndex = i;
                }
                if (empresas.get(i).despesaAnual() < empresas.get(minDespesaMercIndex).despesaAnual()) {
                    minDespesaMercIndex = i;
                }
                if (lucroMerc > maxLucroMerc) {
                    maxLucroMercIndex = i;
                    maxLucroMerc = lucroMerc;
                }
            }
        }
        return "-----------Restauração:\n" +
                "\nEmpresa com maior receita anual: " + empresas.get(maxReceitaRestIndex).getNome() + " >> " + empresas.get(maxReceitaRestIndex).receitaAnual() + "€" +
                "\nEmpresa com menor despesa anual: " + empresas.get(minDespesaRestIndex).getNome() + " >> " + empresas.get(minDespesaRestIndex).despesaAnual() + "€" +
                "\nEmpresa com maior lucro anual: " + empresas.get(maxLucroRestIndex).getNome() + " >> " + maxLucroRest + "€" + "\n" +
                "\n-----------Mercearia:\n" +
                "\nEmpresa com maior receita anual: " + empresas.get(maxReceitaMercIndex).getNome() + " >> " + empresas.get(maxReceitaMercIndex).receitaAnual() + "€" +
                "\nEmpresa com menor despesa anual: " + empresas.get(minDespesaMercIndex).getNome() + " >> " + empresas.get(minDespesaMercIndex).despesaAnual() + "€" +
                "\nEmpresa com maior lucro anual: " + empresas.get(maxLucroMercIndex).getNome() + " >> " + maxLucroMerc + "€";
    }

    /**
     * Método para listar as duas empresas,do tipo Restauração, com maior capacidade média de clientes.
     * @param empresas recebe o Array List de empresas.
     * @return nome e número médio de clientes por dia das duas empresas.
     */
    public static String maiorCapacidade(ArrayList<Empresa> empresas) {
        int max1 = 0;
        int max2 = 0;
        for (int i = 0; i < empresas.size(); ++i) {
            for (int j = 0; j < empresas.size(); ++j) {
                if (empresas.get(j).maxClient() != -1) {
                    if (empresas.get(j).maxClient() > empresas.get(max1).maxClient()) {
                        max1 = j;
                    }
                }
            }
            if (empresas.get(i).maxClient() != -1) {
                if (empresas.get(i).maxClient() > empresas.get(max2).maxClient() && empresas.get(i).maxClient() < empresas.get(max1).maxClient()) {
                    max2 = i;
                }
            }
        }
        return "Empresas da categoria restauração com maior capacidade média de clientes:\n" +
                empresas.get(max1).getNome() + ":" + empresas.get(max1).maxClient() + "\n" +
                empresas.get(max2).getNome() + ":" + empresas.get(max2).maxClient();
    }

    /**
     * Método para ler o ficheiro de texto.
     * Este método percorre o ficheiro linha a linha e, conforme o tipo de empresa, cria um novo objeto e adiciona-o ao Array List de empresas.
     * @param empresas recebe o Array List de empresas.
     */
    public static void leFicheiro(ArrayList<Empresa> empresas) {
        String linha;
        try {
            BufferedReader br = new BufferedReader(new FileReader("StarThrive.txt"));
            while ((linha = br.readLine()) != null) {
                String[] val = linha.split(",");
                if (val[0].equals("Frutaria")) {
                    empresas.add(new Frutaria(val[1], val[2], new Gps(Double.parseDouble(val[3]), Double.parseDouble(val[4])), Double.parseDouble(val[5]), Integer.parseInt(val[6]), Double.parseDouble(val[7])));
                }
                if (val[0].equals("Mercado")) {
                    empresas.add(new Mercado(val[1], val[2], new Gps(Double.parseDouble(val[3]), Double.parseDouble(val[4])), Double.parseDouble(val[5]), val[6], Double.parseDouble(val[7]), Double.parseDouble(val[8])));
                }
                if (val[0].equals("Cafe")) {
                    empresas.add(new Cafe(val[1], val[2], new Gps(Double.parseDouble(val[3]), Double.parseDouble(val[4])), Integer.parseInt(val[5]), Double.parseDouble(val[6]), Double.parseDouble(val[7]), Double.parseDouble(val[8]), Double.parseDouble(val[9])));
                }
                if (val[0].equals("FastFood")) {
                    empresas.add(new FastFood(val[1], val[2], new Gps(Double.parseDouble(val[3]), Double.parseDouble(val[4])), Integer.parseInt(val[5]), Double.parseDouble(val[6]), Double.parseDouble(val[7]), Integer.parseInt(val[8]), Integer.parseInt(val[9]), Double.parseDouble(val[10]), Double.parseDouble(val[11]), Double.parseDouble(val[12])));
                }
                if (val[0].equals("RestLocal")) {
                    empresas.add(new RestLocal(val[1], val[2], new Gps(Double.parseDouble(val[3]), Double.parseDouble(val[4])), Integer.parseInt(val[5]), Double.parseDouble(val[6]), Double.parseDouble(val[7]), Integer.parseInt(val[8]), Integer.parseInt(val[9]), Double.parseDouble(val[10]), Integer.parseInt(val[11]), Double.parseDouble(val[12])));
                }
                if (val[0].equals("Pastelaria")) {
                    empresas.add(new Pastelaria(val[1], val[2], new Gps(Double.parseDouble(val[3]), Double.parseDouble(val[4])), Integer.parseInt(val[5]), Double.parseDouble(val[6]), Double.parseDouble(val[7]), Double.parseDouble(val[8]), Double.parseDouble(val[9])));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro não encontrado");
        } catch (IOException e) {
            System.out.println("Erro com o ficheiro");
        }
    }

    /**
     * Método para guardar as empresas no ficheiro de objetos.
     * Este método percorre o Array List de empresas e escreve os objetos, um a um, no ficheiro.
     * @param empresas recebe o Array List de empresas.
     */
    public static void guardaEmpresas(ArrayList<Empresa> empresas) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("StarThrive.dat"));
            for (int i = 0; i < empresas.size(); ++i) {
                oos.writeObject(empresas.get(i));
            }
            oos.close();
        } catch (IOException e) {
            System.out.println("Erro com o ficheiro");
        }
    }

    /**
     * Método que carrega as empresas do ficheiro de objetos.
     * Este método vai ler o ficheiro de objetos e adiciona as empresas, uma a uma, ao Array List.
     * @param empresas recebe o Array List de empresas.
     */
    public static void carregaEmpresas(ArrayList<Empresa> empresas) {
        boolean x = true;
        try{
            FileInputStream fis = new FileInputStream("StarThrive.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            while(x) {
                if (fis.available() != 0) { //se não houver nada para ler fis.available() -> return 0
                    empresas.add((Empresa) ois.readObject());
                } else {
                    x = false;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro com o ficheiro");
        }
    }

    /**
     * Método executado sempre que um botão é clicado.
     * Neste método tentamos ler o ficheiro de objetos e se ele estiver vazio ou não extistir o ficheiro de texto é lido no lugar dele.
     * No fim a informação é guardada no ficheiro de objetos.
     * @param empresas recebe o Array List de empresas.
     */
    public static void arranqueFicheiro(ArrayList<Empresa> empresas){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("StarThrive.dat"));
            carregaEmpresas(empresas);
            ois.close();
        } catch (FileNotFoundException | EOFException e) {
            leFicheiro(empresas);
        } catch (IOException e) {
            System.out.println("Erro com o ficheiro");
        }
        guardaEmpresas(empresas);
    }

    /**
     * Representa a GUI.
     */
    public static class GUI implements ActionListener {
        private JFrame frame;
        private JPanel panel;
        private JButton criar;
        private JButton editar;
        private JButton apagar;
        private JButton ponto1;
        private JButton ponto2;
        private JButton ponto3;
        private JLabel titulo;
        private JButton enter;
        private JTextField res;
        private JPanel pain;
        private JFrame frameCriar;
        private JFrame frameApagar;
        private JPanel panelApagar;
        private JLabel labelApagar;
        private JLabel labelEditar;
        private JTextField tfApagar;
        private JTextField tfEditar;
        private JButton buttonApagar;
        private JLabel resultApagar;
        private JComboBox<String> selec;

        /**
         * Construtor que vai apresentar a página principal da GUI.
         */
        public GUI() {
            frame = new JFrame();
            frame.setTitle("StarThrive");
            frame.setSize(600, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            panel = new JPanel();
            panel.setLayout(null);
            panel.setBackground(Color.darkGray);

            titulo = new JLabel("Escolha uma operação:");
            titulo.setBounds(240, 40, 200, 25);
            titulo.setForeground(Color.white);
            criar = new JButton("Criar");
            criar.setBounds(50, 100, 225, 75);
            criar.setBackground(Color.lightGray);
            criar.addActionListener(this);
            editar = new JButton("Editar");
            editar.setBounds(50, 240, 225, 75);
            editar.setBackground(Color.lightGray);
            editar.addActionListener(this);
            apagar = new JButton("Apagar");
            apagar.setBounds(50, 380, 225, 75);
            apagar.setBackground(Color.lightGray);
            apagar.addActionListener(this);
            ponto1 = new JButton("Listar");
            ponto1.setBounds(325, 100, 225, 75);
            ponto1.setBackground(Color.lightGray);
            ponto1.addActionListener(this);
            ponto2 = new JButton("2");
            ponto2.setBounds(325, 240, 225, 75);
            ponto2.setBackground(Color.lightGray);
            ponto2.addActionListener(this);
            ponto3 = new JButton("3");
            ponto3.setBounds(325, 380, 225, 75);
            ponto3.setBackground(Color.lightGray);
            ponto3.addActionListener(this);

            panel.add(criar);
            panel.add(editar);
            panel.add(apagar);
            panel.add(ponto1);
            panel.add(ponto2);
            panel.add(ponto3);
            panel.add(titulo);

            frame.add(panel);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }

        private String resp = "";
        private int a, b, c, d, e, f, t, r=0;
        private String nome;
        private String distrito;
        private double latitude;
        private double longitude;
        private double custAnoLimp;
        private int nProd;
        private double valMedFatAnoProd;
        private double areaCorr;
        private String tipo;
        private int nEmpMesa;
        private double salMedAnual;
        private double nMedCliDia;
        private double nMedCafeDia;
        private double valMedFatAnoCafeDia;
        private double nMedBoloDia;
        private double valMedFatAnoBoloDia;
        private int nDiaFunAno;
        private int nMesaInt;
        private double valMedFatMesaDia;
        private double valMedFatDiaCliDrive;
        private int nMesaEspl;
        private double licMesaEsplAno;
        private String[] distritos = {"Aveiro", "Beja", "Braga", "Bragança", "Castelo Branco", "Coimbra", "Évora", "Faro", "Guarda", "Leiria", "Lisboa", "Portalegre", "Porto", "Santarém", "Setúbal", "Viana do Castelo", "Vila Real", "Viseu"};
        private double valMedFatAnoArea;
        private double nCliDriveDia;

        /**
         * Método que contém as funções de cada botão.
         * @param ea the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent ea) {
            ArrayList<Empresa> empresas = new ArrayList<>();
            arranqueFicheiro(empresas);
            if (ea.getSource() == criar) {
                a = 0;
                b = 0;
                c = 0;
                d = 0;
                e = 0;
                f = 0;
                t = 0;
                r = 0;
                frameCriar = new JFrame();
                frameCriar.setTitle("Criar uma empresa");
                frameCriar.setSize(400, 200);
                pain = new JPanel();
                pain.setLayout(null);
                JLabel perg = new JLabel("Tipo de empresa:");
                perg.setBounds(30, 30, 145, 25);
                pain.add(perg);
                String[] listaTipos = {"Frutaria", "Mercado", "Cafe", "Pastelaria", "FastFood", "RestLocal"};
                selec = new JComboBox<>(listaTipos);
                selec.setBounds(30, 60, 220, 25);
                pain.add(selec);
                enter = new JButton("Enter");
                enter.setBounds(260, 60, 75, 25);
                enter.addActionListener(this);
                pain.add(enter);

                frameCriar.add(pain);
                frameCriar.setLocationRelativeTo(null);
                frameCriar.setVisible(true);

            } else if (ea.getSource() == editar) {
                a = 0;
                b = 0;
                c = 0;
                d = 0;
                e = 0;
                f = 0;
                t = 2;
                r = 1;
                frameCriar = new JFrame();
                frameCriar.setTitle("Editar uma empresa");
                frameCriar.setSize(400, 200);
                pain = new JPanel();
                pain.setLayout(null);
                labelEditar = new JLabel("Nome da empresa a editar:");
                labelEditar.setBounds(30, 40, 200, 25);
                tfEditar = new JTextField();
                tfEditar.setBounds(30, 65, 200, 25);
                enter = new JButton("Enter");
                enter.setBounds(260, 65, 75, 25);
                enter.addActionListener(this);
                pain.add(labelEditar);
                pain.add(tfEditar);
                pain.add(enter);
                frameCriar.add(pain);
                frameCriar.setLocationRelativeTo(null);
                frameCriar.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frameCriar.setVisible(true);
            } else if (ea.getSource() == apagar) {
                frameApagar = new JFrame();
                frameApagar.setTitle("Apagar uma empresa");
                frameApagar.setSize(400, 200);
                panelApagar = new JPanel();
                panelApagar.setLayout(null);
                labelApagar = new JLabel("Nome da empresa a apagar:");
                labelApagar.setBounds(30, 40, 200, 25);
                tfApagar = new JTextField();
                tfApagar.setBounds(30, 65, 200, 25);
                buttonApagar = new JButton("Enter");
                buttonApagar.setBounds(260, 65, 75, 25);
                buttonApagar.addActionListener(this);
                resultApagar = new JLabel("");
                resultApagar.setBounds(30, 90, 200, 25);
                panelApagar.add(labelApagar);
                panelApagar.add(tfApagar);
                panelApagar.add(buttonApagar);
                panelApagar.add(resultApagar);
                frameApagar.add(panelApagar);
                frameApagar.setLocationRelativeTo(null);
                frameApagar.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frameApagar.setVisible(true);

            } else if (ea.getSource() == ponto1) {
                JFrame tela = new JFrame("Lista de empresas");
                tela.setSize(400,400);
                JPanel painList = new JPanel();
                painList.setLayout(null);
                JTextArea list = new JTextArea(listaEmpresas(empresas));
                list.setEditable(false);
                JScrollPane scroll = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scroll.setBounds(10,10,370,350);
                painList.add(scroll);
                tela.add(painList);
                tela.setLocationRelativeTo(null);
                tela.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                tela.setVisible(true);
            } else if (ea.getSource() == ponto2) {
                JFrame frame2 = new JFrame("Ponto 2");
                frame2.setSize(425,300);
                JPanel panel2 = new JPanel();
                panel2.setLayout(null);
                JTextArea ta2 = new JTextArea(apresentaEmpresaPersonalizada(empresas));
                ta2.setBounds(30,30, 350,200);
                ta2.setEditable(false);
                panel2.add(ta2);
                frame2.add(panel2);
                frame2.setLocationRelativeTo(null);
                frame2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame2.setVisible(true);
            } else if (ea.getSource() == ponto3) {
                JFrame frame3 = new JFrame("Ponto 3");
                frame3.setSize(500,175);
                JPanel panel3 = new JPanel();
                panel3.setLayout(null);
                JTextArea ta2 = new JTextArea(maiorCapacidade(empresas));
                ta2.setBounds(30,30, 425,75);
                ta2.setEditable(false);
                panel3.add(ta2);
                frame3.add(panel3);
                frame3.setLocationRelativeTo(null);
                frame3.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame3.setVisible(true);

            } else if (ea.getSource() == buttonApagar) {
                if (eliminaEmpresa(empresas, tfApagar.getText()) == 1) {
                    resultApagar.setText("Empresa eliminada com sucesso!");
                    resultApagar.setForeground(Color.black);
                } else {
                    resultApagar.setText("Empresa não encontrada");
                    resultApagar.setForeground(Color.red);
                }
                guardaEmpresas(empresas);
            } else if (ea.getSource() == enter) {
                if (t == 0) {
                    resp = selec.getItemAt(selec.getSelectedIndex());
                } else if (t == 1){
                    resp = res.getText();
                }else if (t == 2){
                    int count = 0;
                    for (int i = 0; i < empresas.size(); ++i){
                        if (tfEditar.getText().equalsIgnoreCase(empresas.get(i).getNome())){
                            count++;
                            resp = empresas.get(i).toString();
                            resp = resp.split("\\@")[0];

                        }
                    }
                    if (resp.equalsIgnoreCase("Frutaria")) a = 1;
                    if (resp.equalsIgnoreCase("Mercado")) b = 1;
                    if (resp.equalsIgnoreCase("Cafe")) c = 1;
                    if (resp.equalsIgnoreCase("Pastelaria")) d = 1;
                    if (resp.equalsIgnoreCase("FastFood")) e = 1;
                    if (resp.equalsIgnoreCase("RestLocal")) f = 1;
                    else if (count == 0) JOptionPane.showMessageDialog(null, "Empresa não encontrada");
                }
                if ((resp.equalsIgnoreCase("Frutaria")) || a > 0) {
                    pain.setVisible(false);
                    pain.removeAll();
                    pain.setVisible(true);
                    if (a == 0 && r == 0) {
                        JLabel perg = new JLabel("Digite o nome da Frutaria:");
                        perg.setBounds(20, 60, 210, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(235, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(310, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        a = 1;
                        t = 1;
                    } else if (a == 1) {
                        if (r == 0){
                            nome = resp;
                        }else{
                            nome = tfEditar.getText();
                        }
                        JLabel perg = new JLabel("Digite o distrito da Frutaria:");
                        perg.setBounds(10, 60, 220, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(235, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(310, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        a = 2;
                        t = 1;
                    } else if (a == 2) {
                        int count = 0;
                        for (int i = 0; i < distritos.length; ++i){
                            if (distritos[i].equalsIgnoreCase(resp)) count++;
                        }
                        if (count == 0){
                            JOptionPane.showMessageDialog(null, "Distrito não existe");
                            frameCriar.setVisible(false);
                        }
                        else {
                            distrito = resp;
                            JLabel perg = new JLabel("Digite a latitude da Frutaria:");
                            perg.setBounds(10, 60, 220, 25);
                            pain.add(perg);
                            res = new JTextField();
                            res.addActionListener(this);
                            res.setBounds(235, 60, 70, 25);
                            pain.add(res);
                            enter = new JButton("Enter");
                            enter.setBounds(310, 60, 75, 25);
                            enter.addActionListener(this);
                            pain.add(enter);
                            frameCriar.add(pain);
                            frameCriar.setVisible(true);
                            resp = "";
                            a = 3;
                        }
                    } else if (a == 3) {
                        try{
                        latitude = Double.parseDouble(resp);
                        JLabel perg = new JLabel("Digite a longitude da Frutaria:");
                        perg.setBounds(10, 60, 220, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(235, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(310, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        a = 4;
                        }catch (NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);
                        }
                    } else if (a == 4) {
                        try{
                        longitude = Double.parseDouble(resp);
                        JLabel perg = new JLabel("Digite o custo de limpeza anual:");
                        perg.setBounds(10, 60, 220, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(235, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(310, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        a = 5;
                        }catch (NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);
                        }
                    } else if (a == 5) {
                        try{
                        custAnoLimp = Double.parseDouble(resp);
                        JLabel perg = new JLabel("Digite o número de produtos:");
                        perg.setBounds(20, 60, 190, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(215, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(290, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        a = 6;
                        }catch (NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);
                        }
                    } else if (a == 6) {
                        try{
                        nProd = Integer.parseInt(resp);
                        JLabel perg = new JLabel("Digite a faturação média anual/produto:");
                        perg.setBounds(10, 60, 230, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(245, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(320, 60, 65, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        a = 7;
                        }catch (NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);
                        }
                    } else if (a == 7) {
                        try{
                        valMedFatAnoProd = Double.parseDouble(resp);
                        if(r == 0) {
                            JLabel perg = new JLabel("FRUTARIA ADICIONADA COM SUCESSO");
                            perg.setBounds(60, 30, 280, 80);
                            pain.add(perg);
                        }else if (r == 1){
                            JLabel perg = new JLabel("FRUTARIA EDITADA COM SUCESSO");
                            perg.setBounds(70, 30, 260, 80);
                            pain.add(perg);
                            eliminaEmpresa(empresas, tfEditar.getText());
                        }
                        empresas.add(new Frutaria(nome, distrito, new Gps(latitude, longitude), custAnoLimp, nProd, valMedFatAnoProd));
                        resp = "";
                        guardaEmpresas(empresas);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                    }
                        catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);
                        }
                    }

                } else if (resp.equalsIgnoreCase("Mercado") || b > 0) {
                    pain.setVisible(false);
                    pain.removeAll();
                    pain.setVisible(true);
                    if (b == 0 && r == 0) {
                        JLabel perg = new JLabel("Digite o nome do Mercado:");
                        perg.setBounds(10, 60, 210, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(225, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(300, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        b = 1;
                        t = 1;
                    } else if (b == 1) {
                        if (r == 0){
                            nome = resp;
                        }else{
                            nome = tfEditar.getText();
                        }
                        JLabel perg = new JLabel("Digite o distrito da Mercado:");
                        perg.setBounds(10, 60, 220, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(235, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(310, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        b = 2;
                        t = 1;
                    } else if (b == 2) {
                        int count = 0;
                        for (int i = 0; i < distritos.length; ++i){
                            if (distritos[i].equalsIgnoreCase(resp)) count++;
                        }
                        if (count == 0){
                            JOptionPane.showMessageDialog(null, "Distrito não existe");
                            frameCriar.setVisible(false);
                        }
                        else {
                            distrito = resp;
                            JLabel perg = new JLabel("Digite a latitude da Mercado:");
                            perg.setBounds(10, 60, 220, 25);
                            pain.add(perg);
                            res = new JTextField();
                            res.addActionListener(this);
                            res.setBounds(235, 60, 70, 25);
                            pain.add(res);
                            enter = new JButton("Enter");
                            enter.setBounds(310, 60, 75, 25);
                            enter.addActionListener(this);
                            pain.add(enter);
                            frameCriar.add(pain);
                            frameCriar.setVisible(true);
                            resp = "";
                            b = 3;
                        }
                    } else if (b == 3) {
                        try {
                            latitude = Double.parseDouble(resp);
                            JLabel perg = new JLabel("Digite a longitude da Mercado:");
                            perg.setBounds(10, 60, 220, 25);
                            pain.add(perg);
                            res = new JTextField();
                            res.addActionListener(this);
                            res.setBounds(235, 60, 70, 25);
                            pain.add(res);
                            enter = new JButton("Enter");
                            enter.setBounds(310, 60, 75, 25);
                            enter.addActionListener(this);
                            pain.add(enter);
                            frameCriar.add(pain);
                            frameCriar.setVisible(true);
                            resp = "";
                            b = 4;
                        }catch(NumberFormatException exception){
                                JOptionPane.showMessageDialog(null, "Valor incorreto");
                                frameCriar.setVisible(false);}
                    } else if (b == 4) {
                        try{
                        longitude = Double.parseDouble(resp);
                        JLabel perg = new JLabel("Digite o custo de limpeza anual:");
                        perg.setBounds(10, 60, 220, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(235, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(310, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        b = 5;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    } else if (b == 5) {
                        try{
                        custAnoLimp = Double.parseDouble(resp);
                        JLabel perg = new JLabel("Digite o tipo de mercado:");
                        perg.setBounds(30, 60, 190, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(225, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(300, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        b = 6;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    } else if (b == 6) {
                        String[] tiposMercado = {"Mini", "Super", "Hiper", "Híper"};
                        int count = 0;
                        for (int i = 0; i < tiposMercado.length; ++i){
                            if (tiposMercado[i].equalsIgnoreCase(resp)) count++;
                        }
                        if (count == 0){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);
                        }
                        else {
                            tipo = resp;
                            JLabel perg = new JLabel("Digite o valor da área dos corredores:");
                            perg.setBounds(10, 60, 230, 25);
                            pain.add(perg);
                            res = new JTextField();
                            res.addActionListener(this);
                            res.setBounds(245, 60, 70, 25);
                            pain.add(res);
                            enter = new JButton("Enter");
                            enter.setBounds(320, 60, 65, 25);
                            enter.addActionListener(this);
                            pain.add(enter);
                            frameCriar.add(pain);
                            frameCriar.setVisible(true);
                            resp = "";
                            b = 7;
                        }
                    } else if (b == 7) {
                        try{
                        areaCorr = Double.parseDouble(resp);
                        JLabel perg = new JLabel("Digite a faturação média/área:");
                        perg.setBounds(10, 60, 210, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(225, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(300, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        b = 8;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    } else if (b == 8) {
                        try {
                            valMedFatAnoArea = Double.parseDouble(resp);
                            resp = "";
                            if (r == 0) {
                                JLabel perg = new JLabel("MERCADO ADICIONADO COM SUCESSO");
                                perg.setBounds(60, 30, 300, 80);
                                pain.add(perg);
                            } else if (r == 1) {
                                JLabel perg = new JLabel("MERCADO EDITADO COM SUCESSO");
                                perg.setBounds(60, 30, 280, 80);
                                pain.add(perg);
                                eliminaEmpresa(empresas, tfEditar.getText());
                            }
                            empresas.add(new Mercado(nome, distrito, new Gps(latitude, longitude), custAnoLimp, tipo, areaCorr, valMedFatAnoArea));
                            guardaEmpresas(empresas);
                            frameCriar.add(pain);
                            frameCriar.setVisible(true);
                        } catch (NumberFormatException exception) {
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);
                        }
                    }
                } else if (resp.equalsIgnoreCase("Cafe") || c > 0) {
                    pain.setVisible(false);
                    pain.removeAll();
                    pain.setVisible(true);
                    if (c == 0 && r == 0) {
                        JLabel perg = new JLabel("Digite o nome do Café:");
                        perg.setBounds(20, 60, 190, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(215, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(290, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        c = 1;
                        t = 1;
                    } else if (c == 1) {
                        if (r == 0){
                            nome = resp;
                        }else{
                            nome = tfEditar.getText();
                        }
                        JLabel perg = new JLabel("Digite o distrito do Café:");
                        perg.setBounds(20, 60, 200, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(225, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(300, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        c = 2;
                        t = 1;
                    } else if (c == 2) {
                        int count = 0;
                        for (int i = 0; i < distritos.length; ++i){
                            if (distritos[i].equalsIgnoreCase(resp)) count++;
                        }
                        if (count == 0){
                            JOptionPane.showMessageDialog(null, "Distrito não existe");
                            frameCriar.setVisible(false);
                        }
                        else {
                            distrito = resp;
                            JLabel perg = new JLabel("Digite a latitude do Café:");
                            perg.setBounds(20, 60, 200, 25);
                            pain.add(perg);
                            res = new JTextField();
                            res.addActionListener(this);
                            res.setBounds(225, 60, 70, 25);
                            pain.add(res);
                            enter = new JButton("Enter");
                            enter.setBounds(300, 60, 75, 25);
                            enter.addActionListener(this);
                            pain.add(enter);
                            frameCriar.add(pain);
                            frameCriar.setVisible(true);
                            resp = "";
                            c = 3;
                        }
                    } else if (c == 3) {
                        try{
                        latitude = Double.parseDouble(resp);
                        JLabel perg = new JLabel("Digite a longitude do Café:");
                        perg.setBounds(20, 60, 200, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(225, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(300, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        c = 4;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    } else if (c == 4) {
                        try{
                        longitude = Double.parseDouble(resp);
                        JLabel perg = new JLabel("Digite o número de empregados de mesa:");
                        perg.setBounds(10, 60, 220, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(235, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(310, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        c = 5;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    } else if (c == 5) {
                        try{
                        nEmpMesa = Integer.parseInt(resp);
                        JLabel perg = new JLabel("Digite o salário médio anual:");
                        perg.setBounds(20, 60, 210, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(235, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(310, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        c = 6;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    } else if (c == 6) {
                        try{
                        salMedAnual = Double.parseDouble(resp);
                        JLabel perg = new JLabel("Digite o número médio de clientes/dia:");
                        perg.setBounds(10, 60, 220, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(235, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(310, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        c = 7;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    } else if (c == 7) {
                        try{
                        nMedCliDia = Double.parseDouble(resp);
                        JLabel perg = new JLabel("Digite o número médio de cafés/dia:");
                        perg.setBounds(10, 60, 220, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(235, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(310, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        c = 8;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    } else if (c == 8) {
                        try{
                        nMedCafeDia = Double.parseDouble(resp);
                        JLabel perg = new JLabel("Digite a faturação anual/café/dia:");
                        perg.setBounds(10, 60, 220, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(235, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(310, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        c = 9;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    } else if (c == 9) {
                        try {
                            valMedFatAnoCafeDia = Double.parseDouble(resp);
                            if (r == 0) {
                                JLabel perg = new JLabel("CAFÉ ADICIONADO COM SUCESSO");
                                perg.setBounds(60, 30, 280, 80);
                                pain.add(perg);
                            } else if (r == 1) {
                                JLabel perg = new JLabel("CAFÉ EDITADO COM SUCESSO");
                                perg.setBounds(70, 30, 260, 80);
                                pain.add(perg);
                                eliminaEmpresa(empresas, tfEditar.getText());
                            }
                            empresas.add(new Cafe(nome, distrito, new Gps(latitude, longitude), nEmpMesa, salMedAnual, nMedCliDia, nMedCafeDia, valMedFatAnoCafeDia));
                            resp = "";
                            guardaEmpresas(empresas);
                            frameCriar.add(pain);
                            frameCriar.setVisible(true);
                        } catch (NumberFormatException exception) {
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);
                        }
                    }
                } else if (resp.equalsIgnoreCase("Pastelaria") || d > 0) {
                    pain.setVisible(false);
                    pain.removeAll();
                    pain.setVisible(true);
                    if (d == 0 && r == 0) {
                        JLabel perg = new JLabel("Digite o nome da Pastelaria:");
                        perg.setBounds(20, 60, 200, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(225, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(300, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        d = 1;
                        t = 1;
                    } else if (d == 1) {
                        if (r == 0){
                            nome = resp;
                        }else{
                            nome = tfEditar.getText();
                        }
                        JLabel perg = new JLabel("Digite o distrito da Pastelaria:");
                        perg.setBounds(20, 60, 210, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(235, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(310, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        d = 2;
                        t = 1;
                    } else if (d == 2) {
                        int count = 0;
                        for (int i = 0; i < distritos.length; ++i){
                            if (distritos[i].equalsIgnoreCase(resp)) count++;
                        }
                        if (count == 0){
                            JOptionPane.showMessageDialog(null, "Distrito não existe");
                            frameCriar.setVisible(false);
                        }
                        else {
                            distrito = resp;
                            JLabel perg = new JLabel("Digite a latitude da Pastelaria:");
                            perg.setBounds(20, 60, 210, 25);
                            pain.add(perg);
                            res = new JTextField();
                            res.addActionListener(this);
                            res.setBounds(235, 60, 70, 25);
                            pain.add(res);
                            enter = new JButton("Enter");
                            enter.setBounds(310, 60, 75, 25);
                            enter.addActionListener(this);
                            pain.add(enter);
                            frameCriar.add(pain);
                            frameCriar.setVisible(true);
                            resp = "";
                            d = 3;
                        }
                    } else if (d == 3) {
                        try{
                        latitude = Double.parseDouble(resp);
                        JLabel perg = new JLabel("Digite a longitude da Pastelaria:");
                        perg.setBounds(20, 60, 210, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(235, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(310, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        d = 4;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    } else if (d == 4) {
                        try{
                        longitude = Double.parseDouble(resp);
                        JLabel perg = new JLabel("Digite o número de empregados de mesa:");
                        perg.setBounds(10, 60, 220, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(235, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(310, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        d = 5;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    } else if (d == 5) {
                        try{
                        nEmpMesa = Integer.parseInt(resp);
                        JLabel perg = new JLabel("Digite o salário médio anual:");
                        perg.setBounds(20, 60, 200, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(225, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(300, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        d = 6;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    } else if (d == 6) {
                        try{
                        salMedAnual = Double.parseDouble(resp);
                        JLabel perg = new JLabel("Digite o número de clientes/dia:");
                        perg.setBounds(15, 60, 210, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(230, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(305, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        d = 7;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    } else if (d == 7) {
                        try{
                        nMedCliDia = Double.parseDouble(resp);
                        JLabel perg = new JLabel("Digite o número médio de bolos/dia:");
                        perg.setBounds(10, 60, 220, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(235, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(310, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        d = 8;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    } else if (d == 8) {
                        try{
                        nMedBoloDia = Double.parseDouble(resp);
                        JLabel perg = new JLabel("Digite a faturação anual/bolo/dia:");
                        perg.setBounds(10, 60, 220, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(235, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(310, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        d = 9;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    } else if (d == 9) {
                        try {
                            valMedFatAnoBoloDia = Double.parseDouble(resp);
                            if (r == 0) {
                                JLabel perg = new JLabel("PASTELARIA ADICIONADA COM SUCESSO");
                                perg.setBounds(50, 30, 300, 80);
                                pain.add(perg);
                            } else if (r == 1) {
                                JLabel perg = new JLabel("PASTELARIA EDITADA COM SUCESSO");
                                perg.setBounds(60, 30, 280, 80);
                                pain.add(perg);
                                eliminaEmpresa(empresas, tfEditar.getText());
                            }
                            empresas.add(new Pastelaria(nome, distrito, new Gps(latitude, longitude), nEmpMesa, salMedAnual, nMedCliDia, nMedBoloDia, valMedFatAnoBoloDia));
                            resp = "";
                            guardaEmpresas(empresas);
                            frameCriar.add(pain);
                            frameCriar.setVisible(true);
                        } catch (NumberFormatException exception) {
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);
                        }
                    }
                } else if (resp.equalsIgnoreCase("FastFood") || e > 0) {
                    pain.setVisible(false);
                    pain.removeAll();
                    pain.setVisible(true);
                    if (e == 0 && r == 0) {
                        JLabel perg = new JLabel("Digite o nome do FastFood:");
                        perg.setBounds(30, 60, 180, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(215, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(290, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        e = 1;
                        t = 1;
                    }else if (e == 1) {
                        if (r == 0){
                            nome = resp;
                        }else{
                            nome = tfEditar.getText();
                        }
                        JLabel perg = new JLabel("Digite o distrito do FastFood:");
                        perg.setBounds(20, 60, 200, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(225, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(300, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        e = 2;
                        t = 1;
                    } else if (e == 2) {
                        int count = 0;
                        for (int i = 0; i < distritos.length; ++i){
                            if (distritos[i].equalsIgnoreCase(resp)) count++;
                        }
                        if (count == 0){
                            JOptionPane.showMessageDialog(null, "Distrito não existe");
                            frameCriar.setVisible(false);
                        }
                        else {
                            distrito = resp;
                            JLabel perg = new JLabel("Digite a latitude do FastFood:");
                            perg.setBounds(20, 60, 200, 25);
                            pain.add(perg);
                            res = new JTextField();
                            res.addActionListener(this);
                            res.setBounds(225, 60, 70, 25);
                            pain.add(res);
                            enter = new JButton("Enter");
                            enter.setBounds(300, 60, 75, 25);
                            enter.addActionListener(this);
                            pain.add(enter);
                            frameCriar.add(pain);
                            frameCriar.setVisible(true);
                            resp = "";
                            e = 3;
                        }
                    } else if (e == 3) {
                        try{
                        latitude = Double.parseDouble(resp);
                        JLabel perg = new JLabel("Digite a longitude do FastFood:");
                        perg.setBounds(20, 60, 200, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(225, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(300, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        e = 4;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    } else if (e == 4) {
                        try{
                        longitude = Double.parseDouble(resp);
                        JLabel perg = new JLabel("Digite o número de empregados de mesa:");
                        perg.setBounds(10, 60, 220, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(235, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(310, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        e = 5;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    } else if (e == 5) {
                        try{
                        nEmpMesa = Integer.parseInt(resp);
                        JLabel perg = new JLabel("Digite o salário médio anual:");
                        perg.setBounds(20, 60, 190, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(215, 60, 80, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(300, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        e = 6;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    } else if (e == 6) {
                        try{
                        salMedAnual = Double.parseDouble(resp);
                        JLabel perg = new JLabel("Digite o número médio de clientes/dia:");
                        perg.setBounds(10, 60, 220, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(235, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(310, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        e = 7;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    } else if (e == 7) {
                        try{
                        nMedCliDia = Double.parseDouble(resp);
                        JLabel perg = new JLabel("Digite o número de dias de função/ano:");
                        perg.setBounds(10, 60, 220, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(235, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(310, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        e = 8;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    }else if (e == 8) {
                        try{
                        nDiaFunAno = Integer.parseInt(resp);
                        JLabel perg = new JLabel("Digite o número de mesas interiores:");
                        perg.setBounds(10, 60, 210, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(235, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(310, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        e = 9;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    }else if (e == 9) {
                        try{
                        nMesaInt = Integer.parseInt(resp);
                        JLabel perg = new JLabel("Digite a faturação/mesa/dia:");
                        perg.setBounds(30, 60, 180, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(215, 60, 80, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(300, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        e = 10;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    }else if (e == 10) {
                        try{
                        valMedFatMesaDia = Double.parseDouble(resp);
                        JLabel perg = new JLabel("Digite o numero de clientes Drive/dia:");
                        perg.setBounds(10, 60, 220, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(235, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(310, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        e = 11;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    }else if (e == 11) {
                        try{
                        nCliDriveDia = Double.parseDouble(resp);
                        JLabel perg = new JLabel("Digite a faturação/cliente Drive/dia:");
                        perg.setBounds(10, 60, 220, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(235, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(310, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        e = 12;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    }else if (e == 12) {
                        try {
                            valMedFatDiaCliDrive = Double.parseDouble(resp);
                            if (r == 0) {
                                JLabel perg = new JLabel("FAST FOOD ADICIONADO COM SUCESSO");
                                perg.setBounds(50, 30, 300, 80);
                                pain.add(perg);
                            } else if (r == 1) {
                                JLabel perg = new JLabel("FAST FOOD EDITADO COM SUCESSO");
                                perg.setBounds(60, 30, 280, 80);
                                pain.add(perg);
                                eliminaEmpresa(empresas, tfEditar.getText());
                            }
                            empresas.add(new FastFood(nome, distrito, new Gps(latitude, longitude), nEmpMesa, salMedAnual, nMedCliDia, nDiaFunAno, nMesaInt, valMedFatMesaDia, nCliDriveDia, valMedFatDiaCliDrive));
                            resp = "";
                            guardaEmpresas(empresas);
                            frameCriar.add(pain);
                            frameCriar.setVisible(true);
                        } catch (NumberFormatException exception) {
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);
                        }
                    }
                } else if (resp.equalsIgnoreCase("RestLocal") || f > 0) {
                    pain.setVisible(false);
                    pain.removeAll();
                    pain.setVisible(true);
                    if (f == 0 && r == 0) {
                        JLabel perg = new JLabel("Digite o nome do Restaurante Local:");
                        perg.setBounds(10, 60, 210, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(225, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(300, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        f = 1;
                        t = 1;
                    }else if (f == 1) {
                        if (r == 0){
                            nome = resp;
                        }else{
                            nome = tfEditar.getText();
                        }
                        JLabel perg = new JLabel("Digite o distrito do Restaurante Local:");
                        perg.setBounds(10, 60, 220, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(235, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(310, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        f = 2;
                        t = 1;
                    } else if (f == 2) {
                        int count = 0;
                        for (int i = 0; i < distritos.length; ++i){
                            if (distritos[i].equalsIgnoreCase(resp)) count++;
                        }
                        if (count == 0){
                            JOptionPane.showMessageDialog(null, "Distrito não existe");
                            frameCriar.setVisible(false);
                        }
                        else {
                            distrito = resp;
                            JLabel perg = new JLabel("Digite a latitude do Restaurante Local:");
                            perg.setBounds(10, 60, 220, 25);
                            pain.add(perg);
                            res = new JTextField();
                            res.addActionListener(this);
                            res.setBounds(235, 60, 70, 25);
                            pain.add(res);
                            enter = new JButton("Enter");
                            enter.setBounds(310, 60, 75, 25);
                            enter.addActionListener(this);
                            pain.add(enter);
                            frameCriar.add(pain);
                            frameCriar.setVisible(true);
                            resp = "";
                            f = 3;
                        }
                    } else if (f == 3) {
                        try{
                        latitude = Double.parseDouble(resp);
                        JLabel perg = new JLabel("Digite a longitude do Restaurante Local:");
                        perg.setBounds(10, 60, 220, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(235, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(310, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        f = 4;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    } else if (f == 4) {
                        try{
                        longitude = Double.parseDouble(resp);
                        JLabel perg = new JLabel("Digite o número de empregados de mesa:");
                        perg.setBounds(10, 60, 220, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(235, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(310, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        f = 5;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    } else if (f == 5) {
                        try{
                        nEmpMesa = Integer.parseInt(resp);
                        JLabel perg = new JLabel("Digite o salário médio anual:");
                        perg.setBounds(20, 60, 190, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(215, 60, 80, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(300, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        f = 6;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    } else if (f == 6) {
                        try{
                        salMedAnual = Double.parseDouble(resp);
                        JLabel perg = new JLabel("Digite o número médio de clientes/dia:");
                        perg.setBounds(10, 60, 220, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(235, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(310, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        f = 7;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    } else if (f == 7) {
                        try{
                        nMedCliDia = Double.parseDouble(resp);
                        JLabel perg = new JLabel("Digite o número de dias de função/ano:");
                        perg.setBounds(10, 60, 220, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(235, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(310, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        f = 8;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    }else if (f == 8) {
                        try{
                        nDiaFunAno = Integer.parseInt(resp);
                        JLabel perg = new JLabel("Digite o número de mesas interiores:");
                        perg.setBounds(10, 60, 220, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(235, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(310, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        f = 9;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    }else if (f == 9) {
                        try{
                        nMesaInt = Integer.parseInt(resp);
                        JLabel perg = new JLabel("Digite oa faturação/mesa/dia:");
                        perg.setBounds(30, 60, 180, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(215, 60, 80, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(300, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        f = 10;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    }else if (f == 10) {
                        try{
                        valMedFatMesaDia = Double.parseDouble(resp);
                        JLabel perg = new JLabel("Digite o numero de mesas de esplanada");
                        perg.setBounds(10, 60, 210, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(225, 60, 80, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(310, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        f = 11;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    }else if (f == 11) {
                        try{
                        nMesaEspl = Integer.parseInt(resp);
                        JLabel perg = new JLabel("Digite o custo de licença/mesa esplan/dia:");
                        perg.setBounds(10, 60, 230, 25);
                        pain.add(perg);
                        res = new JTextField();
                        res.addActionListener(this);
                        res.setBounds(245, 60, 70, 25);
                        pain.add(res);
                        enter = new JButton("Enter");
                        enter.setBounds(320, 60, 75, 25);
                        enter.addActionListener(this);
                        pain.add(enter);
                        frameCriar.add(pain);
                        frameCriar.setVisible(true);
                        resp = "";
                        f = 12;
                        }catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);}
                    }else if (f == 12) {
                        try {
                            licMesaEsplAno = Double.parseDouble(resp);
                            if (r == 0) {
                                JLabel perg = new JLabel("RESTAURANTE LOCAL ADICIONADO COM SUCESSO");
                                perg.setBounds(40, 30, 320, 80);
                                pain.add(perg);
                            } else if (r == 1) {
                                JLabel perg = new JLabel("RESTAURANTE LOCAL EDITADO COM SUCESSO");
                                perg.setBounds(50, 30, 300, 80);
                                pain.add(perg);
                                eliminaEmpresa(empresas, tfEditar.getText());
                            }
                            empresas.add(new RestLocal(nome, distrito, new Gps(latitude, longitude), nEmpMesa, salMedAnual, nMedCliDia, nDiaFunAno, nMesaInt, valMedFatMesaDia, nMesaEspl, licMesaEsplAno));
                            resp = "";
                            guardaEmpresas(empresas);
                            frameCriar.add(pain);
                            frameCriar.setVisible(true);
                        } catch (NumberFormatException exception) {
                            JOptionPane.showMessageDialog(null, "Valor incorreto");
                            frameCriar.setVisible(false);
                        }
                    }
                }
            }
        }
    }
}
