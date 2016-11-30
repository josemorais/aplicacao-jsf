package br.com.k19.sessionbeans;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import bugWebbr.com.k19.entities.Bug;
import bugWebbr.com.k19.entities.Project;

@Stateless
@RolesAllowed({ "ADMIN", "USERS" })
public class ProjectRepository {

	@PersistenceContext
	private EntityManager manager;

	public void add(Project project) {
		this.manager.persist(project);
	}

	public void edit(Project project) {
		this.manager.merge(project);
	}

	@RolesAllowed({ "ADMIN" })
	public void removeById(Long id) {
		Project project = this.manager.find(Project.class, id);

		TypedQuery<Bug> query = this.manager.createQuery("SELECT b FROM Bug b WHERE b.project= :project", Bug.class);
		query.setParameter("project", project);
		List<Bug> bugs = query.getResultList();
		for (Bug bug : bugs) {
			this.manager.remove(bug);
		}
		this.manager.remove(project);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Project> findAll() {
		TypedQuery<Project> query = this.manager.createQuery("SELECT p FROM Project p", Project.class);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Project findById(Long id) {
		return this.manager.find(Project.class, id);
	}

}
