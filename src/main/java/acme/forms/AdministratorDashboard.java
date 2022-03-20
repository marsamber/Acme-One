package acme.forms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

import org.springframework.data.util.Pair;

import acme.entities.Item;
import acme.entities.Patronage;
import acme.entities.Patronage.Status;

public class AdministratorDashboard {
	
	// COMPONENTS
	
	public Integer totalComponents;
	
	// Agrupado por tecnología y divisa
	public Map<Pair<String,String>,Double> retailPriceComponentsAverage;
	public Map<Pair<String,String>,Double> retailPriceComponentsDeviation;
	public Map<Pair<String,String>,Double> retailPriceComponentsMinimum;
	public Map<Pair<String,String>,Double> retailPriceComponentsMaximum;
	
	// TOOLS
	
	public Integer totalTools;
	
	// Agrupado por divisa
	public Map<String,Double> retailPriceToolsAverage;
	public Map<String,Double> retailPriceToolsDeviation;
	public Map<String,Double> retailPriceToolsMinimum;
	public Map<String,Double> retailPriceToolsMaximum;
	
	// PATRONAGES
	public Integer patronagesProposed;
	public Integer patronagesAccepted;
	public Integer patronagesDennied;
	
	// Agrupado por estado y divisa
	public Map<Pair<Status,String>,Double> patronagesAverage;
	public Map<Pair<Status,String>,Double> patronagesDeviation;
	public Map<Pair<Status,String>,Double> patronagesMinimum;
	public Map<Pair<Status,String>,Double> patronagesMaximum;
	
	public AdministratorDashboard() {		
		this.generateComponentsData();
		this.generateToolsData();
		this.generatePatronagesData();
	}

	public void generateComponentsData() {
		Collection<Item> components=null; // TODO Llamada a la funcion servicio que recoja los componentes
		
		this.totalComponents= components.size();
		
		this.generateComponentsStats(components);
		
	}

	private void generateComponentsStats(Collection<Item> components) {
		
		String[] technologies= this.getAllTechnologies(components);
		String[] currencies= new String[] {"EUR","USD","GBP"};
		
		for(int i =0; i<currencies.length;i++) {
			for(int j =0;j<technologies.length;j++) {
				int currencyIndex=i;
				int technologyIndex=j;
				
				//Average
				OptionalDouble average= components.stream().filter(x -> x.getRetailPrice().getCurrency().equals(currencies[currencyIndex])
					&& x.getTechnology().equals(technologies[technologyIndex]))
					.mapToDouble(x -> x.getRetailPrice().getAmount()).average();
				if(average.isPresent())
					this.retailPriceComponentsAverage.put(Pair.of(technologies[j], currencies[i]), average.getAsDouble());
				
				//Deviation
				Long numberOfComponentsByCurrency = components.stream().filter(x -> x.getRetailPrice().getCurrency().equals(currencies[currencyIndex])
					&& x.getTechnology().equals(technologies[technologyIndex])).count();
				
				Double total =components.stream().filter(x -> x.getRetailPrice().getCurrency().equals(currencies[currencyIndex])
					&& x.getTechnology().equals(technologies[technologyIndex]))
					.mapToDouble(x -> x.getRetailPrice().getAmount()).sum();
				
				Double deviation = total/numberOfComponentsByCurrency;
				this.retailPriceComponentsDeviation.put(Pair.of(technologies[j], currencies[i]), deviation);
				
				//Minimum
				OptionalDouble minimum =components.stream().filter(x -> x.getRetailPrice().getCurrency().equals(currencies[currencyIndex])
					&& x.getTechnology().equals(technologies[technologyIndex]))
					.mapToDouble(x -> x.getRetailPrice().getAmount()).min();
					
				if(minimum.isPresent())
					this.retailPriceComponentsMinimum.put(Pair.of(technologies[j], currencies[i]), minimum.getAsDouble());
				
				//Maximum
				OptionalDouble maximum =components.stream().filter(x -> x.getRetailPrice().getCurrency().equals(currencies[currencyIndex])
					&& x.getTechnology().equals(technologies[technologyIndex]))
					.mapToDouble(x -> x.getRetailPrice().getAmount()).min();
					
				if(maximum.isPresent())
					this.retailPriceComponentsMaximum.put(Pair.of(technologies[j], currencies[i]), maximum.getAsDouble());
					
			}
		}		
	}
	public void generateToolsData() {
		Collection<Item> tools=null; // TODO Llamada a la funcion servicio que recoja los componentes
		
		this.totalComponents= tools.size();
		
		this.generateToolsStats(tools);
	}

