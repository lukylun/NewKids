import React from 'react';
import { HowToUseExplainOneWrapper } from './style';

function HowToUseExplainOne() {
	return (
		<HowToUseExplainOneWrapper>
			1. 화면에 뜻이 나타나면 <b className="main-explain">확성기 버튼</b>을 클릭해 발음을 들어요.
			<br />
			(꼭, <b className="main-explain">1번 이상</b>은 들어주세요. 뜻과 발음을 생각해 단어를 공부해요.)
		</HowToUseExplainOneWrapper>
	);
}

export default HowToUseExplainOne;
