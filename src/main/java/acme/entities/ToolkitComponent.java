
package acme.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ToolkitComponent extends AbstractEntity {

	//Serialisation identifier ------------------------------

	protected static final long serialVersionUID = 1L;

	//Attributes ---------------------------------------------

	@ManyToOne(optional=false)
	Toolkit toolkit;
	
	@ManyToOne(optional=false)
	Component component;
}
