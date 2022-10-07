package sports_store;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author alvaro
 *
 * clase Cliente
 */
public class Client {
    
    // generos
    public static final String[] genders = { 
        "Masculino",
        "Femenino",
        "No especificado"
    };

    private String name; // nombre
    private String lastName; // apellido
    private String identityCard; // cedula
    private LocalDate birthDate; // fecha de nacimiento
    private String gender; // genero

    // constructor
    public Client(
        String name,
        String lastName,
        String identityCard,
        LocalDate birthDate,
        String gender
    ) {
        this.name = name;
        this.lastName = lastName;
        this.identityCard = identityCard;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    // obtener nombre
    public String getName() {
        return name;
    }

    // establecer nombre
    public void setName(String name) {
        this.name = name;
    }

    // obtener apellido
    public String getLastName() {
        return lastName;
    }

    // establecer apellido
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // obtener cedula
    public String getIdentityCard() {
        return identityCard;
    }

    // establecer cedula
    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    // obtener fecha de nacimiento
    public LocalDate getBirthDate() {
        return birthDate;
    }

    // establecer fecha de nacimiento
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    // obtener genero
    public String getGender() {
        return gender;
    }

    // establecer genero
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    // mostrar
    public void display() {
        // formato de fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY"); 
        
        // mostrar datos
        System.out.printf("%s: %s%n", "Nombre", getName());
        System.out.printf("%s: %s%n", "Apellido", getLastName());
        System.out.printf("%s: %s%n", "Cedula", getIdentityCard());
        System.out.printf("%s: %s%n", "Genero", getGender());
        System.out.printf(
            "%s: %s%n", "Fecha de Nacimiento", 
            getBirthDate().format(formatter)
        );
    }
}
