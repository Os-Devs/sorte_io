package br.edu.ifpb.pweb2.sorte_io.controller;

/* Erro nos validations */
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.pweb2.sorte_io.model.Apostador;
import br.edu.ifpb.pweb2.sorte_io.model.Authority;
import br.edu.ifpb.pweb2.sorte_io.model.EnumRole;
import br.edu.ifpb.pweb2.sorte_io.model.User;
import br.edu.ifpb.pweb2.sorte_io.model.Authority.AuthorityId;
import br.edu.ifpb.pweb2.sorte_io.repository.ApostadoresRepository;
import br.edu.ifpb.pweb2.sorte_io.repository.AuthorityRepository;
import br.edu.ifpb.pweb2.sorte_io.repository.UserRepository;

@Controller
@RequestMapping("/apostadores")
public class ApostadorController {

	@Autowired
	ApostadoresRepository apostadoresRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	AuthorityRepository authorityRepository;

	@RequestMapping("/form")
	public ModelAndView getForm(Apostador apostador, ModelAndView model) {
		model.addObject("apostador", apostador);
		model.setViewName("apostador/formApostador");

		return model;
	}

	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(@Valid Apostador apostador, BindingResult validation, ModelAndView model) {
		if (validation.hasErrors()) {
			model.setViewName("apostador/formApostador");
			return model;
		}
		else {
			User user = this.createUser(apostador);
			Authority authority = this.createAuthority(user);

			apostador.setUser(user);

			apostadoresRepository.save(apostador);
			model.setViewName("auth/login");

			return model;
		}
	}

	private User createUser(Apostador apostador) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		User user = new User();

		user.setUsername(apostador.getNick());
		user.setPassword(encoder.encode(apostador.getSenha()));
		user.setEnabled(true);

		return userRepository.save(user);
	}

	private Authority createAuthority(User user) {
		Authority auth = new Authority();
		AuthorityId authId = new AuthorityId();

		authId.setUsername(user.getUsername());
		authId.setAuthority(EnumRole.APOSTADOR.getValue());

		auth.setId(authId);
		auth.setUsername(user);
		auth.setAuthority(EnumRole.APOSTADOR.getValue());

		return authorityRepository.save(auth);
	}

}
