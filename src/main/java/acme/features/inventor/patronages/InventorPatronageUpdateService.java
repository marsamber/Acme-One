package acme.features.inventor.patronages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronage;
import acme.features.antiSpam.SpamDetectorRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorPatronageUpdateService implements AbstractUpdateService<Inventor, Patronage> {

	@Autowired
	protected InventorPatronageRepository repo;
	
	@Autowired
	protected SpamDetectorRepository repositorySpam;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		System.out.println(id);
		final Patronage p = this.repo.findById(id);
		System.out.println(p.getId());
		
		return p.getInventor().getId() == request.getPrincipal().getActiveRoleId();
		//return true;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "status", "code", "legalStuff", "budget", "link", "createdAt", "startedAt", "finishedAt", "patron.userAccount.username", "patron.company", "patron.statement", "patron.link");
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "link", "createdAt", "startedAt", "finishedAt", "patron.userAccount.username", "patron.company", "patron.statement", "patron.link");
		
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;
		
		final int patronageId = request.getModel().getInteger("id");
		return this.repo.findById(patronageId);
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;
		this.repo.save(entity);
	}

}
