package acme.features.inventor.patronage;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorPatronageListMineService implements AbstractListService<Inventor, Patronage> {
	// Internal state ---------------------------------------------------------

		@Autowired
		protected InventorPatronageRepository repository;

		@Override
		public boolean authorise(final Request<Patronage> request) {
			assert request != null;

			boolean result;

			result = request.getPrincipal().hasRole(Inventor.class);

			return result;
		}

		@Override
		public Collection<Patronage> findMany(final Request<Patronage> request) {
			assert request != null;

			Collection<Patronage> result;

			result = this.repository.findPatronagesByInventorId(request.getPrincipal().getAccountId());

			return result;
		}

		@Override
		public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "status", "code", "legalStuff", "budget", "creationMoment", "startDate", "finishDate","link");	
		}

	
	
}
