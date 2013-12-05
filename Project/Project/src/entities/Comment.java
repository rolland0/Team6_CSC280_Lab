package entities;

import java.io.Serializable;
import java.util.Date;
//import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
        
        @Temporal(TemporalType.DATE)
        @Column(updatable=false, nullable=false)
        private Date timeStamp;
        
        @Column(updatable=true, nullable=false)
        private int rating;
        
        //@OneToMany(mappedBy = "parentComment")
        //private List<Comment> replies;
        
       // @ManyToOne(optional=true)
       // @JoinColumn(name="parentID")
       // private Comment parentComment;
        
        @ManyToOne(optional=true)
        private Post post;

        public Comment(){
            this.timeStamp = new Date();
            this.rating = 0;
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

       /* public List<Comment> getReplies() {
                return replies;
        }

        public void setReplies(List<Comment> replies) {
                this.replies = replies;
        }
        */

		public Post getPost() {
			return post;
		}

		public void setPost(Post post) {
			this.post = post;
		}

		public void incrementRating(){
			rating ++;
		}
		
		public void decrementRating(){
			rating --;
		}
}