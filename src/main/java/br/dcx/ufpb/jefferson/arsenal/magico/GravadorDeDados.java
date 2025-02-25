package br.dcx.ufpb.jefferson.arsenal.magico;

import java.io.*;
import java.util.*;

public class GravadorDeDados {
    private final String arquivoMagias;

    public GravadorDeDados(){
        this.arquivoMagias = "magias.dat";
    }
    public HashMap<Integer,Magia> recuperaMagias() throws IOException {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.arquivoMagias))) {
            HashMap<Integer,Magia> magiasRecuperadas = (HashMap<Integer,Magia>) in.readObject();
            return magiasRecuperadas;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //RandomAccessFile é um objeto de gravação bidirecional
        //ZipInputStream objeto para compressão de dados, bufferização é um processo intermediario.
    }

    public void gravaMagia(HashMap<Integer,Magia> magias) throws IOException{
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(this.arquivoMagias))){
            out.writeObject(magias);
        }
    }
}
