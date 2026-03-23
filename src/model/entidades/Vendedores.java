package model.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Vendedores   implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Departamento departamento;
	
	private Integer id;
	private String nome;
	private String email;
	private Date aniversario;
	private Double baseSalarial;

	public Vendedores() {
		
	}

	public Vendedores(Integer id, String nome, String email, Date aniversario, Double baseSalarial,
			Departamento departamento) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.aniversario = aniversario;
		this.baseSalarial = baseSalarial;
		this.departamento = departamento;
	}



	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vendedores other = (Vendedores) obj;
		return Objects.equals(id, other.id);
	}



	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getAniversario() {
		return aniversario;
	}

	public void setAniversario(Date aniversario) {
		this.aniversario = aniversario;
	}

	public Double getBaseSalarial() {
		return baseSalarial;
	}

	public void setBaseSalarial(Double baseSalarial) {
		this.baseSalarial = baseSalarial;
	}



	@Override
	public String toString() {
		return "Vendedores [id=" + id + ", nome=" + nome + ", email=" + email + ", aniversario=" + aniversario
				+ ", baseSalarial=" + baseSalarial + ", departamento=" + departamento + "]";
	}


	
	
}
