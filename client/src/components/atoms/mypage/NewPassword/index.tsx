import React, { useState } from 'react';
import Input from 'components/atoms/common/Input';
import { ReactComponent as PwIcon } from 'assets/icons/password.svg';
import { NewPasswordWrapper } from './style';

function NewPassword() {
	const [password, setPassword] = useState('');
	const [passwordCheck, setPasswordCheck] = useState('');

	return (
		<NewPasswordWrapper>
			<Input type="password" value={password} setValue={setPassword} Icon={<PwIcon />} placeholder="새 비밀번호" />
			<div className="new-password-input">
				<Input
					type="password"
					value={passwordCheck}
					setValue={setPasswordCheck}
					Icon={<PwIcon />}
					placeholder="새 비밀번호 확인"
				/>
			</div>
		</NewPasswordWrapper>
	);
}

export default NewPassword;
