package managers;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import entities.Post;

@Stateless
@LocalBean
public class PostManager{
	@PersistenceContext
	EntityManager em;

	public List<Post> getPosts(){
		TypedQuery<Post> getPosts = em.createQuery("SELECT p FROM Post p", Post.class);
		return getPosts.getResultList();
	}

	public Post getPost(int id){ 
		return em.find(Post.class, id);
	}

	public Post create(Post p){
		em.persist(p);
		return p;
	}

	public Post update(Post p){
		em.merge(p);
		return p;
	}

	public void delete(int id){
		Post Post = getPost(id);
		em.remove(Post);
	}
}
