package aplicacao;

import java.util.Date;

import model.entidades.Departamento;
import model.entidades.Vendedores;

public class Programa {

	public static void main(String[] args) {
		Departamento obj= new Departamento(1,"Livros");
		System.out.println(obj);
		
		Vendedores vendedor= new Vendedores(21, "Bob", "bob@hotmail.com",new Date(),3000.0,obj);
        System.out.println(vendedor);
	}

}
