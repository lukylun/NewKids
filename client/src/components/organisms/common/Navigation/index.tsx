import React, { useState, useEffect } from 'react';
import NavBar, { AuthNavBar } from 'components/organisms/common/Navbar';
import Menubar from 'components/organisms/common/Menubar';
import NavigationLayout, { AuthNavigationLayout } from 'layouts/common/NavigationLayout';
import { useLocation } from 'react-router-dom';

function Navigation() {
	const [isAuthPage, setIsAuthPage] = useState(false);
	const location = useLocation();

	useEffect(() => {
		if (/\/auth/.test(location.pathname)) {
			setIsAuthPage(true);
		} else {
			setIsAuthPage(false);
		}
	}, [location]);

	if (isAuthPage) return <AuthNavigationLayout Navibar={<AuthNavBar />} />;
	return <NavigationLayout Navibar={<NavBar />} Menubar={<Menubar />} />;
}

export default Navigation;
