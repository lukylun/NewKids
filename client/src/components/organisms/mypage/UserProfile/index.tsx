import React from 'react';
import ProfileName from 'components/atoms/mypage/ProfileName';
import ProfileImage from 'components/atoms/mypage/ProfileImage';
import { UserProfileContainer } from './style';

function UserProfile() {
	return (
		<UserProfileContainer>
			<div className="anything-name">
				<ProfileImage />
				<ProfileName />
			</div>
		</UserProfileContainer>
	);
}

export default UserProfile;
