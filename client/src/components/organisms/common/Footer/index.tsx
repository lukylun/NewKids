import React from 'react';
import teamLogo from 'assets/imgs/team-logo.png';
import { FooterConatiner } from './style';

function Footer() {
	return (
		<FooterConatiner>
			<div className="wrapper">
				<div className="content">
					<div className="links">
						<button type="button">이용약관 </button>
						<button type="button">개인정보처리방침</button>
						<button type="button">개발팀 소개</button>
						<button type="button">Gitlab</button>
					</div>
					<div className="team">삼성 청년 SW 아카데미, 9기 C205</div>
					<div className="copyright">
						<span>ⓒ 개밥쉰내 All rights reserved</span>
					</div>
				</div>
				<img src={teamLogo} alt="team-logo" />
			</div>
		</FooterConatiner>
	);
}

export default Footer;
