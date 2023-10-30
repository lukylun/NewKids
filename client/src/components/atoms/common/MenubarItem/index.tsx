import React, { useState, useEffect, ReactNode } from 'react';
import useMovePage from 'hooks/useMovePage';
import { useLocation } from 'react-router-dom';
import { MenubarItemContainer } from './style';

interface IMenubarItemProps {
	menu: { key: number; name: string; path: string; icon: ReactNode };
}
function MenubarItem(props: IMenubarItemProps) {
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
		<MenubarItemContainer onClick={() => movePage(menu.path)}>
			<div className={`icon ${isActive ? 'active' : ''}`}>{menu.icon}</div>
			<div className={`name ${isActive ? 'active' : ''}`}>{menu.name}</div>
		</MenubarItemContainer>
	);
}

export default MenubarItem;
