package main.java.it.unisa.ErasmusTracking.bean;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class Documenti{

    private int id;
    private String nome;
    private DateFormat data;
    private String dataString;
    private Date data_caricamento;
    private String url;
    private int proprietario;
    private int fileSize;
    private InputStream inputStream;


    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }


    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }



    public Documenti(int id, String nome, String data_caricamento, String url, int proprietario){
        this.id = id;
        this.nome = nome;
        dataString = data_caricamento;
        data = DateFormat.getDateInstance(DateFormat.SHORT);
        try {
            this.data_caricamento=data.parse(dataString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.url = url;
        this.proprietario = proprietario;
    }

    public Documenti() {

    }

    public int getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public String getDataCaricamento(){
        return data.format(data_caricamento);
    }

    public String getUrl(){
        return url;
    }

    public int getProprietario(){
        return proprietario;
    }

    public void setId(int newId) {id=newId;}

    public void setNome(String newNome) {
        nome=newNome;
    }

    public void setDataCaricamento(String newData) {
        dataString=newData;
        data = DateFormat.getDateInstance(DateFormat.SHORT);
        try {
            this.data_caricamento=data.parse(dataString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setUrl(String newUrl) {
        url=newUrl;
    }

    public void setProprietario(int newProprietario) {
        proprietario=newProprietario;
    }
}