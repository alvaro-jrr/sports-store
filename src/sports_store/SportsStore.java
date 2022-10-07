package sports_store;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author alvaro
 *
 * Tienda de Articulos Deportivos 
 */
public class SportsStore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // estado del programa
        boolean shouldContinue = true;

        // opciones
        String options[] = {
            "Administrar Clientes",
            "Administrar Articulos",
            "Administrar Pedidos",
            "Salir"
        };

        ArrayList<Client> clients = new ArrayList<>(); // clientes
        ArrayList<Article> articles = new ArrayList<>(); // articulos
        ArrayList<Order> orders = new ArrayList<>(); // pedidos

        do {
            System.out.printf("----- %s -----%n%n", "Tienda de Articulos Deportivos");

            // mostrar opciones
            displayOptions(options);
            System.out.println();

            // pedir y leer opcion
            System.out.print("Opcion: ");
            int option = input.nextInt();
            System.out.println();

            switch (option) {
                case 1:
                    // administrar clientes
                    handleClients(clients);
                    break;

                case 2:
                    // administrar articulos
                    handleArticles(articles);
                    break;

                case 3:
                    // administrar pedidos
                    handleOrders(clients, articles, orders);
                    break;

                case 4:
                    // salir
                    shouldContinue = false;
                    break;

                default:
                    System.out.println("Opcion invalida");
            }

            System.out.println();
        } while (shouldContinue);
    }

    // mostrar opciones
    public static void displayOptions(String[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.printf("%d) %s%n", i + 1, options[i]);
        }
    }

    // devuelve posicion del cliente, -1 si no se encuentra
    public static int findClientIndex(ArrayList<Client> clients, String identityCard) {
        // almacenar indice 
        int clientIndex = 0;

        for (Client client : clients) {
            // si cedula es igual, devolver indice
            if (client.getIdentityCard().equals(identityCard)) {
                return clientIndex;
            }

            clientIndex++;
        }

        return -1;
    }

    // agregar cliente
    public static void addClient(ArrayList<Client> clients) {
        Scanner input = new Scanner(System.in);

        // pedir y leer cedula
        System.out.print("Cedula: ");
        String identityCard = input.nextLine();

        // si cedula esta registrada
        if (findClientIndex(clients, identityCard) != -1) {
            System.out.println("Cliente ya registrado");
            return;
        }

        // pedir y leer nombre
        System.out.print("Nombre: ");
        String name = input.nextLine();

        // pedir y leer apellido
        System.out.print("Apellido: ");
        String lastName = input.nextLine();

        // pedir y leer fecha de nacimiento
        System.out.print("Dia de Nacimiento: ");
        int day = input.nextInt();

        System.out.print("Mes de Nacimiento: ");
        int month = input.nextInt();

        System.out.print("Anio de Nacimiento: ");
        int year = input.nextInt();

        LocalDate birthDate = LocalDate.of(year, month, day);

        // mostrar generos
        System.out.println();
        displayOptions(Client.genders);
        System.out.println();

        // pedir y leer genero
        System.out.print("Opcion: ");
        int gender = input.nextInt();
        System.out.println();

        while (gender < 1 || gender > Client.genders.length) {
            System.out.println("Opcion invalida");
            System.out.println();

            // pedir y leer genero
            System.out.print("Opcion: ");
            gender = input.nextInt();
            System.out.println();
        }

        // agregar cliente
        clients.add(
            new Client(
                name,
                lastName,
                identityCard,
                birthDate,
                Client.genders[gender - 1]
            )
        );
    }

    // editar cliente
    public static void editClient(ArrayList<Client> clients) {
        Scanner input = new Scanner(System.in);

        // pedir y leer cedula
        System.out.print("Cedula: ");
        String identityCard = input.nextLine();

        int clientIndex = findClientIndex(clients, identityCard);

        // si cedula no esta registrada
        if (clientIndex == -1) {
            System.out.println("Cliente no esta registrado");
            return;
        }

        // obtener cliente a editar
        Client client = clients.get(clientIndex);

        // opciones
        String options[] = {
            "Editar nombre",
            "Editar apellido",
            "Editar fecha de cumpleanios",
            "Editar genero",
            "Salir"
        };
        
        boolean shouldContinue = true;

        System.out.println();

        do {            
            // mostrar cliente
            client.display();
            System.out.println();

            // mostrar opciones
            displayOptions(options);
            System.out.println();

            // pedir y leer opcion
            System.out.print("Opcion: ");
            int option = input.nextInt();
            System.out.println();
            
            // consumir salto de linea
            input.nextLine();

            switch (option) {
                case 1:
                    // pedir y leer nombre
                    System.out.print("Nombre: ");
                    String name = input.nextLine();

                    // actualizar nombre
                    client.setName(name);
                    break;

                case 2:
                    // pedir y leer apellido
                    System.out.print("Apellido: ");
                    String lastName = input.nextLine();

                    // actualizar apellido
                    client.setLastName(lastName);
                    break;

                case 3:
                    // pedir y leer fecha de nacimiento
                    System.out.print("Dia de Nacimiento: ");
                    int day = input.nextInt();

                    System.out.print("Mes de Nacimiento: ");
                    int month = input.nextInt();

                    System.out.print("Anio de Nacimiento: ");
                    int year = input.nextInt();

                    // actualizar fecha de nacimiento
                    client.setBirthDate(LocalDate.of(year, month, day));
                    break;

                case 4:
                    System.out.println();
                    displayOptions(Client.genders);
                    System.out.println();

                    // pedir y leer genero
                    System.out.print("Opcion: ");
                    int gender = input.nextInt();
                    System.out.println();

                    while (gender < 1 || gender > Client.genders.length) {
                        System.out.println("Opcion invalida");
                        System.out.println();

                        // pedir y leer genero
                        System.out.print("Opcion: ");
                        gender = input.nextInt();
                        System.out.println();
                    }

                    // actualizar genero
                    client.setGender(Client.genders[gender - 1]);
                    break;

                case 5:
                    // salir
                    shouldContinue = false;
                    break;

                default:
                    System.out.println("Opcion invalida");
            }

            System.out.println();
        } while (shouldContinue);

        // actualizar cliente
        clients.set(clientIndex, client);
        System.out.println("Cliente modificado con exito");
    }

    // eliminar cliente
    public static void deleteClient(ArrayList<Client> clients) {
        Scanner input = new Scanner(System.in);

        // pedir y leer cedula
        System.out.print("Cedula: ");
        String identityCard = input.nextLine();

        int clientIndex = findClientIndex(clients, identityCard);

        // si cedula no esta registrada
        if (clientIndex == -1) {
            System.out.println("Cliente no existe");
            return;
        }

        // eliminar cliente
        clients.remove(clientIndex);
        System.out.println("Cliente eliminado con exito");
    }

    // administrar clientes
    public static void handleClients(ArrayList<Client> clients) {
        Scanner input = new Scanner(System.in);

        // opciones
        String options[] = {
            "Agregar cliente",
            "Editar cliente",
            "Eliminar cliente",
            "Mostrar clientes",
            "Salir"
        };

        boolean shouldContinue = true;

        do {
            System.out.printf("# %s%n%n", "Administracion de Clientes");

            // mostrar opciones
            displayOptions(options);
            System.out.println();

            // pedir y leer opcion
            System.out.print("Opcion: ");
            int option = input.nextInt();
            System.out.println();

            switch (option) {
                case 1:
                    // agregar cliente
                    addClient(clients);
                    break;

                case 2:
                    // editar cliente
                    editClient(clients);
                    break;

                case 3:
                    // eliminar cliente
                    deleteClient(clients);
                    break;

                case 4:
                    // mostrar cada cliente
                    for (Client client : clients) {
                        client.display();
                        System.out.println();
                    }

                    break;

                case 5:
                    // salir
                    shouldContinue = false;
                    break;

                default:
                    System.out.println("Opcion invalida");
            }

            System.out.println();
        } while (shouldContinue);
    }

    // devuelve posicion del articulo, -1 si no se encuentra
    public static int findArticleIndex(ArrayList<Article> articles, String productCode) {
        // almacenar indice 
        int articleIndex = 0;

        for (Article article : articles) {
            // si codigo de producto es igual, devolver indice
            if (article.getProductCode().equals(productCode)) {
                return articleIndex;
            }

            articleIndex++;
        }

        return -1;
    }

    // agregar articulo
    public static void addArticle(ArrayList<Article> articles) {
        Scanner input = new Scanner(System.in);

        // pedir y leer codigo de producto
        System.out.print("Codigo de Producto: ");
        String productCode = input.nextLine();

        // si articulo esta registrado
        if (findArticleIndex(articles, productCode) != -1) {
            System.out.println("Articulo ya registrado");
            return;
        }

        // pedir y leer descripcion
        System.out.print("Descripcion: ");
        String description = input.nextLine();

        // pedir y leer precio
        System.out.print("Precio Unitario: ");
        double unitPrice = input.nextDouble();

        while (unitPrice <= 0.0) {
            System.out.println("Precio debe ser mayor que 0.0");
            System.out.println();

            // pedir y leer precio
            System.out.print("Precio Unitario: ");
            unitPrice = input.nextDouble();
        }

        // agregar articulo
        articles.add(new Article(productCode, description, unitPrice));
    }

    // editar articulo
    public static void editArticle(ArrayList<Article> articles) {
        Scanner input = new Scanner(System.in);

        // pedir y leer codigo de producto
        System.out.print("Codigo de Producto: ");
        String productCode = input.nextLine();

        int articleIndex = findArticleIndex(articles, productCode);

        // si articulo no esta registrado
        if (articleIndex == -1) {
            System.out.println("Articulo no esta registrado");
            return;
        }

        // obtener articulo a editar
        Article article = articles.get(articleIndex);

        // opciones
        String options[] = {
            "Editar descripcion",
            "Editar precio unitario",
            "Salir"
        };

        boolean shouldContinue = true;

        System.out.println();

        do {
            article.display();
            System.out.println();
            
            // mostrar opciones
            displayOptions(options);
            System.out.println();

            // pedir y leer opcion
            System.out.print("Opcion: ");
            int option = input.nextInt();
            System.out.println();
            
            // consumir salto de linea
            input.nextLine();
            
            switch (option) {
                case 1:
                    // pedir y leer descripcion
                    System.out.print("Descripcion: ");
                    String description = input.nextLine();

                    // modificar descripcion
                    article.setDescription(description);
                    break;

                case 2:
                    // pedir y leer precio
                    System.out.print("Precio Unitario: ");
                    double unitPrice = input.nextDouble();

                    while (unitPrice <= 0.0) {
                        System.out.println("Precio debe ser mayor que 0.0");
                        System.out.println();

                        // pedir y leer precio
                        System.out.print("Precio Unitario: ");
                        unitPrice = input.nextDouble();
                    }

                    // modificar precio
                    article.setUnitPrice(unitPrice);
                    break;

                case 3:
                    // salir
                    shouldContinue = false;
                    break;

                default:
                    System.out.println("Opcion invalida");
            }

            System.out.println();
        } while (shouldContinue);

        // modificar articulo
        articles.set(articleIndex, article);
        System.out.println("Articulo modificado con exito");
    }

    // eliminar articulo
    public static void deleteArticle(ArrayList<Article> articles) {
        Scanner input = new Scanner(System.in);

        // pedir y leer codigo de producto
        System.out.print("Codigo de Producto: ");
        String productCode = input.nextLine();

        int articleIndex = findArticleIndex(articles, productCode);

        // si articulo no esta registrado
        if (articleIndex == -1) {
            System.out.println("Articulo no esta registrado");
            return;
        }

        // eliminar articulo
        articles.remove(articleIndex);
        System.out.println("Articulo eliminado con exito");
    }

    // administrar articulos
    public static void handleArticles(ArrayList<Article> articles) {
        Scanner input = new Scanner(System.in);

        // opciones
        String options[] = {
            "Agregar articulo",
            "Editar articulo",
            "Eliminar articulo",
            "Mostrar articulos",
            "Salir"
        };

        boolean shouldContinue = true;

        do {
            System.out.printf("# %s%n%n", "Administracion de Articulos");

            // mostrar opciones
            displayOptions(options);
            System.out.println();

            // pedir y leer opcion
            System.out.print("Opcion: ");
            int option = input.nextInt();
            System.out.println();

            switch (option) {
                case 1:
                    // agregar articulo
                    addArticle(articles);
                    break;

                case 2:
                    // editar articulo
                    editArticle(articles);
                    break;

                case 3:
                    // eliminar articulo
                    deleteArticle(articles);
                    break;

                case 4:
                    // mostrar cada articulo
                    for (Article article : articles) {
                        article.display();
                        System.out.println();
                    }

                    break;

                case 5:
                    // salir
                    shouldContinue = false;
                    break;

                default:
                    System.out.println("Opcion invalida");
            }

            System.out.println();
        } while (shouldContinue);
    }

    // agregar pedido de articulo
    public static void addOrderItem(ArrayList<Article> articles, Order order) {
        Scanner input = new Scanner(System.in);

        // pedir y leer codigo de producto
        System.out.print("Codigo de Producto: ");
        String productCode = input.nextLine();

        // buscar articulo
        int articleIndex = findArticleIndex(articles, productCode);

        // si articulo no esta registrado
        if (articleIndex == -1) {
            System.out.println("Articulo no esta registrado");
            return;
        }
        
        // mostrar producto
        System.out.println();
        articles.get(articleIndex).display();
        System.out.println();

        // pedir y leer cantidad
        System.out.print("Cantidad: ");
        int quantity = input.nextInt();

        // agregar pedido
        order.addOrderItem(new OrderItem(
            articles.get(articleIndex),
            quantity
        ));
    }

    // editar pedido de articulo
    public static void editOrderItem(ArrayList<Article> articles, Order order) {
        Scanner input = new Scanner(System.in);

        // pedir y leer codigo de producto
        System.out.print("Codigo de Producto: ");
        String productCode = input.nextLine();

        // si articulo no esta en articulos pedidos
        if (!order.containsArticle(productCode)) {
            System.out.println("Articulo no ha sido pedido");
            return;
        }

        // pedir y leer nueva cantidad
        System.out.print("Nueva cantidad: ");
        int quantity = input.nextInt();

        // actualizar pedido
        order.updateOrderItem(productCode, quantity);
    }

    // eliminar pedido de articulo
    public static void deleteOrderItem(ArrayList<Article> articles, Order order) {
        Scanner input = new Scanner(System.in);

        // pedir y leer codigo de producto
        System.out.print("Codigo de Producto: ");
        String productCode = input.nextLine();

        // si articulo no esta en articulos pedidos
        if (!order.containsArticle(productCode)) {
            System.out.println("Articulo no ha sido pedido");
            return;
        }

        // eliminar pedido de articulo
        order.removeOrderItem(productCode);
    }

    // pagar pedido con tarjeta de credito
    public static void payOrderWithCreditCard(Order order, double amount) {
        Scanner input = new Scanner(System.in);

        // pedir y leer numero de tarjeta
        System.out.print("Numero de Tarjeta: ");
        String cardNumber = input.nextLine();

        // pedir y leer fecha de caducidad
        System.out.print("Dia de Expiracion: ");
        int day = input.nextInt();

        System.out.print("Mes de Expiracion: ");
        int month = input.nextInt();

        System.out.print("Anio de Expiracion: ");
        int year = input.nextInt();

        LocalDate expirationDate = LocalDate.of(
            year, month, day
        );

        // mostrar redes
        System.out.println();
        displayOptions(CreditCard.paymentNetworks);
        System.out.println();

        // pedir y leer red de pago
        System.out.print("Opcion: ");
        int paymentNetwork = input.nextInt();
        System.out.println();

        while (paymentNetwork < 1
            || paymentNetwork > CreditCard.paymentNetworks.length) {
            System.out.println("Opcion invalida");
            System.out.println();

            // pedir y leer red de pago
            System.out.print("Opcion: ");
            paymentNetwork = input.nextInt();
            System.out.println();
        }

        // agregar pago
        order.addPayment(new CreditCard(
            expirationDate,
            cardNumber,
            CreditCard.paymentNetworks[paymentNetwork - 1],
            amount
        ));
    }

    // pagar pedido con cheque
    public static void payOrderWithCheck(Order order, double amount) {
        Scanner input = new Scanner(System.in);

        // pedir y leer numero de cheque
        System.out.print("Numero de Cheque: ");
        String checkNumber = input.nextLine();

        // pedir y leer entidad bancaria
        System.out.print("Entidad Bancaria: ");
        String bankingEntity = input.nextLine();

        // agregar pago
        order.addPayment(new Check(
            checkNumber,
            bankingEntity,
            amount
        ));
    }

    // pagar order
    public static void payOrder(ArrayList<Order> orders, Order order) {
        Scanner input = new Scanner(System.in);

        // opciones de pago
        String paymentMethods[] = {
            "Efectivo",
            "Tarjeta de Credito",
            "Cheque"
        };

        // hacer pagos hasta que pedido este pagado
        while (!order.isPaid()) {
            // mostrar total a pagar y total pagado
            System.out.printf(
                "%s: %f%n%s: %f%n%n",
                "Total a Pagar", order.totalCost(),
                "Restante por Pagar", order.remainingDue()
            );

            // mostrar opciones de pago
            displayOptions(paymentMethods);
            System.out.println();

            // pedir y leer opcion
            System.out.print("Opcion: ");
            int paymentMethod = input.nextInt();
            System.out.println();

            // pedir y leer pago
            System.out.print("Cantidad a Pagar: ");
            double amount = input.nextDouble();

            while (amount <= 0.0 || amount > order.remainingDue()) {
                if (amount <= 0.0) {
                    System.out.println("Cantidad debe ser mayor que 0.0");
                } else {
                    System.out.println("Cantidad debe ser menor o igual a cantidad restante");
                }

                System.out.println();

                // pedir y leer pago
                System.out.print("Cantidad a Pagar: ");
                amount = input.nextDouble();
            }

            switch (paymentMethod) {
                case 1:
                    // agregar efectivo
                    order.addPayment(new Cash(amount));
                    break;

                case 2:
                    payOrderWithCreditCard(order, amount);
                    break;

                case 3:
                    payOrderWithCheck(order, amount);
                    break;

                default:
                    System.out.println("Opcion invalida");
            }

            System.out.println();
        }

        // mostrar factura
        order.generateInvoice();
    }

    // hacer pedido
    public static void makeOrder(
        ArrayList<Client> clients,
        ArrayList<Article> articles,
        ArrayList<Order> orders
    ) {
        Scanner input = new Scanner(System.in);

        // pedir y leer cedula
        System.out.print("Cedula de Cliente: ");
        String identityCard = input.nextLine();

        int clientIndex = findClientIndex(clients, identityCard);

        // si cedula no esta registrada
        if (clientIndex == -1) {
            System.out.println("Cliente no esta registrado");
            return;
        }

        // crear pedido
        Order order = new Order(clients.get(clientIndex));

        // opciones
        String options[] = {
            "Agregar Pedido de Articulo",
            "Editar Pedido de Articulo",
            "Eliminar Pedido de Articulo",
            "Mostrar Articulos Pedidos",
            "Pagar Pedido",
            "Salir"
        };

        boolean shouldContinue = true;

        System.out.println();

        do {
            // mostrar opciones
            displayOptions(options);
            System.out.println();

            // pedir y leer opcion
            System.out.print("Opcion: ");
            int option = input.nextInt();
            System.out.println();

            switch (option) {
                case 1:
                    // agregar pedido de articulo
                    addOrderItem(articles, order);
                    break;

                case 2:
                    // agregar pedido de articulo
                    editOrderItem(articles, order);
                    break;

                case 3:
                    // eliminar articulo pedido
                    deleteOrderItem(articles, order);
                    break;
                
                case 4:
                    // mostrar cada articulo pedido
                    for (OrderItem orderItem : order.getOrderItems()) {
                        orderItem.display();
                        System.out.println();
                    }
                    
                    break;
                    
                case 5:
                    // si no hay articulos pedidos
                    if (order.isEmpty()) {
                        System.out.println("No hay articulos pedidos");
                        break;
                    }

                    // pagar pedido
                    payOrder(orders, order);

                    // agregar a lista de pedidos
                    orders.add(order);

                    // salir de pedido
                    shouldContinue = false;
                    break;

                case 6:
                    shouldContinue = false;
                    break;

                default:
                    System.out.println("Opcion invalida");
            }

            System.out.println();
        } while (shouldContinue);
    }

    // administar pedidos
    public static void handleOrders(
        ArrayList<Client> clients,
        ArrayList<Article> articles,
        ArrayList<Order> orders
    ) {
        Scanner input = new Scanner(System.in);

        // opciones
        String options[] = {
            "Hacer pedido",
            "Mostrar pedidos",
            "Salir"
        };

        boolean shouldContinue = true;

        do {
            System.out.printf("# %s%n%n", "Administracion de Pedidos");

            // mostrar opciones
            displayOptions(options);
            System.out.println();

            // pedir y leer opcion
            System.out.print("Opcion: ");
            int option = input.nextInt();
            System.out.println();

            switch (option) {
                case 1:
                    // si no hay articulos registrados
                    if (articles.isEmpty()) {
                        System.out.println("No hay articulos registrados");
                        break;
                    }
                    
                    // si no hay clientes registrados
                    if (clients.isEmpty()) {
                        System.out.println("No hay clientes registrados");
                        break;
                    }
                    
                    // hacer pedido
                    makeOrder(clients, articles, orders);
                    break;

                case 2:
                    // mostrar cada factura de pedido
                    for (Order order : orders) {
                        order.generateInvoice();
                        System.out.println();
                    }

                    break;

                case 3:
                    shouldContinue = false;
                    break;

                default:
                    System.out.println("Opcion invalida");
            }

            System.out.println();
        } while (shouldContinue);
    }
}
