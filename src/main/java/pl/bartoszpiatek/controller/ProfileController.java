package pl.bartoszpiatek.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.bartoszpiatek.model.entity.Profile;
import pl.bartoszpiatek.model.entity.SiteUser;
import pl.bartoszpiatek.service.ProfileService;
import pl.bartoszpiatek.service.SiteUserService;

@Controller
public class ProfileController {

	@Autowired
	private SiteUserService userService;

	@Autowired
	private ProfileService profileService;

	private SiteUser getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();

		return userService.getByEmail(email);
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	ModelAndView profileGET(ModelAndView modelAndView) {

		SiteUser user = getUser();
		Profile profile = profileService.getUserProfile(user);

		if (profile == null) {
			profile = new Profile();
			profile.setUser(user);
			profileService.save(profile);
		}

		Profile safeProfile = new Profile();
		safeProfile.safeProfileInformation(profile);

		modelAndView.getModel().put("userEmail", user.getEmail());
		modelAndView.getModel().put("profile", safeProfile);
		modelAndView.setViewName("app.profile");
		return modelAndView;
	}

	@RequestMapping(value = "/editprofile", method = RequestMethod.GET)
	ModelAndView editProfileGET(ModelAndView modelAndView) {

		SiteUser user = getUser();
		Profile profile = profileService.getUserProfile(user);

		Profile safeProfile = new Profile();
		safeProfile.safeProfileInformation(profile);
		
		modelAndView.getModel().put("userEmail", user.getEmail());
		modelAndView.getModel().put("profile", safeProfile);
		modelAndView.setViewName("app.editProfile");
		return modelAndView;
	}

	@RequestMapping(value = "/editprofile", method = RequestMethod.POST)
	ModelAndView editProfilePOST(ModelAndView modelAndView, @Valid Profile profile, BindingResult result) {
		modelAndView.setViewName("app.editProfile");

		SiteUser user = getUser();
		Profile dataProfile = profileService.getUserProfile(user);
		
		if (!result.hasErrors()) {
			dataProfile.saveEditedFields(profile);
			profileService.save(dataProfile);
			modelAndView.setViewName("redirect:/profile");
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/editabout", method = RequestMethod.POST)
	ModelAndView editAboutPOST(ModelAndView modelAndView, @Valid Profile profile, BindingResult result) {
		modelAndView.setViewName("app.editAbout");

		SiteUser user = getUser();
		Profile dataProfile = profileService.getUserProfile(user);
		
		if (!result.hasErrors()) {
			dataProfile.saveEditedFields(profile);
			profileService.save(dataProfile);
			modelAndView.setViewName("redirect:/profile");
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/editabout", method = RequestMethod.GET)
	ModelAndView editAboutGET(ModelAndView modelAndView) {

		SiteUser user = getUser();
		Profile profile = profileService.getUserProfile(user);

		Profile safeProfile = new Profile();
		safeProfile.safeProfileInformation(profile);
		
		modelAndView.getModel().put("profile", safeProfile);
		modelAndView.setViewName("app.editAbout");
		return modelAndView;
	}
	
}
