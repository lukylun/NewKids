import React, { ReactNode } from 'react';
import { ContentLayout, FullContentLayout } from 'layouts/common/ContentLayout';
import { ArticleFindPageLayoutContainer } from './style';

interface IArticleFindPageLayoutProps {
	SearchOptions: ReactNode;
	ResultArticleList: ReactNode;
	Pagination: ReactNode;
	Footer: ReactNode;
}

function ArticleFindPageLayout(props: IArticleFindPageLayoutProps) {
	const { SearchOptions, ResultArticleList, Pagination, Footer } = props;

	return (
		<ArticleFindPageLayoutContainer>
			<ContentLayout>
				<div className="search">
					<div className="search-options">{SearchOptions}</div>
					<div className="result-article-list">{ResultArticleList}</div>
					<div className="pagination">{Pagination}</div>
				</div>
			</ContentLayout>
			<FullContentLayout>
				<div className="footer">{Footer}</div>
			</FullContentLayout>
		</ArticleFindPageLayoutContainer>
	);
}

export default ArticleFindPageLayout;