	private void generateToolsStats(Collection<Item> tools) {
		String[] currencies= new String[] {"EUR","USD","GBP"};
		
		Double total;
		Double deviation;
		Long numberOfPatronagesByCurrency;
		OptionalDouble minimum;
		OptionalDouble maximum;

		for(int i =0; i<currencies.length;i++) {
			
			//Average
			int index=i;
			OptionalDouble average= tools.stream().filter(x -> x.getRetailPrice().getCurrency().equals(currencies[index])).mapToDouble(x -> x.getRetailPrice().getAmount()).average();
			if(average.isPresent())
				this.retailPriceToolsAverage.put(currencies[i], average.getAsDouble());
			
			//Deviation
			numberOfPatronagesByCurrency = tools.stream().filter(x -> x.getRetailPrice().getCurrency().equals(currencies[index])).count();
			total = tools.stream().filter(x -> x.getRetailPrice().getCurrency().equals(currencies[index])).mapToDouble(x -> (x.getRetailPrice().getAmount()-average.getAsDouble())).sum();
			deviation = total/numberOfPatronagesByCurrency;
			this.retailPriceToolsDeviation.put(currencies[i], deviation);
			
			//Minimum
			minimum= tools.stream().filter(x -> x.getRetailPrice().getCurrency().equals(currencies[index])).mapToDouble(x -> x.getRetailPrice().getAmount()).min();
			if(minimum.isPresent())
				this.retailPriceToolsMinimum.put(currencies[i], minimum.getAsDouble());
			
			//Maximum
			maximum= tools.stream().filter(x -> x.getRetailPrice().getCurrency().equals(currencies[index])).mapToDouble(x -> x.getRetailPrice().getAmount()).max();
			if(maximum.isPresent())
				this.retailPriceToolsMaximum.put(currencies[i], maximum.getAsDouble());
		}

	}

	public void generatePatronagesData(){
		Collection<Patronage> patronagesProposed=null; // TODO Llamada a la funcion servicio que recoja los patrocinios propuestos
		Collection<Patronage> patronagesAccepted=null; // TODO Llamada a la funcion servicio que recoja los patrocinios aceptados
		Collection<Patronage> patronagesDennied=null;  // TODO Llamada a la funcion servicio que recoja los patrocinios denegados
		
		this.patronagesProposed=patronagesProposed.size();
		this.patronagesAccepted=patronagesAccepted.size(); 
		this.patronagesDennied=patronagesDennied.size(); 
		
		List<Collection<Patronage>> patronagesByStatus= new ArrayList<Collection<Patronage>>();
		patronagesByStatus.add(patronagesProposed);
		patronagesByStatus.add(patronagesAccepted);
		patronagesByStatus.add(patronagesDennied);
		
		this.generatePatronagesStats(patronagesByStatus);
	}
	
	private String[] getAllTechnologies(Collection<Item> components){
		
		List<String> technologies= new ArrayList<String>();
		List<Item> componentsList= new ArrayList<Item>(components);
		
		for(int i=0; i<componentsList.size(); i++) {
			if(technologies.contains(componentsList.get(i).getTechnology()))
				technologies.add(componentsList.get(i).getTechnology());
		}
		
		return (String[]) technologies.toArray();
	}
	
	
	
	private void generatePatronagesStats(List<Collection<Patronage>> patronagesByStatus) {
		Status[] status= new Status[] {Status.PROPOSED,Status.ACCEPTED,Status.DENIED};
		String[] currencies= new String[] {"EUR","USD","GBP"};
	
		Double total;
		Double deviation;
		Long numberOfPatronagesByCurrency;
		OptionalDouble minimum;
		OptionalDouble maximum;

		for(int i=0;i<patronagesByStatus.size(); i++) { // Recorremos estado a estado
			Collection<Patronage> patronages=patronagesByStatus.get(i);
			for(int j =0; j<currencies.length;j++) {
				
				//Average
				int index=j;
				OptionalDouble average= patronages.stream().filter(x -> x.getBudget().getCurrency().equals(currencies[index])).mapToDouble(x -> x.getBudget().getAmount()).average();
				if(average.isPresent())
					this.patronagesAverage.put(Pair.of(status[i], currencies[j]), average.getAsDouble());
				
				//Deviation
				numberOfPatronagesByCurrency = patronages.stream().filter(x -> x.getBudget().getCurrency().equals(currencies[index])).count();
				total = patronages.stream().filter(x -> x.getBudget().getCurrency().equals(currencies[index])).mapToDouble(x -> (x.getBudget().getAmount()-average.getAsDouble())).sum();
				deviation = total/numberOfPatronagesByCurrency;
				this.patronagesDeviation.put(Pair.of(status[i], currencies[j]), deviation);
				
				//Minimum
				minimum= patronages.stream().filter(x -> x.getBudget().getCurrency().equals(currencies[index])).mapToDouble(x -> x.getBudget().getAmount()).min();
				if(minimum.isPresent())
					this.patronagesMinimum.put(Pair.of(status[i], currencies[j]), minimum.getAsDouble());
				
				//Maximum
				maximum= patronages.stream().filter(x -> x.getBudget().getCurrency().equals(currencies[index])).mapToDouble(x -> x.getBudget().getAmount()).max();
				if(maximum.isPresent())
					this.patronagesMaximum.put(Pair.of(status[i], currencies[j]), maximum.getAsDouble());
			}
		}
	}
	
	
}
