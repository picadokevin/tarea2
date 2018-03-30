/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotec;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import org.apache.commons.codec.cli.Digest;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author UsuarioPC
 */
//NOTA:El administrador es kevin
//Contraseña:hola;
//Tipo de usuario:Bibliotecario
public class logica {
    String nombre;
    String tipoUsuario;
    String contraseñaEncriptada;
     String nombreUsuario;
    String nombreLibro;
    String contraseña;
    
   public void ingresarUsuario(){
    nombreUsuario=JOptionPane.showInputDialog(null,"ingrese su usuario");
      contraseña=JOptionPane.showInputDialog(null,"ingrese Contraseña");
     String tipoUsuario=JOptionPane.showInputDialog(null,"ingrese tipo de usuario(Bibliotecario,Usuario,Autor)");
        if(revisarEstadoUsuario(arregloUsuario())==true){
       switch (tipoUsuario.toLowerCase()) {

 

        case "bibliotecario":

       bibliotecarioFunciones();

        break;

 

        case "autor":
           
            lista(arreglolibros());
        break;

        case "usuario":
            
            escogerLibro();
            break;

        default:

        

        break;

 

 }
 }else{
            JOptionPane.showMessageDialog(null,"Usuario no encontrado");
            ingresarUsuario();
        }
   } 
   public void bibliotecarioFunciones(){
       int accion=Integer.parseInt(JOptionPane.showInputDialog(null, "Si desea agregar un usuario digite 1\nsi desea modificar un usuario digite 2\nSi desea agregar un libro digite 3\nsi desea modificar un libro digite 4\nSi desea salir digite 5"));
  
       switch (accion) {

 

       case 1 :

              agregarUsuarios();
              bibliotecarioFunciones();

        break;

 

        case 2:

       modificarUsuario();
       bibliotecarioFunciones();
        break;

        case 3:
            
             agregarLibros();
            break;
        case 4:
            modificarLibros();
            bibliotecarioFunciones();
             break;
             
        case 5:
            System.exit(5);    
                break;
        default:

        

        break;

 

 }

   
   
   
   
   }
   public boolean revisarEstadoUsuario(controlUsuarioBiblio e []){//revisa si la contraseña,usuario y el tipo de usuario(Bibliotecario,Usuario,Autor)son correctos .
        boolean estado=false;
        String contraseñaDesencriptar=DigestUtils.md5Hex(contraseña);
        for(int i=0;i<e.length;i++){
            if(contraseñaDesencriptar.equalsIgnoreCase(e[i].getContraseña())&&nombreUsuario.equalsIgnoreCase(e[i].getNombreCompleto())){
                estado=true;
            
                
              
                
               
            } 
     }
      return estado;  
    }
   public void escogerLibro(){
       String escogerLibro=JOptionPane.showInputDialog(null,"Digite el nombre del libro que desea como préstamo");
       ConstrutorLibrosObtenidos clo=new ConstrutorLibrosObtenidos(nombreUsuario, escogerLibro);
       
        PrintStream ps = getPrintStream("PrestamosLibros.txt");
                    
        ps.println(clo.getNombreUsuario() + ";" + clo.getNombreLibro());
        JOptionPane.showMessageDialog(null,"libro guardado con éxito");
   }
   public void agregarLibros(){
       String nombreLibro=JOptionPane.showInputDialog(null,"ingrese nombre del libro");
       String nombreAutor=JOptionPane.showInputDialog(null,"ingrese el nombre del autor");
       int identificacionAutor=Integer.parseInt(JOptionPane.showInputDialog(null,"ingrese el numero de identificacion del autor"));
       PrintStream ps = getPrintStream("Libros.txt");
        constructorLibros cl=new constructorLibros(nombreLibro, nombreAutor,identificacionAutor);
        ps.println(cl.getNombreLibro() + ";" + cl.getNombreAutor()+";"+cl.getIdentificacionAutor());
       
   }
   
