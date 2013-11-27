package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Comment implements Serializable {

        private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue
        private int id;

        @ManyToOne(optional = false)
        private User originalPoster;
        
        @Column(nullable=false, length=250)
        private String content;
        
        @Column(nullable=false)
        private Date timeStamp;
        
        @OneToMany(mappedBy = "comment")
        private ArrayList<Comment> replies;
        

        public Comment(){
                this.timeStamp = new Date();
        }

        
        public Date getTimeStamp(){
                return timeStamp;
        }
        public void setOriginalPoster(User u){
                originalPoster = u;
        }

        public User getOrignalPoster(){
                return originalPoster;
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

        public ArrayList<Comment> getReplies() {
                return replies;
        }

        public void setReplies(ArrayList<Comment> replies) {
                this.replies = replies;
        }


}