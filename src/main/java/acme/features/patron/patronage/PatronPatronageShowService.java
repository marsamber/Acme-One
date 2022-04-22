package acme.features.patron.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;
import acme.roles.Patron;

@Service
public class PatronPatronageShowService implements AbstractShowService<Patron, Patronage> {

	@Autowired
	protected PatronPatronageRepository repo;

	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		return true;
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;
		
		final int patronageId = request.getModel().getInteger("id");
		return this.repo.findById(patronageId);
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final Inventor inventor = entity.getInventor();
		
		request.unbind(entity, model, "status", "code",
			"legalStuff", "budget", "link", "createdAt",
			"startedAt", "finishedAt", "patron", "inventor");
		request.unbind(inventor, model, "company", "statement");
		request.unbind(inventor.getUserAccount(), model, "username");
		model.setAttribute("inventorLink", inventor.getMoreInfo());
		
	}
	
	
	
}
