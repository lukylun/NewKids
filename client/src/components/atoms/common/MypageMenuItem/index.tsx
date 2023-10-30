import React, { ReactNode, useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import useMovePage from 'hooks/useMovePage';
import { MypageMenuItemContainer } from './style';

interface IMypageMenuItemProps {
	menu: { key: number; name: string; path: string; icon: ReactNode };
}

function MypageMenuItem(props: IMypageMenuItemProps) {
	const { menu } = props;
	const [isActive, setIsActive] = useState(false);
	const location = useLocation();
	const [movePage] = useMovePage();

	useEffect(() => {
		if (location.pathname === menu.path) {
			setIsActive(true);
		} else {
			setIsActive(false);
		}
	}, [location, menu]);

	return (
		<MypageMenuItemContainer onClick={() => movePage(menu.path)}>
			<div className={`icon${isActive ? '-active' : ''}`}>{menu.icon}</div>
			<div className={`icon${isActive ? '-active' : ''}`}>{menu.name}</div>
		</MypageMenuItemContainer>
	);
}

export default MypageMenuItem;
