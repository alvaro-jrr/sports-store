package sports_store;

/**
 *
 * @author alvaro
 *
 * clase Cheque
 */
public class Check extends Payment {

    private String checkNumber; // numero de cheque
    private String bankingEntity; // entidad bancaria

    // constructor
    public Check(String checkNumber, String bankingEntity, double amount) {
        super(amount);
        this.checkNumber = checkNumber;
        this.bankingEntity = bankingEntity;
    }

    // obtener numero de cheque
    public String getCheckNumber() {
        return checkNumber;
    }

    // establecer numero de cheque
    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    // obtener entidad bancaria
    public String getBankingEntity() {
        return bankingEntity;
    }

    // establecer entidad bancaria
    public void setBankingEntity(String bankingEntity) {
        this.bankingEntity = bankingEntity;
    }

    // mostrar
    @Override
    public void display() {
        super.display();
        System.out.printf("%s: %s%n", "Forma de Pago", "Cheque");
        System.out.printf("%s: %s%n", "Numero de Cheque", getCheckNumber());
        System.out.printf("%s: %s%n", "Entidad Bancaria", getBankingEntity());
    }
}
