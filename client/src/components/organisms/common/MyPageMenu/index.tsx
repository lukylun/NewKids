import React from 'react';
import { MYPAGE_MENUS } from 'constants/mypage';
import MypageMenuItem from 'components/atoms/common/MypageMenuItem';
import { MyPageMenuContainer } from './style';

function MyPageMenu() {
	return (
		<MyPageMenuContainer>
			<div className="mypage-container">
				{MYPAGE_MENUS.map((e) => (
					<MypageMenuItem key={e.key} menu={e} />
				))}
			</div>
		</MyPageMenuContainer>
	);
}

export default MyPageMenu;
