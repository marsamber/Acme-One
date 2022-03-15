package acme.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Toolkit extends BaseEntity{

	//Serialisation identifier ------------------------------

	protected static final long serialVersionUID = 1L; 
	
	@OneToMany
	Collection<Component> components;
	
	@OneToOne(optional = true)
	Tool tool;
}
