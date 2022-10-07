package sports_store;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author alvaro
 *
 * clase Pedido
 */
public class Order {

    private LocalDate date; // fecha
    private Client client; // cliente
    private final ArrayList<OrderItem> orderItems; // articulos pedidos
    private final ArrayList<Payment> payments; // pagos
    private final double TAX = 0.16; // impuesto 

    // constructor
    public Order(Client client) {
        this.date = LocalDate.now(); // establecer fecha actual de pedido
        this.client = client;
        this.orderItems = new ArrayList<>();
        this.payments = new ArrayList<>();
    }

    // obtener fecha
    public LocalDate getDate() {
        return date;
    }

    // establecer fecha
    public void setDate(LocalDate date) {
        this.date = date;
    }

    // obtener cliente
    public Client getClient() {
        return client;
    }

    // establecer cliente
    public void setClient(Client client) {
        this.client = client;
    }

    // obtener articulos pedidos
    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    // devuelve posicion de articulo pedido, -1 si no existe
    private int findOrderItemIndex(String productCode) {
        int orderIndex = 0;

        for (OrderItem item : getOrderItems()) {
            Article article = item.getArticle();

            if (article.getProductCode().equals(productCode)) {
                return orderIndex;
            }

            orderIndex++;
        }

        return -1;
    }
    
    // determina si pedido contiene un determinado articulo
    public boolean containsArticle(String productCode) {        
        return findOrderItemIndex(productCode) != -1;
    }

    // agregar articulo pedido
    public void addOrderItem(OrderItem item) {
        // buscar producto en lista de articulos pedidos
        int orderItemIndex = findOrderItemIndex(item.getArticle().getProductCode());
        
        if (orderItemIndex == -1) {
            // nuevo articulo pedido
            orderItems.add(item);
        } else {
            // obtener articulo pedido actual
            OrderItem orderItem = orderItems.get(orderItemIndex);
            
            // sumar cantidades de articulos y establecer nueva cantidad
            int newQuantity = item.getQuantity() + orderItem.getQuantity();
            orderItem.setQuantity(newQuantity);
            
            // actualizar en lista de pedidos
            orderItems.set(orderItemIndex, orderItem);
        }
    }

    // modificar articulo pedido
    public void updateOrderItem(String productCode, int newQuantity) {
        // si cantidad nueva es menor que 0, salir
        if (newQuantity < 0) {
            return;
        }
        
        if (newQuantity == 0) {
            // eliminar articulo pedido
            removeOrderItem(productCode);
        } else {
            // obtener posicion de articulo pedido
            int orderIndex = findOrderItemIndex(productCode);

            if (orderIndex != -1) {
                // obtener y actualizar articulo pedido
                OrderItem orderItemUpdate = getOrderItems().get(orderIndex);
                orderItemUpdate.setQuantity(newQuantity);

                // modificar en articulos pedidos
                orderItems.set(orderIndex, orderItemUpdate);
            }
        }
    }

    // eliminar articulo pedido
    public void removeOrderItem(String productCode) {
        // obtener posicion de articulo pedido
        int orderIndex = findOrderItemIndex(productCode);

        if (orderIndex != -1) {
            orderItems.remove(orderIndex);
        }
    }

    // obtener pagos
    public ArrayList<Payment> getPayments() {
        return payments;
    }
    
    // agregar pago
    public void addPayment(Payment payment) {
        // agregar si no supera el restante por pagar
        if (payment.getAmount() <= remainingDue()) {
            payments.add(payment);
        }
    }
    
    // obtener total pagado
    public double totalPaid() {
        double total = 0.0;

        for (Payment payment : getPayments()) {
            total += payment.getAmount();
        }

        return total;
    }
    
    // coste total
    public double totalCost() {
        double total = 0.0;

        for (OrderItem orderItem : getOrderItems()) {
            // total de articulo pedido + impuesto asociado
            total += orderItem.total() + orderItem.total() * TAX;
        }

        return total;
    }
    
    // obtener total restante por pagar
    public double remainingDue() {
        return totalCost() - totalPaid();
    }
    
    // determinar si pedido esta vacio (sin articulos pedidos)
    public boolean isEmpty() {
        return getOrderItems().isEmpty();
    }
    
    // determinar si esta pagado el pedido
    public boolean isPaid() {
        return remainingDue() == 0.0;
    }
    
    // generar factura
    public void generateInvoice() {
        if (!isPaid()) {
            System.out.println("No ha finalizado el pago de pedido");
            return;
        }
        
        // formato de fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY"); 
        
        System.out.println("-------------------------");
        System.out.printf("%s%n%n", "FACTURA");
        
        // mostrar datos del cliente
        System.out.printf("- %s%n%n", "Datos del Cliente");        
        client.display();
        System.out.println();
        
        // mostrar fecha
        System.out.printf("- %s: %s%n%n", "Fecha", getDate().format(formatter));
        
        // mostrar articulos pedidos
        System.out.printf("- %s%n%n", "Articulos Pedidos");
        
        for (OrderItem orderItem : getOrderItems()) {
            orderItem.display();
            System.out.println();
        }
        
        // mostrar datos de los pagos
        System.out.printf("- %s%n%n", "Pagos");
        
        for (Payment payment : getPayments()) {
            payment.display();
            System.out.println();
        }
        
        // mostrar total
        System.out.printf("- %s: %.2f%n", "Tasa de Impuesto", TAX);
        System.out.printf("- %s: %.2f%n%n", "Coste Total", totalCost());
        System.out.println("-------------------------");
    }
}
