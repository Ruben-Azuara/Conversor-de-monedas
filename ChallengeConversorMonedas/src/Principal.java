
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    private final String API_KEY = "b36afa5bb51aea1154f86f6b";
    private final String URL_BASE = "https://v6.exchangerate-api.com/v6/";
    private final String URL_COMP = "/pair/";
    private Scanner teclado = new Scanner(System.in);
    private ConsultaApi consultaApi = new ConsultaApi();
    private String cantidad;

    public void muestraMenu() {
        var opcion = -1;
        while (opcion != 9) {
            var menu = """
                    
                    ¡Bienvenido al conversor de monedas!
                    
                    ***************************************************************
                    
                    1 - Dolar >> Peso Argentino 
                    2 - Peso Argentino >> Dolar 
                    3 - Dolar >> Real Brasileño
                    4 - Real Brasileño >> Dolar
                    5 - Dolar >> Peso Colombiano
                    6 - Peso Colombiano >> Dolar
                    7 - Dolar >> Peso Mexicano
                    8 - Peso Mexicano >> Dolar     
                    9 - Salir
                                        
                    Elija una opcion valida:
                    ***************************************************************                                     
                    """;
            System.out.println(menu);

            try {
                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion) {
                    case 1:
                        cantidad = preguntaCantidad();
                        convertirMoneda("USD/ARS/", cantidad);
                        break;
                    case 2:
                        cantidad = preguntaCantidad();
                        convertirMoneda("ARS/USD/", cantidad);
                        break;
                    case 3:
                        cantidad = preguntaCantidad();
                        convertirMoneda("USD/BRL/", cantidad);
                        break;
                    case 4:
                        cantidad = preguntaCantidad();
                        convertirMoneda("BRL/USD/", cantidad);
                        break;
                    case 5:
                        cantidad = preguntaCantidad();
                        convertirMoneda("USD/COP/", cantidad);
                        break;
                    case 6:
                        cantidad = preguntaCantidad();
                        convertirMoneda("COP/USD/", cantidad);
                        break;
                    case 7:
                        cantidad = preguntaCantidad();
                        convertirMoneda("USD/MXN/", cantidad);
                        break;
                    case 8:
                        cantidad = preguntaCantidad();
                        convertirMoneda("MXN/USD/", cantidad);
                        break;
                    case 9:
                        System.out.println("Cerrando la aplicación...");
                        break;
                    default:
                        System.out.println("Opcion invalida");
                }
            } catch (InputMismatchException e){
                System.out.println("Solo se permiten opciones numericas que se muestran en el menu");
                teclado.next();
            }

        }
    }

    private String preguntaCantidad(){
        System.out.println("Ingrese el valor que deseas convertir:");
        return teclado.nextLine();
    }

    private void convertirMoneda(String moneda, String cantidad){
        RespuestaConversor respuesta = consultaApi.convertirMoneda(URL_BASE + API_KEY + URL_COMP + moneda + cantidad);
        String mensaje = "El valor de " + cantidad + " " + respuesta.base_code() + " corresponde al valor final de >> "
                + respuesta.conversion_result() + " " + respuesta.target_code();
        System.out.println(mensaje + '\n');
    }


}
