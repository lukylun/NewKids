import React from 'react';
import teamLogo from 'assets/imgs/team-logo.png';
import { LoginFooterContainer } from './style';

function LoginFooter() {
	return (
		<LoginFooterContainer>
			<div className="links">
				<button type="button">이용약관 </button>
				<button type="button">개인정보처리방침</button>
				<button type="button">개발팀 소개</button>
				<button type="button">Gitlab</button>
			</div>
			<div className="team">삼성 청년 SW 아카데미, 9기 C205</div>
			<div className="copyright">
				<img src={teamLogo} alt="team-logo" />
				<span>ⓒ 개밥쉰내 All rights reserved</span>
			</div>
		</LoginFooterContainer>
	);
}

export default LoginFooter;
