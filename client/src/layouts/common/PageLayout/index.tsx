import React, { ReactNode } from 'react';
import { PageLayoutContainer } from './style';

function PageLayout({ children, reducePadding }: { children: ReactNode; reducePadding?: boolean }) {
	return <PageLayoutContainer $reducePadding={reducePadding}>{children}</PageLayoutContainer>;
}

export default PageLayout;
