
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class ProbC2CV_Parentesis
{
	// ------------------------------------------------------
	// Path del directorio donde estan los archivos de prueba
	String camino;
	
	// Arreglo que contiene los archivos de prueba
	File[] listaArchivosPrueba;
	
	// El contenido de un archivo de prueba
	BufferedReader buffer;
	
	// Renglon actual del archivo de prueba
	String renglon;
	// ------------------------------------------------------
	
	// Metodo Constructor
	public ProbC2CV_Parentesis()
	{
		camino = "./";
		listaArchivosPrueba = null;
		buffer = null;
		renglon = null;
	}
	
	// ------------------------------------------------------
	// Los metodos que manejan de archivos
	public void cargarArchivos()
	{
		listaArchivosPrueba = new File(camino).listFiles();		
	}
	
	public void cargarArchivoPrueba(int i)
	{
		try
		{
			buffer = new BufferedReader(new FileReader( listaArchivosPrueba[i] ) );
		}
		catch( IOException e )
        	{
        		System.out.println("Problemas con el archivo: " + listaArchivosPrueba[i].getName() + ".  El error es: " + e);
 		}
	}
	
	public boolean buscarArchivo(String nombreArch)
	{
		for (int i = 0; i < listaArchivosPrueba.length; i ++)
		{
			if ( listaArchivosPrueba[i].getName().equals(nombreArch) )
				return true;
		}
		return false;
	}
	
	public void liberarRecursos()
	{
		try
		{
			buffer.close();
			buffer = null;
			renglon = null;
		}
		catch( IOException e )
		{
        		System.out.println("Problemas al liberar recursos.  El error es: " + e);
        	}
	}
	// ------------------------------------------------------
	
	// Se asume que al menos hay un archivo para hacer pruebas en el disco en la ubicacion explorada
	// Los archivos de prueba estan correctamente bien escritos y sin errores.  Tienen aunque sea un caracter
	// Hay espacio y permisos en disco para escribir los archivos de solucion
	public void ejecutarSolucion()
	{
		// Repite el proceso completo para cada archivo encontrado dentro de la carpeta
		for (int i = 0; i < listaArchivosPrueba.length; i ++)
		{
			// Verifica que el archivo sea de prueba por el nombre.  Las ultimas 5 letras son Prue#
			if ( listaArchivosPrueba[i].getName().substring(listaArchivosPrueba[i].getName().length()-3).equals(".in"))
			{
				// Abre el archivo de la prueba
				cargarArchivoPrueba(i);
				// Llama el metodo que soluciona el problema
				solucion();
				// Cierra los archivos
				liberarRecursos();
			}
			
		} // Fin del ciclo que recorre los archivos a probar
		
		camino = null;
		listaArchivosPrueba = null;
	}
	
	// *******************************************************************************************************
	// TODO.  El equipo debe escribir su propuesta de solucion dentro del limite demarcado por los asteriscos.
	// Si desean pueden escribirla toda dentro del metodo solucion() o si lo consideran necesario, pueden 
	// crear los metodos que le hagan falta
	// Para ilustrar el manejo de los archivos se ha programado un ejemplo sencillo que lee la primera linea,
	// saca una copia en una cadena y la imprime
	public void solucion()
	{		
            try{
                    // Solo hay un renglon en ela archivo, el que contiene la frase a procesar
                    renglon = buffer.readLine();
                    //()
                    
                    if ( renglon != null && !renglon.isEmpty() && evaluaPalabra(renglon) ) 
                    {
                        System.out.println("SI");
                    }else{
                        System.out.println("NO");
                    }
	}
        catch( Exception e )
        {
            System.out.println("NO");
        }   	
	}
	
	// TODO
	// Zona para metodos adicionales
        
        private Stack<Character> arrayLetras = new Stack<Character>();
        
        private boolean evaluaPalabra(String cadena) {
            char[] arrayAuxLetras = cadena.toCharArray();
            int i;
            for (i = 0; i < arrayAuxLetras.length; i++) {
              if (arrayAuxLetras[i] == '(') {
                arrayLetras.push(arrayAuxLetras[i]);
              }else if (arrayAuxLetras[i] == ')') {
                if (!arrayLetras.empty() && arrayLetras.peek() != ')') {
                  arrayLetras.pop();
                }else{
                  arrayLetras.push(arrayAuxLetras[i]);
                }
              }else if (arrayAuxLetras[i] == '[') {
                arrayLetras.push(arrayAuxLetras[i]);
              }else if (arrayAuxLetras[i] == ']') {
                if (!arrayLetras.empty() && arrayLetras.peek() != ']') {
                  arrayLetras.pop();
                }else{
                  arrayLetras.push(arrayAuxLetras[i]);
                }
              }else if (arrayAuxLetras[i] == '{') {
                arrayLetras.push(arrayAuxLetras[i]);
              }else if (arrayAuxLetras[i] == '}') {
                if (!arrayLetras.empty() && arrayLetras.peek() != '}') {
                  arrayLetras.pop();
                }else{
                  arrayLetras.push(arrayAuxLetras[i]);
                }
              }
            }
          return arrayLetras.empty();
        }
        
	// FIN DE LA SECCION DE EDICION DE LA SOLUCION
	// *******************************************************************************************************   
        
                 
	// Metodo main
	public static void main(String[] args) 
	{		
		// Arranca el programa
		ProbC2CV_Parentesis solucion = new ProbC2CV_Parentesis();
		
		// Recorre el directorio en busca de los archivos de prueba
		solucion.cargarArchivos();
		
		// Ejecuta la solucion
		solucion.ejecutarSolucion();
	}

}
