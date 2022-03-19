package acme.forms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

import org.springframework.data.util.Pair;

import acme.entities.Patronage;
import acme.entities.Patronage.Status;

public class PatronDashboard {
	
	public Integer patronagesProposed;
	
	public Integer patronagesAccepted;
	
	public Integer patronagesDennied;
	
	public Map<Pair<Status,String>,Double> patronagesAverage;
	
	public Map<Pair<Status,String>,Double> patronagesDeviation;
	
	public Map<Pair<Status,String>,Double> patronagesMinimum;
	
	public Map<Pair<Status,String>,Double> patronagesMaximum;
	
	public PatronDashboard() {

		final Collection<Patronage> patronagesByPatronAndProposed=null; // TODO Llamada a la funcion servicio que recoja los patrocinios propuestos de este patrocinador
		final Collection<Patronage> patronagesByPatronAndAccepted=null; // TODO Llamada a la funcion servicio que recoja los patrocinios propuestos de este patrocinador
		final Collection<Patronage> patronagesByPatronAndDennied=null; // TODO Llamada a la funcion servicio que recoja los patrocinios propuestos de este patrocinador
		
		this.generateTotalPatronagesProposed(patronagesByPatronAndProposed);
		this.generateTotalPatronagesAccepted(patronagesByPatronAndAccepted);
		this.generateTotalPatronagesDennied(patronagesByPatronAndDennied);	
		
		final List<Collection<Patronage>> patronagesByStatus= new ArrayList<Collection<Patronage>>();
		patronagesByStatus.add(patronagesByPatronAndProposed);
		patronagesByStatus.add(patronagesByPatronAndAccepted);
		patronagesByStatus.add(patronagesByPatronAndDennied);
		
		this.generatePatronagesAverage(patronagesByStatus);
		this.generatePatronagesDeviation(patronagesByStatus);
		this.generatePatronagesMinimum(patronagesByStatus);
		this.generatePatronagesMaximum(patronagesByStatus);
		
	}
	
	public Integer generateTotalPatronagesProposed(Collection<Patronage> patronagesByPatronAndProposed) {

		this.patronagesProposed=patronagesByPatronAndProposed.size(); 
		
		return this.patronagesProposed;
	}
	
	public Integer generateTotalPatronagesAccepted(Collection<Patronage> patronagesByPatronAndAccepted) {

		this.patronagesAccepted=patronagesByPatronAndAccepted.size(); 
		
		return this.patronagesAccepted;
	}
	
	public Integer generateTotalPatronagesDennied(Collection<Patronage> patronagesByPatronAndDennied) {

		this.patronagesDennied=patronagesByPatronAndDennied.size(); 
		
		return this.patronagesDennied;
	}
	public Map<Pair<Status,String>,Double> generatePatronagesAverage(List<Collection<Patronage>> patronagesByStatus) {
		this.patronagesAverage=new HashMap<Pair<Status,String>, Double>();
		Status[] status= new Status[] {Status.PROPOSED,Status.ACCEPTED,Status.DENIED};
		String[] currencies= new String[] {"EUR","USD"};
		
		OptionalDouble average;

		for(int i=0;i<patronagesByStatus.size(); i++) { // Recorremos estado a estado
			Collection<Patronage> patronages=patronagesByStatus.get(i);
			for(int j =0; j<currencies.length;j++) {
				int index=j;
				average= patronages.stream().filter(x -> x.getBudget().getCurrency().equals(currencies[index])).mapToDouble(x -> x.getBudget().getAmount()).average();
				if(average.isPresent())
					this.patronagesAverage.put(Pair.of(status[i], currencies[j]), average.getAsDouble());
			}
		}
		
		return this.patronagesAverage;
	}
	
	public Map<Pair<Status,String>,Double> generatePatronagesDeviation(List<Collection<Patronage>> patronagesByStatus) {
		this.patronagesDeviation=new HashMap<Pair<Status,String>, Double>();
		Status[] status= new Status[] {Status.PROPOSED,Status.ACCEPTED,Status.DENIED};
		String[] currencies= new String[] {"EUR","USD","GBP"};
		
		Double total;
		Double deviation;
		Long numberOfPatronagesByCurrency;

		for(int i=0;i<patronagesByStatus.size(); i++) { // Recorremos estado a estado
			Collection<Patronage> patronages=patronagesByStatus.get(i);
			for(int j =0; j<currencies.length;j++) { // Recorremos divisa a divisa
				int index=j;
				OptionalDouble average = patronages.stream().filter(x -> x.getBudget().getCurrency().equals(currencies[index])).mapToDouble(x -> x.getBudget().getAmount()).average();
				numberOfPatronagesByCurrency = patronages.stream().filter(x -> x.getBudget().getCurrency().equals(currencies[index])).count();
				total = patronages.stream().filter(x -> x.getBudget().getCurrency().equals(currencies[index])).mapToDouble(x -> (x.getBudget().getAmount()-average.getAsDouble())).sum();
				deviation = total/numberOfPatronagesByCurrency;
					this.patronagesDeviation.put(Pair.of(status[i], currencies[j]), deviation);
			}
		}
		
		return this.patronagesDeviation;
	}
	
	public Map<Pair<Status,String>,Double> generatePatronagesMinimum(List<Collection<Patronage>> patronagesByStatus) {
		this.patronagesMinimum=new HashMap<Pair<Status,String>, Double>();
		Status[] status= new Status[] {Status.PROPOSED,Status.ACCEPTED,Status.DENIED};
		String[] currencies= new String[] {"EUR","USD","GBP"};
		
		OptionalDouble minimum;

		for(int i=0;i<patronagesByStatus.size(); i++) { // Recorremos estado a estado
			Collection<Patronage> patronages=patronagesByStatus.get(i);
			for(int j =0; j<currencies.length;j++) { // Recorremos divisa a divisa
				int index=j;
				minimum= patronages.stream().filter(x -> x.getBudget().getCurrency().equals(currencies[index])).mapToDouble(x -> x.getBudget().getAmount()).min();
				if(minimum.isPresent())
					this.patronagesMinimum.put(Pair.of(status[i], currencies[j]), minimum.getAsDouble());
			}
		}
		
		return this.patronagesMinimum;
	}
	
	public Map<Pair<Status,String>,Double> generatePatronagesMaximum(List<Collection<Patronage>> patronagesByStatus) {
		this.patronagesMaximum=new HashMap<Pair<Status,String>, Double>();
		Status[] status= new Status[] {Status.PROPOSED,Status.ACCEPTED,Status.DENIED};
		String[] currencies= new String[] {"EUR","USD","GBP"};
		
		OptionalDouble maximum;

		for(int i=0;i<patronagesByStatus.size(); i++) { // Recorremos estado a estado
			Collection<Patronage> patronages=patronagesByStatus.get(i);
			for(int j =0; j<currencies.length;j++) { // Recorremos divisa a divisa
				int index=j;
				maximum= patronages.stream().filter(x -> x.getBudget().getCurrency().equals(currencies[index])).mapToDouble(x -> x.getBudget().getAmount()).max();
				if(maximum.isPresent())
					this.patronagesMaximum.put(Pair.of(status[i], currencies[j]), maximum.getAsDouble());
			}
		}
		
		return this.patronagesMaximum;
	}
}
