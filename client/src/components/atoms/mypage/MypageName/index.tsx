import React, { useEffect, useState } from 'react';
import Input from 'components/atoms/common/Input';
import { ReactComponent as IdIcon } from 'assets/icons/id.svg';
import { useRecoilState } from 'recoil';
import { MemberInfoState } from 'store/auth';
import { MypageNameWrapper } from './style';

function MypageName() {
	const [memberInfo] = useRecoilState(MemberInfoState);
	const [name, setName] = useState('');

	useEffect(() => {
		if (memberInfo) {
			setName(memberInfo.name);
		}
	});
	return (
		<MypageNameWrapper>
			<h3>이름</h3>
			<Input type="text" value={name} setValue={setName} Icon={<IdIcon />} placeholder="" disabled />
		</MypageNameWrapper>
	);
}

export default MypageName;
