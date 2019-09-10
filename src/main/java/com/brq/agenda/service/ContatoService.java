package com.brq.agenda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.brq.agenda.models.Contato;
import com.brq.agenda.repositories.ContatoRepository;

@Service
public class ContatoService {
	@Autowired
	private ContatoRepository repository;
	private String msgErro = "Ocorreu um erro Interno, por favor, tente novamente!";

	public List<Contato> findAll() throws ApplicationContextException {
		try {
			return repository.findAll();
		} catch (Exception e) {
			throw new ApplicationContextException(this.msgErro);
		}
	}

	public List<Contato> findSearchAll(String search) throws ApplicationContextException {
		try {
			return repository.findBySearchAll(search);
		} catch (Exception e) {
			throw new ApplicationContextException(this.msgErro);
		}
	}

	public Contato findOne(Long id) throws ApplicationContextException {
		try {
			Optional<Contato> contato = repository.findById(id);
			if (!contato.isPresent())
				throw new ApplicationContextException("O Contato informado não foi localizado!");
			return contato.get();
		} catch (ApplicationContextException e) {
			throw new ApplicationContextException(e.getMessage());
		} catch (Exception e) {
			throw new ApplicationContextException(this.msgErro);
		}
	}

	public Contato save(Contato contato) throws ApplicationContextException {
		try {
			return repository.save(contato);
		} catch (Exception e) {
			throw new ApplicationContextException(this.msgErro);
		}
	}

	public void delete(Long id) throws ApplicationContextException {
		try {
			Contato contato = this.findOne(id);
			repository.delete(contato);
		} catch (ApplicationContextException e) {
			throw new ApplicationContextException(e.getMessage());
		} catch (Exception e) {
			throw new ApplicationContextException(this.msgErro);
		}
	}
}
