package acme.features.any.chirp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chirp.Chirp;
import acme.entities.configuration.Configuration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractCreateService;
import main.AntiSpam;


@Service
public class AnyChirpCreateService implements AbstractCreateService<Any, Chirp>{
	
	@Autowired
	protected AnyChirpRepository repository;

	@Override
	public boolean authorise(final Request<Chirp> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment", "title", "author", "body", "email");
		
	}

	@Override
	public void unbind(final Request<Chirp> request, final Chirp entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "creationMoment", "title", "author", "body", "email");
		model.setAttribute("confirmation", false);
	}

	@Override
	public Chirp instantiate(final Request<Chirp> request) {
		assert request != null;

		Chirp result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new Chirp();
		result.setTitle("");
		result.setBody("");
		result.setCreationMoment(moment);
		result.setAuthor("");
		result.setEmail("");

		return result;
	}

	@Override
	public void validate(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean confirmation;
		boolean spamWord;
		boolean spamWordTitle;
		boolean spamWordAuthor;
		
		final Configuration configuration = this.repository.configuration();
		final AntiSpam antiSpam = new AntiSpam(configuration.getStrongSpamWords(), configuration.getStrongSpamThreshold(), configuration.getWeakSpamWords(), configuration.getWeakSpamThreshold(), entity.getBody());
		spamWord = antiSpam.getAvoidSpam();
		errors.state(request, !spamWord, "body", "any.chirp.form.error.spamWord");
		
		final AntiSpam antiSpamTitle = new AntiSpam(configuration.getStrongSpamWords(), configuration.getStrongSpamThreshold(), configuration.getWeakSpamWords(), configuration.getWeakSpamThreshold(), entity.getTitle());
		spamWordTitle = antiSpamTitle.getAvoidSpam();
		errors.state(request, !spamWordTitle, "title", "any.chirp.form.error.spamWord");
		
		final AntiSpam antiSpamAuthor = new AntiSpam(configuration.getStrongSpamWords(), configuration.getStrongSpamThreshold(), configuration.getWeakSpamWords(), configuration.getWeakSpamThreshold(), entity.getAuthor());
		spamWordAuthor = antiSpamAuthor.getAvoidSpam();
		errors.state(request, !spamWordAuthor, "author", "any.chirp.form.error.spamWord");
		
		

		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
		
	}

	@Override
	public void create(final Request<Chirp> request, final Chirp entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);
		this.repository.save(entity);
		
	}

}
