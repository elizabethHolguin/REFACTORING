/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refactoring;

import java.util.HashMap;

/**
 *
 * @author josse
 */
public class Refactoring {

    /**
     * @param args the command line arguments
     */
    public static float main(String[] args) {
       
        // TODO code application logic here
        
        
        //PRIMITIVE OBSESSION 
        //Before Refactoring
         HashMap<String, String> mapa= new HashMap<>();
          mapa.put("0952201093", "jeholgui@hotmail.com");
          mapa.put("0952201094", "odalug@hotmail.com");
          mapa.put("0952201095", "viczad@hotmail.com");
          mapa.put("0952201096", "jazgus@hotmail.com");
          
          for(String i: mapa.keySet()){
              System.out.println(mapa.get(i));
          }
          
        
        return 0;
          
       
 

    }
    //After Refactoring
        public class Datos{
            String cedula;
            String correo;

        public Datos(String cedula, String correo) {
            this.cedula = cedula;
            this.correo = correo;
        }
            
            
        }
        
        
        
       //FEATURE ENVY  
       //Antes de hacer refactoring
      class OtherItem{
        float getPrecioTotal(Item it) {
                    float precio = it.getPrecio() + it.getIva();
                    if (it.estaEnVenta())
                        precio = precio - it.getDescuentoVenta() * precio;
                    return precio;
                }
        }
      
      
      class Item {

        float getPrecio() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        float getIva() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        boolean estaEnVenta() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        float getDescuentoVenta() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
     
    //Después de hacer refactoring
         float getPrecioTotal() {
                    float precio = getPrecio() + getIva();
                    if (estaEnVenta())
                        precio = precio - getDescuentoVenta() * precio;
                    return precio;
                }


    }
     
      
      //SWITCH STATEMENT 
      //Antes de hacer refactoring 
      enum Profesiones{arquitecto, secretaria, docente, doctor}
      public class ProfesionesPrincipales{
          Profesiones p;
          
          public void ImprimirLabor(){
              switch(this.p){
                  case arquitecto:
                      System.out.println("Diseñar planos de casas");
                      break;
                  case secretaria:
                      System.out.println("Redactar informes");
                      break;
                  case docente:
                      System.out.println("Impartir clases");
                      break;
                  case doctor:
                      System.out.println("Curar pacientes");
                      break;
              }
          }
          class Profesion{
              void ImprimirLabor(){
                  
              }
          }
        //Después del refactoring
         class Arquitecto extends Profesion{
             @Override
             void ImprimirLabor(){
                 System.out.println("Yo laboro diseñando casas");
                 
             }
         }
         class Secretaria extends Profesion{
             @Override
             void ImprimirLabor(){
                 System.out.println("Yo laboro redactando informes");
                 
             }
         }
         class Docente extends Profesion{
             @Override
             void ImprimirLabor(){
                 System.out.println("Yo laboro impartiendo clases");
                 
             }
         }
         class Doctor extends Profesion{
             @Override
             void ImprimirLabor(){
                 System.out.println("Yo laboro curando pacientes");
                 
             }
         }
      }
      
      //DATA CLUMPS
      //Antes de refactoring
      public class Figura{
          //suponiendo que estos atributos y llamada al método medidas va dentro del main 
          double diametro=4.0;
          double radio=2.0;
//          medidas(diametro,radio);
//          
          public void medidas(double diametro, double radio){
              System.out.println("Las medidas del círculo son: Diametro:"+diametro+"radio:"+radio+" \n");
          }
      }
      
      //Después del refactoring
      public class Circulo{
          protected int diametro;
          protected int radio;

        public Circulo(int diametro, int radio) {
            this.diametro = diametro;
            this.radio = radio;
        }
          
         @Override
         public String toString(){
             return "Sus medidas son: Diametro:"+diametro+"radio:"+radio+" \n";
         }
          
      }
      
      
   
      
      //SHOTGUN SURGERY 
      //Antes de refactoring
      public class Cuenta {
      
       private String tipo;
       private String numeroCuenta;
       private int cantidad;
      
       public Cuenta(String tipo,String numeroCuenta,int cantidad)
       {
           
              this.tipo=tipo;
              this.numeroCuenta=numeroCuenta;
              this.cantidad=cantidad;
       }
      
      
       public void debito(int debito) throws Exception
       {
              if(cantidad <= 1500)
              {
                     throw new Exception("El saldo mínimo debe ser superior a 1500");
              }
             
              cantidad = cantidad-debito;
              System.out.println("La cantidad actual es" + cantidad);
             
       }
      
       public void transferir(Cuenta from,Cuenta to,int cuentaCredito) throws Exception
       {
              if(from.cantidad<= 1500)
              {
                     throw new Exception("El saldo mínimo debe ser superior a 1500");
              }
             
              to.cantidad = cantidad+cuentaCredito;
             
       }
      
       public void enviarMensajeAdvertencia()
       {
              if(cantidad <= 1500)
              {
                     System.out.println("El saldo mínimo debe ser superior a 1500");
              }
       }
       // En el main de esta clase, se puede colocar esto
//        Cuenta c = new Cuenta ("Personal","AC1234",1000);
//        acc.debit(500);
//        acc.sendWarningMessage();
      
       
       //Después del Refactoring
       
       public class CuentaRefactoring {
      
       private String tipo;
       private String numeroCuenta;
       private int cantidad;
      
       public CuentaRefactoring(String tipo,String numeroCuenta,int cantidad)
       {
           
              this.tipo=tipo;
              this.numeroCuenta=numeroCuenta;
              this.cantidad=cantidad;
       }
       
       private boolean isAccountUnderflow()
       {
             return cantidad<=1500;
       }
       public void debito(int debito) throws Exception
       {
              if(isAccountUnderflow())
              {
                     throw new Exception("El saldo mínimo debe ser superior a 1500");
              }
              cantidad = cantidad-debito;
              System.out.println("La cantidad actual es" + cantidad);
       }
       public void transferir(CuentaRefactoring from,CuentaRefactoring to,int cantidadCredito) throws Exception
       {
              if(isAccountUnderflow())
              {
                     throw new Exception("Mininum balance shuold be over 500");
              }
              to.cantidad = cantidad+cantidadCredito;
       }
       public void enviarMensajeAdvertencia()
       {
              if(isAccountUnderflow())
              {
                     System.out.println("El saldo mínimo debe ser superior a 1500");
              }
       }
    }
      

    }
      
}