   public void agregarUsuarios(){
      String Nombre=JOptionPane.showInputDialog(null,"ingrese nombre");
      String tipoUsuario=JOptionPane.showInputDialog(null,"ingrese tipo de usuario");
      int identificacion=Integer.parseInt(JOptionPane.showInputDialog(null,"ingrese identificacion"));
      String contraseña=JOptionPane.showInputDialog(null,"ingrese su contraseña");
      String contraseñaEncriptada=DigestUtils.md5Hex(contraseña);
      controlUsuarioBiblio cb=new controlUsuarioBiblio(tipoUsuario, Nombre,contraseñaEncriptada, identificacion);
       guardaUsuarios(cb);
   }
    public void guardarLibroUsuario(controlUsuarioBiblio e) {

        PrintStream ps = getPrintStream("Usuario.txt");
                    
        ps.println(e.getTipoUsuario() + ";" + e.getNombreCompleto() + ";" + e.getContraseña() + ";" + e.getIdentificacion());

    }
   public void guardaUsuarios(controlUsuarioBiblio e) {

        PriStream ps = getPrintStream("Usuario.txt");
                    
        ps.println(e.getTipoUsuario() + ";" + e.getNombreCompleto() + ";" + e.getContraseña() + ";" + e.getIdentificacion());

    }
   public void modificarUsuario(){
       int identificacion=Integer.parseInt(JOptionPane.showInputDialog(null,"ingrese la identificacion correctamete para cambiar sus otros datos"));
       nombre=JOptionPane.showInputDialog(null,"ingrese nombre");
       tipoUsuario=JOptionPane.showInputDialog(null,"ingrese tipo de usuario");
      String contraseña=JOptionPane.showInputDialog(null,"ingrese su contraseña");
       contraseñaEncriptada=DigestUtils.md5Hex(contraseña);
      int posicion=ActualizarUsuario(arregloUsuario(), identificacion);
       guardarUsuarioAtualizar(arregloUsuario(), posicion);
   }
     public void modificarLibros(){
       int identificacionAutor=Integer.parseInt(JOptionPane.showInputDialog(null,"ingrese la identificación del autor correctamete para cambiar sus otros datos"));
       nombreLibro=JOptionPane.showInputDialog(null,"ingrese nombre del libro");
       
      int posicion=actualizarLibros(arreglolibros(), identificacionAutor);
       guardarLibroAtualizar(arreglolibros(), posicion);
   }
   public int  ActualizarUsuario(controlUsuarioBiblio e[],int clienteBuscar){
         int encontrado=-1;
        for(int i=0;i<e.length;i++){
            if(e[i].getIdentificacion()==clienteBuscar){
                encontrado=i;
                
                
                break;
            }
        
        }
        
         return encontrado;
     }
    public int  actualizarLibros(constructorLibros e[],int identificacionAutor){
         int encontrado=-1;
        for(int i=0;i<e.length;i++){
            if(e[i].getIdentificacionAutor()==identificacionAutor){
                encontrado=i;
                
                
                break;
            }
        
        }
        
         return encontrado;
     }
    public void guardarLibroAtualizar(constructorLibros[] e, int posicion) {
       
        PrintStream ps = getPrintStream("Libros.txt",false);
        for (int i = 0; i < e.length; i++) {
            if (i==posicion) {
                ps.println(nombreLibro+ ";" + e[i].getNombreAutor()+";"+e[i].getIdentificacionAutor());
                JOptionPane.showMessageDialog(null, "el nombre del libro fue cambiado con exito");
            }
            else{
             ps.println(e[i].getNombreLibro() + ";" + e[i].getNombreAutor()+";"+e[i].getIdentificacionAutor());
               }

        }
    }
      public void guardarUsuarioAtualizar(controlUsuarioBiblio[] e, int posicion) {
       
        PrintStream ps = getPrintStream("Usuario.txt",false);
        for (int i = 0; i < e.length; i++) {
            if (i==posicion) {
                ps.println(tipoUsuario+";" + nombre + ";" + contraseñaEncriptada+ ";" + e[i].getIdentificacion());
            }
            else{
             ps.println(e[i].getTipoUsuario() + ";" + e[i].getNombreCompleto() + ";" + e[i].getContraseña()+ ";" + e[i].getIdentificacion());
               }

        }
    }
     public PrintStream getPrintStream(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        PrintStream ps = null;
        try {
            FileOutputStream fos = new FileOutputStream(archivo, true);
            ps = new PrintStream(fos);

        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "error");
            
        }
        return ps;
    }
      public controlUsuarioBiblio[] arregloUsuario() {
        controlUsuarioBiblio ElementosCliente[] = new controlUsuarioBiblio[ContadorTamaño()];
        int indice = 0;
        try {
            BufferedReader br = getBufferedReader("Usuario.txt");
            String registro = br.readLine();
            while (registro != null) {

                int identificacion = 0;
                String contraseña = "";
                String tipoUsuario="";
                int controlaTokens = 1;
                String nombre = "";

                StringTokenizer st = new StringTokenizer(registro, ";");

                while (st.hasMoreTokens()) {

                    if (controlaTokens == 1) {
                        tipoUsuario = st.nextToken();
                    } else if (controlaTokens == 2) {
                        nombre = st.nextToken();
                    } else if (controlaTokens == 3) {
                        contraseña=st.nextToken();
                    } else if (controlaTokens == 4) {
                        identificacion =Integer.parseInt(st.nextToken());
                    } else {
                        controlaTokens = st.countTokens();
                    }

                    controlaTokens++;
                }//Fin del While 2;

                controlUsuarioBiblio c = new controlUsuarioBiblio(tipoUsuario, nombre, contraseña, identificacion);
                ElementosCliente[indice] = c;
                indice++;
                registro = br.readLine();
            }//Fin del while 1

        }//Fin del try
        catch (FileNotFoundException fnfe) {
           
            JOptionPane.showMessageDialog(null, "Problemas con el archivo");
        }//Fin del catch 
        catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "Problemas con el archivo");
        }

        return ElementosCliente;
    }
       public constructorLibros[] arreglolibros() {
        constructorLibros ElementosCliente[] = new constructorLibros[ContadorTamañoLibros()];
        int indice = 0;
        try {
            BufferedReader br = getBufferedReader("Libros.txt");
            String registro = br.readLine();
            while (registro != null) {

                
                String nombreLibro= "";
                String nombreAutor="";
                int identificacionAutor=0;
               int controlaTokens = 1;

                StringTokenizer st = new StringTokenizer(registro, ";");

                while (st.hasMoreTokens()) {

                    if (controlaTokens == 1) {
                        nombreLibro = st.nextToken();
                    } else if (controlaTokens == 2) {
                        nombreAutor = st.nextToken();
                    }else if(controlaTokens==3){
                        identificacionAutor=Integer.parseInt(st.nextToken());
                    } else {
                        controlaTokens = st.countTokens();
                    }

                    controlaTokens++;
                }//Fin del While 2;

                constructorLibros c = new constructorLibros(nombreLibro, nombreAutor,identificacionAutor);
                ElementosCliente[indice] = c;
                indice++;
                registro = br.readLine();
            }//Fin del while 1

        }//Fin del try
        catch (FileNotFoundException fnfe) {
           
            JOptionPane.showMessageDialog(null, "Problemas con el archivo");
        }//Fin del catch 
        catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "Problemas con el archivo");
        }

        return ElementosCliente;
    }
      public BufferedReader getBufferedReader(String nombrearchivo) {
        File archivo = new File(nombrearchivo);
        BufferedReader br = null;
        try {
            FileInputStream fis = new FileInputStream(archivo);
            InputStreamReader isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);

        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "error");
        }
        return br;
    } 
      public int ContadorTamaño() {
        int contador = 0;
        //File archivo=new File("aleatorio.txt");
        try {
            BufferedReader br = getBufferedReader("Usuario.txt");
            String registroactual = br.readLine();
            while (registroactual != null) {
                contador++;
                registroactual = br.readLine();
            }
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "error");
        } catch (IOException IOE) {
            JOptionPane.showMessageDialog(null, "error");
        }
        return contador;
    }
        public int ContadorTamañoLibros() {
        int contador = 0;
        //File archivo=new File("aleatorio.txt");
        try {
            BufferedReader br = getBufferedReader("Libros.txt");
            String registroactual = br.readLine();
            while (registroactual != null) {
                contador++;
                registroactual = br.readLine();
            }
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "error");
        } catch (IOException IOE) {
            JOptionPane.showMessageDialog(null, "error");
        }
        return contador;
    }
       public PrintStream getPrintStream(String nombreArchivo, boolean mantieneRegistro) {
        File archivo = new File(nombreArchivo);
        PrintStream ps = null;
        try {
            FileOutputStream fos = new FileOutputStream(archivo, mantieneRegistro);
            ps = new PrintStream(fos);

        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "error");
        }
        return ps;
    }
//     public void lista(constructorLibros[] e){
//         String listado="lista de libros "+"\n";
//        for(int i=0;i<e.length;i++){
//            if(nombreUsuario.equalsIgnoreCase(e[i].getNombreAutor())){
//                
//            listado=listado+arreglolibros()[i].getNombreLibro()+"\n";
//                
//              
//                
//               
//            } 
//     }
//        
//        JOptionPane.showMessageDialog(null, listado);
//     }
}
