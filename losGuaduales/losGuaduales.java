import java.util.Scanner;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Arreglo de 6 posiciones para los consumos (0 indica sin registro)
        double[] consumos = new double[6];
        int opcion;

        // Menú principal con do-while + switch
        do {
            mostrarMenu();
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    registrarConsumo(consumos);
                    break;
                case 2:
                    consultarFactura(consumos);
                    break;
                case 3:
                    generarReporte(consumos);
                    break;
                case 4:
                    System.out.println("\nGracias por usar el sistema. Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
            System.out.println(); // Línea de separación
        } while (opcion != 4);
    }

    // 1. Mostrar menú
    public static void mostrarMenu() {
        System.out.println("===== LOS GUADUALES - CONTROL DE AGUA =====");
        System.out.println("1. Registrar consumo de un apartamento");
        System.out.println("2. Consultar factura de un apartamento");
        System.out.println("3. Ver reporte general del conjunto");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }

    // 2. Registrar consumo con validaciones
    public static void registrarConsumo(double[] consumos) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nIngrese el número de apartamento (1 a 6): ");
        int apartamento = scanner.nextInt();

        // Validación del apartamento con while
        while (apartamento < 1 || apartamento > 6) {
            System.out.print("Número inválido. Ingrese un apartamento de 1 a 6: ");
            apartamento = scanner.nextInt();
        }

        System.out.print("Ingrese el consumo en m³: ");
        double consumo = scanner.nextDouble();

        // Validación del consumo con while (debe ser mayor que 0)
        while (consumo <= 0) {
            System.out.print("El consumo debe ser mayor que 0. Ingrese nuevamente: ");
            consumo = scanner.nextDouble();
        }

        // Se guarda en el arreglo usando la posición (apartamento - 1)
        consumos[apartamento - 1] = consumo;
        System.out.println("Consumo registrado exitosamente.");
    }

    // 3. Consultar si un apartamento tiene consumo guardado
    public static void consultarFactura(double[] consumos) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nIngrese el número de apartamento a consultar (1 a 6): ");
        int apartamento = scanner.nextInt();

        // Validación del apartamento con while
        while (apartamento < 1 || apartamento > 6) {
            System.out.print("Número inválido. Ingrese un apartamento de 1 a 6: ");
            apartamento = scanner.nextInt();
        }

        double consumo = consumos[apartamento - 1];

        if (consumo == 0) {
            System.out.println("El apartamento " + apartamento + " no tiene consumo registrado.");
        } else {
            imprimirFactura(apartamento, consumo);
        }
    }

    // 4. Calcular y mostrar los valores de la factura
    public static void imprimirFactura(int apartamento, double consumo) {
        double cargoFijo = 12000;
        double tarifa;

        // Determinación de tarifa por rangos
        if (consumo <= 10) {
            tarifa = 3000;
        } else if (consumo <= 20) {
            tarifa = 4500;
        } else {
            tarifa = 6000;
        }

        double valorConsumo = consumo * tarifa;
        double subtotal = cargoFijo + valorConsumo;
        double recargo = 0;

        // Recargo si supera los 25 m³
        if (consumo > 25) {
            recargo = subtotal * 0.10;
        }

        double totalAPagar = subtotal + recargo;

        System.out.println("\n--- FACTURA DE AGUA ---");
        System.out.println("Apartamento: " + apartamento);
        System.out.println("Consumo registrado: " + consumo + " m³");
        System.out.println("Tarifa aplicada: $" + tarifa + " por m³");
        System.out.println("Valor del consumo: $" + valorConsumo);
        System.out.println("Cargo fijo: $" + cargoFijo);
        System.out.println("Recargo (10%): $" + recargo);
        System.out.println("Total a pagar: $" + totalAPagar);

        if (consumo > 25) {
            System.out.println("ALERTA: consumo excesivo");
        }
    }

    // 5. Generar informe general con estadísticas
    public static void generarReporte(double[] consumos) {
        double sumaConsumos = 0;
        int registrados = 0;
        int sinRegistro = 0;
        int excesivos = 0;
        double mayorConsumo = -1;
        int aptoMayorConsumo = 0;

        // Recorrido del arreglo para calcular estadísticas
        for (int i = 0; i < consumos.length; i++) {
            if (consumos[i] == 0) {
                sinRegistro++;
            } else {
                registrados++;
                sumaConsumos += consumos[i];

                if (consumos[i] > 25) {
                    excesivos++;
                }

                if (consumos[i] > mayorConsumo) {
                    mayorConsumo = consumos[i];
                    aptoMayorConsumo = i + 1;
                }
            }
        }

        if (registrados == 0) {
            System.out.println("\nAún no hay consumos registrados.");
        } else {
            double promedio = sumaConsumos / registrados;

            System.out.println("\n--- REPORTE GENERAL DEL CONJUNTO ---");
            System.out.println("Consumo total del conjunto: " + sumaConsumos + " m³");
            System.out.println("Consumo promedio (registrados): " + promedio + " m³");
            System.out.println("Apartamento con mayor consumo: Apt " + aptoMayorConsumo + " (" + mayorConsumo + " m³)");
            System.out.println("Cantidad de apartamentos sin registro: " + sinRegistro);
            System.out.println("Cantidad de apartamentos con consumo excesivo: " + excesivos);
        }
    }
}