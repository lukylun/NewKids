import React, { ReactNode } from 'react';
import { ContentLayoutContainer, FullContentLayoutContainer, NarrowContentLayoutContainer } from './style';

interface IContentLayoutProps {
	children: ReactNode;
}

export function NarrowContentLayout({ children }: IContentLayoutProps) {
	return <NarrowContentLayoutContainer>{children}</NarrowContentLayoutContainer>;
}

export function ContentLayout({ children }: IContentLayoutProps) {
	return <ContentLayoutContainer>{children}</ContentLayoutContainer>;
}

export function FullContentLayout({ children }: IContentLayoutProps) {
	return <FullContentLayoutContainer>{children}</FullContentLayoutContainer>;
}
