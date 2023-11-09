package com.example.lectordocumentos;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;

public class LectorController {


    public Button botonEscribir;
    public Button botonSobreescribir;
    public TextField textoACargar;
    public TextArea areaEscribir;
    public TextArea areaSobreescribir;
    public String texto;
    public BufferedReader fEntrada;
    public BufferedWriter fSalida;


    public void CargarArchivo(MouseEvent mouseEvent){

        texto = textoACargar.getText();
        if (texto == null)
        {

            areaEscribir.setText("El Buscador esta vacio");

        }
        else {

            if (texto.startsWith("http://") || texto.startsWith("https://")){

                URI uri = URI.create(texto);

                try {
                    fEntrada = new BufferedReader(new InputStreamReader(uri.toURL().openStream()));
                } catch (IOException e) {
                    areaEscribir.setText(e.getMessage());
                }
                String linea;
                try {
                    linea = fEntrada.readLine();
                    while (linea != null)
                    {

                        areaEscribir.appendText(linea);

                        linea = fEntrada.readLine();

                    }
                } catch (IOException e) {
                    areaEscribir.setText(e.getMessage());
                }
                try {
                    fEntrada.close();
                } catch (IOException e) {
                    areaEscribir.setText(e.getMessage());
                }
            }
            else {
                File file = new File("C:\\Users\\alumTA\\Desktop/" + texto);
                if (file.exists()) {
                    try {
                        StringBuilder lineas = new StringBuilder();
                        areaEscribir.setText("Archivo Cargado");
                        fEntrada = new BufferedReader(new FileReader(texto));
                        String linea = fEntrada.readLine();
                        while (linea != null) {

                            lineas.append(linea);
                            linea = fEntrada.readLine();
                        }
                        fEntrada.close();
                        areaEscribir.setText(lineas.toString());
                    }catch (IOException e)
                    {
                        areaEscribir.setText(e.getMessage());
                    }
                }
                else {

                    try {
                        areaEscribir.setText( "El archivo ha sido creado = " + file.createNewFile());
                    } catch (IOException e) {
                        areaEscribir.setText(e.getMessage());
                    }

                }
            }
        }

        botonEscribir.setDisable(false);
        botonSobreescribir.setDisable(false);
    }
    public void EscribirArchivo(MouseEvent mouseEvent){


        String contenido;


        try {
            fEntrada = new BufferedReader(new FileReader(texto));
            contenido = fEntrada.readLine();
            if (contenido != null) {
                contenido += areaSobreescribir.getText();
                fSalida = new BufferedWriter(new FileWriter(texto));
                fSalida.write(contenido);
                fSalida.close();
                fEntrada.close();
                areaEscribir.setText(contenido);
            }
            else {
                SobreescribirArchivo(mouseEvent);
            }
        }catch (IOException e){

            areaEscribir.setText(e.getMessage());

        }

    }
    public void SobreescribirArchivo(MouseEvent mouseEvent) {

        String contenido;


        try {
            fEntrada = new BufferedReader(new FileReader(texto));
            contenido = areaSobreescribir.getText();
            fSalida = new BufferedWriter(new FileWriter(texto));
            fSalida.write(contenido);
            fSalida.close();
            fEntrada.close();
            areaEscribir.setText(contenido);
        }catch (IOException e){

            areaEscribir.setText(e.getMessage());

        }

    }

}