package entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final int USERNAME_MIN_LENGTH = 5;
	public static final int USERNAME_MAX_LENGTH = 20;
	public static final int PASSWORD_MIN_LENGTH = 5;
	public static final int PASSWORD_MAX_LENGTH = 50;

	@Id
	@GeneratedValue
	private int id;

	@Column(nullable = false,
			length = 100)
	private String email;

	@Column(nullable = false,
			length = 50)
	private String password;

	@Column(nullable = false,
			length = 20,
			unique = true)
	private String username;

	@OneToMany(mappedBy = "poster")
	private List<Post> posts;

	@OneToMany(mappedBy="poster")
	private List<Comment> comments;

	@ElementCollection
	@Enumerated(EnumType.STRING)
	@Column(name = "groups",
	nullable = false,
	length = 50)
	@CollectionTable( name = "users_groups",
	joinColumns = @JoinColumn(	nullable = false,
	name = "user_id",
	referencedColumnName = "user_id"),
	foreignKey = @ForeignKey(	name = "FK_user_id",
	foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES users (user_id)"))
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}


}