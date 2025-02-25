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
        //RandomAccessFile é um objeto de gravação bidirecional
        //ZipInputStream objeto para compressão de dados, bufferização é um processo intermediario.
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
        Magia magia = new Magia(003,"Tsunamiiiiiiii", TipoElementar.AGUA,300.0, 100);
        Magia magia2 = new Magia(004,"Vento uivante", TipoElementar.AR,20.0, 50);

        List<Magia> magias = new LinkedList<>();
        magias.add(magia);
        magias.add(magia2);
        GravadorDeDados gravador = new GravadorDeDados();
        try {
            magias.addAll(gravador.recuperaMagias());
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
