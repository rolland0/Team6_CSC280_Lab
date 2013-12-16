package managers;

import java.util.ArrayList;
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
	
	public List<User> getActiveUsers(){
		TypedQuery<User> getUsers = em.createQuery("SELECT u FROM User u", User.class);
		List<User> activeUsers = new ArrayList<User>();
		for(User u : getUsers.getResultList()){
			if(u.isActive()){
				activeUsers.add(u);
			}
		}		
		return activeUsers;
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
	
	public User getUserByName(String username){
		TypedQuery<User> user = em.createNamedQuery("User.findName", User.class);
		user.setParameter("username", username);
		List<User> result = user.getResultList();
		int size = result.size();
		if (size > 1) throw new NonUniqueResultException();
		return size == 1 ? result.get(0) : null;
	}
	
	public boolean isUsernameFree(String username) {
		boolean nameFree = true;
		
		List<User> allUsers = getUsers();
		for(User u : allUsers){
			if(u.getUsername().equals(username)){
				nameFree = false;
			}
		}
		return nameFree;
	}

	public boolean isWithinLength(String info, int minLength, int maxLength){
		return (info.length() >= minLength && 
				info.length() <= maxLength);
	}
	
	public boolean isPasswordValid(String password) {
		return !(password == null 
				|| password.isEmpty()
				|| !isWithinLength(password, User.PASSWORD_MIN_LENGTH, User.PASSWORD_MAX_LENGTH));
	}
	
	public boolean isEmailValid(String email) {
		return !(email == null
				|| email.isEmpty()
				|| !isWithinLength(email, User.EMAIL_MIN_LENGTH, User.EMAIL_MAX_LENGTH));
	}
	
	public boolean isUsernameValid(String username) {
		return !(username == null
				|| username.isEmpty()
				|| !isWithinLength(username, User.USERNAME_MIN_LENGTH, User.EMAIL_MAX_LENGTH));
	}
	
}
