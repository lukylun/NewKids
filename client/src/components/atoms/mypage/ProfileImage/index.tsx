import React from 'react';
import userImage from 'assets/imgs/profile.png';
import { ProfileImageWrapper } from './style';

function ProfileImage() {
	return (
		<ProfileImageWrapper>
			<div className="profile-image">
				<img src={userImage} alt="profile" />
			</div>
		</ProfileImageWrapper>
	);
}

export default ProfileImage;
