package sports_store;

/**
 *
 * @author alvaro
 *
 * clase Articulo
 */
public class Article {

    private String productCode; // codigo del producto
    private String description; // descripcion
    private double unitPrice; // precio unitario

    // constructor
    public Article(String productCode, String description, double unitPrice) {
        this.productCode = productCode;
        this.description = description;

        if (unitPrice > 0.0) {
            this.unitPrice = unitPrice;
        }
    }

    // obtener codigo del producto
    public String getProductCode() {
        return productCode;
    }

    // establecer codigo del producto
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    // obtener descripcion
    public String getDescription() {
        return description;
    }

    // establecer descripcion
    public void setDescription(String description) {
        this.description = description;
    }

    // obtener precio unitario
    public double getUnitPrice() {
        return unitPrice;
    }

    // establecer precio unitario
    public void setUnitPrice(double unitPrice) {
        if (unitPrice > 0.0) {
            this.unitPrice = unitPrice;
        }
    }
    
    // mostrar
    public void display() {
        System.out.printf("%s: %s%n", "Codigo de Producto", getProductCode());
        System.out.printf("%s: %s%n", "Descripcion", getDescription());
        System.out.printf("%s: %.2f%n", "Precio Unitario", getUnitPrice());
    }
}
