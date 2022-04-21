package acme.features.patron.dashboard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronage;
import acme.entities.Patronage.Status;
import acme.forms.PatronDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronDashboardShowService implements AbstractShowService<Patron, PatronDashboard>{
	
	/*
	@Autowired
	protected PatronageShowService	patronageService;
	@Autowired
	protected AuthenticatedPatronShowService	patronShowService;
	*/
	@Autowired
	protected PatronDashboardRepository patronDashboardRepository;
	
	@Override
	public boolean authorise(Request<PatronDashboard> request) {
		assert request != null;

		return true;
	}
	
	private PatronDashboard createPatronDashboard(Patron patron) {
		
		Collection<Patronage> patronagesByPatronAndProposed=this.patronDashboardRepository.findPatronagesByPatronAndStatus(patron, Status.PROPOSED);
		Collection<Patronage> patronagesByPatronAndAccepted=this.patronDashboardRepository.findPatronagesByPatronAndStatus(patron, Status.ACCEPTED);
		Collection<Patronage> patronagesByPatronAndDenied=this.patronDashboardRepository.findPatronagesByPatronAndStatus(patron, Status.DENIED);
		
		List<Collection<Patronage>> patronages= new ArrayList<Collection<Patronage>>();
		
		patronages.add(patronagesByPatronAndProposed);
		patronages.add(patronagesByPatronAndAccepted);
		patronages.add(patronagesByPatronAndDenied);
		PatronDashboard patronDashboard= new PatronDashboard(patronages);
		
		return patronDashboard;
	}
	
	@Override
	public PatronDashboard findOne(Request<PatronDashboard> request) {
		
		Patron patron=this.patronDashboardRepository.findOnePatronByUserAccountId(PrincipalHelper.get().getAccountId());
		PatronDashboard patronDashboard= this.createPatronDashboard(patron);
		System.out.println("findOne");

		return patronDashboard;
	}

	@Override
	public void unbind(Request<PatronDashboard> request, PatronDashboard entity, Model model) {
		
		assert request != null;
		assert entity != null;
		assert model != null;
		System.out.println("unbind");
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
