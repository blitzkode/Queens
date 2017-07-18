package Core;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="madaline")
@XmlType(propOrder = {"nombre", "adalines"})
public class Madaline {
    private String nombre;
    private NeuronaAdaline[] adalines;
    
    public Madaline() {
        
    }
    
    public Madaline(int adalines) {
        this.adalines = new NeuronaAdaline[adalines];
    }
    
    public Madaline(double[][] pesosAdalines) {
        this(pesosAdalines.length);
        inicializarAdalines(pesosAdalines);
        this.nombre = "";
    }
    
    public Madaline(String nombre, double [][] pesosAdalines) {
        this(pesosAdalines);
        this.nombre = nombre;
    }
    
    private void inicializarAdalines(double[][] pesosAdalines) {
        for (int i=0; i<adalines.length; i++) {
            adalines[i] = new NeuronaAdaline(pesosAdalines[i]);
        }
    }
    
    public void entrenar(byte[][] entradas, byte[] salidasDeseadas) {
        boolean error = false;
        do {
            for (int i = 0; i < entradas.length; i++) {
                byte m = calcularSalida(entradas[i]); // salida del madaline
                byte d = salidasDeseadas[i]; // salida deseada
                error = (m != d);
                if (error) {
                    ajustarPesos(entradas[i], d);
                    break;
                }
            }
        } while (error);        
    }
    
    public void entrenar(byte [] entrada, byte salidaDeseada) {
        entrenar(new byte[][] {entrada}, new byte[] {salidaDeseada});
    }
    
    protected void ajustarPesos(byte[] entradas, byte salidaDeseada) {
        int c = adalineGanador(entradas, salidaDeseada);
        adalines[c].ajustarPesos(entradas, salidaDeseada);
    }
    
    protected int adalineGanador(byte[] entradas, byte salidaDeseada) {
        double[] salidas = salidasAdalines(entradas);
        int pos_ganador = 0;
        double cercanoACero = Integer.MAX_VALUE;
        for (int i = 0; i < salidas.length; i++) {
            if ( adalines[i].calcularSalida(entradas) != salidaDeseada && // adaline con salida erronea
                    Math.abs(salidas[i]) < cercanoACero) {
                cercanoACero = Math.abs(salidas[i]);
                pos_ganador = i;
            }
        }
        return pos_ganador;
    }
    
    public byte calcularSalida(byte[] entradas) {
        byte[] salidas = new byte[adalines.length];
        for (int i = 0; i < salidas.length; i++) {
            salidas[i] = adalines[i].calcularSalida(entradas);
        }
        return funcionMayoria(salidas);
    }
    
    public double[] salidasAdalines(byte[] entradas) {
        double[] salidas = new double[adalines.length];
        for (int i = 0; i < salidas.length; i++) {
            salidas[i] = adalines[i].salidaOriginal(entradas);
        }
        return salidas;
    }
    
    protected byte funcionMayoria(byte[] entradas) {
        int mas = 0, menos = 0;
        for (int entrada : entradas) {
            if (entrada == 1) mas++;
            else menos++;
        }
        return (byte) (mas >= menos ? 1 : -1);
    }
    
    public double[][] getPesos() {
        double[][] pesos = new double[adalines.length][];
        for (int i=0; i<adalines.length; i++) {
            pesos[i] = adalines[i].getPesos();
        }
        return pesos;
    }
    
    @Override
    public String toString() {
        return toString(4);
    }
    
    public String toString(int decimales) {
        String cadena = "";
        for (int i = 0; i < adalines.length; i++) {
            cadena += String.format("Adaline %d: %s\n", 
                    (i+1), adalines[i].toString(decimales));
        }
        return cadena;
    }

    @XmlElementWrapper(name = "adalines")
    @XmlElement(name="adaline")
    public NeuronaAdaline[] getAdalines() {
        return adalines;
    }

    public void setAdalines(NeuronaAdaline[] adalines) {
        this.adalines = adalines;
    }
    
    @XmlAttribute(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
