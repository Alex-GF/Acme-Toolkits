package acme.features.authenticated.announcement;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.announcement.Announcement;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedAnnouncementShowService implements AbstractShowService<Authenticated, Announcement>{

	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedAnnouncementRepository authenticatedAnnouncementRepository;

	// AbstractShowService<Authenticated, Announcement> interface ---------------------------
		
	@Override
	public boolean authorise(final Request<Announcement> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Announcement findOne(final Request<Announcement> request) {
		assert request != null;
		Announcement result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.authenticatedAnnouncementRepository.getItemById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "creationMoment", "title", "body", "criticalFlag", "link");
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", true);
	}

}
