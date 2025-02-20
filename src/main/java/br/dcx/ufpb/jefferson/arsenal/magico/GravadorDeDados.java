package br.dcx.ufpb.jefferson.arsenal.magico;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class GravadorDeDados {
    private final String arquivoMagias;

    public GravadorDeDados(){
        this.arquivoMagias = "magias.dat";
    }
    public Collection<Magia> recuperaMagias() throws IOException {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.arquivoMagias))) {
            Collection<Magia> magiasRecuperadas = (ArrayList) in.readObject();
            return magiasRecuperadas;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void gravaMagia(Collection<Magia> magias) throws IOException{
        ArrayList<Magia> magiaArrayList = new ArrayList<>();
        magiaArrayList.addAll(magias);
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(this.arquivoMagias))){
            out.writeObject(magiaArrayList);
        }
    }
    public static void main(String [] args){
        //Testa gravador
        Magia magia = new Magia(003,"Tsunami", TipoElementar.AGUA,FormaElementar.CHUVA,EfeitoElementar.NORMAL,300.0, 100);
        Magia magia2 = new Magia(004,"Vento uivante", TipoElementar.AR,FormaElementar.RAIO,EfeitoElementar.LENTIDAO, 20.0, 50);

        List<Magia> magias = new LinkedList<>();
        magias.add(magia);
        magias.add(magia2);
        GravadorDeDados gravador = new GravadorDeDados();
        try {
            gravador.gravaMagia(magias);
            System.out.println("Magias do arsenal");
            for(Magia m: gravador.recuperaMagias()){
                System.out.println(m.toString());
            }
            gravador.gravaMagia(magias);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
