package managers;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.User;

@Stateless
@LocalBean
public class UserManager{
	@PersistenceContext
	EntityManager em;
	
	public List<User> getUsers(){
		TypedQuery<User> getUsers = em.createQuery("SELECT u FROM User u", User.class);
		return getUsers.getResultList();
	}
	
	public User getUser(int id){ 
		return em.find(User.class, id);
	}
	
	public User create(User u){
		em.persist(u);
		return u;
	}
	
	public User update(User u){
		em.merge(u);
		return u;
	}
	
	public void delete(int id){
		User user = getUser(id);
		em.remove(user);
	}
	
	public User getUserByName(String name){
		TypedQuery<User> user = em.createNamedQuery("User.findName", User.class);
		user.setParameter("name", name);
		List<User> result = user.getResultList();
		int size = result.size();
		if (size > 1) throw new NonUniqueResultException();
		return size == 1 ? result.get(0) : null;
	}
}
