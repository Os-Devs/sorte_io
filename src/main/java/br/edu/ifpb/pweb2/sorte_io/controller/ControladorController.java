package br.edu.ifpb.pweb2.sorte_io.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.sorte_io.model.Controlador;
import br.edu.ifpb.pweb2.sorte_io.model.Authority;
import br.edu.ifpb.pweb2.sorte_io.model.EnumRole;
import br.edu.ifpb.pweb2.sorte_io.model.User;
import br.edu.ifpb.pweb2.sorte_io.model.Authority.AuthorityId;
import br.edu.ifpb.pweb2.sorte_io.repository.ControladoresRepository;
import br.edu.ifpb.pweb2.sorte_io.repository.AuthorityRepository;
import br.edu.ifpb.pweb2.sorte_io.repository.UserRepository;

@Controller
@RequestMapping("/controladores")
public class ControladorController {

	@Autowired
	ControladoresRepository controladoresRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	AuthorityRepository authorityRepository;

	@RequestMapping("/form")
	public ModelAndView getForm(Controlador controlador, ModelAndView model) {
		model.addObject("controlador", controlador);
		model.setViewName("controlador/formControlador");

		return model;
	}

	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(@Valid Controlador controlador, BindingResult validation, ModelAndView model, RedirectAttributes flash) {
		if (validation.hasErrors()) {
			model.setViewName("controlador/formControlador");
			return model;
		}
		else {
			User user = this.createUser(controlador);
			Authority authority = this.createAuthority(user);

			controlador.setUser(user);

			controladoresRepository.save(controlador);

			model.setViewName("redirect:login");
			flash.addFlashAttribute("mensagem", "Controlador cadastrado com sucesso!");

			return model;
		}
	}

	private User createUser(Controlador controlador) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		User user = new User();

		user.setUsername(controlador.getNick());
		user.setPassword(encoder.encode(controlador.getSenha()));
		user.setEnabled(true);

		return userRepository.save(user);
	}

	private Authority createAuthority(User user) {
		Authority auth = new Authority();
		AuthorityId authId = new AuthorityId();

		authId.setUsername(user.getUsername());
		authId.setAuthority(EnumRole.CONTROLADOR.getValue());

		auth.setId(authId);
		auth.setUsername(user);
		auth.setAuthority(EnumRole.CONTROLADOR.getValue());

		return authorityRepository.save(auth);
	}

}
