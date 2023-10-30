import React, { useEffect, useState } from 'react';
import Input from 'components/atoms/common/Input';
import { ReactComponent as EmailIcon } from 'assets/icons/email.svg';
import { useRecoilState } from 'recoil';
import { MemberInfoState } from 'store/auth';
import { MypageEmailWrapper } from './style';

function MypageEmail() {
	const [memberInfo] = useRecoilState(MemberInfoState);
	const [email, setEmail] = useState('');

	useEffect(() => {
		if (memberInfo) {
			setEmail(memberInfo.email);
		}
	}, []);
	return (
		<MypageEmailWrapper>
			<h3>이메일</h3>
			<Input type="text" value={email} setValue={setEmail} Icon={<EmailIcon />} placeholder="" disabled />
		</MypageEmailWrapper>
	);
}

export default MypageEmail;
