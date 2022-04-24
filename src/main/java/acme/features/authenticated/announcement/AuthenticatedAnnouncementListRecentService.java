package acme.features.authenticated.announcement;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.announcement.Announcement;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractListService;


@Service
public class AuthenticatedAnnouncementListRecentService implements AbstractListService<Authenticated, Announcement>{

	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedAnnouncementRepository authenticatedAnnouncementRepository;
	
	// AbstractListService<Any, Chirp> interface ---------------------------

	@Override
	public boolean authorise(final Request<Announcement> request) {
		assert request != null;
		return request.getPrincipal().isAuthenticated();
	}

	@Override
	public Collection<Announcement> findMany(final Request<Announcement> request) {
		assert request != null;
		
		Collection<Announcement> result;
		Calendar calendar;
		Date deadline;
		
		calendar =  Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		deadline = calendar.getTime();
		result = this.authenticatedAnnouncementRepository.findRecentAnnouncements(deadline);
		return result;
	}

	@Override
	public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "creationMoment", "title","criticalFlag");
		
	}

}
