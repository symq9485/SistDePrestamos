/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistdeprestamos;
import java.io.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author symq9485
 */

class Solicitante
{
    int cedula;
    String nombre;
    String apellido1;
    String apellido2;
    String telf;
    String movil;
    static String dato;

    void registrar() throws IOException
    {
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));

        boolean x = true;

        while (x != false)
        {
            try
            {
            System.out.print("Ingrese la cedula del solicitante: ");
            cedula = Integer.parseInt(br.readLine());

            System.out.print("Ingrese el nombre del solicitante: ");
            nombre = br.readLine();

            System.out.print("Ingrese el primer apellido del solicitante: ");
            apellido1 = br.readLine();

            System.out.print("Ingrese el segundo apellido del solicitante: ");
            apellido2 = br.readLine();


            System.out.print("Ingrese el numero de telefono de habitacion del solicitante: ");
            telf = br.readLine();

            System.out.print("Ingrese numero de telefono movil del solicitante: ");
            movil = br.readLine();

            x=false;

            }

            catch(Exception e)
            {
                System.err.println("Se produjo un Error: " + e.getMessage() + ".Por favor vuelva a ingresar los datos");
            }
            finally
            {
                System.out.println("Proceso terminado satisfactoriamente.");
            }
        }
    }
    void capturar() throws IOException
    {
        dato = "\nNombre: "+ nombre +"\nApellidos: "+ apellido1 + " "
                + apellido2+ "\nCedula: "+cedula + "\nTelefono fijo: "+telf
                + "\nMovil: "+ movil+ "\n";
        System.out.println(dato);
    }
}

class Prestamo {

    int N_Prestamo;
    //int i;
    double Credito;
    double Valor_de_prestamo;
    double Temp;
    boolean siguiente;
    boolean posible;
    String seguir;
    String[] Fecha_pagos = new String[6];
    String reiniciar;

 void calendario() throws IOException
    {
        Calendar fecha_auto = GregorianCalendar.getInstance();
        Calendar fecha_limite = GregorianCalendar.getInstance();

        fecha_auto.add(Calendar.DAY_OF_MONTH,7);
        fecha_limite.set(Calendar.DAY_OF_MONTH,20);

        Date dDate = fecha_auto.getTime();
        Date sDate = fecha_limite.getTime();

        if (dDate.compareTo(sDate) < 0) // chequea si la fecha de autorizaciones mayor que fecha tope//
        {
            System.out.println("\nLas fecha de sus pagos y los montos son: ");
            for(int i = 0; i <= 5; i++)
            {

                fecha_auto.add(Calendar.MONTH, 1);
                Fecha_pagos[i] =fecha_auto.getTime().toString();
                System.out.println(Fecha_pagos[i]);


            }
            System.out.println("El monto de cada uno de sus pagos es : " + (Valor_de_prestamo/6) + " BsF");
        }

        else if (dDate.compareTo(sDate) >= 0)
        {
            System.out.println("\nLa fecha de aprobacion exede los primeros 20 dias del mes");
        }

    }
    void calcular()

    {

        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));

        boolean x = true;

        while(x!=false)
        {
            try
            {
                System.out.print("Ingrese el valor del prestamo a solicitar: ");
                    Valor_de_prestamo = Double.parseDouble(br.readLine());
                    siguiente = true;
                    Credito= 1000000.00d;
                    
                while ((siguiente == true)) //&& (Valor_de_prestamo < Credito ))
                {

                    Valor_de_prestamo = Valor_de_prestamo + Temp;

                    if (Valor_de_prestamo > Credito)
                    {
                        System.out.println("Usted se ha excedido el limite");
                        System.out.print("Desea reiniciar los prestamos: (y/n)");
                        reiniciar = br.readLine();
                        
                        if ("y".equals(reiniciar))
                        {
                            Valor_de_prestamo = 0;
                            System.out.println("Los prestamos se reiniciaron. \nIngrese sus prestamos nuevamente y recuerde que el limite es de 1.000.000,oo");
                        }
                        
                        else 
                        {
                            break;
                        }
                    }

                    N_Prestamo = N_Prestamo + 1;
                    System.out.print("Quiere hacer otro prestamo? s/n: ");

                    seguir = br.readLine();

                    if ("s".equals(seguir))
                    {
                      System.out.print("Ingrese el nuevo monto: ");
                      Temp = Double.parseDouble(br.readLine());
                      siguiente = true;

                    }

                    else if ("n".equals(seguir)){
                        siguiente = false;
                    }
                }
                if (Valor_de_prestamo > Credito)
                {
                    Valor_de_prestamo = 0;
                }
                x = false;
            }
            catch(Exception e)
            {
                System.err.println("Se produjo un Error: " + e.getMessage() + ".Por favor vuelva a ingresar los datos");
            }
            finally
            {
                System.out.println("\nProceso terminado satisfactoriamente.\n\nEl valor total del prestamo es: "+ Valor_de_prestamo);
            }
        }
    }

        void capturar() throws IOException
        {
            if (posible == true)
                {

                System.out.println("\nLas fecha de sus pagos y los montos del prestamo "+N_Prestamo+" son: ");
                for(int i = 0; i <= 5; i++)
                {
                    System.out.println(Fecha_pagos[i]);
                }
                System.out.println("El monto de cada uno de sus pagos es : " + (Valor_de_prestamo/6) + " BsF");
                }
           guardar();
        }

    void guardar() throws IOException{
        Solicitante solic = new Solicitante();
        String datos;
        datos = "Fechas: " + Arrays.toString(Fecha_pagos) +
                "\nMensualidad: " + (Valor_de_prestamo/6) + "\nNumero de prestamo: "
                + N_Prestamo+ "\nValor del prestamo: " + Valor_de_prestamo + "\n"
                + solic.dato;
        archivop arch = new archivop();
        arch.crear("prestamo.txt" , datos);


    }
}

class archivop {
   PrintWriter pf;
   FileReader fr;

   void crear(String nombre,String Datos) throws IOException{
       pf=new PrintWriter(new FileWriter(nombre,true));
       pf.println(Datos);
       pf.close();
       System.out.println("Archivo creado");
   }

   void leer(String nombre) throws IOException{
       fr=new FileReader(nombre);
       BufferedReader br=new BufferedReader(fr);
       String linea;
       while((linea=br.readLine())!=null){
            System.out.println(linea);

       }

    }
}

public class SistDePrestamos
{

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException
    {
        // TODO code application logic here
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
        String nuevo = "y";
        
            try
            {
                while ("y".equals(nuevo))
                {
                    Solicitante soli = new Solicitante();
                    Prestamo pres = new Prestamo();

                    soli.registrar();
                    soli.capturar();

                    pres.calcular();
                    pres.calendario();
                    pres.capturar();

                    archivop ar = new archivop();
                    ar.leer("prestamo.txt");

                    System.out.print("Desea ingresar un nuevo Solicitante de prestamo(y/n): ");
                    nuevo = br.readLine();
                    
                    
                    if ((!"y".equals(nuevo)) && (!"n".equals(nuevo)))
                    {
                        while ((!"y".equals(nuevo)) && (!"n".equals(nuevo)))
                        {
                            System.out.print("Ingrese una opcion valida(y/n)");
                            nuevo = br.readLine();
                        }
                    }
                }
                System.out.println("Gracias por usar el nuestro sistema.");
            }
            catch(Exception e)
                    {
                        System.out.print("Por favor ingrese la opcion nuevamente(y/n): ");
                    }        
    }
}