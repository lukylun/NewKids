import React, { useEffect, useState } from 'react';
import { ReactComponent as PwIcon } from 'assets/icons/password.svg';
import { ReactComponent as WithdrawalIcon } from 'assets/icons/withdrawal.svg';
import { useNavigate } from 'react-router-dom';
import Input from 'components/atoms/common/Input';
import Button from 'components/atoms/common/Button';
import { withdrawalApi } from 'utils/apis/auth';
import { UserWithdrawalWrapper } from './style';

interface IUserWithdrawalProps {
	onClose: () => void;
}

function UserWithdrawal({ onClose }: IUserWithdrawalProps) {
	const [isDone, setIsDone] = useState(false);
	const [currentPwd, setCurrentPwd] = useState('');
	const navigate = useNavigate();

	const withdrawal = async () => {
		const confirmation = window.confirm('정말로 회원탈퇴하시겠습니까?');
		try {
			const memberkey = localStorage.getItem('memberkey');
			const data = {
				password: currentPwd,
			};
			if (memberkey) {
				if (confirmation) {
					const response = await withdrawalApi(memberkey, data);
					if (response.status === 200) {
						alert('회원탈퇴완료');
						navigate('/auth/login');
					} else {
						alert('비밀번호가 틀렸습니다.');
					}
				}
			}
		} catch (e) {
			console.log(e);
		}
	};

	useEffect(() => {
		if (currentPwd) {
			setIsDone(true);
		} else {
			setIsDone(false);
		}
	});

	return (
		<UserWithdrawalWrapper>
			<div className="user-withdrawal-form">
				<div className="user-withdrawal-title">
					<WithdrawalIcon />
					<h1>회원탈퇴를 하시겠습니까?</h1>
				</div>
				<div className="user-withdrawal-password">
					<h2>※ 현재 비밀번호를 입력해주세요.</h2>
				</div>
				<div className="user-withdrawal-container">
					<div className="user-withdrawal-input">
						<Input
							type="password"
							value={currentPwd}
							setValue={setCurrentPwd}
							Icon={<PwIcon />}
							placeholder="현재 비밀번호"
						/>
					</div>
				</div>
				<div className="user-withdrawal-button">
					<div className="button-container">
						<Button text="확인" size="full" radius="m" color={isDone ? 'Primary' : 'Normal'} handleClick={withdrawal} />
						<Button text="취소" size="full" radius="m" color="Normal" handleClick={onClose} />
					</div>
				</div>
			</div>
		</UserWithdrawalWrapper>
	);
}

export default UserWithdrawal;
