package acme.entities;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Component extends BaseEntity {

	//Serialisation identifier ------------------------------

	protected static final long serialVersionUID = 1L; 
	
	
}
