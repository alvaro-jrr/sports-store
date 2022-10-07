package sports_store;

/**
 *
 * @author alvaro
 *
 * clase Pago
 */
public class Payment {

    private double amount; // cantidad

    // constructor
    public Payment(double amount) {
        this.amount = amount;
    }

    // obtener cantidad
    public double getAmount() {
        return amount;
    }

    // establecer cantidad
    public void setAmount(double amount) {
        this.amount = amount;
    }

    // mostrar
    public void display() {
        System.out.printf("%s: %.2f%n", "Cantidad", getAmount());
    }
}
