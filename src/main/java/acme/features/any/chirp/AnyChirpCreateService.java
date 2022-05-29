package acme.features.any.chirp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Chirp;
import acme.entities.SystemConfiguration;
import acme.features.antiSpam.SpamDetector;
import acme.features.antiSpam.SpamDetectorRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractCreateService;

@Service
public class AnyChirpCreateService implements AbstractCreateService<Any, Chirp> {
	
	@Autowired
	protected AnyChirpRepository repository;
	
	@Autowired
	protected SpamDetectorRepository repositorySpam; 

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
        request.bind(entity, errors,"title", "author", "body", "email");
		
	}

	@Override
	public void unbind(final Request<Chirp> request, final Chirp entity, final Model model) {
		assert request != null;
        assert entity != null;
        assert model != null;
        request.unbind(entity, model,"title", "author", "body", "email");
        model.setAttribute("confirmation", false);
        model.setAttribute("creationMoment", entity.getCreationMoment());
	}

	@Override
	public Chirp instantiate(final Request<Chirp> request) {
		assert request != null;
		Chirp result;
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		result = new Chirp();
		result.setCreationMoment(moment);
		return result;
	}

	@Override
	public void validate(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean confirmation;

		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "any.Chirp.confirmation.error");
		
		final SystemConfiguration systemConfiguration= this.repositorySpam.findTheSystemConfiguration();
        final SpamDetector spamDetector= new SpamDetector(systemConfiguration);

        errors.state(request, !spamDetector.detectSpam(entity.getAuthor()), "author", "any.chirp.form.error.spam");
 
        errors.state(request, !spamDetector.detectSpam(entity.getBody()), "body", "any.chirp.form.error.spam");
        
        errors.state(request, !spamDetector.detectSpam(entity.getEmail()), "email", "any.chirp.form.error.spam");
        
        errors.state(request, !spamDetector.detectSpam(entity.getTitle()), "title", "any.chirp.form.error.spam");
		
	}



	@Override
	public void create(final Request<Chirp> request, final Chirp entity) {
		assert request != null;
        assert entity != null;

        this.repository.save(entity);
		
	}
}

