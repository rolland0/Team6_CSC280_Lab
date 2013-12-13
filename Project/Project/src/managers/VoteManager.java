package managers;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Vote;

@Stateless
@LocalBean
public class VoteManager {
	@PersistenceContext
	EntityManager em;
	
	public List<Vote> getVotes() {
		TypedQuery<Vote> getVotes = em.createQuery("SELECT v FROM Vote v", Vote.class);
		return getVotes.getResultList();
	}
	
	public Vote getVote(int voteID) {
		return em.find(Vote.class, voteID);
	}
	
	public Vote createVote(Vote v) {
		em.persist(v);
		return v;
	}
	
	public Vote updateVote(Vote v) {
		em.merge(v);
		return v;
	}
	
	public void deleteVote(int voteID) {
		Vote v = getVote(voteID);
		em.remove(v);
	}
}
