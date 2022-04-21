package acme.features.inventor.patronages;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

public class InventorPatronageListService implements AbstractListService<Inventor, Patronage>{

	@Autowired 
	protected InventorPatronageRepository repository;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request !=null;
		
		final boolean result;
		int inventorId;
		final Inventor inventor;
		
		inventorId = request.getModel().getInteger("inventorId");
		inventor
		return false;
	}

	@Override
	public Collection<Patronage> findMany(final Request<Patronage> request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		// TODO Auto-generated method stub
		
	}

}
