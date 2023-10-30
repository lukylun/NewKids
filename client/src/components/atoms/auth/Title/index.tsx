import React from 'react';
import { TitleWrapper } from './style';

function Title() {
	return (
		<TitleWrapper>
			<b className="main-color-text">어린이 맞춤형 기사 추천</b>과 <br />
			<b className="sub-color-text">유익한 어휘 교육 서비스</b>를 제공합니다.
		</TitleWrapper>
	);
}

export default Title;
