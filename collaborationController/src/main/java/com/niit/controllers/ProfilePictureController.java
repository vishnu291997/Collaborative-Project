package com.niit.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.collaborationbackend.ErrorClazz;
import com.niit.collaborationbackend.ProfilePicture;
import com.niit.collaborationdao.ProfilePictureDao;

@Controller
public class ProfilePictureController {
	@Autowired
	private ProfilePictureDao profilePictureDao;
	
		@RequestMapping(value="/uploadprofilepicture",method=RequestMethod.POST)
		public ResponseEntity<?> uploadProfilePicture(@RequestParam CommonsMultipartFile image,HttpSession session){
			System.out.println("print the image object");
			String email=(String)session.getAttribute("loggedInUser");
			if(email==null){
				ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
				return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
			ProfilePicture profilePicture=new ProfilePicture();
			profilePicture.setEmail(email);
			profilePicture.setImage(image.getBytes());
			profilePictureDao.uploadProfilePicture(profilePicture);
			return new ResponseEntity<ProfilePicture>(profilePicture,HttpStatus.OK);
		}
	    @RequestMapping(value="/getimage",method=RequestMethod.GET)
		public @ResponseBody byte[] getProfilePicture(@RequestParam String email,HttpSession session){
	    	System.out.println("email is " + email);
			String authEmail=(String)session.getAttribute("loggedInUser");
			if(authEmail==null){
				return null;
			}
			ProfilePicture profilePicture=profilePictureDao.getProfilePicture(email);
			if(profilePicture==null)//No image
				return null;
			else
				return profilePicture.getImage();
		}

	
}
