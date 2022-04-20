package acme.features.authenticated.patron.dashboard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronage;
import acme.entities.Patronage.Status;
import acme.features.authenticated.patron.AuthenticatedPatronShowService;
import acme.forms.PatronDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;
import acme.services.PatronageShowService;

@Service
public class PatronDashboardShowService implements AbstractShowService<Patron, PatronDashboard>{
	
	@Autowired
	protected PatronageShowService	patronageService;
	@Autowired
	protected AuthenticatedPatronShowService	patronShowService;
	
	@Override
	public boolean authorise(Request<PatronDashboard> request) {
		// TODO Auto-generated method stub
		return false;
	}
	private PatronDashboard createPatronDashboard(Patron patron) {
		
		Collection<Patronage> patronagesByPatronAndProposed=this.patronageService.findPatronagesByPatronAndStatus(patron, Status.PROPOSED); // TODO Llamada a la funcion servicio que recoja los patrocinios propuestos de este patrocinador
		Collection<Patronage> patronagesByPatronAndAccepted=this.patronageService.findPatronagesByPatronAndStatus(patron, Status.ACCEPTED);; // TODO Llamada a la funcion servicio que recoja los patrocinios aceptados de este patrocinador
		Collection<Patronage> patronagesByPatronAndDenied=this.patronageService.findPatronagesByPatronAndStatus(patron, Status.DENIED);;
		
		List<Collection<Patronage>> patronages= new ArrayList<Collection<Patronage>>();
		
		patronages.add(patronagesByPatronAndProposed);
		patronages.add(patronagesByPatronAndAccepted);
		patronages.add(patronagesByPatronAndDenied);
		PatronDashboard patronDashboard= new PatronDashboard(patronages);
		
		return patronDashboard;
	}

	@Override
	public PatronDashboard findOne(Request<PatronDashboard> request) {

		Patron patron=this.patronShowService.findOnePatronByUserAccountId(PrincipalHelper.get().getAccountId());
		PatronDashboard patronDashboard= this.createPatronDashboard(patron);
		
		return patronDashboard;
	}

	@Override
	public void unbind(Request<PatronDashboard> request, PatronDashboard entity, Model model) {
		
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, 
			"patronagesProposed", 
			"patronagesAccepted",
			"patronagesDenied", 
			"patronagesAverage",
			"patronagesDeviation",
			"patronagesMinimum",
			"patronagesMaximum");
	}

}
