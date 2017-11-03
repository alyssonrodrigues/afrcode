package com.afrcode.dominio;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.afrcode.dominio.usuario.Usuario;
import com.afrcode.dominio.usuario.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(propagation = Propagation.REQUIRED)
@Rollback
public class UsuarioRepositoryTest {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Test
	public void findByLoginIgnoreCase() {
		Usuario usuario = usuarioRepository.save(new Usuario("Alysson Felix Rodrigues", "alyssonfr"));
		long count = usuarioRepository.count();
		Assert.assertEquals("Usuário não salvo!", 1, count);
		Usuario outro = usuarioRepository.findByLoginIgnoreCase("ALYSSONFR");
		Assert.assertEquals("Usuário diferente do esperado recuperado!", usuario, outro);
	}

	@Test
	public void findByNomeContainingIgnoreCase() {
		Usuario usuario = usuarioRepository.save(new Usuario("Alysson Felix Rodrigues", "alyssonfr"));
		long count = usuarioRepository.count();
		Assert.assertEquals("Usuário não salvo!", 1, count);
		List<Usuario> outros = usuarioRepository.findByNomeContainingIgnoreCase("aLyssOn");
		Assert.assertEquals("Usuário não recuperado!", 1, outros.size());
		Assert.assertEquals("Usuário diferente do esperado recuperado!", usuario, outros.get(0));
	}

}
