package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
//import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
//import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Comment implements Serializable {
        private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue
        private int id;

        @ManyToOne(optional = false)
        private User poster;
        
        @Column(nullable=false, length=500)
        private String content;
        
    	@Temporal(TemporalType.TIMESTAMP)
    	@Column(columnDefinition= "DATETIME NOT NULL", updatable=false)
    	private Date timeStamp;
        
        
        @Column(updatable=true, nullable=false)
        private int rating;
        
        @OneToMany(cascade=CascadeType.REMOVE)
        private List<Vote> votes;
        
        @OneToMany(mappedBy = "parentComment", cascade=CascadeType.REMOVE)
        private List<Comment> replies;
        
        @ManyToOne(optional=true)
        private Comment parentComment;
        
        @ManyToOne(optional=true)
        private Post post;

        public Comment(){
            this.timeStamp = new Date();
        }

        public Date getTimeStamp(){
                return timeStamp;
        }
        public void setPoster(User u){
                poster = u;
        }

        public User getPoster(){
                return poster;
        }

        public int getId(){
                return id;
        }

        public String getContent() {
                return content;
        }

        public void setContent(String content) {
                this.content = content;
        }

        public List<Comment> getReplies() {
                return replies;
        }

        public void setReplies(List<Comment> replies) {
                this.replies = replies;
        }
        
        public Comment getParentComment() {
			return parentComment;
		}
        
        public void setParentComment(Comment parentComment) {
			this.parentComment = parentComment;
		}
        
        public int getRating() {
			return rating;
		}
        
		public Post getPost() {
			return post;
		}

		public void setPost(Post post) {
			this.post = post;
		}
		
		public List<Vote> getVotes(){
			return votes;
		}
		
		public void addVote(Vote vote) {
			Iterator<Vote> voteIter = votes.iterator();
			while(voteIter.hasNext()) {
				Vote currentVote = voteIter.next();
				if(vote.getVoter().getUserId() == currentVote.getVoter().getUserId()) {
					voteIter.remove();
					break;
				}
			}
			votes.add(vote);
			validateRating();
		}
		
		private void validateRating() {
			rating = 0;
			for (Vote vote : votes) {
				if(vote.isUp()) {
					rating++;
				}
				else {
					rating--;
				}
			}
			
		}
}