import React, { useState } from 'react';
import MypageName from 'components/atoms/mypage/MypageName';
import MypageNickname from 'components/atoms/mypage/MypageNickname';
import MypageEmail from 'components/atoms/mypage/MypageEmail';
import MypageAge from 'components/atoms/mypage/MypageAge';
import Button from 'components/atoms/common/Button';
import ModalComponent from 'components/organisms/common/ModalComponent';
import UserWithdrawal from 'components/organisms/mypage/UserWithdrawal';
import UserPasswordChange from 'components/organisms/mypage/UserPasswordChange';
import { UserDetailContainer } from './style';

function UserDetail() {
	const [newPassword, setNewPassword] = useState(false);
	const [userWithdrawal, setUserWithdrawal] = useState(false);
	const changePw = () => {
		setNewPassword(true);
	};

	const handleUserWithdrawal = () => {
		setUserWithdrawal(true);
	};

	const closeModal = () => {
		setNewPassword(false);
		setUserWithdrawal(false);
	};

	return (
		<UserDetailContainer>
			<div className="mypage-title">
				<p>내 정보</p>
				<div className="mypage-button">
					<Button text="비밀번호 변경" radius="s" color="SubFirst" size="s" handleClick={changePw} />
					{newPassword && (
						<ModalComponent>
							<UserPasswordChange onClose={closeModal} />
						</ModalComponent>
					)}
					<Button text="회원탈퇴" radius="s" color="Danger" size="s" handleClick={handleUserWithdrawal} />
					{userWithdrawal && (
						<ModalComponent>
							<UserWithdrawal onClose={closeModal} />
						</ModalComponent>
					)}
				</div>
			</div>
			<hr />
			<div className="mypage-form">
				<MypageName />
				<MypageEmail />
				<MypageNickname />
				<MypageAge />
			</div>
		</UserDetailContainer>
	);
}

export default UserDetail;
