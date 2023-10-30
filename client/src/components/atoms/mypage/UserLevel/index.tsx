import React, { useEffect, useState } from 'react';
import { useRecoilState } from 'recoil';
import { MemberInfoState } from 'store/auth';
import { UserLevelWrapper } from './style';

function UserLevel() {
	const [memberInfo] = useRecoilState(MemberInfoState);
	const [level, setLevel] = useState(0);

	useEffect(() => {
		if (memberInfo) {
			setLevel(memberInfo.level);
		}
	}, []);
	return (
		<UserLevelWrapper>
			<div className="user-level-wrapper">
				<p className="level-title">Lv.</p>
				<p className="level-number">{level}</p>
			</div>
		</UserLevelWrapper>
	);
}

export default UserLevel;
