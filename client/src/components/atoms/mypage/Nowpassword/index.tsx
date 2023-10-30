import React, { useState } from 'react';
import Input from 'components/atoms/common/Input';
import { ReactComponent as PwIcon } from 'assets/icons/password.svg';
import { NowPasswordWrapper } from './style';

function NowPassword() {
	const [password, setPassword] = useState('');

	return (
		<NowPasswordWrapper>
			<Input type="password" value={password} setValue={setPassword} Icon={<PwIcon />} placeholder="현재 비밀번호" />
		</NowPasswordWrapper>
	);
}

export default NowPassword;
