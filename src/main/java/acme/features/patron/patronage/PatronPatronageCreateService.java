package acme.features.patron.patronage;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronage;
import acme.entities.SystemConfiguration;
import acme.features.antiSpam.SpamDetector;
import acme.features.antiSpam.SpamDetectorRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import acme.roles.Patron;
import acme.roles.RolesRepository;

@Service
public class PatronPatronageCreateService implements AbstractCreateService<Patron, Patronage> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronPatronageRepository repository;
	
	@Autowired 
	protected RolesRepository roleRepository;
	
	@Autowired
	protected SpamDetectorRepository repositorySpam; 

	// AbstractCreateService<Patron, Patronage> interface -------------------------


	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		return true;
	}

	@Override
	public Patronage instantiate(final Request<Patronage> request) {
		assert request != null;

		Patronage patronage;
		Patron patron;

		patron = this.roleRepository.findPatronById(request.getPrincipal().getActiveRoleId());
		patronage = new Patronage();
		patronage.setPatron(patron);
		
		Date createdAt, startedAt, finishedAt;
		
		createdAt = Date.from(Instant.now());
		startedAt = DateUtils.addMonths(createdAt, 1);
		finishedAt = DateUtils.addMonths(startedAt,1);
		
		final Money money = new Money();
		money.setAmount(0.);
		money.setCurrency("EUR");
		
		patronage.setCreatedAt(createdAt);
		patronage.setStartedAt(startedAt);
		patronage.setFinishedAt(finishedAt);
		patronage.setLink("");
		patronage.setLegalStuff("");
		
		return patronage;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		String username;
		Inventor inventor;
		
		username = request.getModel().getString("inventor.username");
		inventor = this.roleRepository.findInventorByUsername(username);
		
		entity.setInventor(inventor);

		request.bind(entity, errors, "code", "legalStuff", "budget", "link", "startedAt", "finishedAt", "inventor.username");
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (errors.hasErrors("code")) {
			Patronage existing;

			existing = this.repository.findByCode(entity.getCode());
			errors.state(request, existing == null || existing.getId() == entity.getId(), "code", "patron.patronage.form.error.code.existingPatronage");
		}
		
		if (errors.hasErrors("startedAt")) {
			final Date minStartedAt = DateUtils.addMonths(entity.getCreatedAt(), 1);
			errors.state(request, entity.getStartedAt().after(minStartedAt), "startedAt", "patron.patronage.form.error.startDate.tooClose");
		}
		
		if (errors.hasErrors("budget")) {
			final List<String> acceptedCurrencies = Arrays.asList(this.repository.getAcceptedCurrencies().split(","));
			
			errors.state(request, entity.getBudget().getAmount() > 0, "budget", "patron.patronage.form.error.negative");
			
			errors.state(request, acceptedCurrencies.contains(entity.getBudget().getCurrency()), "budget", "patron.patronage.form.error.invalidCurrency");
		}
		
		final SystemConfiguration systemConfiguration= this.repositorySpam.findTheSystemConfiguration();
        final SpamDetector spamDetector= new SpamDetector(systemConfiguration);

        errors.state(request, !spamDetector.detectSpam(entity.getLegalStuff()), "legalStuff", "patron.patronage.form.error.spam");
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		final List<String> usernames = new ArrayList<>();
		this.roleRepository.findAllInventors().stream().forEach(x -> usernames.add(x.getUserAccount().getUsername()));
		
		request.unbind(entity, model, "code", "legalStuff", "budget", "link", "createdAt", "startedAt", "finishedAt","inventor.username");
		model.setAttribute("allInventors", usernames);
		
	}

	@Override
	public void create(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
