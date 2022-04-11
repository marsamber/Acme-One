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
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronDashboard {
	
	public Integer patronagesProposed;
	
	public Integer patronagesAccepted;
	
	public Integer patronagesDenied;
	
	public Map<Pair<Status,String>,Double> patronagesAverage;
	
	public Map<Pair<Status,String>,Double> patronagesDeviation;
	
	public Map<Pair<Status,String>,Double> patronagesMinimum;
	
	public Map<Pair<Status,String>,Double> patronagesMaximum;
	
	public PatronDashboard(List<Collection<Patronage>> patronages) {

		Collection<Patronage> patronagesByPatronAndProposed=patronages.get(0);
		Collection<Patronage> patronagesByPatronAndAccepted=patronages.get(1); 
		Collection<Patronage> patronagesByPatronAndDenied=patronages.get(2);
		
		this.patronagesProposed=patronagesByPatronAndProposed.size();
		this.patronagesAccepted=patronagesByPatronAndAccepted.size(); 
		this.patronagesDenied=patronagesByPatronAndDenied.size(); 
		
		this.patronagesAverage = new HashMap<Pair<Status,String>, Double>();
		this.patronagesDeviation = new HashMap<Pair<Status,String>, Double>();
		this.patronagesMinimum = new HashMap<Pair<Status,String>, Double>();
		this.patronagesMaximum = new HashMap<Pair<Status,String>, Double>();
		
		List<Collection<Patronage>> patronagesByStatus= new ArrayList<Collection<Patronage>>();
		patronagesByStatus.add(patronagesByPatronAndProposed);
		patronagesByStatus.add(patronagesByPatronAndAccepted);
		patronagesByStatus.add(patronagesByPatronAndDenied);
		
		this.generatePatronagesStats(patronagesByStatus);
		
	}
	
	public void generatePatronagesStats(List<Collection<Patronage>> patronagesByStatus) {
		Status[] status= new Status[] {Status.PROPOSED,Status.ACCEPTED,Status.DENIED};
		String[] currencies= new String[] {"EUR","USD","GBP"};
	
		Double total;
		Double deviation;
		Long numberOfPatronagesByCurrency;
		OptionalDouble minimum;
		OptionalDouble maximum;
		System.out.println("patronagesByStatus: "+patronagesByStatus.get(0));

		for(int i=0;i<patronagesByStatus.size(); i++) { // Recorremos estado a estado
			Collection<Patronage> patronages=patronagesByStatus.get(i);
			for(int j =0; j<currencies.length;j++) {
				
				//Average
				int index=j;
				OptionalDouble average= patronages.stream().filter(x -> x.getBudget().getCurrency().equals(currencies[index])).mapToDouble(x -> x.getBudget().getAmount()).average();

				if(average.isPresent())
					this.patronagesAverage.put(Pair.of(status[i], currencies[j]), average.getAsDouble());
				else
					this.patronagesAverage.put(Pair.of(status[i], currencies[j]), 0.);
				
				//Deviation
				numberOfPatronagesByCurrency = patronages.stream().filter(x -> x.getBudget().getCurrency().equals(currencies[index])).count();
				total = patronages.stream().filter(x -> x.getBudget().getCurrency().equals(currencies[index])).mapToDouble(x -> Math.pow((x.getBudget().getAmount()-average.getAsDouble()),2)).sum();
				if(numberOfPatronagesByCurrency != 0)
					deviation = total/numberOfPatronagesByCurrency;
				else
					deviation = 0.;
				this.patronagesDeviation.put(Pair.of(status[i], currencies[j]), deviation);

				//Minimum
				minimum= patronages.stream().filter(x -> x.getBudget().getCurrency().equals(currencies[index])).mapToDouble(x -> x.getBudget().getAmount()).min();
				if(minimum.isPresent())
					this.patronagesMinimum.put(Pair.of(status[i], currencies[j]), minimum.getAsDouble());
				else
					this.patronagesMinimum.put(Pair.of(status[i], currencies[j]), 0.);
				
				//Maximum
				maximum= patronages.stream().filter(x -> x.getBudget().getCurrency().equals(currencies[index])).mapToDouble(x -> x.getBudget().getAmount()).max();
				if(maximum.isPresent())
					this.patronagesMaximum.put(Pair.of(status[i], currencies[j]), maximum.getAsDouble());
				else
					this.patronagesMaximum.put(Pair.of(status[i], currencies[j]), 0.);
			}
		}
	}
}

