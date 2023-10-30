import React, { ReactNode } from 'react';
import { ContentLayout } from 'layouts/common/ContentLayout';
import { MyCollectionLayoutContainer } from './style';

interface IMyCollectionLayoutProps {
	MyPageMenu: ReactNode;
	MyCollectionArticle: ReactNode;
	Pagination: ReactNode;
}

function MyCollectionLayout({ MyPageMenu, MyCollectionArticle, Pagination }: IMyCollectionLayoutProps) {
	return (
		<MyCollectionLayoutContainer>
			<div className="my-page-menu">
				<ContentLayout>{MyPageMenu}</ContentLayout>
			</div>
			<div className="my-collection-article">
				<ContentLayout>{MyCollectionArticle}</ContentLayout>
			</div>
			<div className="pagination">
				<ContentLayout>{Pagination}</ContentLayout>
			</div>
		</MyCollectionLayoutContainer>
	);
}

export default MyCollectionLayout;
