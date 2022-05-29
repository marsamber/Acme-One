package acme.features.inventor.patronageReport;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronage;
import acme.entities.PatronageReport;
import acme.entities.SystemConfiguration;
import acme.features.antiSpam.SpamDetector;
import acme.features.antiSpam.SpamDetectorRepository;
import acme.features.inventor.patronage.InventorPatronageRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;


@Service
public class InventorPatronageReportCreateService implements AbstractCreateService<Inventor, PatronageReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorPatronageReportRepository patronageReportRepository;
	
	@Autowired
	protected InventorPatronageRepository patronageRepository;
	
	@Autowired
	protected SpamDetectorRepository repositorySpam;

	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		//creationMoment
		final Date moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);
		
		//patronage
		final int principalId = request.getPrincipal().getActiveRoleId();
		final Collection<Patronage> patronages = this.patronageRepository.findAllInventorPatronagesByInventorId(principalId);
		final Patronage patronage = patronages.stream()
			.filter(x -> x.getCode().equals(request.getModel().getAttribute("codigoPatronage")))
			.collect(Collectors.toList()).get(0);
		entity.setPatronage(patronage);
	
		//seqNumber
		final Collection<PatronageReport> listaPatronages = this.patronageReportRepository.findManyPatronageReportByPatronageId(patronage.getId());
		String seqNumber = "";
		
		if (listaPatronages == null || listaPatronages.isEmpty()) {
			seqNumber = patronage.getCode() + ":0001";
		}
		else {
			final Integer nextNumber = listaPatronages.size() + 1;
			
			switch((nextNumber).toString().length()) {
			
			case 1:
				seqNumber = patronage.getCode() + ":000" + nextNumber;
				break;
			case 2:
				seqNumber = patronage.getCode() + ":00" + nextNumber;
				break;
			case 3:
				seqNumber = patronage.getCode() + ":0" + nextNumber;
				break;
			case 4:
				seqNumber = patronage.getCode() + ":" + nextNumber;
				break;
			
			default:
				break;
			}
			
		}
		entity.setSeqNumber(seqNumber);
		
		request.bind(entity, errors, "memorandum", "link");
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final int principalId = request.getPrincipal().getActiveRoleId();
		final Collection<Patronage> patronages = this.patronageRepository.findAllInventorPatronagesByInventorId(principalId);
		final List<String> codigosPatronagesDisponibles = patronages.stream().map(Patronage::getCode).collect(Collectors.toList());
		
		request.unbind(entity, model, "memorandum", "link");
		
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", false);
		model.setAttribute("availablePatronages", codigosPatronagesDisponibles);
		model.setAttribute("codigoPatronage", "");
	}

	@Override
	public PatronageReport instantiate(final Request<PatronageReport> request) {
		assert request != null;

		PatronageReport result;
		final Patronage p = new Patronage();
		final Date moment = new Date(System.currentTimeMillis() - 1);
		

		result = new PatronageReport();
		result.setMemorandum("");
		result.setLink("");
		result.setCreationMoment(moment);
		result.setPatronage(p);
		result.setSeqNumber("");
		
		return result;
	}

	@Override
	public void validate(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean confirmation;

		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
		
		final SystemConfiguration systemConfiguration= this.repositorySpam.findTheSystemConfiguration();
        final SpamDetector spamDetector= new SpamDetector(systemConfiguration);

        errors.state(request, !spamDetector.detectSpam(entity.getMemorandum()), "memorandum", "inventor.patronageReport.form.error.spam");
	}

	@Override
	public void create(final Request<PatronageReport> request, final PatronageReport entity) {
		assert request != null;
		assert entity != null;
		
		this.patronageReportRepository.save(entity);
	}
}
	
