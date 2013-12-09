package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable=false, length=250)
	private String title;
	
	@Column(nullable=false, length=500)
	private String content;
	
	@Temporal(TemporalType.DATE)
	@Column(updatable=false, nullable=false)
	private Date timeStamp;
	
	@ManyToOne(optional = false)
	private User poster;
	
	@OneToMany(mappedBy = "post")
	private List<Comment> comments;
	
	public Post(){
		timeStamp = new Date();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}
	
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public User getPoster() {
		return poster;
	}

	public void setPoster(User originalPoster) {
		this.poster = originalPoster;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
