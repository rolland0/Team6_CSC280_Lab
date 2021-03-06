package entities;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

import us.walkenhorst.crypto.PBEDigest;
import us.walkenhorst.crypto.PasswordDigest;


@Entity
@NamedQueries({

	@NamedQuery(name = "User.findName",
			query = "SELECT u FROM User u where u.username=:username")})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final int USERNAME_MIN_LENGTH = 5;
	public static final int USERNAME_MAX_LENGTH = 20;
	public static final int PASSWORD_MIN_LENGTH = 5;
	public static final int PASSWORD_MAX_LENGTH = 50;
	public static final int EMAIL_MIN_LENGTH = 5;
	public static final int EMAIL_MAX_LENGTH = 100;
	private static final String GENERATOR = "USERS_GEN";

	@Id
	@TableGenerator(name = GENERATOR,
	allocationSize = 10,
	initialValue = 10)
	@GeneratedValue(strategy = GenerationType.TABLE,
	generator = GENERATOR)
	@Column(name = "user_id",
	updatable = false,
	unique = true,
	nullable = false)
	private int userId;
	
	@Column(nullable = false,
			length = EMAIL_MAX_LENGTH)
	private String email;

	@Column(nullable = false,
			length = PASSWORD_MAX_LENGTH)
	private String password;

	@Column(nullable = false,
			length = USERNAME_MAX_LENGTH,
			unique = true)
	private String username;

	@Column(nullable = false,
			length = 255)
	private String salt;

	@OneToMany(mappedBy = "poster")
	private List<Post> posts = new ArrayList<Post>();

	@OneToMany(mappedBy="poster", cascade=CascadeType.REMOVE)
	private List<Comment> comments;
	
	@ElementCollection
	@Enumerated(EnumType.STRING)
	@Column(name = "groups",
	nullable = false,
	length = 50)
	@CollectionTable(name = "users_groups",
	joinColumns = @JoinColumn(nullable = false,
	name = "user_id",
	referencedColumnName = "user_id"),
	foreignKey = @ForeignKey(name = "FK_user_id",
	foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES USER (user_id)"))
	private Set<UserGroups> groups;

	public User(){
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNewPassword(String clearPassword) throws NoSuchAlgorithmException{
		PasswordDigest digest;
		digest = new PBEDigest(clearPassword.toCharArray(), 1000);
		this.setPassword(digest.getSaltedDigest());
		this.setSalt(digest.getSalt());
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Post> getPosts() {
		if(this.posts== null)this.posts = new ArrayList<Post>();
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Comment> getComments() {
		if(this.comments==null)this.comments = new ArrayList<Comment>();
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Set<UserGroups> getGroups() {
		if (groups == null) groups = EnumSet.noneOf(UserGroups.class);
		return this.groups;
	}

	public void setGroups(Set<UserGroups> groups) {
		this.groups = groups;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override public String toString()
	{
		return this.username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((groups == null) ? 0 : groups.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((posts == null) ? 0 : posts.hashCode());
		result = prime * result + ((salt == null) ? 0 : salt.hashCode());
		result = prime * result + userId;
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean usersEqual = false;
		
		if(obj instanceof User){
			User other = (User)obj;
			if(other.getUserId()== this.userId){
				if(other.getUsername().equals(this.username)){
					if(other.getEmail().equals(this.password)){
						if(other.getPassword().equals(this.password)){
							usersEqual = true;
						}
					}
				}
			}
		}
		return usersEqual;
	}
	
}