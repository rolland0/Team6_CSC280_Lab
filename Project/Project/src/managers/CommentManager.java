package managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Comment;

@Stateless
@LocalBean
public class CommentManager {
	@PersistenceContext
	EntityManager em;
	
	public List<Comment> getComments(){
		TypedQuery<Comment> getComments = em.createQuery("SELECT c FROM Comment c", Comment.class);
		return getComments.getResultList();
	}
	
	public Comment getComment(int commentID){
		return em.find(Comment.class,commentID);
	}
	
	public Comment createComment(Comment c){
		em.persist(c);
		return c;
	}
	
	public Comment updateComment(Comment c){
		em.merge(c);
		return c;
	}
	
	public void deleteComment(int commentID){
		Comment c = getComment(commentID);
		//doesn't work yet
//		if(c.getReplies() != null){
//			ArrayList<Comment> commentList = (ArrayList<Comment>) c.getReplies();
//			for(Comment reply: commentList){
//				deleteComment(c.getId());
//			}
//		}
//		else
			em.remove(c);
	}
}
