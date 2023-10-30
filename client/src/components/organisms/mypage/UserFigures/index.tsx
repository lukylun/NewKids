import React from 'react';
import USerExperience from 'components/atoms/mypage/UserExperience';
import UserLevel from 'components/atoms/mypage/UserLevel';
import profileImage from 'assets/imgs/profile-level.png';
import { UserFiguresContainer } from './style';

function UserFigures() {
	return (
		<UserFiguresContainer>
			<div className="user-figure-container">
				<div className="figure-image">
					<img className="figure-image-content" src={profileImage} alt="profile" />
				</div>
				<div className="user-experience-container">
					<USerExperience />
				</div>
				<div className="user-level-container">
					<UserLevel />
				</div>
			</div>
		</UserFiguresContainer>
	);
}

export default UserFigures;
