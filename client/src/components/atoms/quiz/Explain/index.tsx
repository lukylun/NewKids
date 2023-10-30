import React from 'react';
import { ExplainWrapper } from './style';

function Explain() {
	return (
		<ExplainWrapper>
			일주일 간 가장 많은 관심을 받은 <b className="main-explain">키워드 단어</b>들을 알아보아요.
			<br />
			퀴즈 후에는 키워드 단어들을 <b className="main-explain">단어장</b>에도 넣을 수 있답니다.
		</ExplainWrapper>
	);
}

export default Explain;
