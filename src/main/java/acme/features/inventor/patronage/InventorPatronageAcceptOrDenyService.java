package acme.features.inventor.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;
import acme.utils.ChangeCurrencyLibrary;

@Service
public class InventorPatronageAcceptOrDenyService implements AbstractUpdateService<Inventor, Patronage> {
	// Internal state ---------------------------------------------------------

		@Autowired
		protected InventorPatronageRepository repository;
		
		@Autowired
		protected ChangeCurrencyLibrary changeLibrary;

		@Override
		public boolean authorise(final Request<Patronage> request) {
			assert request != null;

			boolean result;

			result = request.getPrincipal().hasRole(Inventor.class);

			return result;
		}


		@Override
		public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "status", "code", "legalStuff", "budget", "creationMoment", "startDate", "finishDate", "link", "patron");
		
		}



		@Override
		public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			request.bind(entity, errors, "status", "code", "legalStuff", "budget", "creationMoment", "startDate", "finishDate", "link", "patron");
			
		}


		@Override
		public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			/*final List<String> acceptedCurrencies = AcceptedCurrencyLibrary.getAcceptedCurrencies(this.repository.findAcceptedCurrencies());


			if(!errors.hasErrors("budget")) {
				boolean acceptedCurrency;

				acceptedCurrency = acceptedCurrencies.contains(entity.getBudget().getCurrency());

				errors.state(request, acceptedCurrency, "budget", "inventor.patronage.form.error.acceptedCurrency");

			}
*/

			
		}


		@Override
		public void update(final Request<Patronage> request, final Patronage entity) {
			assert request != null;
			assert entity != null;

			entity.setPatron(this.repository.getPatronByPatronageId(request.getModel().getInteger("id")));
			this.repository.save(entity);
			
		}

		@Override
		public Patronage findOne(final Request<Patronage> request) {
			assert request != null;

			Patronage result;
			int id;

			id = request.getModel().getInteger("id");
			result = this.repository.getPatronageById(id);
			
			final String defaultCurrency = this.repository.findDefaultCurrency();
			
			if(!(result.getBudget().getCurrency().equals(defaultCurrency))){
				result.setBudget(this.changeLibrary.computeMoneyExchange(result.getBudget(), defaultCurrency).getTarget());
			}

			return result;
		}
	
}
