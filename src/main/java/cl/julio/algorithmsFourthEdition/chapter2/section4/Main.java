package cl.julio.algorithmsFourthEdition.chapter2.section4;

public class Main {

    public void set(StringBuilder mensaje) {
        mensaje.append("asdad");
    }
    
    public static void main(String[] args) {
        StringBuilder mensaje = new StringBuilder();
        Main main = new Main();
        main.set(mensaje);
        
        System.out.println(mensaje);
    }
    
}
