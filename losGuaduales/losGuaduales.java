import java.util.Scanner;import java.util.Scanner;

public class ControlAguaGuaduales {

    public static void main(String[] args) {
        double[] consumos = new double[6];
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            mostrarMenu();
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    registrarConsumo(consumos, scanner);
                    break;
                case 2:
                    consultarFactura(consumos, scanner);
                    break;
                case 3:
                    generarReporte(consumos);
                    break;
                case 4:
                    System.out.println("¡Gracias por usar el sistema!");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }
            System.out.println();
        } while (opcion != 4);

        scanner.close();
    }

    public static void mostrarMenu() {
        System.out.println("=== MENÚ GUADUALES ===");
        System.out.println("1. Registrar consumo de un apartamento");
        System.out.println("2. Consultar factura de un apartamento");
        System.out.println("3. Ver reporte general del conjunto");
        System.out.println("4. Salir");
    }

    public static void registrarConsumo(double[] consumos, Scanner scanner) {
        int apartamento = 0;
        while (apartamento < 1 || apartamento > 6) {
            System.out.print("Ingrese el número de apartamento (1-6): ");
            apartamento = scanner.nextInt();
            if (apartamento < 1 || apartamento > 6) {
                System.out.println("Número de apartamento inválido. Intente de nuevo.");
            }
        }

        double consumo = 0;
        while (consumo <= 0) {
            System.out.print("Ingrese el consumo del apartamento (metros cúbicos): ");
            consumo = scanner.nextDouble();
            if (consumo <= 0) {
                System.out.println("El consumo debe ser mayor a 0, ingrese un valor valido.");
            }
        }

        if (consumo <= 10) {
            System.out.println("El valor x metro cúbico es de 3000");
        } else if (consumo > 10 && consumo <= 20) {
            System.out.println("El valor x metro cúbico es de 4500");
        } else if (consumo > 20) {
            System.out.println("El valor x metro cúbico es de 6000");
        }

        if (consumo > 25) {
            System.out.println("Alerta: consumo excesivo");
        }

        consumos[apartamento - 1] = consumo;
        System.out.println("Consumo registrado correctamente.");
    }

    public static void consultarFactura(double[] consumos, Scanner scanner) {
        int apartamento = 0;
        while (apartamento < 1 || apartamento > 6) {
            System.out.print("Ingrese el número de apartamento (1-6): ");
            apartamento = scanner.nextInt();
            if (apartamento < 1 || apartamento > 6) {
                System.out.println("Número de apartamento inválido. Intente de nuevo.");
            }
        }

        double consumo = consumos[apartamento - 1];
        if (consumo <= 0) {
            System.out.println("El apartamento no registra consumo");
        } else {
            imprimirFactura(apartamento, consumo);
        }
    }

    public static void imprimirFactura(int apartamento, double consumo) {
        double tarifa = 0;
        if (consumo <= 10) {
            tarifa = 3000;
        } else if (consumo <= 20) {
            tarifa = 4500;
        } else {
            tarifa = 6000;
        }

        double valorPorConsumo = consumo * tarifa;
        double cargoFijo = 12000;
        double subtotal = cargoFijo + valorPorConsumo;
        double recargo = 0;

        if (consumo > 25) {
            recargo = subtotal * 0.10;
        }

        double valorTotal = subtotal + recargo;

        System.out.println("Su consumo es " + consumo + " m³");
        System.out.println("El valor por consumo es " + valorPorConsumo);
        System.out.println("Cargo fijo: " + cargoFijo);

        if (consumo > 25) {
            System.out.println("Se aplicó un recargo del 10%");
            System.out.println("ALERTA: consumo excesivo");
        }

        System.out.println("El valor total a pagar es " + valorTotal);
    }

    public static void generarReporte(double[] consumos) {
        double sumaTotal = 0;
        int registrados = 0;
        double mayorConsumo = 0;
        int aptoMayor = 0;
        int sinRegistro = 0;
        int excesivos = 0;

        for (int i = 0; i < consumos.length; i++) {
            if (consumos[i] > 0) {
                sumaTotal += consumos[i];
                registrados++;

                if (consumos[i] > mayorConsumo) {
                    mayorConsumo = consumos[i];
                    aptoMayor = i + 1;
                }

                if (consumos[i] > 25) {
                    excesivos++;
                }
            } else {
                sinRegistro++;
            }
        }

        if (registrados == 0) {
            System.out.println("Aún no hay consumos registrados.");
        } else {
            double promedio = sumaTotal / registrados;
            System.out.println("===== REPORTE GENERAL =====");
            System.out.println("Consumo total del conjunto: " + sumaTotal + " m³");
            System.out.println("Consumo promedio: " + promedio + " m³");
            System.out.println("Apartamento con mayor consumo: Apartamento " + aptoMayor + " (" + mayorConsumo + " m³)");
            System.out.println("Cantidad de apartamentos sin registro: " + sinRegistro);
            System.out.println("Cantidad de apartamentos con consumo excesivo: " + excesivos);
        }
    }
}