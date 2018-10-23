package com.niit.collaborationdao;

import com.niit.collaborationbackend.ProfilePicture;

public interface ProfilePictureDao {
	void uploadProfilePicture(ProfilePicture profilePicture);
	ProfilePicture  getProfilePicture(String email);
}
