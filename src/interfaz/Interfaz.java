package interfaz;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import dominio.*;
import java.lang.Exception;
import java.util.Scanner;

public class Interfaz {

    private ArrayList<Profesor> profesores;
    private Scanner sc = new Scanner(System.in);
    boolean salir = true;

    
    public Interfaz()
    {
        leer();
    }

    public void mostrarMenu()
    {
        System.out.println("1) Añadir profesor titular");
        System.out.println("2) Añadir Catedratico");
        System.out.println("3) Añadir Merito");
        System.out.println("4) Mostrar profesores");
        System.out.println("5) Salir");
        int opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) 
        {
            case 1:
                annadirProfesorTitular();
                mostrarMenu();
                break;
            case 2:
                annadirCatedratico();
                mostrarMenu();
            case 3:
                annadirMerito();
                mostrarMenu();
            case 4:
                listarProfesores();
                mostrarMenu();
            case 5:
                salir = false;
            default:
                System.out.println("Opcion no valida");
                mostrarMenu();
                break;
        }

    }

    private void leer()
    {
        ObjectInputStream obj;
        File file = new File("meritos.txt");
        try
        {
            obj = new ObjectInputStream(new FileInputStream(file));
            try
            {
                profesores=(ArrayList<Profesor>) obj.readObject();
            }
            catch(Exception e)
            {
                System.out.println("Error al leer el archivo");
                profesores = new ArrayList<>();
            }
        }
        catch(Exception e)
        {
            profesores = new ArrayList<>();
        }
    }

    private void grabar()
    {
        ObjectOutputStream obj;
        File file = new File("meritos.txt");
            try
            {
                obj = new ObjectOutputStream(new FileOutputStream(file));
                obj.writeObject(profesores);
                obj.close();
                System.out.println("Guardado");
            }
            catch(Exception e)
            {
                System.out.println("Error al guardar");
                System.out.println(e);
            }
    }
    
    private void annadirProfesorTitular()
    {
        System.out.print("Introduzca el nombre del profesor: ");
        String nombre = sc.nextLine();
        profesores.add(new ProfesorTitular(nombre));
        
    }

    private void annadirCatedratico()
    {
        System.out.print("Introduzca el nombre del catedratico: ");
        String nombre = sc.nextLine();
        profesores.add(new Catedratico(nombre));       
    }

    private void annadirMerito()
    {
        System.out.print("Introduce el nombre del profesor: ");
        String nombre = sc.nextLine();
        int n = profesores.indexOf(new ProfesorTitular(nombre));
        if(n==-1)
        {
            System.out.println("Profesor no encontrado");
        }
        else
        {
            System.out.print("Titulo del merito: ");
            String titulo = sc.nextLine();
            System.out.println("Escoja el tipo de mérito: ");
            System.out.println("1) Articulo");
            System.out.println("2) Proyecto");
            int opcion = sc.nextInt();
            sc.nextLine();
            if(opcion==1)
            {
                System.out.print("Introduzca el impacto del artículo: ");
                double impacto = sc.nextDouble();
                sc.nextLine();
                profesores.get(n).annadirMerito(new Articulo(titulo, impacto));
            }
            else if(opcion==2)
            {
                System.out.print("Introduzca la financiación del proyecto: ");
                double financiacion = sc.nextDouble();
                sc.nextLine();
                profesores.get(n).annadirMerito(new Proyecto(titulo, financiacion));
            }
            else
            {
                System.out.println("Opcion no disponible");
            }
        }        
    }

    public void listarProfesores()
    {
        for(Profesor n : profesores)
        {
            System.out.println(n);
        }
    }
    
    

    
}