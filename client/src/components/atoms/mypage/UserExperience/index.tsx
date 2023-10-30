import React, { useEffect, useState } from 'react';
import { useRecoilState } from 'recoil';
import { MemberInfoState } from 'store/auth';
import { USerExperienceWrapper } from './style';

function USerExperience() {
	const [memberInfo] = useRecoilState(MemberInfoState);
	const [exp, setExp] = useState(0);

	useEffect(() => {
		if (memberInfo) {
			setExp(memberInfo.exp);
		}
	}, []);
	return (
		<USerExperienceWrapper $bar={exp}>
			<div className="user-experience-wrapper">
				<div className="user-experience-content">Exp {exp} / 100</div>
				<div className="user-experience-bar">
					<div className="experience-fill" />
				</div>
			</div>
		</USerExperienceWrapper>
	);
}

export default USerExperience;
