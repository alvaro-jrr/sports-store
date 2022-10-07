package sports_store;

/**
 *
 * @author alvaro
 *
 * clase Efectivo
 */
public class Cash extends Payment {

    // constructor
    public Cash(double amount) {
        super(amount);
    }

    // mostrar
    @Override
    public void display() {
        super.display();
        System.out.printf("%s: %s%n", "Forma de Pago", "Efectivo");
    }
}
