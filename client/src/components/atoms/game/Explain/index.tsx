import React from 'react';
import { ExplainWrapper } from './style';

function Explain() {
	return (
		<ExplainWrapper>
			단어를 들으며 <b className="main-explain">어떤 단어</b>인지 맞춰보아요.
			<br />
			뜻과 함께 공부하면서 <b className="main-explain">단어의 쓰임새</b>도 함께 알아보아요.
		</ExplainWrapper>
	);
}

export default Explain;
