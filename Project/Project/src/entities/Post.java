package entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition= "DATETIME NOT NULL", updatable=false)
	private Date timeStamp;

	@ManyToOne(optional = false)
	private User poster;

	@OneToMany(mappedBy = "post", cascade=CascadeType.REMOVE)
	private List<Comment> comments;

	public Post(){
		timeStamp = Calendar.getInstance().getTime();
	}

	public int getId() {
		return id;
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
	
	@Override
	public boolean equals(Object obj) {

		boolean postsEqual = false;

		if(obj instanceof Post){
			Post other = (Post) obj;

			if(other.getId() == this.id){

				if(other.getPoster().equals(this.poster)){
					if(other.getTimeStamp().equals(this.timeStamp)){

						if(other.getTitle().equals(this.title)){

							if(other.getContent().equals(this.content)){
								postsEqual = true;
							}

						}
					}
				}
			}
		}
		return postsEqual;
	}
}
