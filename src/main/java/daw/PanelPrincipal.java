/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author danielnavasborjas
 */
public class PanelPrincipal extends JPanel implements ActionListener {

    // Atributos de la clase (privados)
    private PanelBotones botonera;
    private JTextArea areaTexto;
    private int tipoOperacion;

    // Constructor
    public PanelPrincipal() {
        initComponents();
        tipoOperacion = -1; // No hay operaciones en la calculadora
    }

    // Se inicializan los componentes gráficos y se colocan en el panel
    private void initComponents() {
        // Creamos el panel de botones
        botonera = new PanelBotones();
        // Creamos el área de texto
        areaTexto = new JTextArea(10, 50);
        areaTexto.setEditable(false);
        areaTexto.setBackground(Color.white);

        //Establecemos layout del panel principal
        this.setLayout(new BorderLayout());
        // Colocamos la botonera y el área texto
        this.add(areaTexto, BorderLayout.NORTH);
        this.add(botonera, BorderLayout.SOUTH);
        for (JButton grupoBotone : this.botonera.getgrupoBotones()) {
            grupoBotone.addActionListener(this);
        }

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String valor;
        // Se obtiene el objeto que desencadena el evento
        Object o = ae.getSource();
        // Si es un botón
        if (o instanceof JButton) {
            System.out.println(((JButton) o).getText());
            valor = ((JButton) o).getText();
            areaTexto.append(valor);
            if (valor.equals("=")) {
                operacion();
            } else if (valor.equalsIgnoreCase("c")) {
                areaTexto.setText("");
            }
        }

        // RESTO DEL CÓDIGO DE LA LÓGICA DE LA CALCULADORA
    }

    public void operacion() {
        int valor1 = 0;
        int valor2 = 0;
        int operacion = 0;
        double operacionDecimal;
        String operador;
        String regex = "(\\d+)\\s*([+\\-*/])\\s*(\\d+)";
        
        //hacemos un pattern para pasarele el regex creado con anterioridad
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(areaTexto.getText());

        while (matcher.find()) {
            valor1 = Integer.parseInt(matcher.group(1));
            operador = matcher.group(2);
            valor2 = Integer.parseInt(matcher.group(3));

            switch (operador) {
                case "+" -> {
                    operacion = valor1 + valor2;
                }
                case "-" -> {
                    operacion = valor1 - valor2;
                }
                case "*" -> {
                    operacion = valor1 * valor2;
                }
                case "/" -> {
                    operacionDecimal = (double) valor1 / valor2;
                    areaTexto.setText(Double.toString(operacionDecimal));
                    //el return aquí sirve para terminar el método y que
                    //no se siga ejecutando
                    return;
                }
            }
        }
        
        areaTexto.setText(Integer.toString(operacion));

    }

}
