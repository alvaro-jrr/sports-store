package sports_store;

/**
 *
 * @author alvaro
 *
 * clase ArticuloPedido
 */
public class OrderItem {

    private Article article; // articulo
    private int quantity; // cantidad

    // constructor
    public OrderItem(Article article, int quantity) {
        this.article = article;

        if (quantity > 0) {
            this.quantity = quantity;
        }
    }

    // obtener articulo
    public Article getArticle() {
        return article;
    }

    // establecer articulo
    public void setArticle(Article article) {
        this.article = article;
    }

    // obtener cantidad
    public int getQuantity() {
        return quantity;
    }

    // establecer cantidad
    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        }
    }
    
    // obtener total
    public double total() {
        return getArticle().getUnitPrice() * getQuantity();
    }
    
    // mostrar
    public void display() {
        article.display();
        System.out.printf("%s: %d%n", "Cantidad", getQuantity());
        System.out.printf("%s: %.2f%n", "Total", total());
    }
}
