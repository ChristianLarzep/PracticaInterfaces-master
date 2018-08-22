/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import objetosUtileria.Estudiante;

/**
 *
 * @author rafaelm
 */
public class Consultor {
    
    List<Estudiante> estudiantes;
    onConsultorInterface laSecre;
    
    public Consultor(onConsultorInterface secre){
        this.laSecre=secre;
        estudiantes=new ArrayList<Estudiante>();
    }
    
    
    public void agregarEstudianteALista(Estudiante estudiante){
        estudiantes.add(estudiante);
        laSecre.onCreateEstudiante(); 
    }
    
    public void buscar(String nombre){
        for(Estudiante estudianteARevisar:estudiantes){
            System.out.println("Estudiante iterado "+estudianteARevisar.getNombre());
            if(nombre.equals(estudianteARevisar.getNombre())){
                laSecre.onEstudianteEncontrado(estudianteARevisar);
                break;
            }
        }
    }
    
    public void muestra(){
        for(Estudiante estudianteARevisar:estudiantes){
            System.out.println("Estudiante iterado "+estudianteARevisar.getNombre()+" "+estudianteARevisar.getPaterno()+" "+estudianteARevisar.getMaterno());
            
        }
    }
    
    public void borrar(String nombre){
         for(Estudiante estudianteARevisar:estudiantes){
            
            if(nombre.equals(estudianteARevisar.getNombre())){
                estudiantes.remove(estudianteARevisar);
                laSecre.onEstudianteBorrado(nombre);
                break;
            }
        }
    }
    
    public void modificar(String nombre, String option){
        
         int opt = Integer.parseInt(option);
         int index = 0;
         boolean founded = false;
         Scanner modified = new Scanner(System.in);
         
         for (int i = 0; i < estudiantes.size(); i++) {
            if(nombre.equals(estudiantes.get(i).getNombre())){
                index = i;
                founded = true;
                switch(opt){
                    case 1:
                        System.out.println("Nuevo nombre: ");
                        String newName = modified.next();
                        estudiantes.get(index).setNombre(newName);
                        break;
                    case 2:
                        System.out.println("Nuevo paterno: ");
                        String newPaterno= modified.next();
                        estudiantes.get(index).setPaterno(newPaterno);
                        break;
                    case 3:
                        System.out.println("Nuevo materno: ");
                        String newMaterno = modified.next();
                        estudiantes.get(index).setMaterno(newMaterno);
                        break;
                    default:
                        break;
                }
                
                break;
            }
            
        }
         
         laSecre.onEstudianteModificado(option, founded);
    }
    
    public interface onConsultorInterface{
        public void onConsulta();
        public void onError();
        public void onCreateEstudiante();
        public void onEstudianteEncontrado(Estudiante estudiante);
        public void onEstudianteBorrado(String data);
         public void onEstudianteModificado(String data, boolean founded);
    }
    
}
