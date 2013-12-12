package managers;

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
	
	
	public List<Comment> getRootComments() {
		TypedQuery<Comment> getRootComments = em.createQuery("SELECT c FROM Comment WHERE c.parentComment IS NOT NULL", Comment.class);
		return getRootComments.getResultList();
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
		em.remove(c);
	}
}
