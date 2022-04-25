package acme.features.inventor.patronageReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorPatronageReportShowService implements AbstractShowService<Inventor, PatronageReport>{
	
	// Internal state
	
	@Autowired
	protected InventorPatronageReportRepository repository;

	// Interface 
	
	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;
		
		int id;
		int principalId;
		PatronageReport pr;
		boolean result;
		
		id = request.getModel().getInteger("id");
		pr = this.repository.findOnePatronageReportById(id);
		
		principalId = request.getPrincipal().getActiveRoleId();
		result = pr != null && pr.getPatronage().getInventor().getId() == principalId;
		
		return result;
	}

	@Override
	public PatronageReport findOne(final Request<PatronageReport> request) {
		assert request != null;
		
		PatronageReport result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOnePatronageReportById(id);
		
		return result;
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "seqNumber", "creationMoment", "memorandum", "link");
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", true);
	}

	
}



