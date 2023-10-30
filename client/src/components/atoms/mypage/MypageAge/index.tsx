import React, { useEffect, useState } from 'react';
import Input from 'components/atoms/common/Input';
import { ReactComponent as AgeIcon } from 'assets/icons/age.svg';
import { useRecoilState } from 'recoil';
import { MemberInfoState } from 'store/auth';
import { MypageAgeWrapper } from './style';

function MypageAge() {
	const [memberInfo] = useRecoilState(MemberInfoState);
	const [age, setAge] = useState(0);

	useEffect(() => {
		if (memberInfo) {
			setAge(memberInfo.age);
		}
	}, []);
	return (
		<MypageAgeWrapper>
			<h3>나이</h3>
			<Input type="text" value={age} setValue={setAge} Icon={<AgeIcon />} placeholder="" disabled />
		</MypageAgeWrapper>
	);
}

export default MypageAge;
