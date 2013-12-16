package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Vote implements Serializable {
static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne(optional = false)
	private Comment comment;
	
	@ManyToOne(optional = false)
	private User voter;
	
	@Column(updatable=true, nullable=false)
	private boolean isUp;
	
	public int getId() {
		return id;
	}
	
	public Comment getComment() {
		return comment;
	}
	
	public User getVoter() {
		return voter;
	}
	
	public boolean isUp() {
		return isUp;
	}
	
	public void setValue(boolean isUp) {
		this.isUp = isUp;
	}
	
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	
	public void setVoter(User voter) {
		this.voter = voter;
	}
	
	public void setId(int id) {
		this.id = id;
	}

}
