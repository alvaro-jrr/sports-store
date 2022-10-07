package sports_store;

import java.time.LocalDate;

/**
 *
 * @author alvaro
 *
 * clase TarjetaCredito
 */
public class CreditCard extends Payment {

    // redes de pago
    public static final String[] paymentNetworks = {
        "Visa",
        "MasterCard"
    };

    private LocalDate expirationDate; // fecha de caducidad
    private String number; // numero
    private String paymentNetwork; // red de pago

    // constructor
    public CreditCard(
        LocalDate expirationDate,
        String number,
        String paymentNetwork,
        double amount
    ) {
        super(amount);
        this.expirationDate = expirationDate;
        this.number = number;
        
        // establecer red si se encuentra entre las redes disponibles
        for (String network : paymentNetworks) {
            if (paymentNetwork.equals(network)) {
                this.paymentNetwork = paymentNetwork;
                break;
            }
        }
    }

    // obtener fecha de caducidad
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    // establecer fecha de caducidad
    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    // obtener numero
    public String getNumber() {
        return number;
    }

    // establecer numero
    public void setNumber(String number) {
        this.number = number;
    }

    // obtener red de pago
    public String getPaymentNetwork() {
        return paymentNetwork;
    }

    // establecer red de pago
    public void setPaymentNetwork(String paymentNetwork) {
        // establecer red si se encuentra entre las redes disponibles
        for (String network : paymentNetworks) {
            if (paymentNetwork.equals(network)) {
                this.paymentNetwork = paymentNetwork;
                break;
            }
        }
    }

    // mostrar
    @Override
    public void display() {
        super.display();
        System.out.printf("%s: %s%n", "Forma de Pago", "Tarjeta de Credito");
        System.out.printf("%s: %s%n", "Numero", getNumber());
        System.out.printf("%s: %s%n", "Red de Pago", getPaymentNetwork());
    }
}
