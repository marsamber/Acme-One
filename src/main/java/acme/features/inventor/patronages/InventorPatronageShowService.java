package acme.features.inventor.patronages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;
import acme.roles.Patron;

@Service
public class InventorPatronageShowService implements AbstractShowService<Inventor, Patronage> {

	@Autowired
	protected InventorPatronageRepository repo;

	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final Patronage p = this.repo.findById(id);
		
		return p != null && p.getInventor().getId() == request.getPrincipal().getActiveRoleId();
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
		assert request != null;
		assert request != null;
		
		final Patron patron = entity.getPatron();
		
		request.unbind(entity, model, "status", "code",
			"legalStuff", "budget", "link", "createdAt",
			"startedAt", "finishedAt", "patron", "inventor");
		request.unbind(patron, model, "company", "statement");
		request.unbind(patron.getUserAccount(), model, "username");
		model.setAttribute("patronLink", patron.getMoreInfo());
		
		
	}
	
}
