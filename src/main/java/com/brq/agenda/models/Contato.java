/**
 * 
 */
package com.brq.agenda.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

/**
 * @author marcosbatista
 *
 */
@Entity(name = "contato")
public class Contato implements Serializable {
	private static final long serialVersionUID = 7267083811369201752L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O campo 'Github' é obrigatório!")
	@Length(min = 5, max = 255, message = "O campo 'Github' deve conter de 5 a 255 caracteres!")
	private String github;

	@NotBlank(message = "O campo 'Nome' é obrigatório!")
	@Length(min = 3, max = 255, message = "O campo 'Nome' deve conter de 3 a 255 caracteres!")
	private String nome;

	@NotBlank(message = "O campo 'Sobrenome' é obrigatório!")
	@Length(min = 3, max = 255, message = "O campo 'Sobrenome' deve conter de 3 a 255 caracteres!")
	private String sobrenome;

	@NotBlank(message = "O campo 'E-mail' é obrigatório!")
	@Email(message = "O campo 'E-mail' informado não é válido!")
	@Length(min = 5, max = 100, message = "O campo 'E-mail' deve conter de 5 a 100 caracteres!")
	private String email;

	@Column(columnDefinition = "varchar(255) default 'https://image.flaticon.com/icons/png/128/149/149072.png'")
	private String avatar;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGithub() {
		return github;
	}

	public void setGithub(String github) {
		this.github = github;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
