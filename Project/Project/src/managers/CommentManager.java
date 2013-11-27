package managers;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Comment;

@Stateless
@LocalBean
public class CommentManager {
	@PersistenceContext
	EntityManager em;
	
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
